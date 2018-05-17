//初始化
var eventId = getParamter("eventId");
var appnumber = 1;
var parentPrivilegeNO = "";
$(document).ready(function(){
	parentPrivilegeNO = getParamter("privilegeNumber");
	$.getJSON(rootPath+'jasframework/privilege/privilege/findPrivilegeById.do', {'eventId':eventId},function(item, i){			
		$('#editgroups').form('load',item);
	});
	
	$("#privilegetype").combobox({
		onChange:function(newValue, oldValue){
			onchangmenu();
		}
	});
	setComboObjWidth('privilegetype',0.3,'combobox');
	setComboObjWidth('openType',0.3,'combobox');
});

/**
 * 描述：重新加载数据
 * @param shortUrl 重新加载数据的页面
 * @param elementId 权限树的id
 */
function reloadData(shortUrl, elementId){
	var fra= parent.$("iframe");
	for(var i=0; i<fra.length;i++) {
		if(fra[i].src.indexOf(shortUrl) != -1) {
			fra[i].contentWindow.selectappsystem();
		}
	}
}

/**
 * 描述：修改权限
 */
function updatePrivilege(){
	disableButtion("savebutton");
	var privilegenumber = $("#privilegenumber").val();
	if( privilegenumber.length%2 != 0 || privilegenumber.length != parentPrivilegeNO.length+2){
		top.showAlert('错误','权限编号只能是父权限编号+两个数字','error');
		enableButtion("savebutton");
		return false;
	}
	var validateResault = $('#editgroups').form("validate");
	if(validateResault == false){
		top.showAlert("提示", "表单验证失败", 'info');
		enableButtion("saveButton");
		return false;
	}	
	$.ajax({
		type: "POST",
	   	url: rootPath+"jasframework/privilege/privilege/checkPrivilegeNumberExist.do",
   		data: {"privilegenumber":privilegenumber,"eventId":eventId,"appnumber":appnumber},
	   	success: function(check){
     		if (check.error=='-1'){
				top.showAlert('错误',check.msg,'error');
				enableButtion("savebutton");
			} else{
				$.ajax({
					type: "POST",
				   	url: rootPath+'jasframework/privilege/privilege/updatePrivilege.do?appNumber='+appnumber,
			   		data: $('#editgroups').serializeToJson(),
				   	success: function(result){
				   		if(result.success){
				   			top.showAlert("提示","修改成功",'info',function(){
								reloadZtreeNode('menu_buttons_management.html',eventId);
							});
				   		}
				   	},
				   	dataType:"json"
				});
			}
		},
	   	dataType:"json"
	});
}	
/**
 * 描述：关闭页面
 */
function closePrivilege(){
	top. closeDlg("saveiframe");
}

$.ajaxSetup ({
	 cache: false 
});

function onchangmenu(){
	var changtype=$("#privilegetype").combobox("getValue");
	if(changtype==1||"1"==changtype){
		$("#openType").combobox("enable");
		$("#openType").parent().parent().show();
		$("#openType").combobox("setValue","1");
	}else{
		$("#openType").combobox("disable");
		$("#openType").parent().parent().hide();
		$("#openType").combobox("setText","");
		$("#openType").combobox("setValue","");
	}
}

function reloadZtreeNode(shortUrl,nodeId){
	var fra= parent.$("iframe");
	for(var i=0; i<fra.length;i++) {
		if(fra[i].src.indexOf(shortUrl) != -1) {
			fra[i].contentWindow.reloadZtreeNode(nodeId);
			closePrivilege();
		}
	}
}