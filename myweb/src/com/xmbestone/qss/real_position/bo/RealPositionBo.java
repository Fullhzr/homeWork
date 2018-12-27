package com.xmbestone.qss.real_position.bo;

import com.xmbestone.qss.real_position.po.RealPosition;




public interface RealPositionBo {
	


	/**
	 * @param real_position
	 * @throws Exception
	 * @author 作者：stone
	 * @datatime 时间：  2015年8月31日  下午1:54:31
	 * @Description 描述：保存用户实时坐标
	 */
	public abstract void saveRealPosition(RealPosition real_position);
	
	/**
	 * @param userid
	 * @param position_time
	 * @return
	 * @throws Exception
	 * @author 作者：stone
	 * @datatime 时间：  2015年8月31日  下午2:03:31
	 * @Description 描述：查询用户点 根据 userid&position_time
	 */
	public abstract RealPosition findByPersonId(int userid) throws Exception;
	/**
	 * 查看坐标信息
	 * @throws Exception
	 */
}
