(function(window) {
    var domain = {
        dbType: [],
        tableType: [],
        oracleFieldsType: [],
        mysqlFieldsType: [],
        pgFieldsType: [],
        domainType: ["db_type", "table_type", "oracle_fields_type", "mysql_fields_type", "pg_fields_type"],
        init: function() {
            this.requestDomainList();
        },
        requestDomainList: function() {
            var that = this;
            $.ajax({
                type: "post",
                url: jasTools.base.rootPath + "/customDict/getListByDictType.do?token=" + localStorage.getItem("token"),
                contentType: "application/json",
                data: JSON.stringify({ dictTypeList: that.domainType }),
                dataType: "json",
                success: function(data, status) {
                    if (data.status == 1) {
                        that.render(data.data);
                    }
                },

            });
        },
        render: function(data) {
            var that = this;
            data.forEach(function(item) {
                if (item.dictType == "db_type") {
                    that.dbType.push({
                        label: item.dictValue,
                        value: item.dictCode
                    });
                }
                if (item.dictType == "table_type") {
                    that.tableType.push({
                        label: item.dictValue,
                        value: item.dictCode
                    });
                }
                if (item.dictType == "oracle_fields_type") {
                    that.oracleFieldsType.push({
                        label: item.dictValue,
                        value: item.dictCode,
                        dictValueDesc:item.dictValueDesc
                    });
                }
                if (item.dictType == "mysql_fields_type") {
                    that.mysqlFieldsType.push({
                        label: item.dictValue,
                        value: item.dictCode,
                         dictValueDesc:item.dictValueDesc
                    });
                }
                if (item.dictType == "pg_fields_type") {
                    that.pgFieldsType.push({
                        label: item.dictValue,
                        value: item.dictCode,
                         dictValueDesc:item.dictValueDesc
                    });
                }
            });
        }
    };
    window.domain = domain;
})(window);