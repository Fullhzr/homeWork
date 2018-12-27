package com.xmbestone.qss.request_control.bo;

import com.xmbestone.qss.request_control.po.RequestControl;

public interface RequestControlBo {
	/**
	 * @param request_control
	 * @throws Exception
	 * @author 作者：stone
	 * @datatime 时间：  2015年9月8日  下午7:56:20
	 * @Description 描述：保存用户请求
	 */
	public abstract void saveRequestControl(RequestControl request_control) throws Exception;	
	
	/**
	 * @param bemonitorUser
	 * @param status
	 * @throws Exception
	 * @author 作者：stone
	 * @datatime 时间：  2015年9月8日  下午9:34:08
	 * @Description 描述：查询请求表中被定位用户的id 以及 记录中 状态字段为 “0”
	 */
	public abstract RequestControl findRequest(int bemonitorUser,String status) throws Exception;
	
	/**
	 * @param request_id
	 * @param status
	 * @return
	 * @throws Exception
	 * @author 作者：stone
	 * @datatime 时间：  2015年9月10日  上午10:43:27
	 * @Description 描述：更新用户请求状态
	 */
	public abstract RequestControl upStatus(int request_id,String status) throws Exception;
	
	/**
	 * @param request_id
	 * @return
	 * @throws Exception
	 * @author 作者：stone
	 * @datatime 时间：  2015年9月14日  上午3:48:02
	 * @Description 描述：
	 */
	public abstract RequestControl findById(int request_id) throws Exception;
}
