package com.devops.bbm.bean;

public class Location {
	private String country;
	private String state;
	private String district;
	private String co_ordinates;
	private String address;
	
	public Location(String country, String state, String district, String co_ordinates, String address) {
		super();
		this.country = country;
		this.state = state;
		this.district = district;
		this.co_ordinates = co_ordinates;
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCo_ordinates() {
		return co_ordinates;
	}

	public void setCo_ordinates(String co_ordinates) {
		this.co_ordinates = co_ordinates;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
