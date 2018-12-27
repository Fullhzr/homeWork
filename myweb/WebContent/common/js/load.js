/**
 * 数据加载. 
 * @param {} div_id
 */
function wait_for_data(div_id){
	var wait="<div style='text-align:center;margin:0 auto;'><img style=\"vertical-align: middle;\" src=\"common/img/loading.gif\"> 数据加载中....</div>"
	$(div_id).html(wait);
}