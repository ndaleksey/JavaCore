package com.alex.concurrency.high_level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Shishkov A.V. on 25.05.18.
 */
public class ThreadPoolDemo {

	static Random random = new Random();

	static List<Integer> getRandNumbers(int size) {
		ArrayList<Integer> numbers = new ArrayList<>();

		while (size-- > 0) {
			numbers.add(random.nextInt(100));
		}

		return numbers;
	}

	static List<List<Integer>> listList = Arrays.asList(
			getRandNumbers(10),
			getRandNumbers(10),
			getRandNumbers(10),
			getRandNumbers(10),
			getRandNumbers(10)
	);

	public static void main(String[] args) throws Exception {
		listList.forEach(l -> System.out.println(Arrays.toString(l.stream().sorted().toArray())));

		ExecutorService es = Executors.newFixedThreadPool(4);

		List<Future<Integer>> futures = new ArrayList<>();

		for (List<Integer> list : listList) {
			futures.add(es.submit(() -> list.stream().max(Integer::compareTo).get()));
		}

		List<Integer> results = new ArrayList<>();

		futures.forEach(f -> {
			try {
				results.add(f.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		});

		System.out.println("Result");
		System.out.println(Arrays.toString(results.toArray()));

		es.shutdown();
	}

}
