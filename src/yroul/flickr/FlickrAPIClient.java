package yroul.flickr;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import yroul.flickr.model.PhotoSet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import javax.json.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public  class FlickrAPIClient {

	private static String apiKey = "de7a49a6d554b0e216dfcf2976635605";
	
	public static PhotoSet  searchPhotos(String keywords){
		FlickrApiConnectionProvider provider = new FlickrApiConnectionProvider(apiKey, "json");
		Map<String,String> arguments = new HashMap<String,String>();
		arguments.put("tags",keywords);
		String response = provider.sendRequest("flickr.photos.search", arguments);
		
		response = response.substring(0, response.length()-1);
		response = response.substring(9);
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			PhotoSet photoSet = mapper.readValue(response, PhotoSet.class);
			return photoSet;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
			return null;
		}
	}
	
	public static String getImageURL(String imageSize,String photoId){
		FlickrApiConnectionProvider provider = new FlickrApiConnectionProvider(apiKey, "json");
		Map<String,String> arguments = new HashMap<String,String>();
		arguments.put("photo_id",photoId);
		String response = provider.sendRequest("flickr.photos.getSizes", arguments);
		response = response.substring(8);
		response = response.substring(0, response.length()-15);
		JSONParser parser = new JSONParser();
		String toReturn = null;
		try {
			JSONObject sizes = (JSONObject) parser.parse(response);
			JSONArray sizeArray = (JSONArray) sizes.get("size");
			for(int i=0;i<sizeArray.size();i++){
				JSONObject obj = (JSONObject) sizeArray.get(i);
				if(obj.containsKey("label")){
					if(obj.get("label").equals(imageSize)){
						toReturn =  (String) obj.get("source");
					}
					else{
						break;
					}
						
				}
				
			}
			if (toReturn == null)
				throw new IllegalArgumentException("Sorry, the size "+imageSize+" does not exist.");
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			toReturn = e.toString();
		}
		return toReturn;
		
		
		
	}
	

}
