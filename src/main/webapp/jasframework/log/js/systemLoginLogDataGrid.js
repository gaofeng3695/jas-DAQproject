$(function(){	
	$("#loginLogDatagid").datagrid({
		url:rootPath+"/jasframework/loginLog/querySystemLoginLogStatisticsInfo.do",
		idField:"systemId",
		collapsible:false,
		rownumbers:true,
		nowrap:false,
		striped:true,
		columns:[[
		    {field:"systemId",title:"系统编号",width:150},
		    {field:"systemName",title:"系统名称",width:250},
		    {field:"loginNumber",title:"登录次数",width:100}
		]],
	});
	initDatagrigHeight('loginLogDatagid');
});
