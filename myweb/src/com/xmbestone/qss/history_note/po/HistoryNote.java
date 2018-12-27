package com.xmbestone.qss.history_note.po;

import java.util.Date;

import com.xmbestone.qss.util.DatabaseUtils;


public class HistoryNote implements java.io.Serializable{
	private int historyId;
	private int userid;
	private int friendUserId;
	private int historyInfoId;
	private String longitude;
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
	private String latitude;
	private Date starttime;
	private Date endtime;
	public  HistoryNote(){
		historyId = DatabaseUtils.INVALID_INT_ID;
 
	}
	public int getHistoryId() {
		return historyId;
	}
	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getFriendUserId() {
		return friendUserId;
	}
	public void setFriendUserId(int friendUserId) {
		this.friendUserId = friendUserId;
	}
	public int getHistoryInfoId() {
		return historyInfoId;
	}
	public void setHistoryInfoId(int historyInfoId) {
		this.historyInfoId = historyInfoId;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	/* 
	 * 
	 */
	@Override
	public String toString() {
		return "HistoryNote [historyId=" + historyId + ", userid=" + userid
				+ ", friendUserId=" + friendUserId + ", historyInfoId="
				+ historyInfoId + ", longitude=" + longitude + ", latitude="
				+ latitude + ", starttime=" + starttime + ", endtime="
				+ endtime + "]";
	}
	
}
