package com.alex.concurrency;

import java.util.Scanner;

/**
 * Created by Shishkov A.V. on 22.05.18.
 */
public class WaitTest {
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(() -> {
			int counter = 0;

			try {
				while (true) {
					counter++;
					Thread.sleep(2000);
					Thread.currentThread().wait();
				}
			} catch (InterruptedException e) {
				System.out.println(counter);
			}
		});
		thread.start();

		Scanner scanner = new Scanner(System.in);
		System.out.println("Type any:");

		while (scanner.hasNext()) {
			String text = scanner.next();
			if (text.equalsIgnoreCase("exit"))
				break;
		}
		thread.notifyAll();
	}
}
