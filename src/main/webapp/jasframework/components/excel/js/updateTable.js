
/**
 * 
 * 文件描述: Excel模板基本信息新增js。
 *
 * @author XuWeiTao
 * 创建时间： 2015-8-27 09:51:07
 * 
 */


/**
 * 
 * 方法描述：初始化页面
 * 
 */

$(document).ready(function() {	
	var templateId = getParamter("templateId");
	$("#tableName").combobox({
		url:rootPath + 'jasframework/excel/getTableNamesList.do?templateId=' + templateId,
		valueField:'TABLE_NAME',
		textField:'TABLE_NAME',
		panelHeight:200
	});	
	$("#tableName").combobox("readonly",true);
	$("#formType").combobox({
		data:formTypeList,
		valueField:'id',
		textField:'text',
		panelHeight:42
	});
	
	$("#geometryType").combobox({
		data:geometryTypeList,
		valueField:'id',
		textField:'text',
		panelHeight:86
	});
	
	$("#operateType").combobox({
		data:operateTypeList,
		valueField:'id',
		textField:'text',
		panelHeight:86
	});
		
	$("#isMainTable").combobox({
		data:isMainTableList,
		valueField:'id',
		textField:'text',
		panelHeight:42
	});
	
	var tableId=getParamter('eventid');
	$("#eventid").val(tableId);
	$.ajax({
		url : rootPath + 'jasframework/excel/getTableInfoById.do',
		type:"POST",
		data:{"tableId":tableId},
		success:function(result){
			$("#tableName").combobox("setValue",result.tableName);
			$("#tableAlias").val(result.tableAlias);
			$("#formType").combobox("setValue",result.formType);
			$("#geometryType").combobox("setValue",result.geometryType);
			$("#operateType").combobox("setValue",result.operateType);
			$("#isMainTable").combobox("setValue",result.isMainTable);
			$("#sheetIndex").val(result.sheetIndex+1);
			$("#fieldNames").val(result.fieldNames);
			$("#remark").val(result.remark);
			$("#templateId").val(result.templateId);
		},
		dataType:"json",
		async:false,
		error:function(result){
			$.messager.alert('错误', '请求数据失败！', 'info');
		}
	});
	setComboObjWidth("tableName", 0.3, "combobox");
	setComboObjWidth("formType", 0.3, "combobox");
	setComboObjWidth("geometryType", 0.3, "combobox");
	setComboObjWidth("operateType", 0.3, "combobox");
	setComboObjWidth("isMainTable", 0.3, "combobox");
});

/**
 * 方法描述：修改Excel模板基本信息
 */
function updateExcelTableInfo(){
	//表单验证
	if($('#updateExcelTableInfo').form('validate') == false){
		return false;
	}
	$.messager.confirm('提示', '是否修改?\n\t', function(r){
		if(r){
			$('#updateExcelTableInfoButton').linkbutton('disable');
			//表单提交
			$.ajax({
				url: addTokenForUrl(rootPath + "jasframework/excel/updateTableInfo.do"),
				type: 'post',
				dataType: "json",
				data: $("#updateExcelTableInfo").serializeToJson(),
				success: function(data){
					if (data) {
						$.messager.alert('提示', '修改成功！', 'info', function(){
							parent.reloadDataGrid();
							closePanel();
						});
					}else{
						$('#updateExcelTableInfoButton').linkbutton('enable');
						$.messager.alert('错误', '修改失败！', 'error');
					}
				}
			});
		}
	});
}
/**
 * 关闭窗口
 */
function closePanel(){
	parent.closeDlg('updatetable');
}
