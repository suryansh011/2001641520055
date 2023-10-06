package com.suryansh.numbers;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class numbersRepo {
	
	public static JSONObject getNumbers(URL url) {
	    String json = null;
		try {
			json = IOUtils.toString(url, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return new JSONObject(json);
	}

}
