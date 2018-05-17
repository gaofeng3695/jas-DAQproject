/** 
 * @file
 * @author  张超飞
 * @version 1.0 
 * @desc  用户主页面js
 * @date  2012-12-18
 * @last modified by lizz
 * @last modified time  2017-08-17
 */


/**
 * @desc 页面初始化
 */
$(function(){
	$('#userrange').combobox({
		data: [{ 
			"id":0,   
		     "text":"只显示本部门用户"  
	       },{   
		     "id":1,   
		     "text":"显示所有子部门用户"  
	       }],
		valueField : 'id',
		textField : 'text',
		width :  $('#right').width() * 0.35,
		panelHeight:50
		
	});
	
	
	setComboObjWidth('userrange',0.35,'combobox','right');
	$('#userrange').combobox("setValue",0);
	$('#tt').tree({		
		url: rootPath+'jasframework/privilege/unit/getLeftTree.do',
		onLoadSuccess:function(node,data) {
		 	var aa=$('#tt').tree('select',$('#tt').tree('getRoot').target);
			var url = rootPath+"jasframework/privilege/user/getAllUserByUnitid.do?unitEventid="+$('#tt').tree('getRoot').id;
			getChildren();
			$("#userDatagrid").datagrid("options").url = url;
			$("#userDatagrid").datagrid('load'); 
		},
		onClick:function(node){
			$("#userDatagrid").datagrid('clearSelections'); // clear
			queryUser();
			if(node.attributes.type){
				//$("#toolbar").hide();
			}else{
				$("#toolbar").show();
			}
		}
	});
	//datagrid初始化
  	$("#userDatagrid").datagrid({
		autoRowHeight: false,
		fitColumns:true,
		columns:[[{field:'ck',checkbox:true},   
			  {field:'loginName',title:'用户名',align:"center",width:100},   
			  {field:'name',title:'姓名',align:"center",width:100}, 
			  {field:'roleList',title:'拥有角色',align:"center",width:100}, 
			  {field:'unitName',title:'部门',align:"center",width:100}, 
			  {field:'phone',title:'电话',align:"center",width:100},
			  {field:'email',title:'邮箱',align:"center",width:150},
			  {field:'passwordexpireddate',title:'密码到期时间',align:"center",width:150},
			  {field:'description',title:'描述',align:"center",width:200},
			  {field:'operate',title:'操作',width:150,align:"center",formatter: function(value,row,index){
					if (row.eventid){
						var opt = '<p class="table-operate"><a href="#" title = "查看" onclick="viewUser(\'' + row.eventid+'\')">\
										<span class="fa fa-eye"></span>\
								   </a><a href="#" title = "修改" onclick="editUser(\'' + row.eventid+ '\'\,\'' + row.loginName+'\')">\
										<span class="fa fa-edit"></span>\
							   	   </a><a href="#" title = "删除" onclick="removeUser(\'' + row.eventid+'\')">\
										<span class="fa fa-minus"></span>\
							       </a><a href="#" title = "角色设置" onclick="roleAdd(\'' + row.eventid+'\')">\
										<span class="fa fa-cog"></span>\
								   </a></p>'
						return opt
					} 
				}}
			]],
			/*title:"用户查询列表",*/
			onDblClickRow : function(index, row) {
				  $('#userDatagrid').datagrid('selectRow',index);  //指定行选中
				  top.getDlg("viewUser.htm?random="+new Date().getTime()+"&id=" + row.eventid,"viewiframe",getLanguageValue('user.chakanyonghu'),710,225,false,true,true);
			},
			onLoadSuccess:function(data){
		    	$('#userDatagrid').datagrid('clearSelections'); //clear selected options
		    }
	});
  	
    //高级搜索
	$("#moreQuery").click(function(){
		$(this).toggleClass("active");
		$("#moreTable").toggleClass("active");
		var span = $(this).children().find(".l-btn-icon");
		if($(this).hasClass("active")){
			$(span).removeClass("accordion-expand").addClass("accordion-collapse");
			initDatagrigHeight('userDatagrid','userQuery','147','right');
		}else{
			$(span).removeClass("accordion-collapse").addClass("accordion-expand");
			initDatagrigHeight('userDatagrid','userQuery','64','right');
		}
	});
	
  	tempWidth = $('#right').css('width');
	if(tempWidth.lastIndexOf('px')>0){
		tempWidth = parseInt(tempWidth.substring(0,tempWidth.length-2));
	}
	
	initDatagrigHeight('userDatagrid','userQuery','64','right');
	initResize();

});

	var clientWidth = document.documentElement.clientWidth;
	var clientHeight = document.documentElement.clientHeight;
	var div_left_width = 200;
	var tempWidth = 0;
	
	
	
 	/**
 	 * @desc 页面自适应
 	 */
	$(window).bind("resize",function(){
		resizeLayout();
	});
	
	
	/**
 	 * @desc 窗口的自适应处理
 	 */
	function resizeLayout(){
		try{

			clientWidth = document.documentElement.clientWidth;
			clientHeight = document.documentElement.clientHeight;
			var div_left_width = $("#left").width()+10;
	
			$('#userQuery').panel('resize',{width:clientWidth-div_left_width-5}); 
			$('#userDatagrid').datagrid('resize',{width:clientWidth-div_left_width-5,height:clientHeight-$('#userQuery').panel('panel').height()});
			$('#userrange').combobox({
				width :  $('#right').width() * 0.35
			});
		}catch(e){
		}
	}
	
