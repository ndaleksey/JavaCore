package com.alex.collections.map;

import com.alex.collections.queue.Department;
import com.alex.collections.queue.Employee;

import java.util.*;

/**
 * Created by Shishkov A.V. on 08.05.18.
 */
public class MapTest {
	public static void main(String[] args) {

		/*Department software = new Department(1, "Software");
		Department firmware = new Department(2, "Firmware");

		Employee alex = new Employee(1, "Alex", software);
		Employee eugen = new Employee(2, "Eugen", firmware);
		Employee juli = new Employee(3, "Juli", software);
		Employee nicole = new Employee(4, "Nicole", software);
		Employee nick = new Employee(5, "Nick", firmware);

		List<Employee> employees = new ArrayList<>(Arrays.asList(alex, eugen, juli, nick, nicole));
		Map<Department, List<Employee>> emplsByDeps = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));

		display(emplsByDeps);*/


	}

	private static void populateMap(Map<Integer, String> map) {

	}

	private static void frequencyWordsTest() {
		String[] words = new String[]{"if", "it", "is", "to", "be", "it", "is", "up", "to", "me", "to", "delegate"};
		calculateFrequency(words);
	}

	private static void calculateFrequency(String[] words) {
		Map<String, Integer> times = new LinkedHashMap<>();

		for (String word : words) {
			Integer freq = times.get(word);
			times.put(word, freq == null ? 1 : freq + 1);
		}

		System.out.println(times);
	}

	private static void display(Map<Department, List<Employee>> depAndEmployees) {
		for (Iterator<Department> deps = depAndEmployees.keySet().iterator(); deps.hasNext(); ) {
			Department dep = deps.next();
			System.out.print(dep.getName() + " [ ");

			for (Iterator<Employee> empls = depAndEmployees.get(dep).iterator(); empls.hasNext(); ) {
				System.out.print(empls.next().getName() + " ");
			}

			System.out.print("]\n");
		}
	}
}
