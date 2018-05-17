/** 
 * @file
 * @author  xxx
 * @version 1.0 
 * @desc  查询角色js
 * @date  2012-08-30 上午17:46:07 
 * @last modified by lizz
 * @last modified time  2017-08-17
 */

var oid = "";

//初始化
$(function() {
	oid = getParamter("eventid");
	 getRoleById();
});



/**
 * @desc 描述：关闭查看页面
 */
function closeRole() {
	top. closeDlg("view");
}


/**
 * @desc 加载数据
 */
function getRoleById() {
	$.getJSON(rootPath+"jasframework/privilege/role/getRoleById.do", {
		"oid" : oid
	}, function(data) {
		loadData(data.data);
	});
}

/*function getDomainText(businessTypeCode){
	$.ajax({
		url : rootPath+"jasframework/sysdoman/getsysdoman.do?domainname=roleBusinessType",
		type : 'POST',
		dataType:"json",
		success : function(data) {
			for(var i=0;i<data.length;i++){
				if(businessTypeCode == data[i].codeid){
					$("#businessTypeCode").text(data[i].codename)
				}
			}
		},
		error : function(result) {
			top.showAlert('错误', '查询出错', 'info');
		}
	});
}*/

/**
 * @desc 显示数据
 * @param obj 数据
 */
function loadData(obj) {
	$("#name").text(obj.name);
	$("#unitName").text(obj.unitName);
	$("#roleType").text(obj.roleType);
	$("#description").text(obj.description);
	$("#businessTypeName").text(obj.businessTypeName)
}

