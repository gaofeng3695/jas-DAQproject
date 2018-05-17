/** 
 * @file
 * @author  lujingrui
 * @version 1.0 
 * @desc  查询页面js
 * @date  2017-09-20 上午17:46:07 
 * @last modified by lujingrui
 * @last modified time  2017-09-20
 */

var projectOid = "";
var firstLoad = true;//第一次加载
var flag =2;//项目角色管理
$(function(){
	projectOid = getParamter("oid");
	if(isNull(projectOid)){//项目角色管理
		$(".hide").show();
		loadDatagrid();
		initDatagrigHeight('dg','queryDiv','84');
		loadEngineeringSelect();
	}else{
		flag = 1;//平台角色管理（主要用于刷新）
		loadDatagrid();
		initDatagrigHeight('dg','queryDiv','84');
	}
	
});

/**
 * @desc 获取工程下拉
 * @returns
 */
function loadEngineeringSelect(){
	$('#engineering').combobox({
	    url:rootPath+'scobaseengineering/getPageByName.do?rows=10000&order=ordernum',
	    valueField:'oid',
	    textField:'name',
	    onLoadSuccess:function(data){
	    	$('#engineering').combobox('setValue', data[0].oid);
	    },
	    onSelect: function(rec){
	    	loadProjectSelect(rec.oid);
	    }
	});
}

/**
 * @desc 获取项目下拉
 * @returns
 */
function loadProjectSelect(engineeringoid){
	$('#project').combobox({
	    url:rootPath+'scobaseproject/getPageByName.do?engineeringoid='+engineeringoid,
	    valueField:'oid',
	    textField:'name',
	    onLoadSuccess:function(data){
	    	$('#project').combobox('setValue', data[0].oid);
	    	projectOid = data[0].oid;
	    	if(firstLoad){
	    		loadDatagrid();
	    		firstLoad = false;
	    	}
	    	
	    },
	    onSelect: function(rec){
	    	projectOid = rec.oid;
	    	var query={"projectOid":projectOid}; //把查询条件拼接成JSON
	    	$("#dg").datagrid('options').queryParams=query;
	    	$("#dg").datagrid('load');
	    }
	});
}

/**
 * @desc 加载角色网格列表
 * @returns
 */
function loadDatagrid(){
	$('#dg').datagrid({
		width:'100%',
		nowrap: false,
		collapsible:false,
		url:rootPath+"jasframework/privilege/role/getPage.do",
		queryParams:{"projectOid":projectOid},
		remoteSort: true,
		nowrap:true,
		columns:[[{field:'ck',title:"全选",checkbox:true},   
			  {field:'name',title:'角色名称',align:"center",width:200},   
			  {field:'businessTypeName',title:'角色业务类型',align:"center",width:200}, 
			  {field:'description',title:'描述',align:"center",width:200},
			  {field:'roleSerialNo',title:'顺序号',align:"center",width:200},
			  {field:'operate',title:'操作',align:"center",width:150,formatter: function(value,row,index){
					var opt = '<p class="table-operate"><a href="#" title = "查看" onclick="showInfo(\'' + row.oid+'\')">\
									<span class="fa fa-eye"></span>\
							   </a><a href="#" title = "修改" onclick="editRole(\'' + row.oid+'\')">\
									<span class="fa fa-edit"></span>\
						   	   </a><a href="#" title = "删除" onclick="removeRole(\'' + row.oid+'\')">\
									<span class="fa fa-minus"></span>\
						       </a><a href="#" title = "角色权限分配" onclick="setPrivilege(\'' + row.oid+'\')">\
								<span class="fa fa-cog"></span>\
							       </a></p>'
					return opt;
				}}
			]],
		onDblClickRow:function(index,data){
			showInfo(data.oid);
		}
	});
}

/**
 * @desc 查询角色
 */
function queryRole(){
	var query={"keywords":$("#roleName").val(),"projectOid":projectOid}; //把查询条件拼接成JSON
	$("#dg").datagrid('options').queryParams=query; //把查询条件赋值给datagrid内部变量
	$("#dg").datagrid('load'); //重新加载
}

/**
 * 重新加载列表
 * @returns
 */
function reloadDatagrid(){
	$("#dg").datagrid("reload")
}

/**
 * @desc 新增按钮事件
 */
function newRole(){
	top.getDlg(rootPath+"privilege/role/add_project_role.html?projectOid="+projectOid+"&flag="+flag,"saveiframe","新增",700,350,false,true,true);
}

/**
 * @desc 修改按钮事件
 */
function editRole(eventid){
	var rows = $("#dg").datagrid("getSelections");
	
	if(isNull(eventid)){
		if(rows.length == 1){
			eventid = rows[0].oid;
		}else {
			top.showAlert("提示","请选择一条数据",'info');
			return;
		}
	}
	top.getDlg(rootPath+"privilege/role/add_project_role.html?eventid="+eventid+"&projectOid="+projectOid+"&flag="+flag,"saveiframe","修改",700,350,false,true,true);
}


/**
 * @desc 删除按钮事件
 */
function removeRole(eventid){
	var rows = $("#dg").datagrid("getSelections");
	if(isNull(eventid)){
		if(rows.length > 0){
			eventid ="";
			for(var i = 0;i<rows.length;i++){
				eventid += rows[i].eventid+",";
			}
			eventid.substring(0,eventid.length-1);
		}else{
			top.showAlert("提示","请选中一条数据","info");
			return;
		}
		eventid = eventid.substring(0,eventid.length-1);
	}
	$.messager.confirm("删除","确认删除",function(r){
		if(r){
			$.ajax({
				url : rootPath+"jasframework/privilege/role/deleteRole.do",
				data :{"oids" : eventid},
				type : 'POST',
				dataType:"json",
				success : function(data) {
					if(data.status == 1){
						top.showAlert("成功","删除成功",'ok',function(){
							$('#dg').datagrid('reload');	// reload the user data
							$('#dg').datagrid('clearSelections'); //clear selected options
						});
					}else if(data.status == 2){
						top.showAlert('提示', '该角色已经在使用不能删除', 'info');
					}else{
						top.showAlert('错误', '删除出错', 'info');
					}
				},
				error : function(result) {
					top.showAlert('错误', '删除出错', 'info');
				}
			});
		}
	})
	
}

/**
 * 给角色分配权限
 * @param eventid
 * @returns
 */
function setPrivilege(eventid){
	if(isNull(eventid)){
		var rows = $("#dg").datagrid("getSelections");
		if(rows.length == 1){
			eventid = rows[0].oid;
		}else {
			top.showAlert("提示","请选择一条数据",'info');
			return;
		}
	}
	top.getDlg(rootPath+"privilege/role/set_privilege.html?eventid="+ eventid ,'setPrivilege',"设置",550,440,false,true,true);
}

/**
 * @desc 查看角色
 */
function showInfo(eventid){
	var rows = $("#dg").datagrid("getSelections");
	if(isNull(eventid)){
		if(rows.length == 1){
			eventid = rows[0].oid;
		}else {
			top.showAlert("提示","请选择一条数据",'info');
			return;
		}
	}
	top.getDlg(rootPath+"privilege/role/view_project_role.html?eventid="+ eventid ,'view',"查看",700,250,false,true,true);
}

