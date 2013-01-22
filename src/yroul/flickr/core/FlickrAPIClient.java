package yroul.flickr.core;


import java.util.HashMap;
import java.util.Map;

import yroul.flickr.model.PhotoSet;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/**
 * 
 * @author yroul
 *
 *	Application core
 *	Here is all the functions the application needs
 */
public  class FlickrAPIClient {

	private static String apiKey = "de7a49a6d554b0e216dfcf2976635605";
	public static String photosPerPage = "30";
	
	/**
	 *  Search photos by keywords
	 * @param keywords keywords to request
	 * @return photoset
	 */
	public static PhotoSet  searchPhotos(String keywords){
		FlickrApiConnectionProvider provider = new FlickrApiConnectionProvider(apiKey, "json");
		Map<String,String> arguments = new HashMap<String,String>();
		keywords = keywords.replace(" ", ",");
		arguments.put("tags",keywords);
		arguments.put("sort","relevance");
		//return only 30 image 
		arguments.put("per_page",photosPerPage);
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
	
	/**
	 * Return the url of a picture
	 * @param imageSize Image size (can be Large,Thumbnail,Original,Square,...) see Flickr API documentation
	 * @param photoId photo id you want the url
	 * @return the picture url
	 */
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
						break;
						
					}
						
				}
				
			}
			if (toReturn == null){
				throw new IllegalArgumentException("Sorry, the size "+imageSize+" does not exist.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toReturn;
		
		
		
		
	}
	

}
