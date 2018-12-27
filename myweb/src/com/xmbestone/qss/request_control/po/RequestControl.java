package com.xmbestone.qss.request_control.po;

import java.util.Date;

import com.xmbestone.qss.util.DatabaseUtils;


public class RequestControl implements java.io.Serializable{
	
 
	private int requestId;
	private int monitorUser;
	private int bemonitorUser;
	private String requestTime;
	private String status;
	public  RequestControl(){
		requestId = DatabaseUtils.INVALID_INT_ID;
 
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getMonitorUser() {
		return monitorUser;
	}
	public void setMonitorUser(int monitorUser) {
		this.monitorUser = monitorUser;
	}
	public int getBemonitorUser() {
		return bemonitorUser;
	}
	public void setBemonitorUser(int bemonitorUser) {
		this.bemonitorUser = bemonitorUser;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "RequestControl [requestId=" + requestId + ", monitorUser="
				+ monitorUser + ", bemonitorUser=" + bemonitorUser
				+ ", requestTime=" + requestTime + ", status=" + status + "]";
	}
 
}
