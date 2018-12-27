package com.xmbestone.qss.request_control.bo.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.xmbestone.qss.real_position.po.RealPosition;
import com.xmbestone.qss.request_control.bo.RequestControlBo;
import com.xmbestone.qss.request_control.dao.RequestControlDao;
import com.xmbestone.qss.request_control.po.RequestControl;
import com.xmbestone.qss.users.po.Users;



public class RequestControlBoImpl extends HibernateDaoSupport implements RequestControlBo {
	private RequestControlDao requestControlDao;
	public RequestControlDao getRequestControlDao() {
		return requestControlDao;
	}
	public void setRequestControlDao(RequestControlDao requestControlDao) {
		this.requestControlDao = requestControlDao;
	}
	/* 
	 * 
	 */
	@Override
	public void saveRequestControl(RequestControl request_control)
			throws Exception {
		// TODO Auto-generated method stub
		int request_id = request_control.getRequestId();
		if(request_id<0){
			getRequestControlDao().save(request_control);
		}else{
			getRequestControlDao().update(request_control);
		}		
	}
	/* 
	 * 
	 */
	@Override
	public RequestControl findRequest(int bemonitorUser, String status) throws Exception {
		// TODO Auto-generated method stub
//		String hql = "from RequestControl  where bemonitorUser =? and status = ? order by requestId limit 1";
//		String sql ="select * from request_control where bemonitor_user='"+bemonitorUser+"'and "
//				+ "status = '" + status group by userid order by person_id  limit 1"
		String sql =" select * from request_control  where bemonitor_user ='"+bemonitorUser+"' and status = '"+status+"' order by request_id DESC limit 1 ";
//		String sql ="select * from real_position where userid='"+userid+"' group by userid order by person_id  limit 1"
//		RequestControl s = getRequestControlDao().findUnique(sql, bemonitorUser,status);
		SQLQuery query=this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		List<RequestControl> list2 = query.addEntity(RequestControl.class).list();
		RequestControl r =list2.get(0);
		return r;		
	}
	/* 
	 * 
	 */
	@Override
	public RequestControl upStatus(int request_id, String status)
			throws Exception {
		// TODO Auto-generated method stub
		String hql = "update RequestControl set status=? where requestId =? ";
//		UPDATE Person SET FirstName = 'Fred' WHERE LastName = 'Wilson' 
		RequestControl s = getRequestControlDao().findUnique(hql, request_id,status);
		return s;	
	}
	/* 
	 * 
	 */
	@Override
	public RequestControl findById(int request_id) throws Exception {
		// TODO Auto-generated method stub
		return getRequestControlDao().get(Integer.valueOf(request_id));
	}
}