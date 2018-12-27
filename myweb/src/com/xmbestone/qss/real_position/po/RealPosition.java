package com.xmbestone.qss.real_position.po;


import java.util.Date;

import com.xmbestone.qss.util.DatabaseUtils;


public class RealPosition  {
 	/**
	 * 
	 */
 
	private int personId;
	private int userid;
	private String longitude;
	private String latitude;
	private String positionTime;
	private int accuracyRange;
	public  RealPosition(){
		personId = DatabaseUtils.INVALID_INT_ID;
	};	
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getPositionTime() {
		return positionTime;
	}
	public void setPositionTime(String positionTime) {
		this.positionTime = positionTime;
	}
	public int getAccuracyRange() {
		return accuracyRange;
	}
	public void setAccuracyRange(int accuracyRange) {
		this.accuracyRange = accuracyRange;
	}
	@Override
	public String toString() {
		return "Real_position [personId=" + personId + ", userid=" + userid
				+ ", longitude=" + longitude + ", latitude=" + latitude
				+ ", positionTime=" + positionTime + ", accuracyRange="
				+ accuracyRange + "]";
	}
 
}
