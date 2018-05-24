package com.alex.serialization;

import java.io.Serializable;

/**
 * Created by Shishkov A.V. on 24.05.18.
 */
public class Employee implements Serializable {
	private Long id;
	private String name;
	private String department;

	public Employee(Long id, String name, String department) {
		this.id = id;
		this.name = name;
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", name='" + name + '\'' +
				", department='" + department + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;

		if (obj == null) return false;

		if (obj.getClass() != Employee.class) return false;

		Employee employee = (Employee) obj;

		return Long.compare(this.id, employee.id) == 0 ? true : false && this.name.equals(employee.getName()) && this
				.department.equals
				(employee
				.getDepartment());
	}
}
