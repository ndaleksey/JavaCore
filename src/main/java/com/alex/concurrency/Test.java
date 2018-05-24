package com.alex.concurrency;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Shishkov A.V. on 21.05.18.
 */
public class Test {
	private static <E> E max(Collection<E> elements, Comparator<E> comparator) {
		return elements.stream().max(comparator).get();
	}

	public static void main(String[] args) throws InterruptedException {
		List<Integer> numbers = Arrays.asList(1, 23, 35, 353, 2, 34, 67);
		List<String> strings = Arrays.asList("wr", "ewwrg", "qwerreg");

		Thread numThread = new Thread(() -> System.out.println(max(numbers, Integer::compareTo)));

		Thread strThread = new Thread(() -> System.out.println(max(strings, (s1, s2) -> (int) (s1.chars().filter(c -> c
				== 'w').count() - s2.chars
				().filter(c -> c == 'w').count()))));
		strThread.start();
		numThread.start();

		strThread.join();

		System.exit(0);
	}
}
