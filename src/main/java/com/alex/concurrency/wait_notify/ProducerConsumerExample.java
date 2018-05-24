package com.alex.concurrency.wait_notify;

/**
 * Created by Shishkov A.V. on 22.05.18.
 */
public class ProducerConsumerExample {
	public static void main(String[] args) {
		Drop drop = new Drop();
		(new Thread(new Producer(drop))).start();
		(new Thread(new Consumer(drop))).start();
	}
}
