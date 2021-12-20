package org.spray.darkeye.objects;

import java.io.File;

public class FileData {
	
	private File file;
	private String name;
	
	public FileData(File file, String name) {
		this.file = file;
		this.name = name;
	}

	public File getFile() {
		return file;
	}

	public String getName() {
		return name;
	}

}
