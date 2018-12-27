package com.xmbestone.qss.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet.do")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Properties pro = new Properties(); 
       
    public Properties getPro() {
		return pro;
	}

	public void setPro(Properties pro) {
		this.pro = pro;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			pro.load(new FileInputStream(config.getServletContext().getRealPath("/")+"fileFolder.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String filename = request.getParameter("filename");
		
		download(filename,response);
	}
	
	private HttpServletResponse download(String filename,HttpServletResponse response) {  
		
	    try {  
	        // path是指欲下载的文件的路径。  
	    	String path = pro.getProperty("workGuideFileFolder")+filename;
	        File file = new File(path);  
	        
	        // 取得文件的后缀名。  
	        String ext = filename.substring(filename.lastIndexOf(".") + 1)  
	                .toUpperCase();  
	  
	        // 以流的形式下载文件。  
	        InputStream fis = new BufferedInputStream(new FileInputStream(path));  
	        byte[] buffer = new byte[fis.available()];  
	        fis.read(buffer);  
	        fis.close();  
	        // 清空response  
	        response.reset();  
	        // 设置response的Header  
	        response.addHeader("Content-Disposition", "attachment;filename="  
	                + new String(filename.getBytes()));  
	        response.addHeader("Content-Length", "" + file.length());  
	        OutputStream toClient = new BufferedOutputStream(  
	                response.getOutputStream());  
	        response.setContentType("application/octet-stream");  
	        toClient.write(buffer);  
	        toClient.flush();  
	        toClient.close();  
	    } catch (IOException ex) {  
	        ex.printStackTrace();  
	    }  
	    return response;  
	}  

}
