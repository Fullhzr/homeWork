
 var imagArry = new Array();
/***
 * url action路径
 * id1 form表单中图片地址id
 * id2 form表单中图片关联实体id
 * id3 form表单中图片id
 * id4 form表单中图片说明id
 * id5 form表单中显示图片img id
 * formPo form表单中对应的实体
 * doMsg 操作初始化查询init,更新 图片id，删除init,添加图片id
 */
function pictureDo(url,id1,id2,id3,id4,id5,formPo,doMsg){
	id1 = "#"+id1;
	id2 = "#"+id2;
	id3 = "#"+id3;
	id4 = "#"+id4;
	id5 = "#"+id5;
	$.ajax({                                    // 这里使用到Jquery的ajax方法
		type : "POST",
		dataType : "json",
		url :url,    // 请求的处理数据
		success : function(data){
			var d = eval("["+data+"]");

			imagArry = new Array();
			if(d.length==3){
				imagArry = d[0].list;
				if(doMsg=="init"){
					if(formPo=="goodsImage"){
						$(id2).val(imagArry[0].gdsId);
					}else if(formPo=="terminalDeviceImage"){
						$(id2).val(imagArry[0].tdId);
					}else if(formPo=="personImage"){
						$(id2).val(imagArry[0].prsnId);
					}else if(formPo=="customerImage"){
						$(id2).val(imagArry[0].cId);
					}else if(formPo=="salesPromotionImage"){
						$(id2).val(imagArry[0].spId);
					}else if(formPo=="salesChannelImage"){
						$(id2).val(imagArry[0].scId);
					}else if(formPo=="compatibleGoodsInfoImage"){
						$(id2).val(imagArry[0].infoId);
					}else if(formPo=="salesChannelPoolImage"){
						$(id2).val(imagArry[0].poolId);
					}else if(formPo=="customerPoolImage"){
						$(id2).val(imagArry[0].poolId);
					}else if(formPo=="compatibleGoodsPoolImage"){
						$(id2).val(imagArry[0].cgpId);
					}
					$(id3).val(imagArry[0].id);
					$(id1).val(imagArry[0].file);
					$(id4).val(imagArry[0].notes);
					$(id5).attr("src",imagArry[0].file);
				}else{
					for(var i=0;i<imagArry.length;i++){
						var obj = imagArry[i];
						if(doMsg==obj.id){
							if(formPo=="goodsImage"){
								$(id2).val(imagArry[i].gdsId);
							}else if(formPo=="terminalDeviceImage"){
								$(id2).val(imagArry[i].tdId);
							}else if(formPo=="personImage"){
								$(id2).val(imagArry[i].prsnId);
							}else if(formPo=="customerImage"){
								$(id2).val(imagArry[0].cId);
							}else if(formPo=="salesPromotionImage"){
								$(id2).val(imagArry[0].spId);
							}else if(formPo=="salesChannelImage"){
								$(id2).val(imagArry[0].scId);
							}else if(formPo=="compatibleGoodsImage"){
								$(id2).val(imagArry[0].cgId);
							}else if(formPo=="salesChannelPoolImage"){
								$(id2).val(imagArry[0].poolId);
							}else if(formPo=="compatibleGoodsInfoImage"){
								$(id2).val(imagArry[0].infoId);
							}else if(formPo=="customerPoolImage"){
								$(id2).val(imagArry[0].poolId);
							}else if(formPo=="compatibleGoodsPoolImage"){
								$(id2).val(imagArry[0].cgpId);
							}
							$(id3).val(imagArry[i].id);
							$(id1).val(imagArry[i].file);
							$(id4).val(imagArry[i].notes);
							$(id5).attr("src",imagArry[i].file);
							return;
						}
					}
				}
			}else{
				$(id4).val("暂无图片");
				$(id5).attr("src","./common/img/004.jpg");
			}
		}
	});
}
					
					
/***
 * id1 form表单中图片地址id
 * id2 form表单中图片关联实体id
 * id3 form表单中图片id
 * id4 form表单中图片说明id
 * id5 form表单中显示图片img id
 * formPo form表单中对应的实体
 */

	function imgPre(id1,id2,id3,id4,id5,formPo){
		id1 = "#"+id1;
		id2 = "#"+id2;
		id3 = "#"+id3;
		id4 = "#"+id4;
		id5 = "#"+id5;
		var val = $(id3).val();
		for(var i=0;i<imagArry.length;i++){
			var obj = imagArry[i];
			if(val==obj.id){
				if(i==0){
					$(id5).attr("src",imagArry[i].file);
					if(formPo=="goodsImage"){
						$(id2).val(imagArry[0].gdsId);
					}else if(formPo=="terminalDeviceImage"){
						$(id2).val(imagArry[0].tdId);
					}else if(formPo=="personImage"){
						$(id2).val(imagArry[0].prsnId);
					}else if(formPo=="customerImage"){
						$(id2).val(imagArry[0].cId);
					}else if(formPo=="salesPromotionImage"){
						$(id2).val(imagArry[0].spId);
					}else if(formPo=="compatibleGoodsImage"){
						$(id2).val(imagArry[0].cgId);
					}else if(formPo=="salesChannelPoolImage"){
						$(id2).val(imagArry[0].poolId);
					}else if(formPo=="compatibleGoodsInfoImage"){
						$(id2).val(imagArry[0].infoId);
					}else if(formPo=="customerPoolImage"){
						$(id2).val(imagArry[0].poolId);
					}else if(formPo=="compatibleGoodsPoolImage"){
						$(id2).val(imagArry[0].cgpId);
					}
					$(id3).val(imagArry[0].id);
					$(id1).val(imagArry[0].file);
					$(id4).val(imagArry[0].notes);
					$.messager.alert("温馨提示","当前已是第一张");
				}else{
					$(id5).attr("src",imagArry[i-1].file);
					if(formPo=="goodsImage"){
						$(id2).val(imagArry[i-1].gdsId);
					}else if(formPo=="terminalDeviceImage"){
						$(id2).val(imagArry[i-1].tdId);
					}else if(formPo=="personImage"){
						$(id2).val(imagArry[i-1].prsnId);
					}else if(formPo=="customerImage"){
						$(id2).val(imagArry[i-1].cId);
					}else if(formPo=="salesPromotionImage"){
						$(id2).val(imagArry[i-1].spId);
					}else if(formPo=="compatibleGoodsImage"){
						$(id2).val(imagArry[i-1].cgId);
					}else if(formPo=="salesChannelPoolImage"){
						$(id2).val(imagArry[i-1].poolId);
					}else if(formPo=="compatibleGoodsInfoImage"){
						$(id2).val(imagArry[i-1].infoId);
					}else if(formPo=="customerPoolImage"){
						$(id2).val(imagArry[i-1].poolId);
					}else if(formPo=="compatibleGoodsPoolImage"){
						$(id2).val(imagArry[0].cgpId);
					}
					$(id3).val(imagArry[i-1].id);
					$(id1).val(imagArry[i-1].file);
					$(id4).val(imagArry[i-1].notes);
				}	
				break;
				
			}
		}
		
	}
	/***
	 * id1 form表单中图片地址id
	 * id2 form表单中图片关联实体id
	 * id3 form表单中图片id
	 * id4 form表单中图片说明id
	 * id5 form表单中显示图片img id
	 * formPo form表单中对应的实体
	 */
	function imgNext(id1,id2,id3,id4,id5,formPo){
		id1 = "#"+id1;
		id2 = "#"+id2;
		id3 = "#"+id3;
		id4 = "#"+id4;
		id5 = "#"+id5;
		var val = $(id3).val();
		for(var i=0;i<imagArry.length;i++){
			var obj = imagArry[i];
			if(val==obj.id){
				if(i==imagArry.length-1){
					$(id5).attr("src",imagArry[imagArry.length-1].file);
					if(formPo=="goodsImage"){
						$(id2).val(imagArry[imagArry.length-1].gdsId);
					}else if(formPo=="terminalDeviceImage"){
						$(id2).val(imagArry[imagArry.length-1].tdId);
					}else if(formPo=="personImage"){
						$(id2).val(imagArry[imagArry.length-1].prsnId);
					}else if(formPo=="customerImage"){
						$(id2).val(imagArry[imagArry.length-1].cId);
					}else if(formPo=="salesPromotionImage"){
						$(id2).val(imagArry[imagArry.length-1].spId);
					}else if(formPo=="compatibleGoodsImage"){
						$(id2).val(imagArry[imagArry.length-1].cgId);
					}else if(formPo=="salesChannelPoolImage"){
						$(id2).val(imagArry[imagArry.length-1].poolId);
					}else if(formPo=="customerPoolImage"){
						$(id2).val(imagArry[imagArry.length-1].poolId);
					}else if(formPo=="compatibleGoodsPoolImage"){
						$(id2).val(imagArry[imagArry.length-1].cgpId);
					}
					$(id3).val(imagArry[imagArry.length-1].id);
					$(id1).val(imagArry[imagArry.length-1].file);
					$(id4).val(imagArry[imagArry.length-1].notes);
					$.messager.alert("温馨提示","当前已是最后一张");
				}else{
					$(id5).attr("src",imagArry[i+1].file);
					if(formPo=="goodsImage"){
						$(id2).val(imagArry[i+1].gdsId);
					}else if(formPo=="terminalDeviceImage"){
						$(id2).val(imagArry[i+1].tdId);
					}else if(formPo=="personImage"){
						$(id2).val(imagArry[i+1].prsnId);
					}else if(formPo=="customerImage"){
						$(id2).val(imagArry[i+1].cId);
					}else if(formPo=="salesPromotionImage"){
						$(id2).val(imagArry[i+1].spId);
					}else if(formPo=="compatibleGoodsImage"){
						$(id2).val(imagArry[i+1].cgId);
					}else if(formPo=="salesChannelPoolImage"){
						$(id2).val(imagArry[i+1].poolId);
					}else if(formPo=="customerPoolImage"){
						$(id2).val(imagArry[i+1].poolId);
					}else if(formPo=="compatibleGoodsPoolImage"){
						$(id2).val(imagArry[i+1].cgpId);
					}
					$(id3).val(imagArry[i+1].id);
					$(id1).val(imagArry[i+1].file);
					$(id4).val(imagArry[i+1].notes);
				}	
				break;
				
			}
		}
	}
	/**
	   * 图片操作结束
	   */
	/***
	 * id4 form表单中图片说明id
	 * id5 form表单中显示图片img id
	 */
	function pictureInit(id4,id5){
		id4 = "#"+id4;
		id5 = "#"+id5;
		$(id4).val("暂无图片");
		$(id5).attr("src","./common/img/004.jpg");
		imagArry = new Array();
	}
	
	
	
	/***
	 * @param id1 图片id
	 * @param id2 放大显示图片id
	 * @returns id3窗口id
	 */
	function openZoom(id1,id2,id3){
		var sid1= "#"+id1;
		var sid2 = "#"+id2;
		var sid3 = "#"+id3;
		var width1 = window.screen.width*0.8;
		var height1 = window.screen.height*0.8;
		$(sid3).panel('resize',{
			width: width1,
			height: height1
		});
		var theImage = new Image();
		theImage.src = $(sid1).attr("src");
		var imageWidth = theImage.width;
		var imageHeight = theImage.height;
		var h1 = window.screen.height*0.76;
		var w1 = window.screen.width*0.78;
		var d = "0";
		
		if(height1<imageHeight){

			if(width1<imageWidth){
				//图片高宽均大于容器
				d="1";
				imageWidth = imageWidth/imageHeight*h1;
				imageHeight = h1; 
				$(sid2).css("width",imageWidth);
				$(sid2).css("height",h1);

			}else if(width1>imageWidth){
				//图片高大于容器，宽小于容器
				d="1";
				var w2 = imageWidth*h1/imageHeight;
				$(sid2).css("width",w2);
				$(sid2).css("height",h1);
			}
		}
		var top = 0;

		if(height1 >imageHeight){
			if(d=="0"){
				if(imageWidth>width1){
					//图片高小于容器，宽大于容器
					
					imageHeight = imageHeight/imageWidth*w1;
					imageWidth = w1;
					$(sid2).css("width",imageWidth);
					$(sid2).css("height",imageHeight);
					top = (height1 - imageHeight)/2;
				}else{
					//图片宽高均小于容易
					
					top = (height1 - imageHeight)/2;	
					$(sid2).css("width",imageWidth);
					$(sid2).css("height",imageHeight);
				}
				
			}
			

		}

		$(sid2).attr("src",$(sid1).attr("src"));

		if(d == "1"){
			top = h1*0.01;
		}
		if(top>0){
			$(sid2).css({
				'margin-top':top
			});
		}
		$(sid3).attr("align","center");
		$(sid3).window('center'); 
	    $(sid3).window('open');  // open a window   
	   
	}
	function closeImgWin(id){
		var sid1= "#"+id;
		
		 $(sid1).window('close');    
	}
	/***
	 * @param id1 图片文件file id
	 * @param id2 图片显示div id
	 * @param id3窗口id
	 * @param id4窗口id
	 */
	 function setImage(id1,id2,id3,id4,id5){
		   var sid1= "#"+id1;
		   var sid2 = "#"+id2;
		   var sid3 = "#"+id3;
		   var sid4 = "#"+id4;
		   var sid5 = "#"+id5;
		   $(sid5).html("");
		   $(sid4).val("");
		   var file = $(sid1);
		   file.after(file.clone().val(""));  
		   file.remove();   
		   $(sid2).html("");
		   //限制预览的类型
	       $(sid1).uploadReview({
	    	   width: 390,
	           height: 300,
	           target: sid2,//也可在元素中添加此属性
	           ext: '.jpg|.jpeg|.png|.bmp|.gif'
	       });
	       $(sid3).window('center');  
		   $(sid3).window('open');
		  
	   }      
	    /**
		 * 
		 * @param id 文件id
		 * @param tip 文件后span 显示id
		 * @returns {Number}
		 */
		function uploadPic(id,tip){
			var fid = "#"+id;
			var sid = "#"+tip;
			var val = $(fid).val();
			$(sid).html(""); 
			
			if(val.indexOf(".jpg")>0 || val.indexOf(".jpeg")>0 ||val.indexOf(".png")>0 ||val.indexOf(".bmp")>0 ||val.indexOf(".gif")>0 ||val.indexOf(".JPG")>0 || val.indexOf(".JPEG")>0 ||val.indexOf(".PNG")>0 ||val.indexOf(".BMP")>0 ||val.indexOf(".GIF")>0){
				$(sid).html(""); 
				return 1;
			 }else{
				 var msg = "<font color='red'>*图片类型错误</font>";
				 $(sid).html(msg);
				 return 0;
			 }
		}
		/**
		 * @autor 机智小梅
		 * @param id 文件id
		 * @param file 文件后span 显示id
		 */
	function checkPic(id,file){
		var jzxm="#"+id; 
		var jzxmPic = document.getElementById(file.id);
		var msg="";
		
		if(!jzxmPic.value.match(/.jpg|.jpeg|.png|.bmp|.gif|.JPG|.JPEG|.PNG|.BMP|.GIF/i)){
			msg="<font color='red'>*图片类型错误 </font>";
			$(jzxm).html(msg);
			
	     }else{
			$(jzxm).html(msg);
		}
		
	}
	 
	/**
	 * @autor lxq
	 * @param id 文件id
	 * @param file 文件后span 显示id
	 */
	function checkAudio(id,file){
	var jzxm="#"+id; 
	var jzxmAudio = document.getElementById(file.id);
	var msg="";
	
	if(!jzxmAudio.value.match(/.mp3|.MP3/i)){
		msg="<font color='red'>*请上传mp3格式音频 </font>";
		$(jzxm).html(msg);
     }else{
		$(jzxm).html(msg);
	}
	
	}
	/**
	 * @autor lxq
	 * @param id 文件id
	 * @param tip 文件后span 显示id
	 * @returns {Number}
	 */
	function uploadAudio(id,tip){
		var fid = "#"+id;
		var sid = "#"+tip;
		var val = $(fid).val();
		$(sid).html(""); 
		
		if(val.indexOf(".mp3")>0 || val.indexOf(".MP3")>0 ){
			$(sid).html(""); 
			return 1;
		 }else{
			 var msg = "<font color='red'>*类型错误，请上传mp3格式音频</font>";
			 $(sid).html(msg);
			 return 0;
		 }
	}
	/**
	 * @autor lxq
	 * @param id 文件id
	 * @param file 文件后span 显示id
	 */
	function checkuploadCusFile(id,file){
	var jzxm="#"+id; 
	var jzxmAudio = document.getElementById(file.id);
	var msg="";
	
	if(!jzxmAudio.value.match(/.xls|.XLS/i)){
		msg="<font color='red'>*请上传xls格式音频 </font>";
		$(jzxm).html(msg);
     }else{
		$(jzxm).html(msg);
	}
	
	}
	/**
	 * @autor lxq
	 * @param id 文件id
	 * @param tip 文件后span 显示id
	 * @returns {Number}
	 */
	function uploadCusFile(id,tip){
		var fid = "#"+id;
		var sid = "#"+tip;
		var val = $(fid).val();
		$(sid).html(""); 
		
		if(val.indexOf(".xls")>0 || val.indexOf(".XLS")>0||val.indexOf(".XLSX")||val.indexOf(".xlsx") ){
			$(sid).html(""); 
			return 1;
		 }else{
			 var msg = "<font color='red'>*类型错误，请上传xls、xlsx格式的文件</font>";
			 $(sid).html(msg);
			 return 0;
		 }
	}