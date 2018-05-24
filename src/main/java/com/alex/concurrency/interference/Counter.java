package com.alex.concurrency.interference;

/**
 * Created by Shishkov A.V. on 23.05.18.
 */
public class Counter {
	private volatile int c = 0;

	public synchronized void increment() {
		c++;
		System.out.println(Thread.currentThread().getName() +  ". Counter: " + c);
	}

	public synchronized void decrement() {
		c--;
		System.out.println(Thread.currentThread().getName() +  ". Counter: " + c);
	}

	public synchronized int value() {
		return c;
	}
}
