/** 
 * @file
 * @author  张超飞
 * @version 1.0 
 * @desc  用户主页面js
 * @date  2012-12-18
 * @last modified by lizz
 * @last modified time  2017-08-17
 */

var clientWidth = document.documentElement.clientWidth;
var clientHeight = document.documentElement.clientHeight;
// var div_left_width = 200;
var tempWidth = 0;

var oid ="";//部门id
var treeNodeId = "";//部门类型id

/**
 * @desc 页面初始化
 */
$(function() {
	oid = getParamter("oid");
	treeNodeId = getParamter("treeNodeId");
	loadSelect();
	loadDatagrid();
	// 高级搜索
	$("#moreQuery").click(function() {
		$(this).toggleClass("active");
		$("#moreTable").toggleClass("active");
		var span = $(this).children().find(".l-btn-icon");
		if ($(this).hasClass("active")) {
			$(span).removeClass("accordion-expand").addClass("accordion-collapse");
			initDatagrigHeight('userDatagrid', 'userQuery', '147','right');
		} else {
			$(span).removeClass("accordion-collapse").addClass("accordion-expand");
			initDatagrigHeight('userDatagrid', 'userQuery', '64','right');
		}
	});

	tempWidth = $('#right').css('width');
	if (tempWidth.lastIndexOf('px') > 0) {
		tempWidth = parseInt(tempWidth.substring(0, tempWidth.length - 2));
	}

	initDatagrigHeight('userDatagrid', 'userQuery', '64', 'right');
});

function loadSelect(){
	$('#userrange').combobox({
		data : [ {
			"id" : 0,
			"text" : "只显示本部门用户"
		}, {
			"id" : 1,
			"text" : "显示所有子部门用户"
		} ],
		valueField : 'id',
		textField : 'text',
		width : $('#right').width() * 0.35,
		panelHeight : 50
	});
//	setComboObjWidth('userrange', 0.35, 'combobox', 'right');
	$('#userrange').combobox("setValue", 0);
}

function loadDatagrid(){
	// datagrid初始化
	$("#userDatagrid").datagrid({
		url:rootPath+ "jasframework/privilege/user/getAllUserByUnitid.do?unitEventid="+ oid,
		autoRowHeight : false,
		fitColumns : true,
		columns : [ [
				{
					field : 'ck',
					checkbox : true
				},
				{
					field : 'loginName',
					title : '用户名',
					align : "center",
					width : 100
				},
				{
					field : 'name',
					title : '姓名',
					align : "center",
					width : 100
				},
				{
					field : 'roleList',
					title : '拥有角色',
					align : "center",
					width : 100
				},
				{
					field : 'unitName',
					title : '部门',
					align : "center",
					width : 100
				},
				{
					field : 'phone',
					title : '电话',
					align : "center",
					width : 100
				},
				{
					field : 'email',
					title : '邮箱',
					align : "center",
					width : 150
				},
				{
					field : 'passwordExpiredDate',
					title : '密码到期时间',
					align : "center",
					width : 150,
					formatter:function(value, row, index){
						if(value){
							var date = new Date(value);
							Y = date.getFullYear() + '-';
							M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
							D = date.getDate() + ' ';
							return Y+M+D;
						}
					}
				},
				{
					field : 'description',
					title : '描述',
					align : "center",
					width : 200
				},
				{
					field : 'operate',
					title : '操作',
					width : 150,
					align : "center",
					formatter : function(value, row, index) {
						if (row.eventid) {
							var opt = '<p class="table-operate"><a href="#" title = "查看" onclick="viewUser(\''+ row.eventid+ '\')">\<span class="fa fa-eye"></span>\
										<a href="#" title = "修改" onclick="editUser(\''+ row.eventid+ '\'\,\''+ row.loginName+ '\')">\<span class="fa fa-edit"></span>\
										</a><a href="#" title = "删除" onclick="removeUser(\''+ row.eventid+ '\')">\
										<span class="fa fa-minus"></span>\</a><a href="#" title = "角色设置" onclick="roleAdd(\''+ row.eventid+ '\')">\
										<span class="fa fa-cog"></span>\</a></p>'
							return opt
						}
					}
				} ] ],
		/* title:"用户查询列表", */
		onDblClickRow : function(index, row) {
			$('#userDatagrid').datagrid('selectRow', index); // 指定行选中
			top.getDlg("viewUser.htm?id="+ row.eventid, "viewiframe","查看", 710,225, false, true, true);
		}
	});
}

