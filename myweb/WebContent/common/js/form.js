var loadData = null;
var dbxmm=null;

/**
 * 用户账号密码重置
 * @param rId
 */

function mimareset(rId) {
	var id = "#"+rId;
	
	$(id).form('load',dbxmm);
}
/**
 * 表单重置
 * @param strId 表单id
 */
function formreset(strId) {
	var id = "#"+strId;
	$(id).form('load',loadData);
}/**
 * 表单清空
 * @param strId 表单id
 */
function formclean(strId) {
	var id = "#"+strId;
	$(id).form('clear');
}
var alldata = new Array();
var editcount = 0;
var pid = -1;
var id = "";
var formrowcount=0;
var formtemp=new Object();
formtemp.url_del="";
formtemp.poName="";
formtemp.tid="";
formtemp.url_save="";
formtemp.url_="";
formtemp.flag="";
formtemp.orderflag=0;
formtemp.move=0;
/**
 * 创建动态表格，返回json字符串
 * @param tid 表格id
 * @param type 动态类型(0表示既有编辑又有移动，1表示仅有移动，2表示仅有编辑)
 * @param poName 动态表格载入实体名
 * @param url 动态表格载入数据action url
 * @param url_del 删除数据action
 * @param url_save 保存数据action
 */
function dynamicTable(tid,poName,url_,url_del,url_save){
	formtemp.move=0;
	formtemp.flag="";
	formtemp.tid=tid;
	formtemp.url_=url_;
	formtemp.poName=poName;
	formtemp.url_del=url_del;
	formtemp.url_save=url_save;
	id = "#"+tid;
	alldata = new Array();
	editcount = 0;
	pid = -1;
	formtemp.orderflag=0;
	var colArray = null;
	if(poName=="terminalDeviceClassify"){
		colArray =  new Array([{field:'id',title:'分类编号',hidden:true},
		        				{field:'orderNo',title:'排序号',hidden:true},
		        				{field:'name',title:'分类名称',width:20,editor:{
		        					type:'validatebox',
		        						options:{
		        							required:true,
		        							missingMessage:"请输入设备分类名称"
		        						}
		        					}
		        				},
		        				{field:'action',title:'操作',width:40,
		        					formatter:function(value,row,index){
		        							if (row.editing){
		        								var a = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="saverow('+index+',1)"><img src="./common/img/save.png" style=\" vertical-align:middle;\"/><label style=\"height:1px;line-height:1px\">保存</label></a>';
		        								var b = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="cancelrow('+index+')"><img src="./common/img/cancel.png" style=\" vertical-align:middle;\"/><label style=\"height:1px;line-height:1px\">取消</label></a>';
		        								var c = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,0,0,'+row.id+')"><img src="./common/img/top.png" style=\" vertical-align:middle;\"/><label style=\"height:1px;line-height:1px\">置顶</label></a>';
		        								var d = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,1,0,'+row.id+')"><img src="./common/img/up.png" style=\" vertical-align:middle;\"/><label style=\"height:1px;line-height:1px\">上移</label></a>';
		        								var e = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,2,0,'+row.id+')"><img src="./common/img/down.png" style=\" vertical-align:middle;\" /><label style=\"height:1px;line-height:1px\">下移</label></a>';
		        								var f = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,3,0,'+row.id+')"><img src="./common/img/bottom.png" style=\" vertical-align:middle;\" /><label style=\"height:1px;line-height:1px\">置底</label></a>';
		        								return a+b+c+d+e+f;
		        							} else {
		        								var a = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="editrow('+index+')"><img src="./common/img/edit.png" style=\" vertical-align:middle;\"/><label style=\"height:1px;line-height:1px\">编辑</label></a>';
		        								var b = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="deleterow('+index+')"><img src="./common/img/del.png" style=\" vertical-align:middle;\"/><label style=\"height:1px;line-height:1px\">删除</label></a>';
		        								var c = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,0,1,'+row.id+')"><img src="./common/img/top.png" style=\" vertical-align:middle;\" /><label style=\"height:1px;line-height:1px\">置顶</label></a>';
		        								var d = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,1,1,'+row.id+')"><img src="./common/img/up.png" style=\" vertical-align:middle;\"/><label style=\"height:1px;line-height:1px\">上移</label></a>';
		        								var e = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,2,1,'+row.id+')"><img src="./common/img/down.png" style=\" vertical-align:middle;\" /><label style=\"height:1px;line-height:1px\">下移</label></a>';
		        								var f = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,3,1,'+row.id+')"><img src="./common/img/bottom.png" style=\" vertical-align:middle;\"/><label style=\"height:1px;line-height:1px\">置底</label></a>';
		        								return a+b+c+d+e+f;
		        							}
		        					}
		        				}
		                        ]);
	}else if(poName=="questionnairesSubject"){
		colArray =  new Array([{field:'id',title:'编号',hidden:true},
		        				{field:'qId',title:'问卷id',hidden:true},
		        				{field:'orderNo',title:'排序号',hidden:true},
		        				{field:'A',title:'A',hidden:true},
		        				{field:'B',title:'B',hidden:true},
		        				{field:'C',title:'C',hidden:true},
		        				{field:'D',title:'D',hidden:true},
		        				{field:'EOther',title:'EOther',hidden:true},
		        				{field:'title',title:'题目名称',width:40},
		        				{field:'action',title:'操作',width:40,
		        					formatter:function(value,row,index){
		        							if (row.editing){
		        								var c = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,0,0,'+row.id+')"><img src="./common/img/top.png" style=\" vertical-align:middle;\"/><label style=\"height:1px;line-height:1px\">置顶</label></a>';
		        								var d = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,1,0,'+row.id+')"><img src="./common/img/up.png" style=\" vertical-align:middle;\"/><label style=\"height:1px;line-height:1px\">上移</label></a>';
		        								var e = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,2,0,'+row.id+')"><img src="./common/img/down.png" style=\" vertical-align:middle;\" /><label style=\"height:1px;line-height:1px\">下移</label></a>';
		        								var f = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,3,0,'+row.id+')"><img src="./common/img/bottom.png" style=\" vertical-align:middle;\" /><label style=\"height:1px;line-height:1px\">置底</label></a>';
		        								return c+d+e+f;
		        							} else {
		        								var c = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,0,1,'+row.id+')"><img src="./common/img/top.png" style=\" vertical-align:middle;\" /><label style=\"height:1px;line-height:1px\">置顶</label></a>';
		        								var d = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,1,1,'+row.id+')"><img src="./common/img/up.png" style=\" vertical-align:middle;\"/><label style=\"height:1px;line-height:1px\">上移</label></a>';
		        								var e = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,2,1,'+row.id+')"><img src="./common/img/down.png" style=\" vertical-align:middle;\" /><label style=\"height:1px;line-height:1px\">下移</label></a>';
		        								var f = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,3,1,'+row.id+')"><img src="./common/img/bottom.png" style=\" vertical-align:middle;\"/><label style=\"height:1px;line-height:1px\">置底</label></a>';
		        								
		        								return c+d+e+f;
		        							}
		        					}
		        				}
		                        ]);
	}else if(poName=="informClassify"){
		colArray =  new Array([{field:'id',title:'分类编号',hidden:true},
		        				{field:'orderNo',title:'排序号',hidden:true},
		        				{field:'name',title:'分类名称',width:20,editor:{
		        					type:'validatebox',
		        						options:{
		        							required:true,
		        							missingMessage:"请输入分类名称"
		        						}
		        					}
		        				},
		        				{field:'action',title:'操作',width:40,
		        					formatter:function(value,row,index){
	        							if (row.editing){
	        								var a = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="saverow('+index+',3)"><img src="./common/img/save.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">保存</label></a>';
	        								var b = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="cancelrow('+index+')"><img src="./common/img/cancel.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">取消</label></a>';
	        								var c = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,0,0,'+row.id+')"><img src="./common/img/top.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">置顶</label></a>';
	        								var d = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,1,0,'+row.id+')"><img src="./common/img/up.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">上移</label></a>';
	        								var e = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,2,0,'+row.id+')"><img src="./common/img/down.png" style=\" vertical-align:middle;\" /><label style=\"height:13px;line-height:13px\">下移</label></a>';
	        								var f = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,3,0,'+row.id+')"><img src="./common/img/bottom.png" style=\" vertical-align:middle;\" /><label style=\"height:13px;line-height:13px\">置底</label></a>';
	        								return a+b+c+d+e+f;
	        							} else {
	        								var a = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="editrow('+index+')"><img src="./common/img/edit.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">编辑</label></a>';
	        								var b = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="deleterow('+index+')"><img src="./common/img/del.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">删除</label></a>';
	        								var c = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,0,1,'+row.id+')"><img src="./common/img/top.png" style=\" vertical-align:middle;\" /><label style=\"height:13px;line-height:13px\">置顶</label></a>';
	        								var d = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,1,1,'+row.id+')"><img src="./common/img/up.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">上移</label></a>';
	        								var e = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,2,1,'+row.id+')"><img src="./common/img/down.png" style=\" vertical-align:middle;\" /><label style=\"height:13px;line-height:13px\">下移</label></a>';
	        								var f = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,3,1,'+row.id+')"><img src="./common/img/bottom.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">置底</label></a>';
	        								return a+b+c+d+e+f;
	        							}
		        					}
		        				}
		                        ]);
	}else if(poName=="industryDynamicsClassify"){
		colArray =  new Array([{field:'id',title:'分类编号',hidden:true},
		        				{field:'orderNo',title:'排序号',hidden:true},
		        				{field:'name',title:'分类名称',width:20,editor:{
		        					type:'validatebox',
		        						options:{
		        							required:true,
		        							missingMessage:"请输入分类名称"
		        						}
		        					}
		        				},
		        				{field:'action',title:'操作',width:40,
		        					formatter:function(value,row,index){
	        							if (row.editing){
	        								var a = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="saverow('+index+',4)"><img src="./common/img/save.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">保存</label></a>';
	        								var b = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="cancelrow('+index+')"><img src="./common/img/cancel.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">取消</label></a>';
	        								var c = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,0,0,'+row.id+')"><img src="./common/img/top.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">置顶</label></a>';
	        								var d = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,1,0,'+row.id+')"><img src="./common/img/up.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">上移</label></a>';
	        								var e = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,2,0,'+row.id+')"><img src="./common/img/down.png" style=\" vertical-align:middle;\" /><label style=\"height:13px;line-height:13px\">下移</label></a>';
	        								var f = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,3,0,'+row.id+')"><img src="./common/img/bottom.png" style=\" vertical-align:middle;\" /><label style=\"height:13px;line-height:13px\">置底</label></a>';
	        								return a+b+c+d+e+f;
	        							} else {
	        								var a = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="editrow('+index+')"><img src="./common/img/edit.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">编辑</label></a>';
	        								var b = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="deleterow('+index+')"><img src="./common/img/del.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">删除</label></a>';
	        								var c = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,0,1,'+row.id+')"><img src="./common/img/top.png" style=\" vertical-align:middle;\" /><label style=\"height:13px;line-height:13px\">置顶</label></a>';
	        								var d = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,1,1,'+row.id+')"><img src="./common/img/up.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">上移</label></a>';
	        								var e = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,2,1,'+row.id+')"><img src="./common/img/down.png" style=\" vertical-align:middle;\" /><label style=\"height:13px;line-height:13px\">下移</label></a>';
	        								var f = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,3,1,'+row.id+')"><img src="./common/img/bottom.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">置底</label></a>';
	        								return a+b+c+d+e+f;
	        							}
		        					}
		        				}
		                        ]);
	}else if(poName=="workGuideClassify"){
		colArray =  new Array([{field:'id',title:'分类编号',hidden:true},
		        				{field:'orderNo',title:'排序号',hidden:true},
		        				{field:'name',title:'分类名称',width:20,editor:{
		        					type:'validatebox',
		        						options:{
		        							required:true,
		        							missingMessage:"请输入分类名称"
		        						}
		        					}
		        				},
		        				{field:'action',title:'操作',width:40,
		        					formatter:function(value,row,index){
	        							if (row.editing){
	        								var a = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="saverow('+index+',5)"><img src="./common/img/save.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">保存</label></a>';
	        								var b = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="cancelrow('+index+')"><img src="./common/img/cancel.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">取消</label></a>';
	        								var c = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,0,0,'+row.id+')"><img src="./common/img/top.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">置顶</label></a>';
	        								var d = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,1,0,'+row.id+')"><img src="./common/img/up.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">上移</label></a>';
	        								var e = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,2,0,'+row.id+')"><img src="./common/img/down.png" style=\" vertical-align:middle;\" /><label style=\"height:13px;line-height:13px\">下移</label></a>';
	        								var f = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,3,0,'+row.id+')"><img src="./common/img/bottom.png" style=\" vertical-align:middle;\" /><label style=\"height:13px;line-height:13px\">置底</label></a>';
	        								return a+b+c+d+e+f;
	        							} else {
	        								var a = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="editrow('+index+')"><img src="./common/img/edit.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">编辑</label></a>';
	        								var b = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="deleterow('+index+')"><img src="./common/img/del.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">删除</label></a>';
	        								var c = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,0,1,'+row.id+')"><img src="./common/img/top.png" style=\" vertical-align:middle;\" /><label style=\"height:13px;line-height:13px\">置顶</label></a>';
	        								var d = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,1,1,'+row.id+')"><img src="./common/img/up.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">上移</label></a>';
	        								var e = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,2,1,'+row.id+')"><img src="./common/img/down.png" style=\" vertical-align:middle;\" /><label style=\"height:13px;line-height:13px\">下移</label></a>';
	        								var f = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,3,1,'+row.id+')"><img src="./common/img/bottom.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">置底</label></a>';
	        								return a+b+c+d+e+f;
	        							}
		        					}
		        				}
		                        ]);
	}else if(poName=="salesPromotionClassify"){
		colArray =  new Array([{field:'id',title:'分类编号',hidden:true},
		        				{field:'orderNo',title:'排序号',hidden:true},
		        				{field:'name',title:'分类名称',width:20,editor:{
		        					type:'validatebox',
		        						options:{
		        							required:true,
		        							missingMessage:"请输入分类名称"
		        						}
		        					}
		        				},
		        				{field:'action',title:'操作',width:40,
		        					formatter:function(value,row,index){
	        							if (row.editing){
	        								var a = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="saverow('+index+',8)"><img src="./common/img/save.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">保存</label></a>';
	        								var b = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="cancelrow('+index+')"><img src="./common/img/cancel.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">取消</label></a>';
	        								var c = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,0,0,'+row.id+')"><img src="./common/img/top.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">置顶</label></a>';
	        								var d = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,1,0,'+row.id+')"><img src="./common/img/up.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">上移</label></a>';
	        								var e = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,2,0,'+row.id+')"><img src="./common/img/down.png" style=\" vertical-align:middle;\" /><label style=\"height:13px;line-height:13px\">下移</label></a>';
	        								var f = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,3,0,'+row.id+')"><img src="./common/img/bottom.png" style=\" vertical-align:middle;\" /><label style=\"height:13px;line-height:13px\">置底</label></a>';
	        								return a+b+c+d+e+f;
	        							} else {
	        								var a = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="editrow('+index+')"><img src="./common/img/edit.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">编辑</label></a>';
	        								var b = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="deleterow('+index+')"><img src="./common/img/del.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">删除</label></a>';
	        								var c = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,0,1,'+row.id+')"><img src="./common/img/top.png" style=\" vertical-align:middle;\" /><label style=\"height:13px;line-height:13px\">置顶</label></a>';
	        								var d = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,1,1,'+row.id+')"><img src="./common/img/up.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">上移</label></a>';
	        								var e = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,2,1,'+row.id+')"><img src="./common/img/down.png" style=\" vertical-align:middle;\" /><label style=\"height:13px;line-height:13px\">下移</label></a>';
	        								var f = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,3,1,'+row.id+')"><img src="./common/img/bottom.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">置底</label></a>';
	        								return a+b+c+d+e+f;
	        							}
		        					}
		        				}
		                        ]);
	}else if(poName=="customerType"){
		colArray =  new Array([{field:'id',title:'分类编号',width:20,editor:{
									type:'validatebox',
									options:{
										required:true,
										missingMessage:"请输入分类名称"
									}
								}},
		        				{field:'name',title:'分类名称',width:20,editor:{
		        					type:'validatebox',
		        						options:{
		        							required:true,
		        							missingMessage:"请输入分类名称"
		        						}
		        					}
		        				},
		        				{field:'action',title:'操作',width:40,
		        					formatter:function(value,row,index){
		        							if (row.editing){
		        								var a = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="saverow('+index+',10)"><img src="./common/img/save.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">保存</label></a>';
		        								var b = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="cancelrow('+index+')"><img src="./common/img/cancel.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">取消</label></a>';
		        								return a+b;
		        							} else {
		        								var a = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="editrow('+index+')"><img src="./common/img/edit.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">编辑</label></a>';
		        								var b = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="deleterow('+index+')"><img src="./common/img/del.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">删除</label></a>';
		        								return a+b;
		        							}
		        					}
		        				}
		                        ]);
	}else if(poName=="salesChannelClassify"){
		colArray =  new Array([{field:'id',title:'分类编号',hidden:true},
		        				{field:'orderNo',title:'排序号',hidden:true},
		        				{field:'name',title:'分类名称',width:20,editor:{
		        					type:'validatebox',
		        						options:{
		        							required:true,
		        							missingMessage:"请输入分类名称"
		        						}
		        					}
		        				},
		        				{field:'action',title:'操作',width:40,
		        					formatter:function(value,row,index){
		        						if (row.editing){
	        								var a = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="saverow('+index+',9)"><img src="./common/img/save.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">保存</label></a>';
	        								var b = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="cancelrow('+index+')"><img src="./common/img/cancel.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">取消</label></a>';
	        								var c = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,0,0,'+row.id+')"><img src="./common/img/top.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">置顶</label></a>';
	        								var d = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,1,0,'+row.id+')"><img src="./common/img/up.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">上移</label></a>';
	        								var e = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,2,0,'+row.id+')"><img src="./common/img/down.png" style=\" vertical-align:middle;\" /><label style=\"height:13px;line-height:13px\">下移</label></a>';
	        								var f = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,3,0,'+row.id+')"><img src="./common/img/bottom.png" style=\" vertical-align:middle;\" /><label style=\"height:13px;line-height:13px\">置底</label></a>';
	        								return a+b+c+d+e+f;
	        							} else {
	        								var a = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="editrow('+index+')"><img src="./common/img/edit.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">编辑</label></a>';
	        								var b = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="deleterow('+index+')"><img src="./common/img/del.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">删除</label></a>';
	        								var c = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,0,1,'+row.id+')"><img src="./common/img/top.png" style=\" vertical-align:middle;\" /><label style=\"height:13px;line-height:13px\">置顶</label></a>';
	        								var d = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,1,1,'+row.id+')"><img src="./common/img/up.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">上移</label></a>';
	        								var e = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,2,1,'+row.id+')"><img src="./common/img/down.png" style=\" vertical-align:middle;\" /><label style=\"height:13px;line-height:13px\">下移</label></a>';
	        								var f = '<a style="font-family: Microsoft YaHei;font: normal;font-style: normal;" onclick="move_test(event,this,3,1,'+row.id+')"><img src="./common/img/bottom.png" style=\" vertical-align:middle;\"/><label style=\"height:13px;line-height:13px\">置底</label></a>';
	        								return a+b+c+d+e+f;
	        							}
		        					}
		        				}
		                        ]);
	}
	$(id).datagrid({
			iconCls:'icon-edit',
			columns:colArray,
			url:url_,
			onLoadSuccess:function(data){
				alldata = new Array();
				var rows = $(id).datagrid('getRows');
				if(formtemp.orderflag==0){
					if(rows.length>1){
						formrowcount=rows[rows.length-1].orderNo;
						formrowcount++;
					}else{
						formrowcount=0;
					}
					formtemp.orderflag++;
				}

				
				for(var i=0; i<rows.length; i++){
					var obj = new Object();
					if(poName=="terminalDeviceClassify"){
						obj.id = rows[i].id;
						obj.name = rows[i].name;
					}else if(poName=="questionnairesSubject"){
						obj.id = rows[i].id;
						obj.title = rows[i].title;
						obj.qId = rows[i].qId;
						obj.A = rows[i].A;
						obj.B = rows[i].B;
						obj.C = rows[i].C;
						obj.D = rows[i].D;
						obj.EOther = rows[i].EOther;
					}else if(poName=="informClassify"){
						obj.id = rows[i].id;
						obj.name = rows[i].name;
					}else if(poName=="industryDynamicsClassify"){
						obj.id = rows[i].id;
						obj.name = rows[i].name;
					}else if(poName=="workGuideClassify"){
						obj.id = rows[i].id;
						obj.name = rows[i].name;
					}else if(poName=="salesPromotionClassify"){
						obj.id = rows[i].id;
						obj.name = rows[i].name;
					}
					else if(poName=="salesChannelClassify"){
						obj.id = rows[i].id;
						obj.name = rows[i].name;
					}
					else if(poName=="customerType"){
						obj.id = rows[i].id;
						obj.name = rows[i].name;
					}
					alldata.push(obj);
				}
			},
			onBeforeEdit:function(index,row){
				row.editing = true;
				$(id).datagrid('refreshRow', index);
				editcount++;
			},
			onAfterEdit:function(index,row){
				row.editing = false;
				$(id).datagrid('refreshRow', index);
				editcount--;
			},
			onCancelEdit:function(index,row){
				row.editing = false;
				$(id).datagrid('refreshRow', index);
				editcount--;
			}
			
	});
}

	/**
	 * 编辑行操作
	 * @param index 数据所在行
	 */
		function editrow(index){
			if (editcount > 0){
				$.messager.alert('警告','当前还有'+editcount+'记录正在编辑，不能编辑记录');
				return;
			}
			formtemp.flag="edit";
			$(id).datagrid('beginEdit', index);
		}
		/**
		 * 界面删除行
		 * @param index 数据所在行
		 */
		function deleterow(index){
			if (editcount > 0){
				$.messager.alert('警告','当前还有'+editcount+'记录正在编辑，不能删除记录');
				return;
			}

			$.messager.confirm('确认','是否确认删除?',function(r){
				if (r){
					
					if(formtemp.move==1){
						$.messager.confirm('确认','未保存的调节顺序操作将丢失',function(r){
							if (r){
								deleteform(index);
							}
							});
					}else{
						deleteform(index);
					}
					
				}
			});
		}
		/**
		 * 数据库删除行
		 * @param index 数据所在行
		 */
		function deleteform(index){
			var row = $(id).datagrid("getRows");
			var param="delrowid="+row[index].id;
			$.ajax({
	            type: "POST",
	            dataType : "json",
	            url:  formtemp.url_del,
	            data: param,
			    success: function(data){
			        if (data.msg =="success"){
			        	dynamicTable(formtemp.tid,formtemp.poName,formtemp.url_,formtemp.url_del,formtemp.url_save);
			        	$.messager.alert("温馨提示", "删除成功");
			        }else{
			        	 $.messager.alert("温馨提示", "数据关联，请勿删除");
			        	 
			        }    
			    } 
	        });
		}
		/**
		 * 保存数据到数据库
		 * @param index 数据所在行
		 * @param flag 模块id
		 */
		function saverow(index,flag){
			var poName="";
			if(flag==1){
				poName = "terminalDeviceClassify";
			}else if(flag==3){
				poName = "informClassify";
			}else if(flag==4){
				poName = "industryDynamicsClassify";
			}else if(flag==5){
				poName = "workGuideClassify";
			}else if(flag==8){
				poName = "salesPromotionClassify";
			}else if(flag==9){
				poName = "salesChannelClassify";
			}
			else if(flag==10){
				poName = "customerType";
			}
			$(id).datagrid('endEdit', index);
			if(formtemp.move==1){
				$.messager.confirm('确认','未保存的调节顺序操作将丢失',function(r){
					if (r){
						
						saveform(index,flag,poName);
					}else{
						$(id).datagrid('deleteRow', index);
						formtemp.flag="";
					}
					});
			}else{
				saveform(index,flag,poName);
			}
		}
		/**
		 * 保存数据到数据库
		 * @param index 记录行号
		 * @param flag
		 * @param poName 模块名
		 * @returns {Boolean}
		 */
		function saveform(index,flag,poName){
			var rows = $(id).datagrid('getRows');
			if(rows[index].name.replace(/\s/g,"")==""){
				 $.messager.alert("温馨提示", "分类名不能为空");
				 $(id).datagrid('deleteRow', index);
				 return;
			}
			var param="";
			if(formtemp.flag=="add"){
				if(poName=="terminalDeviceClassify"){
					param = "terminalDeviceClassify.name="+rows[index].name+"&terminalDeviceClassify.orderNo="+formrowcount;
				}else if(poName=="informClassify"){
					param = "informClassify.name="+rows[index].name+"&informClassify.orderNo="+formrowcount;
				}else if(poName=="industryDynamicsClassify"){
					param = "industryDynamicsClassify.name="+rows[index].name+"&industryDynamicsClassify.orderNo="+formrowcount;
				}else if(poName=="workGuideClassify"){
					param = "workGuideClassify.name="+rows[index].name+"&workGuideClassify.orderNo="+formrowcount;
				}else if(poName=="salesPromotionClassify"){
					param = "salesPromotionClassify.name="+rows[index].name+"&salesPromotionClassify.orderNo="+formrowcount;
				}
				else if(poName=="salesChannelClassify"){
					param = "salesChannelClassify.name="+rows[index].name+"&salesChannelClassify.orderNo="+formrowcount;
				}
				else if(poName=="customerType"){
					param = "customerType.name="+rows[index].name+"&customerType.id="+rows[index].id;
				}
				
			}else if(formtemp.flag=="edit"){
				if(poName=="terminalDeviceClassify"){
					param = "terminalDeviceClassify.name="+rows[index].name+"&terminalDeviceClassify.orderNo="+rows[index].orderNo+"&terminalDeviceClassify.id="+rows[index].id;
				}else if(poName=="informClassify"){
					param = "informClassify.name="+rows[index].name+"&informClassify.orderNo="+rows[index].orderNo+"&informClassify.id="+rows[index].id;
				}else if(poName=="industryDynamicsClassify"){
					param = "industryDynamicsClassify.name="+rows[index].name+"&industryDynamicsClassify.orderNo="+rows[index].orderNo+"&industryDynamicsClassify.id="+rows[index].id;
				}else if(poName=="workGuideClassify"){
					param = "workGuideClassify.name="+rows[index].name+"&workGuideClassify.orderNo="+rows[index].orderNo+"&workGuideClassify.id="+rows[index].id;
				}else if(poName=="salesPromotionClassify"){
					param = "salesPromotionClassify.name="+rows[index].name+"&salesPromotionClassify.orderNo="+rows[index].orderNo+"&salesPromotionClassify.id="+rows[index].id;
				}
				else if(poName=="salesChannelClassify"){
					param = "salesChannelClassify.name="+rows[index].name+"&salesChannelClassify.orderNo="+rows[index].orderNo+"&salesChannelClassify.id="+rows[index].id;
				}
				else if(poName=="customerType"){
					param = "customerType.name="+rows[index].name+"&customerType.id="+rows[index].id;
				}
				
			}else{
				 $.messager.alert("温馨提示", "操作失误");
				 return false;
			}
			$.ajax({
	            type: "POST",
	            dataType : "json",
	            url:  formtemp.url_save,
	            data: param,
			    success: function(data){
			        if (data.msg =="success"){
			        	dynamicTable(formtemp.tid,formtemp.poName,formtemp.url_,formtemp.url_del,formtemp.url_save);
			        	$.messager.alert("温馨提示", "保存成功");
			        }else{
			        	$.messager.alert("温馨提示", "服务器繁忙，请稍后再试");
			        	 
			        }    
			    } 
	        });
		}
		/**
		 * 取消编辑一行数据
		 * @param index 数据所在行号
		 */
		function cancelrow(index){
			if(formtemp.flag=="edit"){
				$(id).datagrid('cancelEdit', index);
				formtemp.flag="";
			}else if(formtemp.flag=="add"){
				$(id).datagrid('deleteRow', index);
				formtemp.flag="";
			}
		}
		/**
		 * 新增一条可编辑空行
		 * @param poName 模块名
		 */
		function addTableRow(poName){
			if (editcount > 0){
				$.messager.alert('警告','当前还有'+editcount+'记录正在编辑，不能增加记录');
				return;
			}
			formtemp.flag="add";
			pid = pid-1;
			var obj = new Object();
			if(poName=="terminalDeviceClassify"){
				$(id).datagrid('appendRow',{
					id:pid,
					orderNo:'',
					name:''
				});
				
				obj.id = pid;
				obj.name = "";
				obj.orderNo = "";
			}else if(poName=="questionnairesSubject"){
				$(id).datagrid('appendRow',{
					id:pid,
					orderNo:'',
					qId:'',
					title:'',
					A:'',
					B:'',
					C:'',
					D:'',
					EOther:''
				});
				
				obj.id = pid;
				obj.title = "";
				obj.orderNo = "";
				obj.qId = "";
				obj.A = "";
				obj.B = "";
				obj.C = "";
				obj.D = "";
				obj.EOther = "";
			}else if(poName=="informClassify"){
				$(id).datagrid('appendRow',{
					id:pid,
					orderNo:'',
					name:''
				});
				
				obj.id = pid;
				obj.name = "";
				obj.orderNo = "";
			}else if(poName=="industryDynamicsClassify"){
				$(id).datagrid('appendRow',{
					id:pid,
					orderNo:'',
					name:''
				});
				
				obj.id = pid;
				obj.name = "";
				obj.orderNo = "";
			}else if(poName=="workGuideClassify"){
				$(id).datagrid('appendRow',{
					id:pid,
					orderNo:'',
					name:''
				});
				
				obj.id = pid;
				obj.name = "";
				obj.orderNo = "";
			}else if(poName=="salesPromotionClassify"){
				$(id).datagrid('appendRow',{
					id:pid,
					orderNo:'',
					name:''
				});
				
				obj.id = pid;
				obj.name = "";
				obj.orderNo = "";
			}else if(poName=="salesChannelClassify"){
				$(id).datagrid('appendRow',{
					id:pid,
					orderNo:'',
					name:''
				});
				
				obj.id = pid;
				obj.name = "";
				obj.orderNo = "";
			}
			alldata.push(obj);
			var rows = $(id).datagrid('getRows');
			$(id).datagrid('beginEdit',rows.length-1);
			$(id).datagrid('scrollTo',rows.length-1);
		}
		/**
		 * 保存顺序
		 * @param poName 模块名
		 * @returns {String}
		 */
		function saveTableAll(poName){
			
			var rows = $(id).datagrid('getRows');
			var json = "[";
			$.each(alldata, function(k, n) {
				for(var i=0; i<rows.length; i++){
					if(n.id==rows[i].id){
						if(poName=="terminalDeviceClassify"){
							if(n.name !=""){
								json+="{'id':'"+n.id+"','name':'"+n.name+"','orderNo':'"+k+"'},";	
							}
						}else if(poName=="questionnairesSubject"){
							json+="{'id':'"+n.id+"','title':'"+n.title+"','qId':'"+n.qId+"','A':'"+n.A+"','B':'"+n.B+"','C':'"+n.C+"','D':'"+n.D+"','EOther':'"+n.EOther+"','orderNo':'"+k+"'},";
						}else if(poName=="informClassify"){
							if(n.name!=""){
								json+="{'id':'"+n.id+"','name':'"+n.name+"','orderNo':'"+k+"'},";
							}
							
						}else if(poName=="industryDynamicsClassify"){

							if(n.name!=""){
								json+="{'id':'"+n.id+"','name':'"+n.name+"','orderNo':'"+k+"'},";
							}
							
						}else if(poName=="workGuideClassify"){
							if(n.name!=""){
								json+="{'id':'"+n.id+"','name':'"+n.name+"','orderNo':'"+k+"'},";
							}
							
						}else if(poName=="salesPromotionClassify"){
							if(n.name!=""){
								json+="{'id':'"+n.id+"','name':'"+n.name+"','orderNo':'"+k+"'},";
							}
							
						}else if(poName=="salesChannelClassify"){
							if(n.name!=""){
								json+="{'id':'"+n.id+"','name':'"+n.name+"','orderNo':'"+k+"'},";
							}
							
						}
						break;
					}
				}
			})
			var lastIndex = json.lastIndexOf(',');
		    if (lastIndex > -1) {
		    	json = json.substring(0, lastIndex) + json.substring(lastIndex + 1, json.length);
		     }
			json +="]";
			return json;
		}
		/**
		 * 取消所有编辑
		 */
		function cancelall(){
			$(id).datagrid('rejectChanges');
			alldata = new Array();
			var rows = $(id).datagrid('getRows');
			for(var i=0; i<rows.length; i++){
				var obj = new Object();
				obj.id = rows[i].id;
				obj.name = rows[i].name;
				alldata.push(obj);
			}
		}
		/**
		 * 移动一行记录（界面）
		 * @param e
		 * @param target
		 * @param isUp 向上移动行数
		 * @param flag 判断之前操作是否全部完成
		 * @param rowId 行号
		 * @returns {Boolean}
		 */
		function move_test(e, target, isUp,flag,rowId){
			formtemp.move=1;
			if(flag==0){
				$.messager.alert('温馨提示','您当前存在编辑操作无法移动');    
				return false;
			}
			var $view = $(target).closest('div.datagrid-view');
			var index = $(target).closest('tr.datagrid-row').attr('datagrid-row-index');
			var $row = $view.find('tr[datagrid-row-index=' + index + ']');
			if (isUp==1) {
				$row.each(function() {
					$(this).prev().before($(this));
				});
				$.each(alldata, function(i, n) {
					if(rowId==n.id&&i>0){
						var obj = alldata[i-1]
						alldata[i-1] = n;
						alldata[i] = obj;
						return false;
					}
				})
			} else if (isUp==2){
				$row.each(function() {
					$(this).before($(this).next());
				});
				$.each(alldata, function(i, n) {
					if(rowId==n.id&&i<alldata.length-1){
						var obj = alldata[i+1]
						alldata[i+1] = n;
						alldata[i] = obj;
						return false;
					}
				})
				 
			} else if (isUp==0){
				var data = $(id).datagrid('getData');
				$row.each(function() {
					 for(var i=0;i<data.total;i++){
						 $(this).prev().before($(this));
					 }
				});
				var data1 = new Array();
				$.each(alldata, function(i, n) {
					if(rowId==n.id){
						data1[0] = n;
					}
				})
				$.each(alldata, function(i, n) {
					if(rowId!=n.id){
						data1.push(n);
					}
				})
				alldata = data1;
				
			} else if (isUp==3){
				$row.each(function() {
					 var data = $(id).datagrid('getData');
					 for(var i=0;i<data.total;i++){
						 $(this).before($(this).next());
					 }
				});
				
				var data2 = new Array();
				var obj = null;
				$.each(alldata, function(i, n) {
					if(rowId==n.id){
						obj = n;
					}
				})
				$.each(alldata, function(i, n) {
					if(rowId!=n.id){
						data2.push(n);
					}
				})
				data2.push(obj);
				alldata = data2;
			}
			$row.removeClass('datagrid-row-over');
			e.stopPropagation();
	}

	/**
	 * str1 字符串
	 * str2 字符串解析标识
	 * str3 字符串解析实体
	 * flag 0表示不是最后一个字段，1表示最后一个字段
	 * */	
	function doString(str1,str2,str3,flag){
		var begin = str2+'":"';
		var end = '","'+str3+".";
		var str5 = "";
		var str6 = "";
		str1 = str1.substring(1, str1.length-2);
		if(str1.indexOf(begin)>-1){
			var arry1 = new Array();
			arry1 = str1.split(begin);
			str5 = arry1[1];
		}
		if(flag==1){
			return str5;
		}
		if(str5.indexOf(end)>-1){
			var arry2 = new Array();
			arry2 = str5.split(end);
			str6 = arry2[0];
		}
		return str6;
	}
	/****
	 * 去掉文本框左右空格和换行符号
	 * @param id textArea id
	 * @returns
	 */
	function textChange(id)
	{
		var textid="#"+id;
		var val = $(textid).val();
		var resultStr=val.replace(/\ +/g,"");//去掉空格
		resultStr =resultStr.replace(/[\r\n]/g,"");//去掉回车换行
		return resultStr;

	}
	/**
	 * 唯一性校验
	 * id 校验字段id
	 * url校验action地址
	 * msg校验存在提示信息
	 */
	var checkVal = 0;
	function checkUnique(id,url,msg){
		var ids = "#"+id;
		var tip = "#"+id+"_tip";
		var url_ = url+$(ids).val();
		 $.ajax({                                    // 这里使用到Jquery的ajax方法
			type : "POST",
			dataType : "json",
			async:false,
			url :url_,    // 请求的处理数据
			success : function(data){
				 if(data.msg == "success"){
					 checkVal = 0;
					 $(tip).html("");
				 }else{
					 checkVal = 1;//存在
					 msg = "<font color='red'>*"+msg+"</font>";
					 $(tip).html(msg);
				 }
			}
	   })
	}
	/**
	 * 关闭弹窗的时候刷新带全部的combobox
	 * @param idw 窗口id
	 * @param Ajax combobox加载数据方法
	 * @param comboboxid 下拉框id（带全部）
	 * @param idc 下拉框id（不带全部）
	 */
	function refreshCombobox(idw,Ajax,comboboxid,idc){
		   $('#'+idw).window({
	            onClose: function () { 
			    	loadCombobox(Ajax,comboboxid);
					$('#'+idc).combobox('reload');  
	            }
	        });
	}
	/**
	 * 清空表格数据
	 * @param tableid 表格id
	 */
	function clearDatagrid(tableid){
		var item = $('#'+tableid).datagrid('getRows');
		if (item) {
		    for (var i = item.length - 1; i >= 0; i--) {
		        var index = $('#'+tableid).datagrid('getRowIndex', item[i]);
		        $('#'+tableid).datagrid('deleteRow', index);
		    }
		}
	 }
	
	var checkindex = "";
	/**
	 * 校验按钮功能权限
	 * @param rightId 权限id串
	 */
	function checkRights(rightId){
		checkindex = "";
		var rights = rightId.split(";");
		if(idindexlist==null){
			for(var righti = 0;righti<rights.length-1;righti++){
				checkindex+="false;";
			}
		}else{
			var flag=0;
			for(var righti=0;righti<rights.length-1;righti++){
				flag=0;
				for(var indexi=0;indexi<idindexlist.length;indexi++){
					if(idindexlist[indexi].id == rights[righti]){
						flag=1;
						break;
					}
				} 			
				if(flag==1){
					checkindex+="true;";
					flag=0;
				}else if (flag==0){
					checkindex+="false;";
				}
				
			}
		}
	}
	/**
	 * 返回是否拥有权限list
	 * @returns {String} 是否拥有权限list
	 */
	function returncheckindex(){
		return checkindex;
	}
	/**
	 * 显示拥有权限的按钮
	 * @param btnid 按钮id 
	 * @param tf 是否拥有权限list
	 */
	function checkBtnRightIndex(btnid,tf){
		var btns = btnid.split(";");
		var rightCheck = tf.split(";");
		for(var btni=0;btni<btns.length-1;btni++){
			if(rightCheck[btni]=="true"){
				$('#'+btns[btni]).show();
			}
		}
		
	}
	/**
	 * 
	 * @param listname 界面用于缓存id的控件名
	 * @param aname 标签名
	 * @param divname div名
	 * @param namename 界面用于缓存名称的控件名
	 */
	function addToDiv(listname,aname,divname,namename){
		
		var nametext = $('#'+namename).val();
		var idtext = $('#'+listname).val();
		
		var text = "";
		var namegroup = nametext.split(";");
		var idgroup = idtext.split("_");
		for(var ini=0;ini < idgroup.length-1;ini++){
			text+="<a id=\""+aname+idgroup[ini]+"\" onclick=\"removeFromDiv('"+idgroup[ini]+"','"+namegroup[ini]+"','"+namename+"','"+listname+"','"+aname+idgroup[ini]+"')\"><label style=\"height:13px;line-height:13px\">"+namegroup[ini]+"</label><img src=\"./common/img/delete.png\" style=\" vertical-align:middle;\"/></a>";
		}
		$('#'+divname).html(text);
	}
	
	/**
	 * 
	 * @param namer 名称
	 * @param id 用于保存idlist的文本框
	 * @param btnid 标签名
	 */
	function removeFromDiv(idr,namer,name,id,btnid){
		$('#'+id).val($('#'+id).val().replace(idr+"_",""));
		$('#'+name).val($('#'+name).val().replace(namer+"_",""));
		$('#'+btnid).remove();
	} 