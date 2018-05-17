/**
 * 描述：重新加载数据
 * @param shortUrl 重新加载数据的页面
 * @param elementId 权限书的id
 */
function reloadData(shortUrl, elementId){
		var fra= parent.$("iframe");
		for(var i=0; i<fra.length;i++) {
			if(fra[i].src.indexOf(shortUrl) != -1) {
				
				fra[i].contentWindow.selectappsystem();
			}
		}
	}


function reloadtree(shortUrl,parenteventid){
	var fra= parent.$("iframe");
	for(var i=0; i<fra.length;i++) { 
		if(fra[i].src.indexOf(shortUrl) != -1) {
			fra[i].contentWindow.reloadchildren(parenteventid);
		}
	}
}
	var parentid=getParamter("parentid");
	var appnumber=getParamter("appnumber");
//页面初始化
	$(document).ready(function(){
		getappsystem();
		$("#privilegetype").combobox({"onChange":function(){
			onchangmenu();
		}});
		initcombotree();
		
	});
	
	function initcombotree(){
		$("#parenteventid").combotree({
			url: rootPath+"jasframework/privilege/menu/getAllMenuByAppnumber.do?appnumber="+getParamter("appnumber")+"&random=" + new Date().getTime(),
			onLoadSuccess:function(data){
					$("#parenteventid").combotree("setValue",parentid);
			}
		});
		setComboObjWidth('parenteventid',0.3,'combotree');
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
					onSelect:function(data){
						var systemurl=data.url;
						$("#privilegeid").combotree({
							url :  rootPath+"jasframework/privilege/privilege/getAllPrivilegeByAppnumber.do?appnumber="+data.appnumber+"&random=" + new Date().getTime(),
							onClick:function(node){
//								alert(JSON.stringify(node));
								$('#ordernumber').val(node.attributes.privilegeNumber);
								$('#privilegenumber').val(node.attributes.privilegeNumber);
//								var url=node.attributes.url.split("../");
//								url=url[url.length-1];
//								if(url!="null" && url!=""){
//									if(systemurl==undefined ||systemurl==""){
//										$('#url').val(rootPath+url);
//									}else{
//										$('#url').val(systemurl+url);
//									}
//								}
								$('#url').val(node.attributes.url);
							}
						});
						setComboObjWidth('privilegeid',0.3,'combotree');
					},onLoadSuccess:function(data){
							$(this).combobox("select",appnumber);
					}
					}); 
				$('#appnumber').combobox("setValue",getParamter("appnumber"));
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
		var checkurl=rootPath+"jasframework/privilege/menu/checkmenuname.do?appnumber="+getParamter("appnumber")+"&menuname="+$("#name").val();
		$.getJSON(checkurl,function(check){
			if(check.success=="1"){
				$('#addmenu').form('submit',{
					url: rootPath+"/jasframework/privilege/menu/savemenu.do?appnumber="+getParamter("appnumber"),
					onSubmit: function(){
						var bool=$(this).form('validate');
						if(bool==false){
							enableButtion("savebutton");
						}
						return bool;
					},
					success: function(result){
						result=jQuery.parseJSON(result);
					 if(result.success==1){
						 reloadtree("querymenu.htm",parentid);
						 closePrivilege();
					 }else{
						 alert('新增失败');
					 }
					}
				});
			}else{
				top.showAlert(getLanguageValue("tip"), check.msg, 'error');
			}
		});
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
	
	