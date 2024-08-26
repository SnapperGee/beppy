package beppy;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class BloodPressureTracker {
	public static void main(String args[]) throws InvalidOSException, IOException {
		FileIO config = new FileIO();
		if (config.doesExist(0)) {
			System.out.println("Config file found. Loading config...");
		} else {
			CountDownLatch latch = new CountDownLatch(1);
			new SetupWindow(config, latch);
			try {
				// Wait until the SetupWindow completes (i.e., latch.countDown() is called)
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (config.doesExist(1)) {
			System.out.println("Entry log found at: " + config.readProperties());
		} else {
			config.writeEntries();
		}
		new AddEntryWindow();
	}
}
