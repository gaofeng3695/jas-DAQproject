var isroot=getParamter("isroot");
	/**
	 * 描述：重新加载数据
	 * @param shortUrl 重新加载数据的页面
	 * @param elementId 权限树的id
	 */
function reloadData(shortUrl, elementId){
		var fra= parent.$("iframe");
		for(var i=0; i<fra.length;i++) {
			if(fra[i].src.indexOf(shortUrl) != -1) {
				fra[i].contentWindow.$(elementId).tree("reload");
				closeUnit();
				break;
			}
		}
		
	}
	/**
	 * 描述：修改部门
	 */
	function updateUnit(){
 		url = rootPath+'jasframework/privilege/unit/updateUnit.do?token='+localStorage.getItem("token");
 		var name = $("#unitName").val();
 		var unitCode = $("#unitCode").val();
 		var parentEventid = $("#parentId").combotree("getValue");
 		var unittree=$("#parentId").combotree("tree");
 		var unitobj=unittree.tree("find",eventID);
 		var childrenunit=unittree.tree("getChildren",unitobj.target);
 		var bool=false;
 		$.each(childrenunit,function(i,n){
 			if(parentEventid==n.id){
 				top.showAlert(getLanguageValue("tip"),"上级部门不能为自己的子部门",'info');
                    bool=true;
 			}
 		});
 		if(bool){
 			return ;
 		}
 		//检验部门是否存在
 		console.log(name)
 		$.ajax({
 			type: "POST",
 			contentType: "application/json;charset=utf-8",
	        url: rootPath + "jasframework/privilege/unit/checkUnitExist.do",
	        data:JSON.stringify({ "parentId": $("#parentId").combotree("getValue"), "unitName": name, "unitCode": unitCode ,"oid": eventID}),
	        dataType: "json",
		   	success: function(check){
	     		if (check.status=='-1'){
					top.showAlert("提示",check.msg,'error');
				} else{
					//不存在则提交修改
					disableButtion("savebutton");
//					$('#editgroups').form('submit',{
//						url: url,
//						onSubmit: function(){
//							var bool=$(this).form('validate');
//							if(bool==false){
//								enableButtion("savebutton");
//							}
//							return bool;
//						},success: function(result){
//							top.showAlert("提示","保存成功",'info',function(){
//							reloadData('queryUnit.htm','#tt');
//							});
//						}
//					});
					$.ajax({
	                    type: "POST",
	                    url: url,
	                    contentType: "application/json;charset=utf-8",
	                    data: JSON.stringify($('#editgroups').serializeToJson()),
	                    success: function (result) {
	                    	enableButtion("savebutton");
	                        if (result.status == "1") {
	                        	top.showAlert("提示","保存成功",'info',function(){
	    							reloadData('queryUnit.htm','#tt');
    							});
	                        } else {
	                            top.showAlert("提示", result.success, 'error');
	                        }
	                    }
	                });
				}
			},
		   	dataType:"json"
		});
		
	}	
	/**
	 * 描述：关闭窗口
	 */
	function closeUnit(){
		top. closeDlg("saveiframe");
	}
	
	var jso;
	var parentEventid = "";
	var eventID = getParamter("eventID");	
	$(function(){
		//加载初始数据
//		console.log(eventID)
		$.getJSON(rootPath+'jasframework/privilege/unit/findUnitById.do', {'oid':eventID},function(item, i){			
			jso=item;
//			console.log(jso);
			if(item.parentId){
				parenteventid = item.parentId
//				console.log(parenteventid+"1212~~~~~")
			 	if( parenteventid != ""){
					$("#parentId").combotree({
						url: addTokenForUrl(rootPath+'jasframework/privilege/unit/getLeftTree.do?isroot='+isroot),
						panelHeight:235,
						onSelect:function(node){
								var uniteventid=$("#oid").val();
								if(node.id==uniteventid){
									alert("上级部门不能为本部门");
									var unittree=$('#parentId').combotree("tree");
									var parentnone=unittree.tree("getParent",unittree.tree("getSelected").target);
									console.log(parentnone)
									$('#parentId').combotree("setValue",parentnone.id);
								}
						}
					});
					if("true"==isroot){
						$("#parentId").combotree("disable");
					}
				 }else{
					$("#parentId").attr("disabled", "disabled");
				} 
			}
			$('#editgroups').form('load',jso);
			setComboObjWidth('parentId',0.3,'combotree');
			setComboObjWidth('unitType',0.3,'combobox');
		});
	});
	
	
	$.ajaxSetup ({
	   		 cache: false 
		});
		function reload(){
		$.getJSON(rootPath+'jasframework/privilege/unit/findUnitById.do' ,
			{'oid':eventID
			},function(item, i){				
				jso=item;	
			
				$('#editgroups').form('load',jso);				
			});		
	}