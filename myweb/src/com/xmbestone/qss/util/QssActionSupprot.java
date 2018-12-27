package com.xmbestone.qss.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.ActionSupport;

public class QssActionSupprot extends ActionSupport {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	protected static final String WELLDONE = "{\"msg\":\"success\"}";
	protected static final String FAILURE = "{\"msg\":\"error\"}";
	
	private String result;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getParam(String arg){
		
		this.request = ServletActionContext.getRequest();
		return request.getParameter(arg);
	}
	
    public String[] getParams(String arg){
		
		this.request = ServletActionContext.getRequest();
		return request.getParameterValues(arg);
	}
    
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
	public void output4url(Object[] o){
		try{
			this.response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			for(int i=0;i<o.length;i++){
			if(o[i] instanceof List){
			   out.print(JSONUtil.getListJSON4url((List) o[i]));
			}
			else if(o[i] instanceof String){
				out.print(o[i].toString());
			}
			else{
			   out.print(JSONUtil.getObjectJSON(o[i]));
			}
		}
			out.flush();
			out.close(); 	
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	 * 输出翻页信息专用
	 * @param o
	 * @param pageSize
	 * @param pageNo
	 */
	public void output4url(Object[] o,int pageSize,int pageNo){
		try{
			this.response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			for(int i=0;i<o.length;i++){
			if(o[i] instanceof List){
			   out.print(JSONUtil.getListJSON4url((List) o[i],pageSize,pageNo));
			}
			else if(o[i] instanceof String){
				out.print(o[i].toString());
			}
			else{
			   out.print(JSONUtil.getObjectJSON(o[i]));
			}
		}
			out.flush();
			out.close(); 	
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public String output4ajax(Object[] o){
		String s= "";
		try{
			
			for(int i=0;i<o.length;i++){
			if(o[i] instanceof List){
			    s = s+JSONUtil.getListJSON4ajax((List) o[i])+",";
			}
			else if(o[i] instanceof String){
				s = s + o[i].toString()+",";
			}
			else{
			   s =  s + JSONUtil.getObjectJSON(o[i])+",";
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
			
		   return s.substring(0,s.length()-1);
		
	}
	
	/**
	 * 翻页使用
	 * @param o
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public String output4ajax(Object[] o, int pageSize,int pageNo){
		String s= "";
		try{
			
			for(int i=0;i<o.length;i++){
			if(o[i] instanceof List){
			    s = s+JSONUtil.getListJSON4ajax((List) o[i],pageSize,pageNo)+",";
			}
			else if(o[i] instanceof String){
				s = s + o[i].toString()+",";
			}
			else{
			   s =  s + JSONUtil.getObjectJSON(o[i])+",";
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
			
		   return s.substring(0,s.length()-1);
		
	}
	
	public void output4urlTree(Object[] o){
		try{
			this.response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			for(int i=0;i<o.length;i++){
			if(o[i] instanceof List){
			   out.print(JSONUtil.getListJSON4urlTree((List) o[i]));
			}
			else if(o[i] instanceof String){
				out.print(o[i].toString());
			}
			else{
			   out.print(JSONUtil.getObjectJSON(o[i]));
			}
		}
			out.flush();
			out.close(); 	
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/*
	 重载以下方法
	 */
	public  String listAll(){return null;}
	public  String list(){return null;}
	public  String save(){return null;}
	public  String delete(){return null;}
	public  String get(){return null;}
	

}