/**
 * @desc 初始化页面绑定事件
 */
function initResize(){
	
	//自动适应页面大小
	$(".layout-button-left").bind("click",function(){
		$('#userQuery').panel('resize',{width:clientWidth-28-6});
		$('#userDatagrid').datagrid('resize',{width:clientWidth-28-6});
		/*$(".layout-button-right").bind("click",function(){
			resizeLayout();
			
			$('#userQuery').panel('resize',{width:tempWidth}); 
			$('#userDatagrid').datagrid('resize',{width:tempWidth});
		});*/
	});
}

/**
 * @desc 用户角色设置
 */
function roleAdd(userOid){
	var rows = $("#userDatagrid").datagrid("getSelections");
	if(isNull(userOid)){
		if(rows.length == 1){
			userOid = rows[0].eventid;
		} else {
			top.showAlert("提示","请选择一条记录",'info');
			return;
		}
	}
	top.getDlg("../project_organization/user_role.html?refreshPage=query_platform_user&oid="+userOid+"&projectOrganizationOid=","userRoleConfig","设置角色",550,440,false,true,true);
}

/**
 * @desc 添加用户
 */
function addUser(){
	 var row = $('#tt').tree('getSelected');	
	 var unitid; 
	 if (row != null ){	
	 	unitid= row.id; 
	 	url=rootPath+"privilege/user/addUser.htm?refreshPage=query_platform_user&unitid="+unitid;
		top.getDlg(url,"saveiframe","新增",710,310,false,true,true);
	 }else{
		top.showAlert('提示','请选择一条记录','info');
		return;
	}
}

/**
 * @desc 修改用户
 */
function editUser(evtID,name){
	var rows = $("#userDatagrid").datagrid("getSelections");
	var eventID;
	if(!isNull(evtID)){
		eventID = evtID;
	}else if(rows.length == 1){
		eventID = rows[0].eventid;
	} else {
		top.showAlert(getLanguageValue("tip"),getLanguageValue("chooserecord"),'info');
		return;
	}
	if(!isNull(eventID)){
	
	     if(name =="admin"){
	    	 top.getDlg(rootPath+"privilege/user/updateAdminUser.htm??refreshPage=query_platform_user&id="+eventID,"updateiframe","修改",710,300,false,true,true);
	     }else{
	    	 top.getDlg(rootPath+"privilege/user/updateUser.htm??refreshPage=query_platform_user&id="+eventID,"updateiframe","修改",710,300,false,true,true);
	     }
		
	}
}

/**
 * @desc 修改用户密码
 */
function editpassword(index){
	var rows;
	if(!isNull(index)){
		$('#userDatagrid').datagrid('selectRow',index);  //指定行选中
	}
	rows = $('#userDatagrid').datagrid('getSelections');
	
	if(rows.length == 1) {
		top.getDlg(rootPath+"privilege/user/editPassword.htm?refreshPage=query_platform_user&id="+rows[0].eventid,"editiframe","修改",710,300,false,true,true);
	} else {
		top.showAlert('提示','请选择一条记录','info');
	}
}


/**
 * @desc 显示用户详细信息
 */
function viewUser(evtID){
	var rows = $("#userDatagrid").datagrid("getSelections");
	var eventID;
	if(!isNull(evtID)){
		eventID = evtID;
	}else if(rows.length == 1){
		eventID = rows[0].eventid;
	} else {
//		top.showAlert(getLanguageValue("tip"),getLanguageValue("chooserecord"),'info');
		top.showAlert("提示","请选择一条记录",'info');
		return;
	}
	if(!isNull(eventID)){
        top.getDlg(rootPath+"privilege/user/viewUser.htm?id=" + eventID,"viewiframe","查看",710,300,false,true,true);
	}
}

/**
 * @desc 删除用户
 */
