package com.xmbestone.qss.historyview.dao;

import java.util.List;


import org.hibernate.SQLQuery;

import com.xmbestone.qss.historyview.po.Historyview;
import com.xmbestone.qss.util.HibernateDao;

public class HistoryviewDao extends HibernateDao<Historyview, Integer> {

	public HistoryviewDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Historyview listHis(String userid,String username){
//		String hql = "from Student s where s.stuName ="+stuName+" and s.stuNo = "+stuNo;
//		String hql = "from Student s where s.stuName =? and s.stuNo = ?";
//		List<Student> list = find(hql,stuName,stuNo);
//		return (Student) list.get(0);
		String sql ="select * from historyview where USERNAME='李明' and USERID=1";
		SQLQuery query=this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		List<Historyview> list2 = query.addEntity(Historyview.class).list();
		Historyview s = list2.get(0);
		return s;
		
	}
	
//    public void delete(int id){
//    	delete(Integer.valueOf(id));
//    }

}

