package beppy;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.lang.reflect.InvocationTargetException;

class RootWindow implements Runnable
{
	public void run()
	{
		final JFrame _jframe = new JFrame();
		_jframe.setTitle("Beppy");
		_jframe.setSize(700, 100);
		_jframe.setVisible(true);
	}
}

public class BloodPressureTracker {

	public static void main(String args[]) throws InvalidOSException, IOException {
		if( ! OutputDataFile.exists())
		{
			System.out.println("Output data file not found.Creating output data file...");
			OutputDataFile.create();
		}

		System.out.printf("Using output data file: \"%s\"", OutputDataFile.PATH);

		SwingUtilities.invokeLater(new RootWindow());

		// try
		// {
		// 	SwingUtilities.invokeAndWait(
		// 		new Runnable() {
		// 			public void run()
		// 			{
		// 				new RootWindow();
		// 			}
		// 		}
		// 	);
		// }
		// catch (InvocationTargetException err)
		// {
		// 	err.printStackTrace();
		// 	System.exit(8);
		// }
		// catch (InterruptedException err)
		// {
		// 	err.printStackTrace();
		// 	System.exit(9);
		// }








		// FileIO config = new FileIO();
		// if (config.doesExist(0)) {
		// 	System.out.println("Config file found. Loading config...");
		// } else {
		// 	CountDownLatch latch = new CountDownLatch(1);
		// 	new SetupWindow(config, latch);
		// 	try {
		// 		// Wait until the SetupWindow completes (i.e., latch.countDown() is called)
		// 		latch.await();
		// 	} catch (InterruptedException e) {
		// 		e.printStackTrace();
		// 	}
		// }
		// if (config.doesExist(1)) {
		// 	System.out.println("Entry log found at: " + config.readProperties());
		// } else {
		// 	config.writeEntries();
		// }
		// new AddEntryWindow();
	}
}
