package com.example.where;

public class HistoryNote {

	private String starttime;

	private String endtime;

	private String longtitude;

	private String latitude;
	
	private String title;

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "HistoryNote [starttime=" + starttime + ", endtime=" + endtime
				+ ", longtitude=" + longtitude + ", latitude=" + latitude
				+ ", title=" + title + "]";
	}


 
	
	
}
