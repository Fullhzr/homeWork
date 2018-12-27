package com.xmbestone.qss.system.action;

import net.sf.json.JSONObject;

import com.xmbestone.qss.system.bo.UsersBo;
import com.xmbestone.qss.util.QssActionSupprot;
import com.xmbestone.qss.util.ResponseUtil;
import com.xmbestone.qss.util.SecurityUtil;

public class UsersAction extends QssActionSupprot{
	private UsersBo usersBo;
	public UsersBo getUsersBo() {
		return usersBo;
	}

	public void setUsersBo(UsersBo usersBo) {
		this.usersBo = usersBo;
	}


	/**
	 * 登陆
	 * @return
	 */
	public String login(){
		String username = getParam("username").toString();
		String password = getParam("password").toString();//SecurityUtil.getPassword( getParam("password").toString());
	
		JSONObject json = new JSONObject();
		
		try {
			boolean bool = usersBo.findUsers(username, password);
			if(bool){
				json.put("flag",1); 
			}else{
				json.put("flag",0); 
			}
		} catch (Exception e) {
			e.getStackTrace();
			json.put("flag",2); 
		}
		return ResponseUtil.returnJson(json.toString());
	}
}
