package com.xmbestone.qss.users.action;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.xmbestone.qss.request_control.bo.RequestControlBo;
import com.xmbestone.qss.request_control.po.RequestControl;
import com.xmbestone.qss.users.bo.UsersBo;
import com.xmbestone.qss.users.po.Users;
import com.xmbestone.qss.util.QssActionSupprot;
import com.xmbestone.qss.util.ResponseUtil;
public class UsersAction extends QssActionSupprot {	
	private UsersBo usersBo;
	private Users users;
	private String msg;
	private String userid;	
	private int flag;
	private RequestControlBo requestControlBo;
	private RequestControl requestControl;
	private String nowtime;
	private String uid;
	
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
	public UsersBo getUsersBo() {
		return usersBo;
	}
	public void setUsersBo(UsersBo usersBo) {
		this.usersBo = usersBo;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	/**
	 * 登录
	 * @return
	 */
	public String login(){
		msg = null;
		String telephone = getParam("telephone").toString();
		String password = getParam("password").toString();		
		try {
			users = usersBo.findUsers(telephone, password);
			if (users.getUserid()>0) {
				userid =String.valueOf( users.getUserid());
				flag = 1;
			} else {
				flag = 0;
			}			
		} catch (Exception e) {
			flag = 0;
		}
		JSONObject json = new JSONObject();
		json.put("flag", flag);
		json.put("userid", userid);
		return ResponseUtil.returnJson(json.toString());
	}	
	/**
	 * 注册
	 * @return
	 */
	public String regist(){
		msg = null;
		String telephone = getParam("telephone").toString();
		String password = getParam("password").toString();	
		users.setTelephone(telephone);
		users.setPassword(password);
		try {
			usersBo.saveUsers(users);
			userid = String.valueOf(users.getUserid());
			msg = "success";
		} catch (Exception e){
			msg = "error";
		}
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		json.put("telephone", telephone);
		json.put("password", password);
		return ResponseUtil.returnJson(json.toString());
	}	
	/**
	 * 查看个人信息
	 */
	public String get() {
		HttpServletRequest req = this.getRequest();
		int userid = Integer.valueOf(getParam("userid").toString());
		try {
			users=getUsersBo().findById(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put("usersView", output4ajax(new Object[]{users}));
		return ResponseUtil.returnJson(json.toString());
	}
	
	
	
	/**
	 * 修改个人信息
	 * @return
	 */
	public String updateStu(){
		msg = null;
		HttpServletRequest req = this.getRequest();
		String password = getParam("password").toString();
		int userid = Integer.valueOf(getParam("userid").toString());
		try {
			users= getUsersBo().findById(userid);
			users.setPassword(password);
			usersBo.saveUsers(users);
			msg = "success";
		} catch (Exception e1) {
			msg = "error";
		}
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		return ResponseUtil.returnJson(json.toString());
	}
	
	public String requestControl(){
		msg = null;
		int userid =Integer.valueOf(getParam("userid").toString());
		String telephone =getParam("telephone").toString();
//		int userid =Integer.valueOf(getParam("userid").toString());
		try {
			users= getUsersBo().findByTelephone(telephone);
			if (users.getUserid()>0) {
				uid =String.valueOf( users.getUserid());
				//如果数据库中存在该用户     执行保存用户请求操作 
				requestControl.setMonitorUser(userid);
				requestControl.setBemonitorUser(Integer.valueOf(uid));
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				nowtime=df.format(new Date());
				requestControl.setRequestTime(nowtime);
				requestControl.setStatus("0");
				requestControlBo.saveRequestControl(requestControl);
				msg = "success";
			} else {
				msg = "error";
			}			
			flag=1;
		} catch (Exception e1) {
			flag=0;
		}
		System.out.println(msg);
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		json.put("flag", flag);
		json.put("uid", uid);
		return ResponseUtil.returnJson(json.toString());
	}
}


