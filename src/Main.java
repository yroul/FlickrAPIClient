

import java.io.File;

import yroul.flickr.FlickrAPIClient;
import yroul.flickr.model.PhotoSet;

public class Main {

	public static void main(String[] args) {
		/*PhotoSet photoSet = FlickrAPIClient.searchPhotos("sexy,pinup");
		System.out.println(photoSet.toString());*/
		//System.out.println(FlickrAPIClient.getImageURL("Square", "8361091108"));
		//TODO TEST
		File image = new File(FlickrAPIClient.getImageURL("Square", "8361091108"));
		
		
		
		
		
	}

}
