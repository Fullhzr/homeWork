package com.xmbestone.qss.users.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xmbestone.qss.users.bo.UsersBo;
import com.xmbestone.qss.users.po.Users;
import com.xmbestone.qss.util.DatabaseUtils;
import com.xmbestone.qss.util.FileUtil;
import com.xmbestone.qss.util.ImageUtil;

/**
 * Servlet implementation class ImageUpServlet
 */
@MultipartConfig
@WebServlet("/ImageUpServlet.do")
public class ImageUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Properties pro = new Properties();
	private UsersBo usersBo;
	
	public Properties getPro() {
		return pro;
	}

	public void setPro(Properties pro) {
		this.pro = pro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UsersBo getUsersBo() {
		return usersBo;
	}

	public void setUsersBo(UsersBo usersBo) {
		this.usersBo = usersBo;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageUpServlet() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// 初始化时，使得sring 能够注入Bo
		ServletContext servletContext = config.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext
				.getAutowireCapableBeanFactory();
		 autowireCapableBeanFactory.configureBean(this, "usersBo");
		// autowireCapableBeanFactory.configureBean(this, "signPictureBo");
		// autowireCapableBeanFactory.configureBean(this, "customerVisitBo");
//		autowireCapableBeanFactory.configureBean(this, "idMaker");
		try {
			pro.load(new FileInputStream(config.getServletContext()
					.getRealPath("/") + "fileFolder.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String flag = request.getParameter("flag");// 0表示图像采集1表示窜货上报2表示窜货管理图像采集
		// 获取文件上传域
		Part part = request.getPart("file");
		String filename = getFileName(part);
		String lastName = "";
		String s = "";
		if(filename.contains(".")){
			lastName = filename.substring(filename.lastIndexOf('.'));
			s = filename.split("\\.")[0];
		}
		String url = "";
		String res = "";
//		filename = s + lastName;
		if (flag.equals("changePortrait")) {
			try {
				int personId = Integer
						.valueOf(request.getParameter("personId"));
				url = saveFile("personImageFolder", filename, part, lastName,
						s);
				Users person = getUsersBo().findById(personId);
				person.setUrl(url);
				getUsersBo().saveUsers(person);
				res = url;
			} catch (Exception e1) {
				e1.printStackTrace();
				res = "";
			}
		}
		out.print(res);
		out.flush();
		out.close();
	}

	/**
	 * 从Part的Header信息中提取上传文件的文件名
	 * 
	 * @param part
	 * @return 上传文件的文件名，如果如果没有则返回null
	 */
	private String getFileName(Part part) {
		String fileNameExtractorRegex = "filename=\".+\"";
		// 获取header信息中的content-disposition，如果为文件，则可以从其中提取出文件名
		String cotentDesc = part.getHeader("content-disposition");
		String fileName = null;
		Pattern pattern = Pattern.compile(fileNameExtractorRegex);
		Matcher matcher = pattern.matcher(cotentDesc);
		if (matcher.find()) {
			fileName = matcher.group();
			fileName = fileName.substring(10, fileName.length() - 1);
		}
		return fileName;
	}

	/**
	 * 
	 * @param folder
	 * @param filename
	 * @param part
	 * @param lastName
	 * @param s
	 * @return
	 * @date 日期: 2015年8月18日 上午11:49:26
	 * @author 作者： ZhengChenHui
	 * @description 描述: 保存多种尺寸图片
	 */
	private String saveFile(String folder, String filename, Part part,
			String lastName, String s) {
		String folderPath = pro.getProperty(folder);
		String url = "";
		FileUtil.createDir(folderPath);
		try {
			part.write(folderPath + filename);
			// 每个Part对象对应于一个文件上传域，该对象提供的大量方法来访问上传文件的文件类型，大小，输入流等。并提供一个write（String
			// file）将上传文件写入服务器磁盘。
			url = DatabaseUtils.basePath + "temp/" + folder + "/" + filename;
			// 图片缩放各种比例存储开始
			FileUtil.createDir(folderPath + "/" + s);
			ImageUtil.scale(folderPath + filename, folderPath + "/" + s + "/2"
					+ lastName, 2, false);// 图片缩小二分之一
			ImageUtil.scale(folderPath + filename, folderPath + "/" + s + "/5"
					+ lastName, 5, false);// 图片缩小五分之一
			ImageUtil.scale(folderPath + filename, folderPath + "/" + s + "/10"
					+ lastName, 10, false);// 图片缩小十分之一
			// 图片缩放各种比例存储结束
		} catch (IOException e) {
			e.printStackTrace();
		}
		return url;
	}

}
