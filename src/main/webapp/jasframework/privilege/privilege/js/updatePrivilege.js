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
	
	$(document).ready(function(){
		getappsystem();
		$.getJSON(rootPath+'jasframework/privilege/privilege/findPrivilegeById.do', {'oid':oid},function(item, i){			
			jso=item;
			$('#updatePrivilege').form('load',jso);
			$("#appId").combobox("setValue",jso.appId);
			$("#appId").combobox("disable");
		});
		$("#privilegeType").combobox({
			onChange:function(newValue, oldValue){
				onchangmenu();
			}
		});
	});
	
	/**
	 * 描述：获取应用系统编号
	 */
	function getappsystem(){
		$.ajax({
			url:rootPath+"jasframework/privilege/application/getList.do",
			async:false,
			dataType:"json",
			success:function(data){
				$("#appId").combobox({
					data:data,
					valueField:'oid',
					textField:'appName',
					panelHeight:100
				});
			}
			
		});
	}
	
	//初始化
	var jso;
//	var eventId = this.location.search.substr(1).split("=")[1];	
	var oid = getParamter("oid");
	$.ajaxSetup ({
	   		 cache: false 
	});
	
	function reload(){
		$.getJSON(rootPath+'jasframework/privilege/privilege/findPrivilegeById.do' ,
		{'oid':oid},function(item, i){				
			jso=item;	
		
			$('#updatePrivilege').form('load',jso);				
		});		
	}
	
	/**
	 * 描述：修改权限
	 */
	function updatePrivilege(){
		disableButtion("saveButton");
		var appId=$("#appId").combobox("getValue");
		var privilegeCode = $("#privilegeCode").val();
		var flag = false ;
		//校验编号是否合法
		$.ajax({
			type: "POST",
		   	url: rootPath+"jasframework/privilege/privilege/checkPrivilegeCodeExist.do",
	   		data: {"privilegeCode":privilegeCode,"oid":oid,"appId":appId},
		   	success: function(check){
	     		if (check.success=='-1' || check.error == '-1'){
					top.showAlert('错误',check.msg,'error');
					enableButtion("savebutton");
				}else{
					flag = true;
				}
		   	},
		   	dataType:"json",
		   	async:false
		});
		if( flag ){
			var validateResault = $('#updatePrivilege').form("validate");
			var formData = $("#updatePrivilege").serializeToJson();//获取表单中的json,
			formData.appId = appId ;
			var formStringData = JSON.stringify(formData);
			var url = rootPath+'jasframework/privilege/privilege/update.do';
			$.ajax({
				url: url,//调用新增接口
			    method: "post",
			    contentType: "application/json;charset=utf-8",
			    dataType: "json",
			    data: formStringData,
			    success: function(result){
			    	top.showAlert(getLanguageValue("tip"),getLanguageValue("savesuccess"),'info',function(){
						reloadZtreeNode('queryPrivilege.htm',oid);
						closePrivilege();
					});
			    }
			 });
		}
		
		
		
//		disableButtion("saveButton");
//		var appId=$("#appId").combobox("getValue");
// 		url = rootPath+'jasframework/privilege/privilege/updatePrivilege.do?appId='+appId;
// 		var privilegeCode = $("#privilegeCode").val();
// 		if( privilegeCode.length%2 != 0 ){
//			top.showAlert('错误','权限编号只能是父权限编号+两个数字','error');
//			enableButtion("saveButton");
//		}else{
//	 		$.ajax({
//				type: "POST",
//			   	url: rootPath+"jasframework/privilege/privilege/checkPrivilegeCodeExist.do",
//		   		data: {"privilegeCode":privilegeCode,"oid":oid,"appId":appId},
//			   	success: function(check){
//		     		if (check.success=='-1'){
//						top.showAlert('错误',check.msg,'error');
//						enableButtion("saveButton");
//					} else{
//						$('#updatePrivilege').form('submit',{
//							url: url,
//							onSubmit: function(){
//								var bool=$(this).form('validate');
//								if(bool==false){
//									enableButtion("saveButton");
//								}
//								return bool;
//							},success: function(result){
//								top.showAlert(getLanguageValue("tip"),getLanguageValue("savesuccess"),'info',function(){
//								reloadZtreeNode('queryPrivilege.htm',eventId);
//								closePrivilege();
//								});
//							}
//						});
//					
//					}
//				},
//			   	dataType:"json"
//			});
//		}
	}	
	/**
	 * 描述：关闭页面
	 */
	function closePrivilege(){
		top. closeDlg("saveiframe");
	}
	
	
	
		
	function onchangmenu(){
		var changtype=$("#privilegeType").combobox("getValue");
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
			}
		}
	}