/**
 * @desc 页面自适应
 */
$(window).bind("resize", function() {
	resizeLayout();
});

/**
 * @desc 窗口的自适应处理
 */
function resizeLayout() {
	try {
		clientWidth = document.documentElement.clientWidth;
		clientHeight = document.documentElement.clientHeight;

		$('#userQuery').panel('resize', {
			width : clientWidth - 5
		});
		$('#userDatagrid').datagrid('resize', {
			width : clientWidth - 5,
			height : clientHeight - $('#userQuery').panel('panel').height()
		});
		$('#userrange').combobox({
			width : $('#right').width() * 0.35
		});
	} catch (e) {
	}
}

/**
 * @desc 用户角色设置
 */
function roleAdd(userOid) {
	var rows = $("#userDatagrid").datagrid("getSelections");
	if (isNull(userOid)) {
		if (rows.length == 1) {
			userOid = rows[0].eventid;
		} else {
			top.showAlert("提示", "请选择一条记录", 'info');
			return;
		}
	}
	top.getDlg(rootPath + "privilege/project_organization/user_role.html?oid=" + userOid
			+ "&projectOrganizationOid=&refreshPage=query_unit_user", "userRoleConfig", "设置角色", 550, 440,
			false, true, true);
}

/**
 * @desc 添加用户
 */
function addUser() {
	top.getDlg(rootPath + "privilege/user/add_user.html?refreshPage=query_unit_user&unitid=" + oid, "saveiframe", "新增",710, 310, false, true, true);
}

/**
 * @desc 修改用户
 */
function editUser(eventid, name) {
	if (isNull(eventid)) {
		var rows = $("#userDatagrid").datagrid("getSelections");
		if (rows.length == 1){
			eventid = rows[0].eventid;
		}else{
			top.showAlert("提示","请选择一条记录", 'info');
			return;
		}
	} 
//	if (!isNull(eventID)) {
//		if (name == "admin") {
//			top.getDlg(rootPath + "privilege/user/updateAdminUser.htm?refreshPage=query_unit_user&id=" + eventid, "updateiframe","修改", 710, 300, false,true, true);
//		} else {
			top.getDlg(rootPath + "privilege/user/update_user.html?refreshPage=query_unit_user&id=" + eventid, "updateiframe","修改", 710, 300, false,true, true);
//		}
//	}
}

/**
 * @desc 修改用户密码
 */
function editpassword(index) {
	var rows;
	if (!isNull(index)) {
		$('#userDatagrid').datagrid('selectRow', index); // 指定行选中
	}
	rows = $('#userDatagrid').datagrid('getSelections');

	if (rows.length == 1) {
		top.getDlg(rootPath + "privilege/user/editPassword.htm?refreshPage=query_unit_user&id=" + rows[0].eventid,"editiframe", "修改", 710, 300,false, true, true);
	} else {
		top.showAlert("提示",'修改失败', 'info');
	}
}

/**
 * @desc 显示用户详细信息
 */
function viewUser(eventid) {
	if (isNull(eventid)) {
		var rows = $("#userDatagrid").datagrid("getSelections");
		if (rows.length == 1){
			eventid = rows[0].eventid;
		}else{
			top.showAlert("提示","请选择一条记录", 'info');
			return;
		}
	}
	if (!isNull(eventid)) {
		top.getDlg(rootPath + "privilege/user/viewUser.htm?id=" + eventid, "viewiframe","查看", 710, 300, false, true,true);
	}
}

/**
 * @desc 删除用户
 */
