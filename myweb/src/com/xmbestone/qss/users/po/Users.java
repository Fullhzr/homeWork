package com.xmbestone.qss.users.po;
import com.xmbestone.qss.util.DatabaseUtils;
public class Users {
	private int userid;
	private String username;
	private String password;
	private String sex;
	private String email;
	private String telephone;
	private String age;
	private String address;
	private String registerTime;
	private String url;
	
	public  Users(){
		userid = DatabaseUtils.INVALID_INT_ID;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Users [userid=" + userid + ", username=" + username
				+ ", password=" + password + ", sex=" + sex + ", email="
				+ email + ", telephone=" + telephone + ", age=" + age
				+ ", address=" + address + ", registerTime=" + registerTime
				+ ", url=" + url + "]";
	};	
 
}
