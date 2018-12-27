package com.xmbestone.qss.historyview.action;
import java.util.ArrayList;
import java.util.List;








import net.sf.json.JSONObject;

import com.xmbestone.qss.historyview.bo.HistoryviewBo;
import com.xmbestone.qss.historyview.po.Historyview;
import com.xmbestone.qss.util.Page;
import com.xmbestone.qss.util.QssActionSupprot;
import com.xmbestone.qss.util.ResponseUtil;


public class HistoryviewAction extends QssActionSupprot {
	private HistoryviewBo historyviewBo;
	private Historyview historyview;
	private List<Historyview> list;
	private Page<Historyview> page;
	public Historyview getHistoryview() {
		return historyview;
	}

	public List<Historyview> getList() {
		return list;
	}

	public void setList(List<Historyview> list) {
		this.list = list;
	}

	public void setHistoryview(Historyview historyview) {
		this.historyview = historyview;
	}

	public HistoryviewBo getHistoryviewBo() {
		return historyviewBo;
	}

	public void setHistoryviewBo(HistoryviewBo historyviewBo) {
		this.historyviewBo = historyviewBo;
	}

	public Page<Historyview> getPage() {
		return page;
	}

	public void setPage(Page<Historyview> page) {
		this.page = page;
	}
	public String save() {
		try {
			getHistoryviewBo().saveHistoryview(historyview);
			output4url(new Object[] { WELLDONE });
		} catch (Exception e) {
			output4url(new Object[] { FAILURE });
		}
		return SUCCESS;
	}

	public String delete() {

		try {
			boolean bool = getHistoryviewBo().deleteHistoryview(historyview.getUserid());
			if (bool) {
				output4url(new Object[] { WELLDONE });
			} else {
				output4url(new Object[] { FAILURE });
			}

		} catch (Exception e) {
			output4url(new Object[] { FAILURE });
		}
		return SUCCESS;
	}
	public String list() {
		String jsonString = "";
		try {
			list = getHistoryviewBo().listHistoryview();
			String total = "{\"total\":\"" + list.size() + "\"}";
			if (list.size() > 0) {
				jsonString = output4ajax(new Object[] { list, total });
			} else {
				jsonString = output4ajax(new Object[] { total });
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonString = output4ajax(new Object[] { FAILURE });
		}
		this.setResult(jsonString);

		return SUCCESS;
	}
	public String find() {
		String jsonString = "";
		try {
	       
			page = getHistoryviewBo().findUsers(page);
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

