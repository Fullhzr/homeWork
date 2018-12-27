package com.xmbestone.qss.users.bo;
import com.xmbestone.qss.users.po.Users;
/**
 * @author Administrator
 *
 *
 *上午11:16:27
 */
public interface UsersBo {	
	/**
	 * 
	 * @param user
	 * @throws Exception
	 * @date 日期: 2015年4月18日 上午12:23:17
	 * @author 作者：linxiuqing
	 * @description 描述:保存和更新用户信息
	 */
	public abstract void saveUsers(Users users) throws Exception;	
	/**
	 * 查看学生信息
	 * @param student 学生
	 * @return Student 学生
	 * @throws Exception
	 */
	public Users findUsers(String telephone,String password) throws Exception;
	
	/**
	 * @param userId
	 * @return
	 * @throws Exception
	 * @author 作者：stone
	 * @datatime 时间：  2015年9月4日  上午11:16:33
	 * @Description 描述：查询系统是否有这用户  通过id
	 */
	public Users findById(int userid) throws Exception;
	
	/**
	 * @param telephone
	 * @return
	 * @throws Exception
	 * @author 作者：stone
	 * @datatime 时间：  2015年9月4日  下午2:22:11
	 * @Description 描述：查询系统是否有这用户     通过电话号码
	 */
	public Users findByTelephone(String telephone) throws Exception;
}
