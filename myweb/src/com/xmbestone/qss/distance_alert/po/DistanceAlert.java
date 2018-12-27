package com.xmbestone.qss.distance_alert.po;

import java.util.Date;

import com.xmbestone.qss.util.DatabaseUtils;


public class DistanceAlert implements java.io.Serializable{
	
 
	private int alarmId;
	private int historyInfoId;
	private Date alarmTime;
	public  DistanceAlert(){
		alarmId = DatabaseUtils.INVALID_INT_ID;
 
	}
	public int getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(int alarmId) {
		this.alarmId = alarmId;
	}
	public int getHistoryInfoId() {
		return historyInfoId;
	}
	public void setHistoryInfoId(int historyInfoId) {
		this.historyInfoId = historyInfoId;
	}
	public Date getAlarmTime() {
		return alarmTime;
	}
	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}
	@Override
	public String toString() {
		return "DistanceAlert [alarmId=" + alarmId + ", historyInfoId="
				+ historyInfoId + ", alarmTime=" + alarmTime + "]";
	};	
 
}
