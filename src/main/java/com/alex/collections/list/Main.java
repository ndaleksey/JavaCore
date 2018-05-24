package com.alex.collections.list;

import javafx.util.Pair;

import java.util.*;

class Car {
	static int year;
	String name;

	byte aByte;
	short aShort;
	int anInt;
	long aLong;

	float aFloat;
	double aDouble;

	String string;
	boolean aBoolean;


	@Override
	public String toString() {
		return "Car {" +
				"name = " + name + "\t" +
				"year = " + year + "\t" +
				'}';
	}
}

public class Main {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>(getRandomList(20));

		List<Pair<Integer, String>> people = new ArrayList<>();
		people.add(null);
		people.add(new Pair<>(1, "Alex"));
		people.add(new Pair<>(2, "Diana"));
		people.add(null);
		people.add(new Pair<>(1, "Rachel"));
		people.add(new Pair<>(4, "Yan"));

		replace(people, null, new Pair<>(10, "Eugen"));

		Collections.reverse(people);

		displayForwardCollection(people);
	}

	private static <E> void replace(List<E> list, E val, E newVal) {
		for (ListIterator<E> it = list.listIterator(); it.hasNext();) {
			if (val == null ? it.next() == null : it.next().equals(val))
				it.set(newVal);
		}
	}

	private static void displayBackwardCollection(Collection<?> numbers) {
		List<?> list = new ArrayList<>(numbers);
		ListIterator<?> iterator = list.listIterator(list.size());
		while (iterator.hasPrevious()) {
			System.out.println(iterator.previous());
		}
	}

	private static void displayForwardCollection(Collection<?> numbers) {
		Iterator<?> iterator = numbers.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + "\t");
		}
	}

	private static List<Integer> getRandomList(int size) {
		List<Integer> result = new ArrayList<>();
		Random random = new Random();

		for (int i = 0; i < size; i++) {
			result.add(random.nextInt(10));
		}

		return result;
	}

	private static int getSum(String text) {

		return 0;
	}
}