package com.devops.bbm.bean;

public class Donar {
	private PersonalInformation personalinfo;
	private Location location;
	private User user;
	
	public Donar(PersonalInformation personalinfo, Location location, User user) {
		super();
		this.personalinfo = personalinfo;
		this.location = location;
		this.user = user;
	}

	public PersonalInformation getPersonalinfo() {
		return personalinfo;
	}

	public void setPersonalinfo(PersonalInformation personalinfo) {
		this.personalinfo = personalinfo;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
