package com.alex.concurrency;

import javax.naming.InsufficientResourcesException;

/**
 * Created by Shishkov A.V. on 10.05.18.
 */
public class Operations {
	public static void main(String[] args) throws InsufficientResourcesException {
		final Account a = new Account(1000);
		final Account b = new Account(2000);

		Thread thread = new Thread(() -> {
			try {
				transfer(a, b, 500);
			} catch (InsufficientResourcesException e) {
				e.printStackTrace();
			}
		});
		thread.start();

		transfer(b, a, 300);
	}

	private static void transfer(Account account1, Account account2, int value) throws InsufficientResourcesException {
		if (account1.getBalance() < value) throw new InsufficientResourcesException();

		account1.withdraw(value);
		account2.deposit(value);

		System.out.println("Transfer is successful");
	}
}
