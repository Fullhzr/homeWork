/**
 *名称：
 *
 *作者：
 * 
 *下午7:49:45
 */
package com.xmbestone.qss.real_position.bo.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.xmbestone.qss.real_position.bo.RealPositionBo;
import com.xmbestone.qss.real_position.dao.RealPositionDao;
import com.xmbestone.qss.real_position.po.RealPosition;
import com.xmbestone.qss.system.po.Users;
/**
 * @author Administrator
 *
 *
 *下午7:49:45
 */



public  class RealPositionBoImpl extends HibernateDaoSupport  implements RealPositionBo {

	private RealPositionDao realPositionDao;
	
	/**
	 * @return the realPositionDao
	 */
	public RealPositionDao getRealPositionDao() {
		return realPositionDao;
	}

	/**
	 * 
	 */
	public void setRealPositionDao(RealPositionDao realPositionDao) {
		this.realPositionDao = realPositionDao;
	}

	@Override
	public void saveRealPosition(RealPosition real_position){
		// TODO Auto-generated method stub
		int pos_id = real_position.getPersonId();
		try {
			if(pos_id<0){
				getRealPositionDao().save(real_position);
			}else{
				getRealPositionDao().update(real_position);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
//	
//	@Override
//	public RealPosition findByPersonId(int userid) throws Exception {
//		// TODO Auto-generated method stub
//		return getRealPositionDao().get(Integer.valueOf(userid));
//	} 

	@Override
	public RealPosition findByPersonId(int userid) throws Exception {
		String sql ="select * from real_position where userid='"+userid+"' group by userid order by person_id  limit 1";
		SQLQuery query=this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		List<RealPosition> list2 = query.addEntity(RealPosition.class).list();
		RealPosition r =list2.get(0);
		return r;
		
	}	
//	@Override
//	public RealPosition findByPersonId(int personId) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
// 
}

