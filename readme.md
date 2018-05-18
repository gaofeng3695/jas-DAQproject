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