package com.alex.concurrency;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Shishkov A.V. on 21.05.18.
 */

public class DeadlockDemo {
	static Browser browser;


	public static void main(String[] args) {
		browser = new DeadlockDemo.Browser();

		for(int i = 0; i < 10; i++) {
			Page page = browser.openPage();
			new Thread(() -> page.close()).start();
		}

		browser.close();
	}

	static final class Page {
		String name;
		Browser browser;

		public Page(String name, Browser browser) {
			this.name = name;
			this.browser = browser;
			System.out.println("Opened " + name);
		}

		public synchronized void close() {
			System.out.println("Closed " + name);
			browser.closePage(this);
		}
	}

	static final class Browser {
		Collection<Page> pages = new ArrayList<>();

		public synchronized Page openPage() {
			Page page = new Page("Page #" + pages.size(), browser);
			pages.add(page);
			return page;
		}

		public synchronized void closePage(Page page) {
			pages.remove(page);
			System.out.println("Closed " + page.name);
		}

		public synchronized void close() {
			for (Page page : pages) {
				page.close();
			}
			System.out.println("Closed Browser");
		}
	}

}



