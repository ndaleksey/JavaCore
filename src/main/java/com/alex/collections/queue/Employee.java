package com.alex.collections.queue;

/**
 * Created by Shishkov A.V. on 08.05.18.
 */
public class Employee {
	private int id;
	private String name;
	private Department department;

	public Employee(int id, String name, Department department) {
		this.id = id;
		this.name = name;
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Department getDepartment() {
		return department;
	}
}
