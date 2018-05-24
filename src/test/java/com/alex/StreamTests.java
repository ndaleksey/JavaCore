package com.alex;

import com.alex.serialization.Employee;
import com.alex.serialization.SerializationDemo;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Shishkov A.V. on 24.05.18.
 */
public class StreamTests {

	private <T extends Serializable> void writeUsingObjectSteam(T employee, FileOutputStream fileOutputStream) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
		out.writeObject(employee);
		out.close();
	}

	private void writeUsingBufferedStream(String text, FileOutputStream fileOutputStream) throws IOException {
		BufferedOutputStream out = new BufferedOutputStream(fileOutputStream);
		out.write(text.getBytes());
		out.close();
	}

	@Test
	public void streamIsClosedAfterObjectStreamClosing() {
		Throwable exception = assertThrows(IOException.class, () -> {
			String fileName = "/home/alexeyshishkov/employee";
			FileOutputStream fileStream = new FileOutputStream(fileName, true);

			writeUsingObjectSteam(new Employee(1L, "Alex", "Soft"), fileStream);
			writeUsingObjectSteam(new Employee(2L, "Nick", "Hard"), fileStream);
		});

		assertTrue(exception.getMessage().equalsIgnoreCase("Stream Closed"), "Stream is not closed");
	}

	@Test
	public void streamIsClosedAfterBufferedStreamClosing() {
		Throwable exception = assertThrows(IOException.class, () -> {
			String fileName = "/home/alexeyshishkov/test";
			FileOutputStream fileStream = new FileOutputStream(fileName, true);

			writeUsingBufferedStream("Dummy text", fileStream);
			writeUsingBufferedStream("Dummy text", fileStream);
		});

		assertTrue(exception.getMessage().equalsIgnoreCase("Stream Closed"), "Stream is not closed");
	}

	@Test
	public void fivePlusThreeEqualsEight() {
		assertTrue(5 + 3 == 8, "5 + 3 is not 8");
//		assertEquals(7, 5 + 3, "It is not!");
	}

	@Test
	public void employMustBeTheSame() {
		Employee employee = new Employee(1L, "Employee", "Department");
		String fileName = "/home/alexeyshishkov/serialization.txt";

		try (FileOutputStream fileStream = new FileOutputStream(fileName, false)) {
			SerializationDemo.serialize(employee, fileStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Employee anotherEmployee = null;
		try (FileInputStream fileStream = new FileInputStream(fileName)) {
			anotherEmployee = SerializationDemo.deserialize(fileStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		assertTrue(employee.equals(anotherEmployee), "Employees are not equal");
	}
}