function removeUser(evtID){
	var rows = $("#userDatagrid").datagrid("getSelections");
	var ids="";
	if(!isNull(evtID)){
		ids = evtID;
	}else if(rows.length > 0){
		for(var i=0;i<rows.length;i++){
			ids += ","+rows[i].eventid;
		}
		if(ids.length > 0) ids = ids.substring(1);
	} else {
		top.showAlert("提示","请选择记录",'info');
		return;
	};

	if(!isNull(ids)){
		$.messager.confirm("删除",'您确定要删除这些信息吗？\n\t',function(r){
			if (r){
				$.ajax({
					   url: rootPath+'jasframework/privilege/user/deleteUser.do',
					   data: {"oids" : ids},
					   type: "POST",
					   dataType:"json",
					   async:false,
					   success: function(data){
							if(data.status==1){
								top.showAlert("提示","删除成功","info",function(){
									$('#userDatagrid').datagrid('reload');	
									$('#userDatagrid').datagrid('clearSelections'); 
								});
							}else{
								top.showAlert("错误","删除失败","error");
							}
					   },
					   error : function(data) {
							top.showAlert('错误', '删除出错', 'info');
						}
					});
//				$.post(postUrl,{"ids":ids,"rd":Math.random()},function(result){
//					if (result.success){
//						top.showAlert(getLanguageValue("tip"),getLanguageValue("deletesuccess"),'info');
//						$('#userDatagrid').datagrid('reload');	// reload the user data
//						$('#userDatagrid').datagrid('clearSelections'); //clear selected options
//					} else {
//						top.showAlert(getLanguageValue("error"),result.msg,'error');
//						return;
//					}
//				},'json');
			}
		});
	}
}
/**
 * @desc 重置用户密码
 */
function resetUserPwd(){
	var rows = $('#userDatagrid').datagrid('getSelections');
	if (rows.length > 0){
		var ids="";
		for(var i=0;i<rows.length;i++){
			ids += ","+rows[i].eventid;
		}
		if(ids.length > 0)ids = ids.substring(1);
		
//		$.messager.confirm(getLanguageValue('user.resetpass'),getLanguageValue('user.resetconfirm'),function(r){
		$.messager.confirm("重置","确认重置密码吗？",function(r){
			if (r){
				var postUrl = rootPath+'jasframework/privilege/user/resetUserPWD.do?ids=' + ids + '&rd='+Math.random();
				$.post(postUrl,function(result){
					if (result.success){
						top.showAlert("提示","重置密码成功",'info');
					} else {
						top.showAlert("提示",result.msg,'error');
						return;
					}
				},'json');
			}
		});
	}else{
		top.showAlert("提示","请选择一条记录",'info');
		return;
	}
}
/**
 * @desc 修改用户密码(已过时)
 */
function editUserPass(){
	var rows = $('#userDatagrid').datagrid('getSelections');
	if (rows.length == 1){
		var row = $('#userDatagrid').datagrid('getSelected');
		var eventID =row.eventid;
		top.getDlg(rootPath+"privilege/user/updateUserPass.htm?random="+new Date().getTime()+"&id="+eventID,"updateiframe",getLanguageValue('user.xiugaimima'),450,170,false,true,true);		
	}else{
		top.showAlert(getLanguageValue("tip"),getLanguageValue("chooserecord"),'info');
	}
}

/**
 * @desc 获取选中的树节点
 */
function getChildren(){
	var node = $('#tt').tree('getSelected');
    if (node) {
        var children = $('#tt').tree('getChildren', node.target);
    }
    else {
        var children = $('#tt').tree('getChildren');
    }
    var s = "'";
    for (var i = 0; i < children.length; i++) {
        s += children[i].id + "','";
    }
    s+=node.id + "'";
    //alert(s);
    return s;
}


/**
 * @desc 查询用户
 */
function queryUser(){
	$("#userDatagrid").datagrid('clearSelections'); // clear
	var loginName = $("#loginName").val();
	var name = $("#name").val();
	var userrange = $('#userrange').combobox("getValue");
	//var userrange = $('#userrange').combotree('getValue');
	var query={"loginName":loginName,"name":name,"userRange":userrange};
	var row = $('#tt').tree('getSelected');
	var url;
	if (row != null ){	
		if(userrange==1){
	 		var unitidList = getChildren();
			url = rootPath+"jasframework/privilege/user/getAllUserByUnitid.do?unitidList=" + unitidList;
		}else{
			url=rootPath+"jasframework/privilege/user/getAllUserByUnitid.do?unitEventid=" + row.id;
		}	
		$("#userDatagrid").datagrid("options").url = url;
		$("#userDatagrid").datagrid('options').queryParams=query;
		$("#userDatagrid").datagrid('load');	
		$("#userDatagrid").datagrid('options').queryParams=null;
	 }else{
		top.showAlert(getLanguageValue("tip"),getLanguageValue("chooserecord"),'info');
		return;
	}
}

/**
 * @desc 清空查询条件
 */
function clearselectform(){
	$("#loginName").val("");
	$("#name").val("");
	$("#userrange").combobox("setValue","");
	
}
