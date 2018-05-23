## 施工数据采集api

### 一、首页

#### 1. 获取左侧树一级菜单

> 获取单菜单列表 

``` POST /jasframework/privilege/privilege/getAllUserFuntion.do```

参数

[^注:目前三个参数的值是固定的,如下：]
``` json
{
	menutype: "0",
	appId: "402894a152681ba30152681e8b320003",
	language: "zh_CN" 
}
```

返回

``` json
[
  {
        "children": [
            {
                "attributes": {
                    "functionid": "51cfd72f-46c2-4803-8ee1-70d0bbf7be5e",
                    "URL": "jasframework/workflow/myundotask.htm"
                },
                "id": "P-WF-0020",
                "text": "待办工作"
            },
            {
                "attributes": {
                    "functionid": "3fbe1787-7653-4ed3-94b5-79de80c68e49",
                    "URL": "jasframework/workflow/mytask.htm"
                },
                "id": "P-WF-0040",
                "text": "已办工作"
            }
        ],
        "id": "7efdfc31-afed-4d49-91df-e153d6f60300",
        "text": "日常办公",
        "state": "closed"
    },
    {
        "children": [
            {
                "attributes": {
                    "functionid": "40288aee3bd0e9bd013bd0eb34a80001",
                    "URL": "jasframework/privilege/application/queryapplication.htm"
                },
                "id": "P-PRI-0020",
                "text": "应用系统管理"
            }
        ],
        "id": "0c85e571-6ab7-4272-89c7-195ef6d61071",
        "text": "权限管理",
        "state": "closed"
    }
]
```
### 一、权限

#### 1. 获取菜单下的功能按钮

> 获取单菜单列表 

``` POST jasframework/privilege/privilege/getFunctionConfig.do ```

参数

[^注:目前三个参数的值是固定的,如下：]
``` json
{
	privilegeCode: "P-SJ-0001", //菜单权限编号
	appId: "402894a152681ba30152681e8b320003"//应用id，值默认
}
```

返回

``` json
{
    "status": 1,
    "code": "200",
    "msg": "",
    "rows": [
        {
            "privilegeCode": "P-SJ-000101", //权限编号
            "hierarchy": "func.0001.0010.0001,0001",//层级编码
            "name": "新增",//功能点名称
            "oid": "b2d3273f-7779-487b-bd9b-3e1f61148620",//UUID
            "functionType": "bt_add",//功能类型
            "privilegeType": "2" //菜单类型
        },
        {
            "privilegeCode": "P-SJ-000102",
            "hierarchy": "func.0001.0010.0001,0002",
            "name": "修改",
            "oid": "e13c4018-1cc8-4932-ab7e-672aaba8ce46",
            "functionType": "bt_update",
            "privilegeType": "2"
        },
        {
            "privilegeCode": "P-SJ-000103",
            "hierarchy": "func.0001.0010.0001,0003",
            "name": "删除",
            "oid": "cd4470e6-370d-4579-9c41-781cbe66b514",
            "functionType": "bt_delete",
            "privilegeType": "2"
        },
        {
            "privilegeCode": "P-SJ-000104",
            "hierarchy": "func.0001.0010.0001,0004",
            "name": "查看",
            "oid": "bce31bd6-cb33-4981-925a-1499e762618e",
            "functionType": "bt_select",
            "privilegeType": "2"
        }
    ]
}
```