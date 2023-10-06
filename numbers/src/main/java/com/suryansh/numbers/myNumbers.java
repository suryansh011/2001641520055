package com.suryansh.numbers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("")
public class myNumbers {
	HashMap<String, Set<Integer>> mp = new HashMap<>();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Set<Integer>> getNumbers(@QueryParam(value = "url") final List<String> urls) {
		
		Set<Integer> st = new TreeSet<>();
		
		for(String url : urls) {
			JSONObject json = null;

			try {
				final URL uri = new URL(url);
				URLConnection conn = uri.openConnection();
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setConnectTimeout(5000);
				conn.setReadTimeout(5000);
				json = new JSONObject(IOUtils.toString(conn.getInputStream(), conn.getContentEncoding()));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
			
			JSONArray jarr = json.getJSONArray("numbers");
			
			if(jarr != null) {
				for(int i = 0; i < jarr.length(); i++) {
					st.add(jarr.getInt(i));
				}
			}
		}

		mp.put("numbers", st);
		
		return mp;
	}
}
