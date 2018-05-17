/** 
 * @file
 * @author  lujingrui
 * @version 1.0 
 * @desc  角色权限分配
 * @date  2017-09-20 上午17:46:07 
 * @last modified by lujingrui
 * @last modified time  2017-09-20
 */

var oid = "";

//树配置
var setting = {
	check: {
		enable: true
	}, 
	data: {
		simpleData: {
			enable: true
		}
	},
	treeNodeKey : "oid"
};

//初始化
$(function() {
	oid = getParamter("eventid");
	loadTree();
});

/**
 * 加载功能树
 * @returns
 */
function loadTree(){
	var url = rootPath+"jasframework/privilege/privilege/getAllConfig.do?roleid="+oid+"&appNumber=1";
	$('#privilegeEventId').tree( {
		url : url,
		checkbox:true,
		onBeforeLoad:function(node,param){
			$('#beforeShow').dialog({
				title: '',
			    width: 400,
			    height: 150,
			    closed: false,
			    cache: false
			});
		},
		onLoadSuccess:function(node,data) {
			$('#beforeShow').dialog('close')
		},
		onClick : function(node) {
		}
	});
}

/**
 * 描述：保存设置
 */
function saveConfig() {
	disableButtion("savebutton");
	var nodesChecked = $('#privilegeEventId').tree('getChecked');	// get checked nodes
	//var nodes2 = $('#privilegeEventId').tree('getChecked', 'unchecked');	// get unchecked nodes
	var nodePar = $("#privilegeEventId").tree("getChecked", "indeterminate");	// get indeterminate nodes
	var privilegeEventIds = '';
	for(var i=0; i<nodesChecked.length; i++){
		if (privilegeEventIds != '') privilegeEventIds += ',';
		privilegeEventIds += nodesChecked[i].id;
	}
	for(var i=0; i<nodePar.length; i++){
		if (privilegeEventIds != '') privilegeEventIds += ',';
		privilegeEventIds += nodePar[i].id;
	}
	$.post(rootPath+"jasframework/privilege/privilege/setPrivilege.do", {
		"roleOid":oid,
		"privilegeEventIds":privilegeEventIds+"",
		"appnumber":1
	}, function(d) {
		if (d.success == 1) {
			top.showAlert("提示","保存成功", 'info');
			closePanel();
		} else {
			top.showAlert("提示", d.msg, 'error');
		}
		enableButtion("savebutton");
	}, 'json');
}

/**
 * @desc 关闭添加页面
 */
function closePanel() {
	top. closeDlg("setPrivilege");
}
