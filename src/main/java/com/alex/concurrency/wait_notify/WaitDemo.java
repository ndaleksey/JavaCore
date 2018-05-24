package com.alex.concurrency.wait_notify;

import java.util.Scanner;

/**
 * Created by Shishkov A.V. on 23.05.18.
 */

class Timer {
	private boolean stopped;

	public void start() {
		stopped = false;
	}

	public synchronized void stop() {
		stopped = true;
		System.out.println(Thread.currentThread().getName());
		notifyAll();
	}

	public synchronized void showTime() {
		while (!stopped) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("Timer interrupted");
			}
			System.out.println("Timer woke up");
		}
	}
}

public class WaitDemo {
	public static void main(String[] args) throws InterruptedException {
		Timer timer = new Timer();

		Thread thread = new Thread(() -> {
			timer.start();
			timer.showTime();
		});

		thread.start();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Type EXIT for exit programme or FREE to wake up the Timer");
		while (true) {
			String text = scanner.next();

			if (text.equals("exit")) break;

			if (text.equals("free")) {
				timer.stop();
				break;
			}
		}

		thread.join();
		System.out.println("Finish");
	}
}
