package beppy;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

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

	public static void main(String args[])
	{
		if( ! OutputDataFile.exists())
		{
			System.out.println("Output data file not found.Creating output data file...");
			OutputDataFile.create();
		}

		System.out.printf("Using output data file: \"%s\"", OutputDataFile.PATH);

		SwingUtilities.invokeLater(new RootWindow());
	}
}
