package com.alex.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by Shishkov A.V. on 24.05.18.
 */
public class ScanXan {

	public static void main(String[] args) {

		Scanner s = null;

		try {
			String fileName = ScanXan.class.getClassLoader().getResource("xanadu.txt").getFile();
			s = new Scanner(new BufferedReader(new FileReader(fileName)));

			while (s.hasNext()) {
				System.out.println(s.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}
}
