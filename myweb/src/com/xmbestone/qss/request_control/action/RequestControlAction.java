package com.xmbestone.qss.request_control.action;

import net.sf.json.JSONObject;

import com.xmbestone.qss.request_control.bo.RequestControlBo;
import com.xmbestone.qss.request_control.po.RequestControl;
import com.xmbestone.qss.users.po.Users;
import com.xmbestone.qss.util.QssActionSupprot;
import com.xmbestone.qss.util.ResponseUtil;

public class RequestControlAction extends QssActionSupprot{
	
	private RequestControlBo requestControlBo;
	private RequestControl requestControl;
	private String monitorUser;
	private String request_id;
	private String msg;
	private int flag;
	
	public RequestControlBo getRequestControlBo() {
		return requestControlBo;
	}
	public void setRequestControlBo(RequestControlBo requestControlBo) {
		this.requestControlBo = requestControlBo;
	}
	public RequestControl getRequestControl() {
		return requestControl;
	}
	public void setRequestControl(RequestControl requestControl) {
		this.requestControl = requestControl;
	}
	
	public String receiveRequest(){
		flag = 0;
		msg = null;
		int bemonitorUser = Integer.valueOf(getParam("bemonitorUser").toString());
		String status = getParam("status").toString();		
		try {
			requestControl = requestControlBo.findRequest(bemonitorUser, status);
			if (requestControl.getRequestId()>0) {
				monitorUser = String.valueOf( requestControl.getMonitorUser());
				request_id = String.valueOf( requestControl.getRequestId());
				flag = 1;
				msg="success";
			} else {
				flag = 0;	
				msg="error";
			}			
		} catch (Exception e) {
			flag = 0;
			msg="error";
		}
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		json.put("flag", flag);
		json.put("monitorUser", monitorUser);
		json.put("request_id", request_id);
		return ResponseUtil.returnJson(json.toString());
	}
	public String upStatus(){
		msg = null;
		flag=0;
		int request_id =Integer.valueOf(getParam("request_id").toString()) ;
		String status = getParam("status").toString();	
		requestControl.setStatus(status);
		
		try {
			requestControl = getRequestControlBo().findById(request_id);
			requestControl.setStatus(status);
			requestControlBo.saveRequestControl(requestControl);
//			request_id = String.valueOf(requestControl.getRequestId());
			msg = "success";
			flag=1;
		} catch (Exception e){
			e.printStackTrace();
			msg = "error";
			flag=0;
		}
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		json.put("flag", flag);
		return ResponseUtil.returnJson(json.toString());
	}	
}
