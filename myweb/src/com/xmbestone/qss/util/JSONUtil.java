package com.xmbestone.qss.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;


public class JSONUtil {

	/**
	 * List中的单个对象，不带对象名
	 * 
	 * @author vector
	 * @param o
	 * @return
	 * @throws Exception
	 */
	private static String getJSON(Object o) throws Exception {

		Field fields[] = o.getClass().getDeclaredFields();
		String s = "{\"";
		String className = o.getClass().getName();
		className = className.substring(className.lastIndexOf('.') + 1);
		String id = "";
		String type = "";
		if (!isTree(className)) {
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				Object val;
				f.setAccessible(true);// 设置些属性是可以访问的
				// System.out.println(f.getName());
				if (f.get(o) instanceof Date) {
					val = StringFormatter.getFormattedDate(f.get(o));
				} else if (f.getName().equalsIgnoreCase("sex")) {
					val = f.get(o).toString().equals("0") ? "男" : "女";
				} else if (f.getName().equalsIgnoreCase("status")) {
					val = f.get(o).toString().toString();
					if (val.equals("2")) {
						val = "已用";
					} else if (val.equals("1")) {
						val = "未用 ";
					} else if (val.equals("3")) {
						val = "报废";
					}
				}else if(f.getName().equalsIgnoreCase("processStatus")){
					val = f.get(o).toString().toString();
					if (val.equals("2")) {
						val = "已确认";
					} else if (val.equals("1")) {
						val = "未审核";
					} else if (val.equals("3")) {
						val = "已取消";
					}else if (val.equals("4")){
						val = "已完成";
					}
				} else if(f.getName().equalsIgnoreCase("paymentStatus")){
					val = f.get(o).toString().toString();
					if (val.equals("2")) {
						val = "已结款";
					} else if (val.equals("1")) {
						val = "未结款 ";
					} 
				}else if(f.getName().equalsIgnoreCase("processState")){
					val = f.get(o).toString().toString();
					if (val.equals("2")) {
						val = "未处理";
					} else if (val.equals("1")) {
						val = "已处理";
					}
				} else if (f.getName().equalsIgnoreCase("reviewStatus")) {
					val = f.get(o).toString().toString();
					if (val.equals("2")) {
						val = "审核入库";
					} else if (val.equals("1")) {
						val = "未审核";
					} else if (val.equals("3")) {
						val = "审核无效";
					}
				} else if (f.getName().equalsIgnoreCase("state")) {
					val = f.get(o).toString().toString();
					if (val.equals("2")) {
						val = "禁用";
					} else if (val.equals("1")) {
						val = "启用";
					} 
				} else if (f.getName().equalsIgnoreCase("isDone")) {
					val = f.get(o).toString().toString();
					if (val.equals("2")) {
						val = "否";
					} else if (val.equals("1")) {
						val = "是";
					} 
				} else if (f.getName().equalsIgnoreCase("isRemind")) {
						val = f.get(o).toString().toString();
						if (val.equals("2")) {
							val = "否";
						} else if (val.equals("1")) {
							val = "是";
						} 
				} else if (f.getName().equalsIgnoreCase("itemCode")) {
					val = f.get(o).toString().toString();
					if (val.equals("1")) {
						val = "客户调查";
					} else if (val.equals("2")) {
						val = "客户访谈";
					} else if (val.equals("3")) {
						val = "竞品信息采集";
					} else if (val.equals("4")) {
						val = "图像采集";
					} else if (val.equals("5")) {
						val = "人员调查";
					} else if (val.equals("6")) {
						val = "电话拜访";
					} else if (val.equals("7")) {
						val = "交办事项";
					} else if(val.equals("8")) {
						val = "竞品上报";
					}
				}else if (f.getName().equalsIgnoreCase("hasDone")) {
					val = f.get(o).toString().toString();
					if (val.equals("2")) {
						val = "否";
					} else if (val.equals("1")) {
						val = "是";
					} 
				} else if (f.getName().equalsIgnoreCase("stemfrom")) {
					val = f.get(o).toString().toString();
					if (val.equals("2")) {
						val = "领导计划外";
					} else if (val.equals("1")) {
						val = "领导计划内";
					} else if (val.equals("3")) {
						val = "个人计划内";
					} else if (val.equals("4")) {
						val = "个人计划外";
					} 
				} 
				
				else {
					if (f.get(o) != null)
						val = (f.get(o)).toString().replace("\"", "&quot;")
								.replace("\r\n", "<br>");// 得到此属性的值
					else
						val = "";
				}
				s = s + f.getName() + "\":\"" + val + "\",\"";
			}
			s = s.substring(0, s.lastIndexOf(',')) + "}";
		}else if (className.equals("CustomerClassify")) {
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];

				String treeFieldName = "";

				Object val = null;
				f.setAccessible(true);// 设置些属性是可以访问的
				val = f.get(o); // 得到此属性的值

