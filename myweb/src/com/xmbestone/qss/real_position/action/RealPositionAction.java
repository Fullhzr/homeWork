package com.xmbestone.qss.real_position.action;

import net.sf.json.JSONObject;

import com.xmbestone.qss.real_position.bo.RealPositionBo;
import com.xmbestone.qss.real_position.po.RealPosition;
import com.xmbestone.qss.util.QssActionSupprot;
import com.xmbestone.qss.util.ResponseUtil;

public class RealPositionAction extends QssActionSupprot{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RealPosition realPosition;
	/**
	 * @return the realPosition
	 */
	public RealPosition getRealPosition() {
		return realPosition;
	}

	/**
	 * 
	 */
	public void setRealPosition(RealPosition realPosition) {
		this.realPosition = realPosition;
	}

	/**
	 * @return the realPositionBo
	 */
	public RealPositionBo getRealPositionBo() {
		return realPositionBo;
	}

	/**
	 * 
	 */
	public void setRealPositionBo(RealPositionBo realPositionBo) {
		this.realPositionBo = realPositionBo;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the personId
	 */
	public String getPersonId() {
		return personId;
	}

	/**
	 * 
	 */
	public void setPersonId(String personId) {
		this.personId = personId;
	}

	private RealPositionBo realPositionBo;
	private String msg;
	private String personId;
	
	public String saveXY(){
		msg = null;
		String mlatitude = getParam("latitude").toString();
		String mlongitude = getParam("longitude").toString();
		String muserid = getParam("userid").toString();
		String mposition_time = getParam("position_time").toString();
		
		realPosition.setLatitude(mlatitude);
		realPosition.setLongitude(mlongitude);
		realPosition.setUserid(Integer.valueOf(muserid).intValue());
		realPosition.setPositionTime(mposition_time);
		try {
			realPositionBo.saveRealPosition(realPosition);
			personId = String.valueOf(realPosition.getPersonId());
			msg = "success";
		} catch (Exception e) {
			msg = "error";
		}
		System.out.println(msg);
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		json.put("personId", personId);
		return ResponseUtil.returnJson(json.toString());
	}
	/**
	 * 查看个人坐标dhl
	 */
	public String getXY() {
		int userid = Integer.valueOf(getParam("userid").toString());
		try {
			realPosition=getRealPositionBo().findByPersonId(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put("realPosition", output4ajax(new Object[]{realPosition}));
		return ResponseUtil.returnJson(json.toString());
	}	
}
