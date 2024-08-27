package beppy;

import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.Arrays;
import java.awt.Color;
import javax.swing.BoxLayout;

final class RootWindow implements Runnable
{
    public void run()
    {
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        defaults.putIfAbsent("Table.alternateRowColor", Color.LIGHT_GRAY);

        final JFrame rootJFrame = new JFrame();

        rootJFrame.setLayout(new BoxLayout(rootJFrame.getContentPane(), BoxLayout.Y_AXIS));

        rootJFrame.setName("Beppy");
        rootJFrame.setTitle("Beppy");
        rootJFrame.setSize(500, 300);

        final String[][] data = Arrays.copyOfRange(DataFile.readTo2dArray(), 1, DataFile.readTo2dArray().length);

        final var table = ScrollableDataTableFrame.create(data);

        final var addDataPanel = AddDataPanel.create();

        rootJFrame.add(table);
        rootJFrame.add(addDataPanel);

        rootJFrame.setVisible(true);
    }
}

public class Beppy
{

    public static void main(String args[])
    {
        if( ! DataFile.exists())
        {
            System.out.println("Data file not found. Creating data file...");
            final var path = DataFile.create();
            System.out.printf("Data file created at: \"%s\"", path);
        }

        System.out.printf("Using data file: \"%s\"", DataFile.PATH);

        SwingUtilities.invokeLater(new RootWindow());
    }
}
