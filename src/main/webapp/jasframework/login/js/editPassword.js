var userId = getParamter("userId");
var msg = getParameter("msg");
window.onload = function() {
	if(msg!=null){
		//如果找回密码链接过期，则询问用户是否回到登录页面，重新发起找回密码的操作
		$.messager.alert("提示", msg, "info",function(){
			window.location = 'login.htm';
		});
	}
};
/**
 * 保存修改
 */
function save() {
	$("#eventid").val(userId);
	var newPassword = $("#newPassword").val();
	var confirmPassword = $("#confirmPassword").val();
	if(newPassword==""){
		$.messager.alert('提示','请填写新密码','info');
		return false;	
	}
	if(confirmPassword==""){
		$.messager.alert('提示','请填写确认密码','info');
		return false;	
	}
	if(newPassword != confirmPassword){
		$.messager.alert('提示','两次密码输入不一致','info');
		return false;
	}
	var url = rootPath+"jasframework/login/savePassword.do";
	$('#editPassword').form('submit', {
	   	url: url,
   		dataType:"json",
	   	success: function(result){
	   		var result = eval('(' + result + ')');
			if (result.success){
				$.messager.confirm("提示",result.msg,function(r){
						if (r){
							window.location = 'login.htm';
						}
					});
			} else {
				$.messager.alert('错误',result.msg,result.error);
			}
		}
	});
}