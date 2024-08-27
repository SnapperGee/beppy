package beppy;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

final class RootWindow implements Runnable
{
	public void run()
	{
		final JFrame rootJFrame = new JFrame();
		rootJFrame.setTitle("Beppy");
		rootJFrame.setSize(700, 100);
		rootJFrame.setVisible(true);
	}
}

public class Main {

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
