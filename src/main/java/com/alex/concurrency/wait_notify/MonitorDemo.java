package com.alex.concurrency.wait_notify;

/**
 * Created by Shishkov A.V. on 25.05.18.
 */

public class MonitorDemo {
	final static class Resource {
		volatile Object object = null;

		synchronized void push(Object object) throws InterruptedException {
			String threadName = Thread.currentThread().getName();

			if (this.object != null) {
				System.out.println((threadName + " is waiting for notification\n"));
				wait();
			}

			this.object = object;
			System.out.println(threadName + " pushed " + object + "\n");
			notifyAll();
		}

		synchronized Object pull() throws InterruptedException {
			String threadName = Thread.currentThread().getName();

			if (object == null) {
				System.out.println((threadName + " is waiting for notification\n"));
				wait();
			}

			Object temp = object;
			object = null;

			System.out.println(threadName + " pulled " + temp + "\n");
			notifyAll();

			return temp;
		}
	}

	public static void main(String[] args) {
		Resource resource = new Resource();

		Thread pusher = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					resource.push(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread puller = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					resource.pull();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		pusher.setName("Pusher");
		puller.setName("Puller");

		pusher.start();
		puller.start();
	}
}
