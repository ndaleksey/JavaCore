package com.alex.concurrency;

/**
 * Created by Shishkov A.V. on 10.05.18.
 */
public class Account {
	private int balance;

	public Account(int balance) {
		this.balance = balance;
	}

	public void withdraw(int value) {
		balance -= value;
	}

	public void deposit(int value) {
		balance += value;
	}

	public int getBalance() {
		return balance;
	}
}
