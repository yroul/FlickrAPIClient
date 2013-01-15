package yroul.flickr;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FlickrApiConnectionProvider {

	private final String apiKey;
	private final String format;
	private final String flickrAPIURL;
	
	public FlickrApiConnectionProvider(String apiKey, String format,String flickrAPIURL) {
		super();
		this.apiKey = apiKey;
		this.format = format;
		this.flickrAPIURL = flickrAPIURL;
	}
	public FlickrApiConnectionProvider(String apiKey, String format) {
		super();
		this.apiKey = apiKey;
		this.format = format;
		this.flickrAPIURL = "http://api.flickr.com/services/rest/";
	}
	
	public String sendRequest(String methodName,Map<String,String> args){
		String requestUrl = flickrAPIURL+"?method="+methodName+"&api_key="+apiKey;
		//only with json yet
		requestUrl += "&format=json";
		
		Set keys = args.keySet();
		Iterator it = keys.iterator();
		while (it.hasNext()){
		   String key = (String) it.next();
		   String value = args.get(key);
		   
		   requestUrl += "&"+key+"="+value;// TODO URL ENCODE 
		}
		
		try{
		
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStream responseStream = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream));
			String text = reader.readLine();
			text = text.substring(15);
			return text;
		}catch(Exception e){
			e.printStackTrace();
			return "Error : "+e.toString();
		}
		
		
	}

}
