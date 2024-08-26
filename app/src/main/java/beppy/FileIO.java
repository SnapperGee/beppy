package beppy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FileIO {
	// method to check to see if config and entry file exist.
	public final boolean doesExist(int arg) throws IOException {
		if (arg == 0) {
			// throws IOException here if properties file does not exist
			try (FileInputStream input = new FileInputStream(Sysvar.var.getDefaultConfigFilePath())) {
				return true;
			} catch (IOException e) {
				return false;
			}
		}
		if (arg == 1) {
			// throws IOException if entries file does not exist
			FileInputStream input = new FileInputStream(Sysvar.var.getDefaultConfigFilePath());
			Properties properties = new Properties();
			FileInputStream stream = new FileInputStream(Sysvar.var.getDefaultConfigFilePath());
			properties.load(stream);
			try (FileInputStream entries = new FileInputStream(
					properties.getProperty("entries-path") + properties.getProperty("entries-filename"))) {
				input.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				input.close();
				return false;
			}
		}
		return false;
	}

	// this method read the .properties file
	public final String readProperties() throws IOException {
		Properties properties = new Properties();
		FileInputStream stream = new FileInputStream(Sysvar.var.getDefaultConfigFilePath());
		properties.load(stream);
		return properties.getProperty("entries-path");
	}

	// this method creates the properties file and fills it.
	public void writeProperties(String configDefinedPath) throws IOException {
		Properties properties = new Properties();
		File file = new File(Sysvar.var.getDefaultConfigFilePath()); // this is the config.properties file
		File directory = new File(Sysvar.var.getDefaultConfigFilePathDirectory()); // this is the dir for the prop file
		if (!file.exists()) {// test if config file exists already
			if (configDefinedPath == null) { // if the user selects defaults for paths then this runs
				if (directory.exists() && !file.exists()) { // if directory but not file exists run this
					properties.setProperty("entries-path", Sysvar.var.getDefaultEntryFilePathDirectory());
					properties.setProperty("entries-filename", "\\entries.xlsx");
					try (FileOutputStream output = new FileOutputStream(Sysvar.var.getDefaultConfigFilePath())) {
						properties.store(output, null);
						System.out.println("Config file created.");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else { // if directory does not exist, create it and then run the method again to
							// create the file
					if (directory.mkdirs()) {
						writeProperties(null);
						System.out.println(
								"Config directory created using defaults: " + Sysvar.var.getDefaultEntryFilePath());
					} else {
						System.out.println("Failed to make config directory");
					}
				}
			} else if (configDefinedPath != null) { // if the string is not null then we use the supplied directory
				File customFilePath = new File(configDefinedPath); // dir for custom save directory for ENTRY file
				if (!directory.exists()) { // if the prop file directory does not exist, create it and recurse
					System.out.println("System variable directory not found. Creating directory...");
					directory.mkdirs();
					writeProperties(configDefinedPath);
					// if directory for prop file exists, move on to check if custom entries save
					// path exists
				} else if (!customFilePath.exists()) {
					customFilePath.mkdir();
					System.out.println("User defined entries directory not found. Creating directory...");
					writeProperties(configDefinedPath);
					// if custom entries directory and prop directory exist, create the properties
					// file and fill it
				} else if (customFilePath.exists()) {
					if (directory.exists() && !file.exists()) {// if directory but not prop file exists, create propfile
						properties.setProperty("entries-path", configDefinedPath);
						properties.setProperty("entries-filename", "\\entries.xlsx");
						try (FileOutputStream output = new FileOutputStream(Sysvar.var.getDefaultConfigFilePath())) {
							properties.store(output, null);
							System.out.println("Config file created.");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		} else {
			System.out.println("Config File Found. Loading Varaibles...");
		}
	}

	// this method creates the entries file.
	public void writeEntries() {
		Properties properties = new Properties();
		try {
			FileInputStream stream = new FileInputStream(Sysvar.var.getDefaultConfigFilePath());
			properties.load(stream);
			File file = new File(properties.getProperty("entries-path"));
			System.out.println(properties.getProperty("entries-path"));
			file.mkdirs();
			file = new File(properties.getProperty("entries-path") + properties.getProperty("entries-filename"));
			file.createNewFile();
			System.out.println("Entries file created. Opening entry window...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
