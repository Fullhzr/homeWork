package com.xmbestone.qss.historyview.po;

public class HistoryGroupBy {
	private int userid;
	private String username;
	private String history_id;
	private String friend_userid;
	private String history_info_id;
	private String starttime;
	private String endtime;
	private String longitude;
	private String latitude;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHistory_id() {
		return history_id;
	}
	public void setHistory_id(String history_id) {
		this.history_id = history_id;
	}
	public String getFriend_userid() {
		return friend_userid;
	}
	public void setFriend_userid(String friend_userid) {
		this.friend_userid = friend_userid;
	}
	public String getHistory_info_id() {
		return history_info_id;
	}
	public void setHistory_info_id(String history_info_id) {
		this.history_info_id = history_info_id;
	}
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
	/* 
	 * 
	 */
	@Override
	public String toString() {
		return "HistoryGroupBy [userid=" + userid + ", username=" + username
				+ ", history_id=" + history_id + ", friend_userid="
				+ friend_userid + ", history_info_id=" + history_info_id
				+ ", starttime=" + starttime + ", endtime=" + endtime
				+ ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}
	

	



}
