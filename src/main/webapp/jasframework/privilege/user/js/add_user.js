/** 
 * @file
 * @author  xxx
 * @version 1.0 
 * @desc  新增用户js
 * @date  2012-08-30 上午17:46:07 
 * @last modified by lizz
 * @last modified time  2017-08-17
 */	

var refreshPage = "";
$(function(){
	refreshPage = getParamter("refreshPage");
});

/**
 * @desc 新增用户
 */
function save(){
	//获取部门ID
	disableButtion("saveButton");
	var validateResault = $('#savePcUser').form("validate");
	if(validateResault == false){
		top.showAlert("提示", "表单验证失败", 'info');
		enableButtion("saveButton");
		return validateResault;
	}else{
		var unitid =getParamter("unitid");
		var formData = $("#savePcUser").serializeToJson();
		formData.unitEventid=unitid;
		$.ajax({
			url: rootPath+"jasframework/privilege/user/createUser.do",//调用新增接口
		    method: "post",
		    contentType: "application/json;charset=utf-8",
		    dataType: "json",
		    data:JSON.stringify(formData),//获取表单中的json,
		    success: function(data){
				if(data.status==1){
					top.showAlert("提示", "保存成功", 'info', function() {
						//关闭弹出框
//						reloadData('queryUser.htm','#10060201');
						getDcumentObject(refreshPage+".html").$("#userDatagrid").datagrid("reload");
					    closePanel();
					});
				} else {
					top.showAlert("提示", data.data, 'error');
					enableButtion("saveButton");
				}
		    }
		 });
	}
}

/**
 * @desc 关闭添加页面
 */
function closePanel(){
	top. closeDlg("saveiframe");
}

/**
 * 单独为新气项目添加的用户名和姓名验证，主要是让 . 通过验证
 */
$.extend($.fn.validatebox.defaults.rules,{
	usernameForXinQi : {// 验证用户名
		validator : function(value) {
			return /^[a-zA-Z][a-zA-Z0-9_.]{1,25}$/i.test(value);
		},
		message : '用户名不合法（字母开头，允许2-25字符，允许字母数字下划线）'
	},
	generalForXinQi : {
		validator : function(value) {
			return /^[\u4e00-\u9fa5_a-zA-Z0-9\-\:\.\：]+$/i
					.test(value);
		},
		message : '输入值不能为空和包含其他非法字符'
	}
})

