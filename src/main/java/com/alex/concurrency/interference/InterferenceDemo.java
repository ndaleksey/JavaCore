package com.alex.concurrency.interference;

/**
 * Created by Shishkov A.V. on 23.05.18.
 */
public class InterferenceDemo {
	public static void main(String[] args) throws InterruptedException {
		Counter counter = new Counter();

		Thread t1 = new Thread(() -> {
			counter.increment();
			System.out.println("Thread 1: " + counter.value());
		});

		Thread t2 = new Thread(() -> {
			counter.decrement();
			System.out.println("Thread 2: " + counter.value());
		});

		t1.start();
		t2.start();

		System.out.println("Main: " + counter.value());

		t1.join();
		t2.join();

		System.out.println("Final value: " + counter.value());
	}
}
