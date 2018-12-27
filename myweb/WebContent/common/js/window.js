/** 
 * add by cgh 
 * 针对panel window dialog三个组件拖动时会超出父级元素的修正 
 * 如果父级元素的overflow属性为hidden，则修复上下左右个方向 
 * 如果父级元素的overflow属性为非hidden，则只修复上左两个方向 
 * @param left 
 * @param top 
 * @returns 
 */  
var easyuiPanelOnMove = function(left, top) {
	var browserWidth = window.screen.width*1;
	var browserHeight = 750;
	var width = $(this).panel('options').width;
	var height = $(this).panel('options').height;
	var right = left + width;
	var buttom = top + height;
	if (left < 0) {
		$(this).window('move', {
			left : 1
		});
	}
	if (top < 0) {
		$(this).window('move', {
			top : 1
		});
	}
	if(right > browserWidth){
		$(this).window('move', {
			left : browserWidth - width
		});
	}
	
	if (buttom > browserHeight) {
		t = browserHeight - height;
		if(t<0){
			t=0;
		}
		$(this).window('move', {
			top : t
		});
	}
	

};
$.fn.panel.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
