package com.xmbestone.qss.historyview.action;
import java.util.List;

import net.sf.json.JSONObject;

import com.xmbestone.qss.historyview.bo.HistoryGroupByBo;
import com.xmbestone.qss.historyview.po.HistoryGroupBy;
import com.xmbestone.qss.util.Page;
import com.xmbestone.qss.util.QssActionSupprot;
import com.xmbestone.qss.util.ResponseUtil;



public class HistoryGroupByAction extends QssActionSupprot {
	private HistoryGroupByBo historyGroupByBo;
	private HistoryGroupBy historyGroupBy;
	private List<HistoryGroupBy> list;
	private Page<HistoryGroupBy> page;
	
	public HistoryGroupByBo getHistoryGroupByBo() {
		return historyGroupByBo;
	}
	public void setHistoryGroupByBo(HistoryGroupByBo historyGroupByBo) {
		this.historyGroupByBo = historyGroupByBo;
	}
	public HistoryGroupBy getHistoryGroupBy() {
		return historyGroupBy;
	}
	public void setHistoryGroupBy(HistoryGroupBy historyGroupBy) {
		this.historyGroupBy = historyGroupBy;
	}
	public List<HistoryGroupBy> getList() {
		return list;
	}
	public void setList(List<HistoryGroupBy> list) {
		this.list = list;
	}
	public Page<HistoryGroupBy> getPage() {
		return page;
	}
	public void setPage(Page<HistoryGroupBy> page) {
		this.page = page;
	}
//	public String list() {
//		String jsonString = "";
//		try {
//			list = getHistoryGroupByBo().listHistoryGroupBy();
//			String total = "{\"total\":\"" + list.size() + "\"}";
//			if (list.size() > 0) {
//				jsonString = output4ajax(new Object[] { list, total });
//			} else {
//				jsonString = output4ajax(new Object[] { total });
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			jsonString = output4ajax(new Object[] { FAILURE });
//		}
//		this.setResult(jsonString);
//
//		return SUCCESS;
//	}
	public String find() {
		String jsonString = "";
		try {
	       
			page = getHistoryGroupByBo().findUsers(page);
			list = page.getResult();
			for(int i=0;i<list.size();i++){
				
				System.out.println(list.get(i).getUsername());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put("list", output4ajax(new Object[] { list })); 
		json.put("pageNo", page.getPageNo());
		json.put("pagetotal", page.getTotalCount());
		String str=json.toString();
	jsonString=ResponseUtil.returnJson(str);
//		System.out.println(jsonString);
		return str;

	}

}

