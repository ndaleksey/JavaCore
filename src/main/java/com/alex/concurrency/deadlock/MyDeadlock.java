package com.alex.concurrency.deadlock;

/**
 * Created by Shishkov A.V. on 22.05.18.
 */
public class MyDeadlock {
	static class Counter {
		int count;
		public void increment() throws InterruptedException {
			Thread.sleep(10);
			System.out.println(count++);
		}
		public void decrement() throws InterruptedException {
			Thread.sleep(10);
			System.out.println(count--);
		}

		public int getCount() {
			return count;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Counter counter = new Counter();
		Thread t1 = new Thread(() -> {
			try {
				counter.increment();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		Thread t2 = new Thread(() -> {
			try {
				counter.decrement();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		t1.start();
		t2.start();

		Thread.sleep(10);
		System.out.println("Finish");
		System.exit(0);
	}
}
