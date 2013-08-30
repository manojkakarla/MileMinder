package com.mileminder.domain;

import com.owlike.genson.annotation.JsonProperty;

public class User {

    @JsonProperty("display_name")
	private String displayName;
    @JsonProperty("username")
	private String userName;
	private String location;
    @JsonProperty("photo_url")
	private String photoUrl;
	private String goal;
	private String url;

    public User() {
    }

    public User(String displayName, String userName, String location) {
		this.displayName = displayName;
		this.userName = userName;
		this.location = location;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
