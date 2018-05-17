var privilegeid="";
/**
 * 描述：重新加载数据
 * @param shortUrl 重新加载数据的页面
 * @param elementId 权限书的id
 */
function loadData(){
	url =  rootPath+"jasframework/privilege/menu/getMenuById.do?eventid="+getParamter("menuid")+"&random=" + new Date().getTime();
	jQuery.post(url, function(data){
		$('#addmenu').form('load', data);
		privilegeid=data.privilegeid;
	},"json");
}

/***保存完成后刷新父页面的菜单树***/
function reloadtree(shortUrl,parentid){
	var fra= parent.$("iframe");
	for(var i=0; i<fra.length;i++) { 
		if(fra[i].src.indexOf(shortUrl) != -1) {
			fra[i].contentWindow.reloadchildren(parentid);
		}
	}
}
var width;
//页面初始化
$(document).ready(function(){
	getappsystem();
	loadData();
	width=$("#privilegeid").width();
	$("#privilegetype").combobox({"onChange":function(){
		onchangmenu();
	}});
	initcombotree();
});
	
function initcombotree(){
	$("#parenteventid").combotree({
		url: rootPath+"jasframework/privilege/menu/getAllMenuByAppnumber.do?appnumber="+getParamter("appnumber")+"&random=" + new Date().getTime(),
	});
	setComboObjWidth('parenteventid',0.3,'combotree');
	/**定义权限下拉树combotree***/
	$("#privilegeid").combotree({
		panelHeight:200,
		panelWidth:width
	});
	setComboObjWidth('privilegeid',0.3,'combotree');
}

/**
 * 描述：获得应用系统
 */
function getappsystem(){
	$.ajax({
		url:rootPath+"jasframework/appsystem/getAllAppsystem.do",
		type:"post",
		success:function(result){
			$('#appnumber').combobox({ 
				data:result, 
				valueField:'appnumber', 
				textField:'name' 
				}); 
			$('#appnumber1').combobox({ 
				data:result, 
				valueField:'appnumber', 
				textField:'name' ,
				onChange:function(){
					$("#privilegeid").combotree({
						url :  rootPath+"jasframework/privilege/privilege/getAllPrivilegeByAppnumber.do?appnumber="+$('#appnumber1').combobox("getValue")+"&random=" + new Date().getTime(),
						onLoadSuccess:function(node,data){
							var node=$("#privilegeid").combotree("tree").tree('find',privilegeid);
							if(node==null){
								$("#privilegeid").combotree('clear');
							}
						},onShowPanel:function(){
							var node=$("#privilegeid").combotree("tree").tree('find',privilegeid);
							if(node!=null){
								expandNode(node);
								$("#privilegeid").combotree('tree').tree("scrollTo",node.target);
							}
						}
					});
					setComboObjWidth('privilegeid',0.3,'combotree');
				}
				}); 
			$('#appnumber').combobox("setValue",getParamter("appnumber"));
			$('#appnumber1').combobox("setValue",getParamter("appnumber"));
			$('#appsystemnumber').val(getParamter("appnumber"));
			setComboObjWidth('privilegetype',0.3,'combobox');
			setComboObjWidth('appnumber',0.3,'combobox');
			setComboObjWidth('appnumber1',0.3,'combobox');
			setComboObjWidth('childrenPrivilege',0.8,'combobox');
			$('#appnumber').combobox("disable");
		},
		dataType:"json",
		 	error:function(){
		}
	});
}
/**
 * 描述：保存权限
 */
function savemenu(){
	$('#addmenu').form('submit',{
		url: rootPath+"jasframework/privilege/menu/updateMenu.do?appnumber="+getParamter("appnumber"),
		onSubmit: function(){
			var bool=$(this).form('validate');
			if(bool==false){
				enableButtion("savebutton");
			}
			var eventid=getParamter("menuid");
			var parenteventid=$("#parenteventid").combotree("getValue");
			if(eventid==parenteventid){
				alert("父菜单不能为自己");
				return false;
			}else{
				var bool=parsetree(eventid,parenteventid);
				if(!bool){
					alert("父菜单不能为自己的子菜单");
					return false;
				}
			}
			
			return bool;
		},
		success: function(result){
			result=jQuery.parseJSON(result);
			if(result.success==1){
				reloadtree("querymenu.htm",getParamter("parentid"));
				closePrivilege();
			}else{
				alert('新增失败');
			}
		}
	});
}
	
function parsetree(parentid,treeitemid){
	var parenttree=$("#parenteventid").combotree("tree");
	var itema=parenttree.tree("find",treeitemid);
	var parentitem=parenttree.tree("getParent",itema.target);
	if(parentitem!=null){
		if(parentitem.id!=parentid){
			return parsetree(parentid,parentitem.id);
		}
	}else{
		return true;
	}
}
		
/**
 * 描述：关闭添加页面
 */
function closePrivilege(){
	top. closeDlg("saveiframe");
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
		 this.message = '名称不可输入空格！';
		 return false;
	}
	if(b.success==5){
		 return true;
	} else{
		 this.message = '名称已被使用！';
		 return false;
	}
	//return b.success;
	},
	message : null
	}
});
	
function onchangmenu(){
	var changtype=$("#privilegetype").combobox("getValue");
	if(changtype==1||"1"==changtype){
		$("#childrenPrivilege").combobox("enable");
		$("#childrenPrivilege").parent().parent().show();
	}else{
		$("#childrenPrivilege").combobox("disable");
		$("#childrenPrivilege").parent().parent().hide();
	}
}
/**
 * 展开所要查找的节点的父节点，用于tree节点的定位
 * @param node 所有定位的节点
 */
function expandNode(node){
	var parentNode=$("#privilegeid").combotree('tree').tree('getParent',node.target);
	if(parentNode!=null){
		$("#privilegeid").combotree('tree').tree('expand',parentNode.target);
		expandNode(parentNode);
	}
}
	