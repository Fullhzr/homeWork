package com.xmbestone.qss.history_info.po;

import java.util.Date;

import com.xmbestone.qss.util.DatabaseUtils;


public class HistoryInfo implements java.io.Serializable{
	
 
	private int historyInfoId;
	private Date hisTime;
	private String longitude;
	private String latitude;
	public  HistoryInfo(){
		historyInfoId = DatabaseUtils.INVALID_INT_ID;
	}
	public int getHistoryInfoId() {
		return historyInfoId;
	}
	public void setHistoryInfoId(int historyInfoId) {
		this.historyInfoId = historyInfoId;
	}
	public Date getHisTime() {
		return hisTime;
	}
	public void setHisTime(Date hisTime) {
		this.hisTime = hisTime;
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
	@Override
	public String toString() {
		return "HistoryInfo [historyInfoId=" + historyInfoId + ", hisTime="
				+ hisTime + ", longitude=" + longitude + ", latitude="
				+ latitude + "]";
	} 
	
}
