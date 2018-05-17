
var id="";//节点id
var appnumber = 1;//应用应用系统编号 应用
var clientWidth = document.documentElement.clientWidth;
var clientHeight = document.documentElement.clientHeight;

$(document).ready(function(){
	initzTree(appnumber);
	
	//左侧div宽度
 	var div_left_width =$(".layout-panel-west").width();
	my_resize(div_left_width);
	onWindowResize.add(function(){
		clientHeight = document.documentElement.offsetHeight;
		clientWidth = document.documentElement.offsetWidth;
		div_left_width = $(".layout-panel-west").width();
		my_resize(div_left_width);
	});
	//自动适应页面大小
	function my_resize(panelWidth){
		$("#cc").css("width",clientWidth);
		$("#cc").css("height",clientHeight);
		//$("#cc").layout('resize',{height:clientHeight,width:clientWidth});
		$('#left').panel('resize',{height:clientHeight,width:div_left_width});
		$('#right').panel('resize',{height:clientHeight,width:clientWidth - panelWidth});
	}

	$(".layout-button-left").hide();
	$(".layout-button-left").bind("click",function(){
		my_resize(div_left_width-21);
		clientWidth = document.documentElement.clientWidth;
		$(".layout-button-right").bind("click",function(){
			var temp = 0-div_left_width+145;
			my_resize(temp);
			clientWidth = document.documentElement.clientWidth;
		});
	});
});
function initzTree(appnumber){
	$.ajax({
		url: rootPath+'jasframework/privilege/privilege/getAllPrivilegeZTreebyappnumber.do',
		dataType:"json",
		type:'post',
		data:{"appnumber":appnumber},
		success:function(result){
			$.fn.zTree.init($('#tt'),{
				treeObj:null,
				check: {
					enable: true,
					autoCheckTrigger:true,
					display:true
				},data: {
					simpleData: {
						enable: true, 
						idKey: "id"
					}
				},callback: {
					beforeExpand: zTreeBeforeExpand,
					beforeCollapse: zTreeBeforeCollapse,
					onClick: zTreeOnClick,
					onAsyncSuccess: zTreeOnAsyncSuccess,
					beforeDrag: zTreeBeforeDrag,
					beforeDrop: zTreeBeforeDrop,
					onDrop: zTreeOnDrop
				},async: {
					enable: true,
					url: rootPath+"jasframework/privilege/privilege/getChildrenPrivilegeByAppnumber.do",
					type:"post",
					dataType:"json",
					autoParam: ['id'],
					otherParam: ["appnumber", "1"]
				},edit: {
					enable: false,
					drag: {
						isCopy: false,
						isMove: true,
						prev: true,
						next: true,
						inner: false
					}
				}
			},result);
			if(result){
				var zTree = $.fn.zTree.getZTreeObj("tt");
				var nodes = zTree.getNodes(); 
	            zTree.selectNode(nodes[0]);
	            loadRight(nodes[0].id);
			}
		}
	});
}
/**
 * 展开节点之前
 * @param treeId
 * @param treeNode
 */
function zTreeBeforeExpand(treeId, treeNode){
	var treeObj = $.fn.zTree.getZTreeObj("tt");
	treeNode.icon = "icon-tree-folder-node-open";
	treeObj.selectNode(treeNode);
	treeObj.updateNode(treeNode);
}
/**
 * 关闭节点之前
 * @param treeId
 * @param treeNode
 */
function zTreeBeforeCollapse(treeId, treeNode){
	var treeObj = $.fn.zTree.getZTreeObj("tt");
	treeNode.icon = "icon-tree-folder-node-close";
	treeObj.updateNode(treeNode);
}
/**
 * 单击节点
 * @param event
 * @param treeId
 * @param treeNode
 * @param clickFlag
 */
function zTreeOnClick(event, treeId, treeNode,clickFlag){
	loadRight(treeNode.id);
	if(treeNode.getParentNode()==null || treeNode.getParentNode()==null){
		$("#editbtn").linkbutton("disable");
		$("#delbtn").linkbutton("disable");
	}else{
		$("#editbtn").linkbutton("enable");
		$("#delbtn").linkbutton("enable");
	}
}
/**
 * 异步加载成功之后
 * @param event
 * @param treeId
 * @param treeNode
 * @param msg
 */
function zTreeOnAsyncSuccess(event,treeId,treeNode,msg){
//		alert(treeNode.name);
	loadRight(treeNode.id);
}
/***
 * 节点拖动之前
 * @param treeId
 * @param treeNodes
 * @returns {Boolean}
 */
function zTreeBeforeDrag(treeId, treeNodes) {
	if(treeNodes.length==1){
		return true;
	}else{
		return false;
	}
};
/**
 * 拖动节点操作结束之前
 * @param treeId
 * @param treeNodes
 * @param targetNode
 * @param moveType
 * @returns {Boolean}
 */
