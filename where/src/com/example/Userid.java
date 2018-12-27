package com.example;

public class Userid {
	private static String Userid;
	private static String Addid;
	private static String Title;

	/**
	 * @return the title
	 */
	public static String getTitle() {
		return Title;
	}

	/**
	 * @param title the title to set
	 *
	 *作者：
	 *
	 *上午4:34:42
	 */
	public static void setTitle(String title) {
		Title = title;
	}

	/**
	 * @return the addid
	 */
	public static String getAddid() {
		return Addid;
	}

	/**
	 * @param addid the addid to set
	 *
	 *作者：
	 *
	 *上午12:15:44
	 */
	public static void setAddid(String addid) {
		Addid = addid;
	}

	public static String getUserid() {
		return Userid;
	}

	public static void setUserid(String userid) {
		Userid = userid;
	}


}
