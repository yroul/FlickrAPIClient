package yroul.flickr;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author yroul
 * 
 * Core Provider for connection with FlickrAPI
 *
 */
public class FlickrApiConnectionProvider {

	private final String apiKey;
	private final String format;
	private final String flickrAPIURL;
	
	/**
	 * 
	 * @param apiKey your applciation key
	 * @param format response format (only json allowed yet)
	 * @param flickrAPIURL flickr api url
	 */
	public FlickrApiConnectionProvider(String apiKey, String format,String flickrAPIURL) {
		super();
		this.apiKey = apiKey;
		this.format = format;
		this.flickrAPIURL = flickrAPIURL;
	}
	/**
	 * 
	 * @param apiKey applciation key
	 * @param format response format (only json allowed yet)
	 */
	public FlickrApiConnectionProvider(String apiKey, String format) {
		super();
		this.apiKey = apiKey;
		this.format = format;
		this.flickrAPIURL = "http://api.flickr.com/services/rest/";
	}
	
	/**
	 * 
	 * @param methodName flickr's api method to request
	 * @param args arguments to send with the request
	 * @return response string
	 */
	public String sendRequest(String methodName,Map<String,String> args){
		String requestUrl = flickrAPIURL+"?method="+methodName+"&api_key="+apiKey;
		//only with json yet
		requestUrl += "&format=json";
		
		Set<String> keys = args.keySet();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()){
		   String key = (String) it.next();
		   String value = args.get(key);
		   
		   requestUrl += "&"+key+"="+value;
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
