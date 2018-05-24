package com.alex.concurrency;

import java.util.Scanner;

/**
 * Created by Shishkov A.V. on 21.05.18.
 */
public class Interruption {
	public static void main(String[] args) throws InterruptedException {

		Thread thread = new Thread(() -> {
			int amount = 0;

			try {
				while (true) {
					if (Thread.interrupted())
						throw new InterruptedException("Somebody has stopped this thread");

					System.out.println("Amount: " + amount++);

					Thread.sleep(5000);
				}
			} catch (InterruptedException e) {
				System.out.println("Total Amount is: " + amount);
			}
		});

		thread.start();
		System.out.println("Start counting....");

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Enter q to exit");
			String text = scanner.next();
			if (text.equals("q")) break;
		}

		thread.interrupt();
		thread.join();

		System.out.println("The programme is over");
	}
}
