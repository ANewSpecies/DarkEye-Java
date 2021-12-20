package org.spray.darkeye.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class HttpUtils {

	public static String readURLToString(String aUrl) throws MalformedURLException, IOException {
		String urlData = "";
		URL urlObj = new URL(aUrl);
		URLConnection conn = urlObj.openConnection();
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
			urlData = reader.lines().collect(Collectors.joining("\n"));
		}
		return urlData;
	}

}
