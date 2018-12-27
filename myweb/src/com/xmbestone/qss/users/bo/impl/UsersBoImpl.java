package com.xmbestone.qss.users.bo.impl;
import java.util.Date;

import com.xmbestone.qss.users.bo.UsersBo;
import com.xmbestone.qss.users.dao.UsersDao;
import com.xmbestone.qss.users.po.Users;
public class UsersBoImpl implements UsersBo{	
	private UsersDao usersDao;	
	public UsersDao getUsersDao() {
		return usersDao;
	}
	public void setUsersDao(UsersDao usrsDao) {
		this.usersDao = usrsDao;
	}
	@Override
	public  void saveUsers(Users users){
		try {
			int uid = users.getUserid(); 
			if(uid<0){
				users.setRegisterTime(new Date().toLocaleString());
				getUsersDao().save(users);
			}else{
				getUsersDao().update(users);
			}	
		} catch (Exception e) {
			System.out.println(e);
		}
			
	}
	
	@Override
	public Users findUsers(String telephone, String password) throws Exception {
		String hql = "from Users su where su.telephone =? and su.password = ?";
		Users s = getUsersDao().findUnique(hql, telephone,password);
		return s;		
	}
	@Override
	public Users findById(int userid) throws Exception {
		// TODO Auto-generated method stub
		return getUsersDao().get(Integer.valueOf(userid));
	}	
	
	public Users findByTelephone(String telephone) throws Exception {
		// TODO Auto-generated method stub
		String hql = "from Users where telephone =? ";
		Users s = getUsersDao().findUnique(hql, telephone);
		return s;		
	}	
}
