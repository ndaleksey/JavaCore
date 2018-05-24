package com.alex.concurrency.deadlock;

/**
 * Created by Shishkov A.V. on 22.05.18.
 */
public class Deadlock {
	static class Friend {
		private final String name;

		public Friend(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public synchronized void bow(Friend bower) {
			System.out.format("%s: %s"
							+ "  has bowed to me!%n",
					this.name, bower.getName());

			if (true)
				throw new NullPointerException();

			bower.bowBack(this);
		}

		public synchronized void bowBack(Friend bower) {
			System.out.format("%s: %s"
							+ " has bowed back to me!%n",
					this.name, bower.getName());
		}
	}

	public static void main(String[] args) {
		final Friend alphonse =
				new Friend("Alphonse");
		final Friend gaston =
				new Friend("Gaston");
		new Thread(() -> {
			try {
				alphonse.bow(gaston);
			} catch (Exception e) {}
		}).start();
		new Thread(() -> {
			try {
				gaston.bow(alphonse);
			} catch (Exception e){}
		}).start();
	}
}