function removeUser(eventid) {
	var rows = $("#userDatagrid").datagrid("getSelections");
	var ids = "";
	if (!isNull(eventid)) {
		ids = eventid;
	} else if (rows.length > 0) {
		for (var i = 0; i < rows.length; i++) {
			ids += "," + rows[i].eventid;
		}
		if (ids.length > 0)
			ids = ids.substring(1);
	} else {
		top.showAlert("提示", "请选择记录", 'info');
		return;
	}
	;

	if (!isNull(ids)) {
		$.messager.confirm("删除", '您确定要删除这些信息吗？\n\t', function(r) {
			if (r) {
				$.ajax({
					url : rootPath
							+ 'jasframework/privilege/user/deleteUser.do',
					data : {
						"oids" : ids
					},
					type : "POST",
					dataType : "json",
					async : false,
					success : function(data) {
						if (data.status == 1) {
							top.showAlert("提示", "删除成功", "info", function() {
								$('#userDatagrid').datagrid('reload');
								$('#userDatagrid').datagrid('clearSelections');
							});
						} else {
							top.showAlert("错误", "删除失败", "error");
						}
					},
					error : function(data) {
						top.showAlert('错误', '删除出错', 'info');
					}
				});
			}
		});
	}
}
/**
 * @desc 重置用户密码
 */
function resetUserPwd() {
	var rows = $('#userDatagrid').datagrid('getSelections');
	if (rows.length > 0) {
		var ids = "";
		for (var i = 0; i < rows.length; i++) {
			ids += "," + rows[i].eventid;
		}
		if (ids.length > 0)
			ids = ids.substring(1);

		// $.messager.confirm(getLanguageValue('user.resetpass'),getLanguageValue('user.resetconfirm'),function(r){
		$.messager.confirm("重置", "确认重置密码吗？", function(r) {
			if (r) {
				var postUrl = rootPath+ 'jasframework/privilege/user/resetUserPWD.do?refreshPage=query_unit_user&ids='+ ids;
				$.post(postUrl, function(result) {
					if (result.success) {
						top.showAlert(getLanguageValue('success'),getLanguageValue('user.resetsuccess'), 'info');
					} else {
						top.showAlert(getLanguageValue("error"), result.msg,'error');
						return;
					}
				}, 'json');
			}
		});
	} else {
		top.showAlert(getLanguageValue("tip"),getLanguageValue("pleasechoose"), 'info');
		return;
	}
}

/**
 * @desc 查询用户
 */
function queryUser() {
	$("#userDatagrid").datagrid('clearSelections'); // clear
	var loginName = $("#loginName").val();
	var name = $("#name").val();
	var userrange = $('#userrange').combobox("getValue");
	var query = {
		"loginName" : loginName,
		"name" : name,
		"userRange" : userrange
	};
	var url;
	if (userrange == 1) {
		/*var unitidList = "";
		$.ajax({  
            type : 'post',  
            url : rootPath+'treeView/getChildData.do',  
            dataType : 'json',  
            contentType : 'application/json;charset=utf-8', // 设置请求头信息  
            data : JSON.stringify({
            	"parentTreeNodeId":treeNodeId,
            	"parentOid":oid
            }),  
            async:false,
            success : function(data) { 
            	var rows = data.rows;
                for(var i=0;i<rows.length;i++){
                	unitidList +=rows[i].id+",";
                }  
                unitidList = unitidList.substring(0,rows.length-1);
            }  
        }); */ 
		url = rootPath+ "jasframework/privilege/user/getAllUserByUnitid.do";
		query = {
				"loginName" : loginName,
				"name" : name,
				"userRange" : userrange,
				"parentTreeNodeId":treeNodeId,
            	"parentOid":oid
			};
	} else {
		url = rootPath+ "jasframework/privilege/user/getAllUserByUnitid.do?unitEventid="+ oid;
	}
	$("#userDatagrid").datagrid("options").url = url;
	$("#userDatagrid").datagrid('options').queryParams = query;
	$("#userDatagrid").datagrid('load');
	$("#userDatagrid").datagrid('options').queryParams = null;
}

/**
 * @desc 清空查询条件
 */
function clearselectform() {
	$("#loginName").val("");
	$("#name").val("");
	$("#userrange").combobox("setValue", "");
}
