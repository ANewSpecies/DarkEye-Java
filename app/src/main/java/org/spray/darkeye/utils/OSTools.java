package org.spray.darkeye.utils;

import java.util.Locale;

public class OSTools {

	public enum OSType {
		Windows, MacOS, Linux, Other
	};

	private static OSType detectedOS;

	public static OSType getOperatingSystemType() {
		if (detectedOS == null) {
			String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
			if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
				detectedOS = OSType.MacOS;
			} else if (OS.indexOf("win") >= 0) {
				detectedOS = OSType.Windows;
			} else if (OS.indexOf("nux") >= 0) {
				detectedOS = OSType.Linux;
			} else {
				detectedOS = OSType.Other;
			}
		}
		return detectedOS;
	}
	
	public static boolean isOSWindows() {
		return getOperatingSystemType().equals(OSType.Windows);
	}
	
	public static boolean isOSMac() {
		return getOperatingSystemType().equals(OSType.MacOS);
	}

	public static boolean isOSLinux() {
		return getOperatingSystemType().equals(OSType.Linux);
	}

}
