package com.xmbestone.qss.system.bo.impl;
import java.util.List;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.xmbestone.qss.system.bo.UsersBo;
import com.xmbestone.qss.system.dao.UsersDao;
import com.xmbestone.qss.system.po.Users;



public class UsersBoImpl extends HibernateDaoSupport implements UsersBo {

	private UsersDao usersDao;
	
	
	public UsersDao getUsersDao() {
		return usersDao;
	}


	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}


	@Override
	public boolean findUsers(String username,String password)
			throws Exception {
		String sql ="select * from users where username='"+username+"' and password='"+password+"'";
		SQLQuery query=this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		List<Users> list2 = query.addEntity(Users.class).list();
		Users user =list2.get(0);
		if(user!=null){
			return true;
		}else{
			return false;
		}
	}
	
}
