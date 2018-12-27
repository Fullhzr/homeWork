package com.xmbestone.qss.system.bo;
public interface UsersBo {	
	//查看用户
	public abstract boolean findUsers(String username,String password)throws Exception;
}
