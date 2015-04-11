package com.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class HttpUtil {

	public static String read(String url, String proxy, String port) throws Exception {

		HttpURLConnection connection = null;
		OutputStreamWriter wr = null;
		BufferedReader rd = null;
		StringBuilder sb = null;
		String line = null;

		String httpContent = null;

		URL serverAddress = null;

		serverAddress = new URL(url);
		// set up out communications stuff
		connection = null;

		Properties systemProperties = System.getProperties();

		if (proxy != null)
			systemProperties.setProperty("http.proxyHost", proxy);
		// systemProperties.setProperty("http.proxyHost",
		// "proxy-sifi.rd.corpintra.net");

		if (port != null)
			systemProperties.setProperty("http.proxyPort", port);

		// systemProperties.setProperty("http.proxyPort", "3128");

		connection = (HttpURLConnection) serverAddress.openConnection();
		// connection.setRequestProperty("User-Agent",
		// "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Content-length", "0");
		connection.setUseCaches(false);
		connection.setAllowUserInteraction(false);
		connection.setConnectTimeout(10000);
		connection.setReadTimeout(10000);

		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Authorization", "key=" + "AIzaSyBlP2tjzg210o3VHvw_1O1ftg1J_3iPSiA");

		connection.connect();

		// get the output stream writer and write the output to the server
		// not needed in this example
		// wr = new OutputStreamWriter(connection.getOutputStream());
		// wr.write("");
		// wr.flush();

		InputStream is = null;
		boolean success = false;
		sb = new StringBuilder();

		if (connection.getResponseCode() >= 400) {
			is = connection.getErrorStream();
			success = false;
			sb.append(url + "\n");
		} else {
			is = connection.getInputStream();
			success = true;
		}

		// read the result from the server
		rd = new BufferedReader(new InputStreamReader(is));

		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}

		httpContent = sb.toString();

		// close the connection, set all objects to null
		connection.disconnect();
		rd = null;
		sb = null;
		wr = null;
		connection = null;

		if (success)
			return httpContent;
		else {
			throw new Exception(httpContent);
		}

	}

}
