package bptracker2;

import java.io.File;

public enum Sysvar {
	var;

	public final String getOS() {
		String OS = System.getProperty("os.name").toLowerCase();
		return OS;
	}

	/* returns the default file directory to use for the OS running the program. should only be used
	when trying to access the file itself, not the directory*/
	public final String getDefaultConfigFilePath() {
		String OS = getOS();
		try {
			if (OS.contains("win")) {
				return System.getenv("APPDATA") + "\\BPTracker\\config.properties";
			} else if (OS.contains("mac")) {
				return System.getProperty("user.home") + "/Library/Application Support/BPTracker/config.properties";
			} else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
				return System.getProperty("user.home") + "/.BPTracker/config.properties";
			} else throw new InvalidOSException();
		} catch (InvalidOSException e) {
			System.out.println("Illegal OS");
			e.printStackTrace();
			System.exit(1);;
			return"";
		}
	}
	/*this returns the directory of where the config file is being stores, but does not return the
	 * file itself. this should be used when creating the directory itself or checking if the 
	 * directory exists, but not for checking if the file exists or creating the file itself.*/
	public final String getDefaultConfigFilePathDirectory() {
		String OS = getOS();
		try {
			if (OS.contains("win")) {
				return System.getenv("APPDATA") + "\\BPTracker\\";
			} else if (OS.contains("mac")) {
				return System.getProperty("user.home") + "/Library/Application Support/BPTracker/";
			} else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
				return System.getProperty("user.home") + "/.BPTracker/";
			} else throw new InvalidOSException();
		} catch (InvalidOSException e) {
			System.out.println("Illegal OS");
			e.printStackTrace();
			System.exit(1);;
			return"";
		}
	}
	
	/* returns the default file directory to use to store the logged information
	 * this should be used to check if the file exists or to create the file itself
	 * but not to check if the directory exists or to create the directory*/
	public final String getDefaultEntryFilePath() {
		String OS = getOS();
		if (OS.contains("win")) {
			return System.getenv("USERPROFILE") + "\\OneDrive\\Documents\\BPTracker\\entries.xlsx";
		} else if (OS.contains("mac")) {
			return System.getProperty("user.home") + "/Library/Application Support/BPTracker/entries.xlsx";
		} else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
			return System.getProperty("user.home") + "/.BPTracker/entries.xlsx";
		}
		return "";
	}
	/*this returns the default directory for where the config file is going to be stored
	 * this should be used to create the directory where the entries are going to be stored
	 * but not to create the directory file itself.*/
	public final String getDefaultEntryFilePathDirectory() {
		String OS = getOS();
		if (OS.contains("win")) {
			return System.getenv("USERPROFILE") + "\\OneDrive\\Documents\\BPTracker\\";
		} else if (OS.contains("mac")) {
			return System.getProperty("user.home") + "/Library/Application Support/BPTracker/";
		} else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
			return System.getProperty("user.home") + "/.BPTracker/";
		}
		return "";
	}
	// this checks if the config file exists at all.
}
