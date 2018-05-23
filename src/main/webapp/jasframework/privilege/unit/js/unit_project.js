/**
	 * 描述：增加新部门
	 */
	function addUnitOrg() {
		top.getDlg(rootPath+"privilege/unit/add_unit.htm?parentid=" + currentSelectNode, "saveiframe", "新增", 800, 400);
	}
	/**
	 * 描述：修改按钮事件
	 */
	function updateUnittOrg() {
		var eventID;
		var isroot=false;
		var row = $('#dataTree').tree('getSelected');
		var treeroot=$("#dataTree").tree("getRoot");
		if(row.id==treeroot.id){
			isroot=true;
		}
		
		url = "updateUnit.htm?isroot="+isroot+"&eventID=" + row.id;
		top.getDlg(rootPath+"privilege/unit/update_unit.htm?isroot="+isroot+"&eventID=" + row.id, "saveiframe","修改", 800, 400);
	}
	
	/**
	 * 描述：删除部门
	 */
	function deleteUnitOrg() {
		//确认删除
		$.messager.confirm("提示", "确认删除？", function(r) {
			if (r) {
				var url = rootPath+'jasframework/privilege/unit/removeUnitById.do?eventId=' + currentSelectNode;
				//执行删除
				$.post(url, function(result) {
					if (result.success) {
						top.showAlert("提示", result.ok, 'ok', function() {
							reloadNode(currentSelectNode,true)
						});
								 	
					} else {
						top.showAlert("提示", result.msg , 'error');
						return;
					}
				}, 'json');

			}
		});
	}