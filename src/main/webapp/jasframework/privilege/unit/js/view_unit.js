/** 
 * @file
 * @author  lujingrui
 * @desc 企业组织机构查看页面
 * @date  2017-09-25
 * @last modified by lujingrui
 * @last modified time  2017-09-25
 */
var oid = "";

$(function(){
	oid = getParamter("oid");
	loadForm();
});

function loadForm(){
	url = rootPath+'jasframework/privilege/unit/findUnitById.do?eventID='+oid;
	$.post(url, function(bo) {
//		$('#viewSiteForm').form('load', bo);
		$("#parentName").html(bo.parentName);
		$("#name").html(bo.unitName);
		$("#unitNum").html(bo.unitNum);
		$("#unitCode").html(bo.unitCode);
		$("#orderNum").html(bo.orderNum);
		if(bo.unitType == 1){
			$("#unitType").html("机关");
		}else if(bo.unitType == 2){
			$("#unitType").html("单位");
		}
		$("#phone").html(bo.phone);
		$("#address").html(bo.address);
		$("#description").html(bo.description);
		loadSelect(bo.unitType,bo.unitOrgType);
	}, 'json');
}

function loadSelect(unitType,unitOrgType){
	var url="";
	if(unitType == "1"){//部门对应项目组织机构类型
		url=rootPath+'jasframework/sysdoman/getsysdoman.do?domainname=unit_org_type_jg';
	}else{//单位对应项目组织机构类型
		url=rootPath+'jasframework/sysdoman/getsysdoman.do?domainname=unit_org_type_dw';
	}
	$.post(url, function(res) {
		for(var i=0;i<res.length;i++){
			if(res[i].codeid == unitOrgType){
				$("#unitOrgType").html(res[i].codename);
			}
		}
	}, 'json');
	
}
