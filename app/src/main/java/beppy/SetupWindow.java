package beppy;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SetupWindow extends JFrame {
	private CountDownLatch latch;
	public SetupWindow(FileIO config, CountDownLatch latch) {
		this.latch = latch;
		setTitle("Save to...");
		setSize(700, 100);
		setLayout(new GridLayout(1, 2));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton set = new JButton("Save Entry File To...");
		JButton defaults = new JButton("Use Default (Documents Folder)");
		add(set);
		add(defaults);
		setLocationRelativeTo(null);
		setVisible(true);

		// set the actions for the set button
		set.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SaveDialogue saveWindow = new SaveDialogue(config);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				latch.countDown();
				dispose();
			}
		});
		// set the action for the default button
		defaults.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					config.writeProperties(null);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				latch.countDown();
				dispose();
			}
		});
		// return "";
	}
}
