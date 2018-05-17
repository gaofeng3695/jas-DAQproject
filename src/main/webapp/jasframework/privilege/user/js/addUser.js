
	/**
	 * @desc 新增用户
	 */
	function save(){
		//获取部门ID
		disableButtion("saveButton");
		var validateResault = $('#userForm').form("validate");
		if(validateResault == false){
			top.showAlert("提示", "表单验证失败", 'info');
			enableButtion("saveButton");
			return validateResault;
		}
		var unitId =getParamter("unitId");
		var formData = $("#userForm").serializeToJson();
		formData.unitId=unitId;
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
						reloadData('queryUser.htm','#10060201');
					    closePanel();
					});
				} else {
					alert(JSON.stringify(data));
					top.showAlert("提示", data.msg, 'error');
					enableButtion("saveButton");
				}
		    }
		 });
	}
	
	/**
	 * @desc 关闭添加页面
	 */
	function closePanel(){
		top.closeDlg("addUserIframe");
	}
