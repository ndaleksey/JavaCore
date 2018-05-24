package com.alex.concurrency.wait_notify;

import java.util.Random;

/**
 * Created by Shishkov A.V. on 22.05.18.
 */
public class Consumer implements Runnable {
	private Drop drop;

	public Consumer(Drop drop) {
		this.drop = drop;
	}

	public void run() {
		Random random = new Random();

		for (String message = drop.take(); !message.equals("DONE"); message = drop.take()) {
			System.out.format("MESSAGE RECEIVED: %s%n", message);
			try {
				Thread.sleep(random.nextInt(5000));
			} catch (InterruptedException e) {
			}
		}
	}
}