function zTreeBeforeDrop(treeId, treeNodes, targetNode, moveType) {
	if(targetNode.pId==treeNodes[0].pId){
		return true;
	}else{
		return false;
	}
};
/***
 * 拖动节点操作结束之后
 * @param event
 * @param treeId ztree的ID
 * @param treeNodes 拖动的节点
 * @param targetNode 目标节点
 * @param moveType 拖动类型，next,prev和inner(这里没用到)
 */
function zTreeOnDrop(event, treeId, treeNodes, targetNode, moveType) {
	if(moveType=="null"){
		return false;
	}
//	var appNumber = $("#appnumber1").combobox("getValue");
	$.ajax({
		url:rootPath+"/jasframework/privilege/privilege/updateHierarchy.do",
		data:{"targetId":targetNode.id,"sourceId":treeNodes[0].id,"moveType":moveType,"appNumber":appnumber},
		dataType:"json",
		type:"post",
		async:false,
		success:function(result){
			
		}
	});
};

/**
 * 描述：判断节点是否为根节点
 * @param node 节点对象
 * @returns boolean 根节点为true，否则为false
 */
function isRootNode(node){
	if ( node.attributes.isRoot != null && node.attributes.isRoot == "true") {
		return true;
	}
	return false;
}
/**
 * 描述：新增按钮事件
 */
function savePrivilege() {
	var treeObj = $.fn.zTree.getZTreeObj("tt");
	var nodes = treeObj.getSelectedNodes();
	var eventId;
	if (nodes != null) {
		eventId = nodes[0].id;
		url = rootPath+"privilege/privilege/add_menu_buttons.html?appnumber="+appnumber+"&parentid=" + eventId+"&privilegeNumber="+$("#privilegeNumber").val();
		top.getDlg(url, "saveiframe", "新增", 700, 400);
	} else {
		top.getDlg(rootPath+"privilege/privilege/add_menu_buttons.html?appnumber="+appnumber, "saveiframe", "新增", 700, 350);
	}
}

/**
 * 描述：修改按钮事件
 */
function updatePrivilege() {
	var eventId;
	var treeObj = $.fn.zTree.getZTreeObj("tt");
	var nodes = treeObj.getSelectedNodes();
	if (nodes != null) {
		eventId = nodes[0].id;
		url = "update_menu_buttons.html?eventId=" + eventId+"&privilegeNumber="+$("#parentPrivilegeNumber").val();
		top.getDlg(url, "saveiframe", "修改", 700, 350);
	} else {
		top.showAlert("提示", "修改", 'info');
		return;
	}
}

/**
 * 描述：检查权限是否被分配
 */
function checkPrivilege(){
	var treeObj = $.fn.zTree.getZTreeObj("tt");
	var nodes = treeObj.getSelectedNodes();
	if (nodes != null) {
	var eventId=nodes[0].id;
  	$.getJSON(rootPath+"jasframework/privilege/privilege/checkPrivilegeByeventId.do?functionprivilegeeventid="+eventId+"&random=" + new Date().getTime(),function(check) {
		if (check.error=='-1' ){
			$.messager.confirm("提示", "该权限已经被分配，确定继续吗？", function(r){
			if(r){
			 removeUnit();
			}
			});
		} else if(check.error=='-2'){
			top.showAlert("提示", "该权限下存在子权限", 'error');
		 	
		}else{
			removeUnit();
		}
	});
	} else {
		top.showAlert("提示", "请选中一条记录", 'info');
		return;
	}
}
/**
 * 描述：删除权限
 */
function removeUnit() {
	var treeObj = $.fn.zTree.getZTreeObj("tt");
	var nodes = treeObj.getSelectedNodes();
	if (nodes != null) {
		$.messager.confirm("删除", "确认删除？", function(r) {
			if (r) {
				var url = rootPath+'jasframework/privilege/privilege/deletePrivilegeById.do?eventId=' + nodes[0].id+"&appNumber="+appnumber;
				$.post(url, function(result) {
					if (result.success) {
						top.showAlert("提示", "删除成功", 'ok', function() {
							reloadZtreeNode(nodes[0].id);
						});
								 	

					} else {
						top.showAlert("提示",result.msg + '', 'error');
						return;
					}
				}, 'json');

			}
		});
	} else {
		top.showAlert("提示", "请选择一条记录", 'info');
		return;
	}
}

function addNode(parentId,name){
	var treeObj = $.fn.zTree.getZTreeObj("tt");
	var parentNode = treeObj.getNodeByParam("id", parentId, null);
	var newNode = {name:name};
	newNode = treeObj.addNodes(parentNode, newNode);
}

function reloadchildren(nodeid){
	var treeObj = $.fn.zTree.getZTreeObj("tt");
	var node = treeObj.getNodeByParam("id", nodeid, null);
	treeObj.reAsyncChildNodes(node, "refresh");
}

function reloadZtreeNode(nodeid){
	var treeObj = $.fn.zTree.getZTreeObj("tt");
	var node = treeObj.getNodeByParam("id", nodeid, null);
	var parentNode=node.getParentNode();
	treeObj.reAsyncChildNodes(parentNode, "refresh");
	treeObj.selectNode(parentNode);
    loadRight(parentNode.id);
}

