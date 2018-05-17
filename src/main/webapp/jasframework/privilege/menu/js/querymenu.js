/**
 * 描述：获取应用系统并拼接成下拉列表
 */
function getappsystem(){
	$.ajax({
		url:rootPath+"/jasframework/appsystem/getUserAppsystem.do?random=" + new Date().getTime(),
		type:"post",
		success:function(result){
			var selecthtm="";
			for(var i=0;i<result.length;i++){
				selecthtm+="<option value=\""+result[i].appnumber+"\">"+result[i].name+"</option>";
			}
			$("#appnumber").html(selecthtm);
			$('#appnumber1').combobox({   
				 data:result,
				 valueField:'appnumber',   
				 textField:'name'  ,
				 onSelect:function(record){
					 selectappsystem(record.appnumber);
				 },onLoadSuccess:function(){
					 var data=$(this).combobox("getData");
					 if(data.length>0){
//						 $(this).combobox('setValue',data[0].appnumber);
						 $(this).combobox('select',data[0].appnumber);
						 setComboObjWidth('appnumber1',1,'combobox','left');
					 }
				 }
				}); 
//			$('#appnumber1').combobox("setValue",result[0].appnumber);
//			setComboObjWidth('appnumber1',1,'combobox','left');
			
			if(result[0]){
				inittree(result[0].appnumber);
			}
		},
		dataType:"json",
		 	async: false,
		 	error:function(){
		}
	});
	
}

	//节点id
	var id="";
	/**
	 * 自适应
	 */
	var clientWidth = document.documentElement.clientWidth;
	var clientHeight = document.documentElement.clientHeight;
	$(document).ready(function(){
		getappsystem();
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
	
	function clickmenu(menuid){
		url =  rootPath+"/jasframework/privilege/menu/getMenuById.do?eventid="+menuid+"&random=" + new Date().getTime();
		 jQuery.post(url, function(data){
			 $('#viewSiteForm').form('load', data);
		 },"json");
	}
	
	function inittree(appnumber){
		
		$.ajax({
			   url : rootPath+"jasframework/privilege/menu/getAllMenuByAppnumber.do?appnumber="+appnumber+"&random=" + new Date().getTime(),
			   success: function(msg){
			     $('#tt').tree( {
			    	 checked:true,
			    	 data:msg,
			    	 onLoadSuccess:function(node,data) {
			    		 var rootTarget=$('#tt').tree('getRoot');
			    		 if(rootTarget!=null){
			    			 $('#tt').tree('select',rootTarget.target);
			    			 clickmenu(rootTarget.id);
			    		 }
			    	 },
			    	 onClick : function(node) {
			    		 clickmenu(node.id);
			    	 }
			     });
			     
			     $('#parenteventid').combotree( {
			    	 	data:msg,
						checked:true,
						disabled:true,
						onLoadSuccess:function(node,data) {
							var rootTarget=$('#tt').tree('getRoot');
							if(rootTarget!=null){
						 	$('#tt').tree('select',rootTarget.target);
							}
						},
						onClick : function(node) {
						}
					});
			     setComboObjWidth('parenteventid',0.615,'combotree');
			     
			   },
			   dataType:"JSON"
			});
		//初始化权限树
		
	}
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
	function addmenu() {
		var appnumber=$("#appnumber1").combobox("getValue");
		var row = $('#tt').tree('getSelected');
		var eventId;
		if (row != null) {
			eventId  = row.id;
			url = "addmenu.htm?appnumber="+appnumber+"&parentid=" + eventId;
			top.getDlg(url, "saveiframe",getLanguageValue("add"), 700, 430);
		} else {
			top.getDlg("addmenu.htm?appnumber="+appnumber, "saveiframe",getLanguageValue("add"), 700, 430);
		}
	
	}
	/**
	 * 描述：修改按钮事件
	 */
	function updatemenu() {
		var eventId;
		var parentId;
		var row = $('#tt').tree('getSelected');
		if (row != null) {
			var appnumber=$("#appnumber1").combobox("getValue");
			eventId = row.id;
			var node = $('#tt').tree('find',eventId);
			var parentNode=$('#tt').tree('getParent',node.target);
//			alert(JSON.stringify(parentNode));
			parentId=parentNode.id;
			url = "updatemenu.htm?menuid=" + eventId+"&appnumber="+appnumber+"&parentid="+parentId;
			top.getDlg(url, "saveiframe",getLanguageValue("edit"), 700, 500);
		} else {
			top.showAlert(getLanguageValue("tip"),getLanguageValue("chooserecord"), 'info');
			return;
		}
	}
	
	function reloadNote(nodeid){
		var node=$("#tt").tree("find",nodeid);
		$.getJSON(rootPath+"/jasframework/privilege/menu/getAllPrivilegeByAppnumber.do?appnumber="+appnumber+"&random=" + new Date().getTime(), function(data){
			$('#tt').tree('loadData',data);
			});
	}

	function selectappsystem(appnumber){
//		var appnumber=$("#appnumber1").combobox("getValue");
		  var url = rootPath+"/jasframework/privilege/menu/getAllMenuByAppnumber.do?appnumber="+appnumber+"&random=" + new Date().getTime();
		  $.getJSON(url, function(data){
			$('#tt').tree('loadData',data);
			});
	}
	/**
	 * 
	 * 描述：拖动改变布局大小的监听方法
	 */
	function resizeLayout(){
		try{
			clientWidth = document.documentElement.clientWidth;
			$('#appnumber1').combobox('resize', $('#left').width());
		}catch(e){
			
		}
	}
	
	function reloadchildren(parentid){
		if(parentid=="null" || parentid==""){
			$("#tt").tree("options").url= rootPath+"/jasframework/privilege/menu/getAllMenuByAppnumber.do?appnumber="+$("#appnumber1").combobox("getValue")+"&eventid="+parentid+"&random=" + new Date().getTime();
			$("#tt").tree("reload");
		}else{
			var node=$('#tt').tree("find",parentid);
			/***begin tree 当叶子节点变为非叶子节点时，该节点不刷新的问题***/
			var childrenNode=$('#tt').tree("getChildren",node.target);
			if(childrenNode.length==0){
				$('#tt').tree("append",{parent:node.target,data:[{id:"1",text:"dd"}]});
			}
			/***end***/
			$("#tt").tree("options").url= rootPath+"/jasframework/privilege/menu/getAllChildrenMenu.do?appnumber="+$("#appnumber1").combobox("getValue")+"&eventid="+parentid;
			$("#tt").tree("reload",node.target);
			setTimeout(function(){
				$("#tt").tree('select',node.target);
			}, 100);
		}
	}

	
	function deletemenu(){
		var row = $('#tt').tree('getSelected');
		if (row != null) {
			var checkurl=rootPath+"/jasframework/privilege/menu/ismenuisMenuChildrenExist.do?parentid="+row.id;
			$.getJSON(checkurl,function(data){
				if(data.success==1){
					var url= rootPath+"/jasframework/privilege/menu/deleteMenuById.do?menuId="+row.id+"&random=" + new Date().getTime();
					top.$.messager.confirm(getLanguageValue("delete"),getLanguageValue("deleteconfirm"),function(r){
						if(r){
							$.getJSON(url, function(data){
								if(data.success==1){
									top.showAlert(getLanguageValue("success"),getLanguageValue("deletesuccess"),'ok',function(){
										 $('#tt').tree('remove',row.target);
									});
								}
							});
						}
					});
				}else{
					top.showAlert(getLanguageValue("tip"), data.msg, 'error');
				}
			});
		}else{
			top.showAlert(getLanguageValue("tip"),getLanguageValue("chooserecord"), 'info');
		}
	}