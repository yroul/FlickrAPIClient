package yroul.flickr.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Photo {

	private String id;
	private String owner;
	private String secret;
	private String server;
	private int farm;
	private String title;
	@JsonProperty("ispublic")
	private boolean isPublic;
	@JsonProperty("isfriend")
	private boolean isFriend;
	@JsonProperty("isfamily")
	private boolean isFamily;
	
	public boolean isFriend() {
		return isFriend;
	}
	public void setIsFriend(boolean isfriend) {
		this.isFriend = isfriend;
	}
	public boolean isFamily() {
		return isFamily;
	}
	public void setIsFamily(boolean isfamily) {
		this.isFamily = isfamily;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	public Photo() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String ownerId) {
		this.owner = ownerId;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public int getFarm() {
		return farm;
	}
	public void setFarm(int farm) {
		this.farm = farm;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Photo [id=" + id + ", owner=" + owner + ", secret=" + secret
				+ ", server=" + server + ", farm=" + farm + ", title=" + title
				+ ", isPublic=" + isPublic + ", isFriend=" + isFriend
				+ ", isFamily=" + isFamily + "]";
	}
	

}
