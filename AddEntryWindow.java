package bptracker2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class AddEntryWindow extends JFrame {
	private String FILE_PATH = Sysvar.var.getDefaultConfigFilePath();
	private JTextField systolicField;
	private JTextField diastolicField;
	private JTextField bpmField;
	private JTextArea notesField;
	private JCheckBox afterMedsCheckBox;
	private JLabel dateTimeLabel;
	private static String title = "Add Entry";

	public AddEntryWindow() {
		//create variables
		systolicField = new JTextField();
		diastolicField = new JTextField();
		bpmField = new JTextField();
		notesField = new JTextArea();
		afterMedsCheckBox = new JCheckBox();
		dateTimeLabel = new JLabel(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addEntry();
			}
		});

		JButton viewButton = new JButton("View");
		viewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewEntriesWindow();
				dispose();
			}
		});
		//add variables to the window
		setTitle("Add Entry");
		setSize (400,300);
		setLayout(new GridLayout(7, 2));
		add(new JLabel("Systolic:"));
		add(systolicField);
		add(new JLabel("Diastolic:"));
		add(diastolicField);
		add(new JLabel("BPM:"));
		add(bpmField);
		add(new JLabel("Notes:"));
		add(new JScrollPane(notesField));
		add(new JLabel("After Meds:"));
		add(afterMedsCheckBox);
		add(new JLabel("Date/Time:"));
		add(dateTimeLabel);
		add(addButton);
		add(viewButton);
		//set window behaviors
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	//
	private void addEntry() {
		String systolic = systolicField.getText();
		String diastolic = diastolicField.getText();
		String bpm = bpmField.getText();
		String notes = notesField.getText();
		boolean afterMeds = afterMedsCheckBox.isSelected();
		String dateTime = dateTimeLabel.getText();

		try (FileInputStream fis = new FileInputStream(FILE_PATH); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getLastRowNum();

			Row row = sheet.createRow(rowCount + 1);
			row.createCell(0).setCellValue(dateTime);
			row.createCell(1).setCellValue(systolic);
			row.createCell(2).setCellValue(diastolic);
			row.createCell(3).setCellValue(bpm);
			row.createCell(4).setCellValue(afterMeds ? "Y" : "N");
			row.createCell(5).setCellValue(notes);

			try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
				workbook.write(fos);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
