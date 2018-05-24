package com.alex.serialization;

import java.io.*;

/**
 * Created by Shishkov A.V. on 24.05.18.
 */

public class SerializationDemo {
	public static <T extends Serializable> void serialize(T employee, OutputStream stream) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(stream);
		out.writeObject(employee);
		out.flush();
	}

	public static <T extends Serializable> T deserialize(InputStream stream) throws IOException,
			ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(stream);
		Object obj = in.readObject();
		return (T) obj;
	}

	public static void main(String[] args) throws IOException {
		Employee alex = new Employee(1l, "Alex", "Software");
		Employee nick = new Employee(2l, "Nick", "Hardware");

		final String fileName = "/home/alexeyshishkov/serialized.txt";

		try {
			FileOutputStream fileStream = new FileOutputStream(new File(fileName), true);
			serialize(alex, fileStream);
			serialize(nick, fileStream);

			Thread thread = new Thread(() -> {
				int counter = 0;

				while (counter++ < 5) {
					try {
						System.out.println(counter);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});

			thread.start();
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try (FileInputStream stream = new FileInputStream(fileName)) {
			Employee newAlex = deserialize(stream);
			Employee newNick = deserialize(stream);

			System.out.println("Deserialized objects:");
			System.out.println(newAlex);
			System.out.println(newNick);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
