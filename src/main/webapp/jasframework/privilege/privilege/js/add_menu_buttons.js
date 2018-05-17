
var appnumber = 1;
var parentPrivilegeNO = "";
//页面初始化
$(document).ready(function(){
//		getappsystem();
	parentPrivilegeNO = getParamter("privilegeNumber");
	$("#privilegetype").combobox({"onChange":function(){
		onchangmenu();
	}});
	$('#privilegetype').combobox("setValue","1");
	setComboObjWidth('privilegetype',0.3,'combobox');
	setComboObjWidth('childrenPrivilege',0.3,'combobox');
	setComboObjWidth('openType',0.3,'combobox');
});

/**
 * 描述：重新加载数据
 * @param shortUrl 重新加载数据的页面
 * @param elementId 权限书的id
 */
function reloadData(shortUrl, elementId){
		var fra= parent.$("iframe");
		for(var i=0; i<fra.length;i++) {
			if(fra[i].src.indexOf(shortUrl) != -1) {
				
				fra[i].contentWindow.selectappsystem();
			}
		}
	}
function reloadtree(shortUrl,parentEventid){
	var fra= parent.$("iframe");
	for(var i=0; i<fra.length;i++) {
		if(fra[i].src.indexOf(shortUrl) != -1) {
			fra[i].contentWindow.reloadchildren(parentEventid);
		}
	}
}
//	/**
//	 * 描述：获得应用系统
//	 */
//	function getappsystem(){
//		$.ajax({
//			url:rootPath+"/jasframework/appsystem/getAllAppsystem.do",
//			type:"post",
//			success:function(result){
//				$('#appnumber').combobox({ 
//					data:result, 
//					valueField:'appnumber', 
//					textField:'name' 
//					}); 
//				$('#appnumber').combobox("setValue",getParamter("appnumber"));
//				$('#appsystemnumber').val(getParamter("appnumber"));
//				setComboObjWidth('privilegetype',0.3,'combobox');
//				setComboObjWidth('appnumber',0.3,'combobox');
//				setComboObjWidth('childrenPrivilege',0.3,'combobox');
//				setComboObjWidth('openType',0.3,'combobox');
//				$('#appnumber').combobox("disable");
//			},
//			dataType:"json",
//			 	error:function(){
//			}
//		});
//	}
/**
 * 描述：保存权限
 */
function savePrivilege(){
	disableButtion("savebutton");
//	var appnumber=$("#appnumber").combobox("getValue");
	//获取父权限编号
	var eventId = getParamter("parentid");
	var privilegenumber = $("#privilegenumber").val();
	if( privilegenumber.length%2 != 0 || privilegenumber.length != parentPrivilegeNO.length+2){
		top.showAlert('错误','权限编号只能是父权限编号+两个数字','error');
		enableButtion("savebutton");
		return;
	}
	var validateResault = $('#addunit').form("validate");
	if(validateResault == false){
		top.showAlert("提示", "表单验证失败", 'info');
		enableButtion("saveButton");
		return;
	}
	//校验编号是否合法
	$.ajax({
		type: "POST",
	   	url: rootPath+"jasframework/privilege/privilege/checkPrivilegeNumberExist.do",
   		data: {"privilegenumber":privilegenumber,"appnumber":appnumber},
	   	success: function(check){
     		if (check.error=='-1'){
				top.showAlert('错误',check.msg,'error');
				enableButtion("savebutton");
			}else{
				$.ajax({
					type: "POST",
				   	url: rootPath+'jasframework/privilege/privilege/savePrivilege.do?appNumber='+appnumber+'&parentid='+ eventId,
			   		data: $('#addunit').serializeToJson(),
				   	success: function(result){
				   		if(result.success==1){
							if(result.parentEventid!=""&&result.parentEventid!=null){
								getDcumentObject("menu_buttons_management.html").addNode(result.parentEventid,$("#name").val());
								reloadchildren('menu_buttons_management.html',result.parentEventid,appnumber,"#tt");
//									parent.reloadchildren(result.parentEventid);
							}else{
								reloadZtree('menu_buttons_management.html');
							}
						}else{
							top.showAlert('提示',result.message,'info');
							enableButtion("savebutton");
						}
				   	},
				   	dataType:"json"
				});
			}
	   	},
	   	dataType:"json"
	});
}
	
function reloadchildren(shortUrl,nodeid,appnumber,elementid){
	var fra= parent.$("iframe");
	for(var i=0; i<fra.length;i++) {
		if(fra[i].src.indexOf(shortUrl) != -1) {
			fra[i].contentWindow.reloadchildren(nodeid);
			closePrivilege();
		}
	}
}
function reloadZtree(shortUrl){
	var fra= parent.$("iframe");
	for(var i=0; i<fra.length;i++) {
		if(fra[i].src.indexOf(shortUrl) != -1) {
			fra[i].contentWindow.reloadZtree();
		}
	}
	closePrivilege();
}
/**
 * 描述：关闭添加页面
 */
function closePrivilege(){
	top.closeDlg("saveiframe");
}
	
	/**
	 * 描述：扩展校验方法的规则
	 */
$.extend($.fn.validatebox.defaults.rules, {
	verifyName : {//判断分段逻辑号 是否重复
	validator : function(value) {
	var response = $.ajax({
	url :  "../groupmanagement.do?method=verifyName",
	dataType : "json",
	data : {
	groupname : value
	},
	async : false,
	cache : false,
	type : 'POST'
	}).responseText;
	var b = $.parseJSON(response);
	if(b.space== 9){
		 this.message = '名称不可输入空格！';
		 return false;
	}
	if(b.success==5){
		 return true;
	} else{
		 this.message = '名称已被使用！';
		 return false;
	}
	//return b.success;
	},
	message : null
	}
	});

function onchangmenu(){
	var changtype=$("#privilegetype").combobox("getValue");
	if(changtype==1||"1"==changtype){
		$("#childrenPrivilege").combobox("enable");
		$("#childrenPrivilege").parent().parent().show();
		$("#openType").combobox("enable");
		$("#openType").parent().parent().show();
		$("#openType").combobox("setValue","1");
	}else{
		$("#childrenPrivilege").combobox("disable");
		$("#childrenPrivilege").parent().parent().hide();
		$("#openType").combobox("setText","");
		$("#openType").combobox("setValue","");
	}
}

