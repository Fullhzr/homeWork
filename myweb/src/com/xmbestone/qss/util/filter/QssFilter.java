               package com.xmbestone.qss.util.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;

/**
 * Servlet Filter implementation class QssFilter
 */
@WebFilter(dispatcherTypes = {
		DispatcherType.REQUEST, 
		DispatcherType.FORWARD
}, urlPatterns = { "/*" })
public class QssFilter implements Filter {

	private static String ROOTPATH;
	
    public static String getROOTPATH() {
		return ROOTPATH;
	}

	public static void setROOTPATH(String rOOTPATH) {
		ROOTPATH = rOOTPATH;
	}

	/**
     * Default constructor. 
     */
    public QssFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here 
		
		
		
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		 HttpSession session = httpRequest.getSession();
		String user_name = (String) session.getAttribute("userName");
		String request_URI = httpRequest.getRequestURI();
		String root = ROOTPATH.substring(0,    ROOTPATH.length()-1);
		root = "/"+root.substring(root.lastIndexOf("\\")).substring(1);
		if(isMobileDivce(httpRequest)){
			chain.doFilter(request, response);
			return;
		}
		
//		if ( !request_URI.equals(root+"/test.html")
//				&& !request_URI.equals(root+"/login.html")
//				&& !request_URI.equals(root+"/loginTest.html")
//				&& !request_URI.equals(root+"/errorLogin.html")
//				
//				&& !request_URI.equals(root+"/common/plugin/easyui/themes/icon.css")
//				&& !request_URI.equals(root+"/common/plugin/easyui/themes/gray/easyui.css")
//				&& !request_URI.equals(root+"/common/plugin/easyui/jquery.min.js")
//				&& !request_URI.equals(root+"/common/plugin/easyui/jquery.easyui.min.js")
//				&& !request_URI.equals(root+"/common/js/md5.js")
//				
//				&& !request_URI.equals(root+"/common/img/icon/Login_backimg.png")
//				&& !request_URI.equals(root+"/common/img/icon/Login_down.png")
//				&& !request_URI.equals(root+"/common/img/icon/Login_kj.png")
//				&& !request_URI.equals(root+"/common/img/icon/Login_login.png")
//				&& !request_URI.equals(root+"/common/img/icon/Login_reset.png")
//				&& !request_URI.equals(root+"/systemUser_checkSystemUser")
//				&& !request_URI.equals(root+"/ImageUpServlet.do")
//				&& !request_URI.equals(root+"/FileUploadServlet.do")
//				&& !request_URI.contains(root+"/common/img/login")
//				&& !request_URI.contains(root+"/common/img/main")
//				&& !request_URI.contains(root+"/app/")
//				&& !request_URI.contains(root+"/temp/")
//				&& user_name == null
//				&& !isMobileDivce(httpRequest)) {
//			         HttpServletResponse httpResponse = (HttpServletResponse)response;
//			         httpResponse.sendRedirect(root+"/errorLogin.html");
//			         return;
//		} else {
			chain.doFilter(request, response);
			return;
//		}
	}

	
	private boolean isMobileDivce(HttpServletRequest httpRequest){
		boolean flag = false;
		String isMobile = "";
		if(httpRequest.getHeader("isMobile")!=null){
		isMobile = (String)httpRequest.getHeader("isMobile");
		}
		if(isMobile.equals("true"))
		flag = true;
		return flag;
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		ROOTPATH = fConfig.getServletContext().getRealPath("/");
		if(!ROOTPATH.endsWith("\\")){
			ROOTPATH = ROOTPATH+"\\";
		}
		System.out.println(getROOTPATH());
	}

}
