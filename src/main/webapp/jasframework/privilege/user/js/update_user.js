/** 
 * @file
 * @author  xxx
 * @version 1.0 
 * @desc  查询页面js
 * @date  2012-08-30 上午17:46:07 
 * @last modified by lizz
 * @last modified time  2017-08-17
 */

var pkfield="";
var refreshPage = "";

/**
 * @desc 初始化
 */
$(document).ready(function(){
	pkfield=getParamter("id");
	refreshPage = getParamter("refreshPage");
	getUserById();
});

/**
 * @desc 根据用户id获取用户信息
 * @returns
 */
function getUserById(){
	$.ajax({
		url : rootPath+"jasframework/privilege/user/getUserById.do",
		data :{"oid" : pkfield},
		type : 'POST',
		dataType:"json",
		success : function(data) {
			$("#savePcUser").form('load', data.data);
			var passwordExpiredDate = data.data.passwordExpiredDate;
			var date = new Date(passwordExpiredDate);
			Y = date.getFullYear() + '-';
			M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
			D = date.getDate() + ' ';
			$("#passwordExpiredDate").val(Y+M+D);
			$("#unitEventid").combotree({
//				url: rootPath+'jasframework/privilege/unit/getLeftTree.do'
				 url:rootPath+'treeView/getRootData.do',
				    queryParams:{
				    	"treeViewCode":"unitTreeUserListView"
				    },
				    panelHeight:"200",
				    onBeforeExpand:function(node,param){
				    	var tree=$("#unitEventid").combotree("tree"); 
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
				    	var tree=$("#unitEventid").combotree("tree"); 
				    	tree.tree('expandAll');
				    },
				    loader:function(param,success,error){
				    	var tree=$("#unitEventid").combotree("tree"); 
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
			setComboObjWidth("unitEventid","0.35","combotree");
			$('#unitEventid').combotree('setValue', data.data.unitEventid);
		},
		error : function(result) {
			top.showAlert('错误', '查询出错', 'info');
		}
	});
}

/**
 * @desc 加载数据初始数据
 */
/*function loadData(data){
	$("#loginName").html(data.loginName);
	$("#name").html(data.name);
	$("#phone").html(data.phone);
	$("#email").html(data.email);
	$("#passwordexpireddate").html(data.passwordExpiredDate);
	$("#description").html(data.description);
}*/

/**
 * @desc 关闭修改页面
 */
function closePanel() {
	top. closeDlg("updateiframe");
}

/**
 * @desc 修改用户
 */
function updateUser() {
	disableButtion("savebutton");
	var validateResault = $('#savePcUser').form("validate");
	if(validateResault == false){
		top.showAlert("提示", "表单验证失败", 'info');
		enableButtion("savebutton");
		return validateResault;
	}else{
		$.ajax({
			url: rootPath+"jasframework/privilege/user/updateUser.do",//调用新增接口
		    method: "post",
		    contentType: "application/json;charset=utf-8",
		    dataType: "json",
		    data:JSON.stringify($("#savePcUser").serializeToJson()),//获取表单中的json,
		    success: function(data){
				if(data.status==1){
					top.showAlert("提示", "修改成功", 'info', function() {
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
 * @desc 关闭修改页面
 */
function closeUser(){
	top. closeDlg("updateiframe");
}
		