/** 
 * @file
 * @author  lujingrui
 * @version 1.0 
 * @desc  新增修改角色
 * @date  2017-09-20 上午17:46:07 
 * @last modified by lujingrui
 * @last modified time  2017-09-20
 */

var oid = "";
var projectOid = "";
var flag = "";
/**
 * @desc 初始化页面
 */
$(function() {
	oid = getParamter("eventid");
	projectOid = getParamter("projectOid");
	flag = getParamter("flag");
	$("#projectOid").val(projectOid);
	if (!isNull(oid)) {
		loadData();
	}else{
		loadSelect();
	}
});

function loadSelect(jsonData){
	$('#dataFilterRegulationCode').combobox({
	    url:rootPath+'jasframework/sysdoman/getsysdoman.do?domainname=dataFilterRegulation',
	    valueField:'codeid',
	    textField:'codename',
	    onLoadSuccess:function(data){
	    	if (!isNull(oid)) {
	    		$('#dataFilterRegulationCode').combobox('setValue', jsonData.dataFilterRegulationCode);
	    	}else{
	    		$('#dataFilterRegulationCode').combobox('setValue', data[0].codeid);
	    	}
	    }
	});
	/*$('#businessTypeCode').combobox({
	    url:rootPath+'jasframework/sysdoman/getsysdoman.do?domainname=roleBusinessType',
	    valueField:'codeid',
	    textField:'codename',
	    onLoadSuccess:function(data){
	    	if (!isNull(oid)) {
	    		$('#businessTypeCode').combobox('setValue', jsonData.businessTypeCode);
	    	}else{
	    		$('#businessTypeCode').combobox('setValue', data[0].codeid);
	    	}
	    }
	});*/
}

/**
 * #desc 描述：初始化数据
 */
function loadData() {
	//执行修改时数据回填
	if (!isNull(oid)) {
		$.ajax({
			url : rootPath+"jasframework/privilege/role/getRoleById.do",
			data :{"oid" : oid},
			type : 'POST',
			dataType:"json",
			success : function(data) {
				var jsonData = data.data;
				$('#roleForm').form("load",jsonData);
				loadSelect(jsonData);
			},
			error : function(result) {
				top.showAlert('错误', '查询出错', 'info');
			}
		});
	} 
}

/**
 * @desc 关闭添加页面
 */
function closePanel() {
	top. closeDlg("saveiframe");
}

/**
 * @desc 添加角色
 */
function save() {
	disableButtion("saveButton");
	var url = "";
	if(oid != "" && oid != null){
		url = rootPath+"jasframework/privilege/role/updateRole.do";
	}else{
		url = rootPath+"jasframework/privilege/role/createRole.do";
	}
	
	var validateResault = $('#roleForm').form("validate");
	if(validateResault == false){
		top.showAlert("提示", "表单验证失败", 'info');
		enableButtion("saveButton");
		return validateResault;
	}else{
		$.ajax({
			url: url,//调用新增接口
		    method: "post",
		    contentType: "application/json;charset=utf-8",
		    dataType: "json",
		    data:JSON.stringify($("#roleForm").serializeToJson()),//获取表单中的json,
		    success: function(data){
				if(data.status==1){
					top.showAlert("提示", "保存成功", 'info', function() {
						//重新加载表格数据
						if(flag == 1){
							getDcumentObject('query_project_role.html?flag=1').$("#dg").datagrid("reload");
						}else{
							getDcumentObject('query_project_role.html?flag=2').$("#dg").datagrid("reload");
						}
						//关闭弹出框
					    closePanel();
					});
				} else {
					if(data.data){
						top.showAlert("提示", data.data, 'error');
					}else{
						top.showAlert("提示", "角色名重复", 'error');
					}
					enableButtion("saveButton");
				}
		    }
		 });
	}
}
