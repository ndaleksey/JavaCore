package com.alex.collections.queue;

import java.util.*;

/**
 * Created by Shishkov A.V. on 08.05.18.
 */
public class QueueTest {
	public static void main(String[] args) {
		Queue<Integer> numbers = new PriorityQueue<>((o1, o2) -> 0);
		for (int i = 5; i > 0; i--) {
			numbers.offer(i);
		}

		while (!numbers.isEmpty()) {
			System.out.println(numbers.poll());
		}
	}

	private static <T> void display(Collection<T> collection) {
		for (Iterator<T> it = collection.iterator(); it.hasNext(); ) {
			System.out.print(it.next() + "\t");
		}

		System.out.println();
	}
}
