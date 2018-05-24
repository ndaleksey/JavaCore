package com.alex.collections.set;

import java.util.*;

/**
 * Created by Shishkov A.V. on 08.05.18.
 */
public class SetTest {
	public static void main(String[] args) {
		Set<Integer> numbers = new HashSet(Arrays.asList(1, 2, 3, 4, 1));
		System.out.println(numbers);
	}
}
