package beppy;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.Arrays;

final class RootWindow implements Runnable
{
	public void run()
	{
		final JFrame rootJFrame = new JFrame();
		rootJFrame.setName("Beppy");
		rootJFrame.setTitle("Beppy");
		rootJFrame.setSize(500, 300);

		final String[][] data = Arrays.copyOfRange(DataFile.readTo2dArray(), 1, DataFile.readTo2dArray().length);

		final var table = ScrollableDataTableFrame.create(data);

		rootJFrame.add(table);

		rootJFrame.setVisible(true);
	}
}

public class Main {

	public static void main(String args[])
	{
		if( ! DataFile.exists())
		{
			System.out.println("Data file not found. Creating output data file...");
			final var path = DataFile.create();
			System.out.printf("Data file created at: \"%s\"", path);
		}

		System.out.printf("Using output data file: \"%s\"", DataFile.PATH);

		SwingUtilities.invokeLater(new RootWindow());
	}
}
