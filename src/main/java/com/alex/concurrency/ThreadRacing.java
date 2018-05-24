package com.alex.concurrency;

import java.util.Random;

/**
 * Created by Shishkov A.V. on 22.05.18.
 */


public class ThreadRacing {

	static final class Counter {
		private int counter;

		public synchronized void increment() {
			counter++;
		}

		public synchronized void decrement() {
			counter--;
		}

		public int getResult() {
			return counter;
		}
	}

	static class IncThread implements Runnable {

		private Counter counter;

		public IncThread(Counter counter) {
			this.counter = counter;
		}

		@Override
		public void run() {

			while (true) {
				counter.increment();
				System.out.println(counter.getResult());

				try {
					Thread.sleep(new Random().nextInt(3000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static class DecThread implements Runnable {
		private Counter counter;

		public DecThread(Counter counter) {
			this.counter = counter;
		}

		@Override
		public void run() {
			while (true) {
				counter.decrement();
				System.out.println(counter.getResult());

				try {
					Thread.sleep(new Random().nextInt(5000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Counter counter = new Counter();
		/*Thread dec = new Thread(new DecThread(counter));
		Thread inc = new Thread(new IncThread(counter));

		dec.start();
		inc.start();

		dec.join();
		inc.join();*/
		Thread t1 = new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			counter.increment();
			counter.decrement();
			System.out.println("Thread 1 finished");
		});
		Thread t2 = new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			counter.increment();
			counter.decrement();

			System.out.println("Thread 2 finished");
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println(counter.getResult());
	}
}