				if (f.getName().equals("id")) {
					id = val.toString();
					treeFieldName = "id";
				} else if (f.getName().equals("name")) {
					treeFieldName = "text";
				} else if (f.getName().equals("parentId")) {
					treeFieldName = "parentId";
				}

				if (!treeFieldName.equals("")) {
					s = s + treeFieldName + "\":\"" + val + "\",\"";
				}
			}
			s = s + "state\":\"closed\",";
			s = s.substring(0, s.lastIndexOf(',')) + "}";

		}else {
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				String treeFieldName = "";
				Object val = null;
				f.setAccessible(true);// 设置些属性是可以访问的
				// System.out.println(f.getName());
				if (f.get(o) instanceof Date) {
					val = StringFormatter.getFormattedDate(f.get(o));
				} else if (f.getName().equalsIgnoreCase("sex")) {
					val = f.get(o).toString().equals("0") ? "男" : "女";
				} else {
					val = f.get(o); // 得到此属性的值
				}
				if (f.getName().equals("id")) {
					treeFieldName = "id";
				} else if (f.getName().equals("name")) {
					treeFieldName = "text";
				} else if (f.getName().equals("parentId")) {
					treeFieldName = "parentId";
				} else if (f.getName().equals("type")) {
					treeFieldName = "type";
				}
				if (!treeFieldName.equals("")) {
					s = s + treeFieldName + "\":\"" + val + "\",\"";
				}
			}
			s = s + "state\":\"closed\",";
			s = s.substring(0, s.lastIndexOf(',')) + "}";
		}
		return s;
	}

	private static String getIconCls(Object type) {
		String str = "";
		switch ((int) type) {
		case 1:
			str = "iconCls\":\"logic_iconCls\",\"";
			break;
		case 2:
			str = "iconCls\":\"area_iconCls\",\"";
			break;
		case 3:
			str = "iconCls\":\"dealer_iconCls\",\"";
			break;
		}
		return str;
	}

	/**
	 * @author wwang
	 * @param o
	 * @return
	 * @throws Exception
	 */
	private static String getJSONTree(Object o) throws Exception {

		Field fields[] = o.getClass().getDeclaredFields();
		String s = "{\"";
		String className = o.getClass().getName();
		className = className.substring(className.lastIndexOf('.') + 1);
		if (!isTree(className)) {
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				Object val;
				f.setAccessible(true);// 设置些属性是可以访问的
				// System.out.println(f.getName());
				if (f.get(o) instanceof Date) {
					val = StringFormatter.getFormattedDate(f.get(o));
				} else if (f.getName().equalsIgnoreCase("sex")) {
					val = f.get(o).toString().equals("0") ? "男" : "女";
				} else {
					val = (f.get(o)).toString().replace("\"", "&quot;")
							.replace("\r\n", "<br>"); // 得到此属性的值
				}
				s = s + f.getName() + "\":\"" + val + "\",\"";
			}
			s = s.substring(0, s.lastIndexOf(',')) + "}";
		} else {
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				String treeFieldName = "";
				Object val = null;
				f.setAccessible(true);// 设置些属性是可以访问的
				if (f.get(o) instanceof Date) {
					val = StringFormatter.getFormattedDate(f.get(o));
				} else if (f.getName().equalsIgnoreCase("sex")) {
					val = f.get(o).toString().equals("0") ? "男" : "女";
				} else {
					val = (f.get(o)).toString().replace("\"", "&quot;")
							.replace("\r\n", "<br>"); // 得到此属性的值
				}

				if (f.getName().equals("id")) {
					treeFieldName = "id";
				} else if (f.getName().equals("title")) {
					treeFieldName = "text";
				} else if (f.getName().equals("parentId")) {
					treeFieldName = "parentId";
				} else if (f.getName().equals("type")) {
					treeFieldName = "type";
				}
				if (!treeFieldName.equals("")) {
					if (treeFieldName.equals("type")) {
						s = s + treeFieldName + "\":\"" + val + "\",\""
								+ getIconCls(val);
					} else {
						s = s + treeFieldName + "\":\"" + val + "\",\"";
					}
				}
			}
			s = s + "sta\":\"closed\",";
			s = s.substring(0, s.lastIndexOf(',')) + "}";
		}
		return s;
	}

	/**
	 * 以URL方式返回List类型的JSON串
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static String getListJSON4url(List list) throws Exception {
		String s = "[";
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object o = list.get(i);
				s = s + getJSON(o) + ",";
			}
			s = s.substring(0, s.lastIndexOf(',')) + "]";
		} else
			s = s + "]";
		return s;
	}

	/**
	 * 以URL方式返回List类型的JSON串
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static String getListJSON4url(List list, int pageSize, int pageNo)
			throws Exception {
		String s = "[";
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object o = list.get(i);
				String jsonString = getJSON(o);
				int index = pageSize * (pageNo - 1) + i + 1;
				jsonString = jsonString.substring(0, jsonString.length() - 1)
						+ ",\"index\":" + "\"" + index + "\"}";
				s = s + jsonString + ",";
			}
			s = s.substring(0, s.lastIndexOf(',')) + "]";
		} else
			s = s + "]";
		return s;
	}

	public static String getListJSON4urlTree(List list) throws Exception {
		String s = "[";
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object o = list.get(i);
				s = s + getJSONTree(o) + ",";
			}
			s = s.substring(0, s.lastIndexOf(',')) + "]";
		} else {
			s = s + "]";
		}
		return s;
	}

	/**
	 * 以URL方式返回List类型的JSON串
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static String getListJSON4ajax(List list) throws Exception {
		String s = "{list:[";
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object o = list.get(i);

				s = s + getJSON(o) + ",";
			}
			s = s.substring(0, s.lastIndexOf(',')) + "]}";
		} else {
			s = s + "]}";
		}
		return s;
	}

	/**
	 * 以URL方式返回List类型的JSON串
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static String getListJSON4ajax(List list, int pageSize, int pageNo)
			throws Exception {
		String s = "{list:[";
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object o = list.get(i);
				String jsonString = getJSON(o);
				int index = pageSize * (pageNo - 1) + i + 1;
				jsonString = jsonString.substring(0, jsonString.length() - 1)
						+ ",\"index\":" + "\"" + index + "\"}";
				s = s + jsonString + ",";
			}
			s = s.substring(0, s.lastIndexOf(',')) + "]}";
		} else {
			s = s + "]}";
		}
		return s;
	}

	/**
	 * 将普通对象转换成json格式
	 * 
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static String getObjectJSON(Object o) throws Exception {

		String s = "{\"";
		if (o != null) {
			String instName = o.getClass().getName();
			instName = instName.substring(instName.lastIndexOf('.') + 1);
			String temp = instName.substring(0, 1);
			instName = temp.toLowerCase() + instName.substring(1);
			Field fields[] = o.getClass().getDeclaredFields();

			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				Object val;
				f.setAccessible(true);// 设置些属性是可以访问的
				// System.out.println(f.getName());
				if (f.get(o) instanceof Date) {
					val = StringFormatter.getFormattedDate(f.get(o));
				} else {
					val = f.get(o); // 得到此属性的值
				}
				s = s + instName + "." + f.getName() + "\":\"" + val + "\",\"";
			}
			s = s.substring(0, s.lastIndexOf(',')) + "}";
		}
		return s;
	}

	private static boolean isTree(String str) {
		boolean flag = false;
		String[] tree = { "Department", "GoodsClassify", "Questionnaires","MarketSurveyClassify",
				"QuestionnairesClassify", "CustomerClassify","SystemRole" ,"SystemRight","SystemGroup"};
		for (int i = 0; i < tree.length; i++) {
			if (str.equals(tree[i])) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * 复合对象转换
	 * 
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static String getComplexJson(Object o) throws Exception {
		String s = "";
		if (o != null) {
			s = "{\"";
			String instName = o.getClass().getName();
			instName = instName.substring(instName.lastIndexOf('.') + 1);
			String temp = instName.substring(0, 1);
			instName = temp.toLowerCase() + instName.substring(1);
			Field fields[] = o.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				Object val;
				f.setAccessible(true);// 设置些属性是可以访问的
				// System.out.println(f.getName());
				if (f.get(o) instanceof Date) {
					val = StringFormatter.getFormattedDate(f.get(o));
				} else if (f.getName().equalsIgnoreCase("sex")) {
					val = f.get(o).toString().equals("0") ? "男" : "女";
				} else if (f.get(o) instanceof List) {
					val = getComplexJSON4ajax((List) f.get(o));
				} else {
					val = f.get(o); // 得到此属性的值
				}
				s = s + instName + "." + f.getName() + "\":\"" + val + "\",\"";
			}
			s = s.substring(0, s.lastIndexOf(',')) + "}";
		}
		return s;
	}

	public static String getComplexJSON4ajax(List list) throws Exception {
		String s = "[";
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object o = list.get(i);
				s = s + getJSON(o) + ",";
			}
			s = s.substring(0, s.lastIndexOf(',')) + "]";
		}
		return s;
	}

	public static void main(String[] args) throws Exception {

		Person person = new Person();
		person.setName("sss");
		person.setAge(12);
		person.setDate(new Date());

		//
		Person p2 = new Person();
		p2.setName("sss");
		p2.setAge(12);
		p2.setDate(new Date());

		Person p3 = new Person();
		p3.setName("sss");
		p3.setAge(12);
		p3.setDate(new Date());

		List list = new ArrayList();
		list.add(person);
		list.add(p2);
		list.add(p3);

		// System.out.println(getListJSON4ajax(list));

		System.out.println(JSONArray.toList(JSONArray.fromObject(list)));
	}

}

class Person {

	private String name;
	private int age;
	private Date date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