function reloadZtree(){
	var treeObj = $.fn.zTree.getZTreeObj("tt");
	treeObj.refresh();
}

/**
 * 
 * 描述：拖动改变布局大小的监听方法
 */
function resizeLayout(){
	try{
		clientWidth = document.documentElement.clientWidth;
//		$('#appnumber1').combobox('resize', $('#left').width());
	}catch(e){
		
	}
}
function loadRight(id){
	var url = rootPath+'jasframework/privilege/privilege/findPrivilegeById.do?eventId='+id;
	$.post(url, function(bo) {
		$('#right').form('load', bo);
	}, 'json');
}

//function inittree(appnumber){
//	//初始化权限树
//	$('#tt').tree( {
//		url : rootPath+'jasframework/privilege/privilege/getAllPrivilegeByAppnumber.do?appnumber='+appnumber,
//		checked:true,
//		dnd:true,
//		animate:true,
//		closable:false,
//		onLoadSuccess:function(node,data) {
//			//clearQueryForm('right',true,true,true,true);
////			var appnumber=$("#appnumber1").combobox("getValue");
////			$("#appnumber").val(appnumber);
//			//加载成功后选中第一个节点
//			var rootTarget=$('#tt').tree('getRoot');
//			if(rootTarget!=null){
//			 	$('#tt').tree('select',rootTarget.target);
//				loadRight(data[0].id);
//			}
//		},
//		onClick : function(node) {
//			loadRight(node.id);
//			if ( isRootNode(node) ) {
////					$("#delbtn").attr("disabled", "disabled");
//			} else {
//				$("#addbtn").removeAttr("disabled");
//				$("#editbtn").removeAttr("disabled");
//				$("#delbtn").removeAttr("disabled");
//			}
//		},
//		onDragEnter:function(targetNode, source){
////				var target = $(this).tree('getNode', targetNode);
////				if(target.state=="closed"){
////					$(this).tree("expand",targetNode);
////				}
//			return true;
//		},
//		onDragOver:function(target, source){
//			return true;
//		},
//		onBeforeDrop:function(target, source, point){
//			var targetId = $(this).tree('getNode', target).id;
//			var sourceId = source.id;
//			//获取source的父节点,如果源节点的父节点和目标节点相同，则不允许移动
//			var node=$("#tt").tree("find",sourceId);
//			var parent = $("#tt").tree("getParent",node.target);
//			if(parent!=null&&parent.id==targetId&&point=="append"){
//				return false;
//			}
////			var appNumber = $("#appnumber1").combobox("getValue");
//			//菜单允许拖拽，其他不允许拖拽
//			var privilegeType = source.attributes.privilegeType;
//			var isUpdate = false;
//			if(privilegeType>1&&privilegeType<=4){
//				return false;
//			}
//			$.ajax({
//				url:rootPath+"jasframework/privilege/privilege/isUpdateHierarchy.do",
//				data:{"targetId":targetId,
//					"sourceId":sourceId,
//					"appNumber":appnumber,"point":point},
//				success:function(result){
//					isUpdate = result;
//					if(!result){
//						top.showAlert("提示","权限层级结构大于9层或子权限个数超过99个，不允许移动！","info");
//					}
//				},
//				async:false,
//				dataType:"json"
//				});
//			
//			return isUpdate;
//		},
//		onDrop:function(targetNode, sourceNode, point){
//			var target = $(this).tree('getNode', targetNode);
//			var targetId = target.id;
//			var sourceId = sourceNode.id;
////			var appNumber = $("#appnumber1").combobox("getValue");
//			//alert(JSON.stringify(target));
//			//var source = $(this).tree("getNode",sourceNode);
//			var url=rootPath+"jasframework/privilege/privilege/updatePrivilegeHierarchy.do";
//			$.post(url, 
//					{"targetId":targetId,
//					"sourceId":sourceId,
//					"appNumber":appnumber,"point":point},
//					function(result) {
//					}
//			);
//		}
//	});
//	
//}
//
///**
// * 描述：获取应用系统并拼接成下拉列表
// */
//function getappsystem(){
//	$.ajax({
//		url:rootPath+"/jasframework/appsystem/getUserAppsystem.do?",
//		type:"post",
//		success:function(result){
//			var selecthtm="";
//			for(var i=0;i<result.length;i++){
//				selecthtm+="<option value=\""+result[i].appnumber+"\">"+result[i].name+"</option>";
//			}
//			$("#appnumber").html(selecthtm);
//			$('#appnumber1').combobox({   
//				 data:result,
//				 valueField:'appnumber',   
//				 textField:'name'  ,
//				 onSelect:function(record){
//					 initzTree(record.appnumber);
//				 },
//				 onLoadSuccess:function(){
//					 var data=$(this).combobox("getData");
//					 if(data.length>0){
//						 $(this).combobox('select',data[0].appnumber);
//					 }
//				 }
//				}); 
//			setComboObjWidth('appnumber1',1,'combobox','left');
//		},
//		dataType:"json",
//		 	async: false,
//		 	error:function(){
//		}
//	});
//	
//}	