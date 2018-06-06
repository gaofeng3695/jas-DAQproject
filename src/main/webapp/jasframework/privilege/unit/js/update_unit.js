var isroot=getParamter("isroot");

var jso;
var parentEventid = "";
var eventID = getParamter("eventID");	
$(function(){
	//加载初始数据
	$.getJSON(rootPath+'jasframework/privilege/unit/findUnitById.do', {'eventID':eventID},function(item, i){			
		if(item.parentEventid){
			parenteventid = item.parenteventid
		 	if( parenteventid != ""){
				$("#parentId").combotree({
					url:rootPath+'treeView/getRootData.do',
				    queryParams:{
				    	"treeViewCode":"unitTreeUserListView"
				    },
				    panelHeight:"200",
				    onBeforeExpand:function(node,param){
				    	var tree=$("#parentId").combotree("tree"); 
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
				    	var tree=$("#parentId").combotree("tree"); 
				    	tree.tree('expandAll');
				    },
				    onSelect:function(node){
						var uniteventid=$("#eventid").val();
						console.log(uniteventid);
						if(node.id==uniteventid){
							alert("上级部门不能为本部门");
							var unittree=$('#parentId').combotree("tree");
							var parentnone=unittree.tree("getParent",unittree.tree("getSelected").target);
							$('#parentId').combotree("setValue",parentnone.id);
						}
					},
				    loader:function(param,success,error){
				    	var tree=$("#parentId").combotree("tree"); 
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
				/*	url: rootPath+'jasframework/privilege/unit/getLeftTree.do?isroot='+isroot,
					panelHeight:235,
					onSelect:function(node){
						var uniteventid=$("#eventid").val();
						console.log(uniteventid);
						if(node.id==uniteventid){
							alert("上级部门不能为本部门");
							var unittree=$('#parentEventid').combotree("tree");
							var parentnone=unittree.tree("getParent",unittree.tree("getSelected").target);
							$('#parentEventid').combotree("setValue",parentnone.id);
						}
					}*/
				});
				if("true"==isroot){
					$("#parentId").combotree("disable");
				}
			 }else{
				$("#parentId").attr("disabled", "disabled");
			} 
		}
		$.ajaxSettings.async = false;
		loadSelect();
		$('#editgroups').form('load',item);
		$.ajaxSettings.async = true;
//		setComboObjWidth('parentEventid',0.3,'combotree');
//		setComboObjWidth('unitType',0.3,'combobox');
	});
});

function loadSelect(){
	$('#unitOrgType').combobox({
//	    url:rootPath+'jasframework/sysdoman/getsysdoman.do?domainname=unit_org_type_jg',
	    valueField:'codeid',
	    textField:'codename',
	    onLoadSuccess:function(data){
//	    	$('#unitOrgType').combobox('setValue', data[0].codeid);
	    }
	});
	
	$("#unitType").combobox({
		onSelect:function(rec){
	    	var url="";
	    	if(rec.value == "1"){//部门对应项目组织机构类型
	    		url=rootPath+'jasframework/sysdoman/getsysdoman.do?domainname=unit_org_type_jg';
	    	}else{//单位对应项目组织机构类型
	    		url=rootPath+'jasframework/sysdoman/getsysdoman.do?domainname=unit_org_type_dw';
	    	}
	    	$('#unitOrgType').combobox('reload', url);
	    	$('#unitOrgType').combobox('clear');
	    }
	});
}

function reload(){
	$.getJSON(rootPath+'jasframework/privilege/unit/findUnitById.do' ,{'eventID':eventID},function(item, i){	
		$('#editgroups').form('load',item);				
	});		
}

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
	disableButtion("savebutton");
	url = rootPath+'jasframework/privilege/unit/updateUnit.do';
	var name = $("#name").val();
	var unitCode = $("#unitCode").val();
	var parentEventid = $("#parentId").combotree("getValue");
	var unittree=$("#parentId").combotree("tree");
	var unitobj=unittree.tree("find",eventID);
	var childrenunit=unittree.tree("getChildren",unitobj.target);
	var bool=false;
	$.each(childrenunit,function(i,n){
		if(parentEventid==n.id){
			top.showAlert("提示","上级部门不能为自己的子部门",'info');
                bool=true;
		}
	});
	if(bool){
		return ;
	}
	var validateResault = $('#editgroups').form("validate");
	if(validateResault == false){
		top.showAlert("提示", "表单验证失败", 'info');
		enableButtion("saveButton");
		return validateResault;
	}
	//检验部门是否存在
	$.ajax({
		type: "POST",
	    url: rootPath + "jasframework/privilege/unit/checkUnitExist.do",
	    data:JSON.stringify( { "parentId": $("#parentId").combotree("getValue"), "unitName": name, "unitCode": unitCode }),
	    dataType: "json",
	   	success: function(check){
     		if (check.error=='-1'){
				top.showAlert("提示",'编号或名称重复!','error');
			} else{
				$.ajax({
					type: "POST",
				   	url: url,
			   		data: $("#editgroups").serializeToJson(),
			   		success:function(result){
						if (result.success){
							top.showAlert("提示","修改成功",'ok',function(){
								getDcumentObject("tree_tab.html?treeViewCode=unitTreeUserListView").reloadNode(eventID,true);//刷新父节点
								closeUnit();
							});
						} else {
							top.showAlert("错误","保存失败!",'error');
							enableButtion("savebutton");
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
	
	