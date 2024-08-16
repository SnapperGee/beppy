package bptracker2;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class SaveDialogue extends JFrame {
	public SaveDialogue(FileIO config) throws IOException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save Entries To...");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int userSelection = fileChooser.showOpenDialog(null);
        
        if(userSelection == JFileChooser.APPROVE_OPTION) {
        	File selectedDirectory = fileChooser.getSelectedFile();
        	config.writeProperties(selectedDirectory.getAbsolutePath());
        }
	}
}
