//取到父部门id
var eventID =getParamter("parentid");;
//初始化
$(function(){
	//初始化部门树
/*$("#parentEventid").combotree({
	url: rootPath+'jasframework/privilege/unit/getLeftTree.do',
	panelHeight:"200"
});*/
	$("#parentEventid").combotree({
	    url:rootPath+'treeView/getRootData.do',
	    queryParams:{
	    	"treeViewCode":"unitTreeUserListView"
	    },
	    panelHeight:"200",
	    onBeforeExpand:function(node,param){
	    	var tree=$("#parentEventid").combotree("tree"); 
	    	//重置请求参数
	    	tree.tree('options').queryParams = {
	        	"parentTreeNodeId":node.attributes.treeNodeId,
	        	"parentOid":node.id
	        }
	    	//重置请求地址
	    	tree.tree('options').url = rootPath+'treeView/getChildData.do';
	        return true;
	    },
	    onLoadSuccess:function(node,data){
	    	var tree=$("#parentEventid").combotree("tree"); 
	    	tree.tree('expandAll');
	    },
	    loader:function(param,success,error){
	    	var tree=$("#parentEventid").combotree("tree"); 
	    	var opts = tree.tree('options');
	    	$.ajax({  
	            type : opts.method,  
	            url : opts.url,  
	            dataType : 'json',  
	            contentType : 'application/json;charset=utf-8', // 设置请求头信息  
	            data : JSON.stringify(param),  
	            success : function(data) { 
	                success(data.rows);                  
	            }  
	        });  
	    }
	});
	//设置部门初始值
	$('#parentEventid').combotree('setValue', eventID);
	
	loadSelect();
});



function loadSelect(){
	$('#unitOrgType').combobox({
//	    url:rootPath+'jasframework/sysdoman/getsysdoman.do?domainname=unit_org_type_jg',
	    valueField:'codeid',
	    textField:'codename',
	    onLoadSuccess:function(data){
//	    	$('#unitOrgType').combobox('setValue', data[0].codeid);
	    }
	});
	
	$("#unitType").combobox({
		onSelect:function(rec){
	    	var url="";
	    	if(rec.value == "1"){//部门对应项目组织机构类型
	    		url=rootPath+'jasframework/sysdoman/getsysdoman.do?domainname=unit_org_type_jg';
	    	}else{//单位对应项目组织机构类型
	    		url=rootPath+'jasframework/sysdoman/getsysdoman.do?domainname=unit_org_type_dw';
	    	}
	    	$('#unitOrgType').combobox('reload', url);
	    }
	});
}
	
/**
 * 描述：保存部门的方法
 */
function saveUnit(){
	var url = rootPath+'jasframework/privilege/unit/saveUnit.do';
	var name = $("#name").val();
	var unitNum = $("#unitNum").val();
	var unitCode = $("#unitCode").val();
	var parentEventId = $("#parentEventid").combotree("getValue");
	disableButtion("savebutton");
	var validateResault = $('#addunit').form("validate");
	if(validateResault == false){
		top.showAlert("提示", "表单验证失败", 'info');
		enableButtion("saveButton");
		return validateResault;
	}else{
		$.ajax({
			type: "POST",
		   	url: rootPath+"jasframework/privilege/unit/checkUnitExist.do",
	   		data: {"parentEventId":parentEventId,"name":name,"unitNum":unitNum,'unitCode':unitCode},
		   	success: function(check){
	     		if (check.error=='-1'){
					top.showAlert('错误','编号或名称重复!','error');
					enableButtion("savebutton");
				} else{
					$.ajax({
						type: "POST",
					   	url: rootPath+'jasframework/privilege/unit/saveUnit.do',
				   		data: $("#addunit").serializeToJson(),
				   		success:function(result){
							if (result.success){
								top.showAlert("提示","保存成功",'ok',function(){
									var obj = getDcumentObject("tree_tab.html?treeViewCode=unitTreeUserListView");
									obj.addNewNode(parentEventId,result.oid,name);
									obj.reloadNode(parentEventId);
									closeUnit();
								});
							} else {
								top.showAlert("错误","保存失败!",'error');
								enableButtion("savebutton");
							}
				   		}
					});
				}
		   	},
		   	dataType:"json"
		});
	}
}
	
/**
 * 描述：关闭添加窗口
 */
function closeUnit(){
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
		 this.message =getLanguageValue("unit.namecannotspaces");
		 return false;
	}
	if(b.success==5){
		 return true;
	} else{
		 this.message =getLanguageValue('unit.namerepeated');
		 return false;
	}
	},
	message : null
	}
});