package org.spray.darkeye.objects;

import java.util.List;

public class Data {
	
	private List<FileData> files;
	private String name;
	
	public Data(List<FileData> files, String name) {
		this.files = files;
		this.name = name;
	}

	public List<FileData> getFiles() {
		return files;
	}

	public String getName() {
		return name;
	}

}
