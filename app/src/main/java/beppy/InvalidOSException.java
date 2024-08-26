package beppy;


@SuppressWarnings("serial")
public class InvalidOSException extends Exception{
	public InvalidOSException() {
		super("\nInvalid OS, "+ Sysvar.var.getOS()+" is not a supported operating system.\n"
				+ "Supported operating systems are \nWindows\nMac\nLinux");
	}
}
