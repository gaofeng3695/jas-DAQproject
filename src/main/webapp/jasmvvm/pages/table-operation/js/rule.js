(function (window) {
    var rule = {
        dbtype: "",
        verifyObj: function (obj, dbType, callback) {
            this.dbtype = dbType;
            if (this.verifyAttr(obj)) {
                callback();
            }
        },
        verifyAttr: function (obj, index) {
            var that = this;
            var falg = true;
            for (var key in obj) {
                try {
                    if (typeof (eval(key)) == "function") {
                        if (!window[key](obj[key], obj)) {
                            falg = false;;
                            return;
                        }
                    }
                } catch (e) {

                }
            }
            return falg;
        },
    };
    window.rule = rule;
})(window);

function dbType(param) {

    if (!param) {
        window.top.Vue.prototype.$message({
            message: '请选择数据库类型',
            type: 'warning'
        });
        return false;
    }
    return true;
}



function tableType(param) {
    if (!param) {
        window.top.Vue.prototype.$message({
            message: '请选择表类型',
            type: 'warning'
        });
        return false;
    }
    return true;
}

function tableName(param) {
    if (!param) {
        window.top.Vue.prototype.$message({
            message: '请输入表名称',
            type: 'warning'
        });
        return false;
    } else {
        reg = /^[a-zA-Z0-9_]+$/;
        if (!reg.test(param)) {
            window.top.Vue.prototype.$message({
                message: '表名称由字母，数字，下划线组成',
                type: 'warning'
            });
            return false;
        }
        return true;

    }

}

function tableNameCn(param) {
    if (!param) {
        window.top.Vue.prototype.$message({
            message: '请输入表中文名称',
            type: 'warning'
        });
        return false;
    }
    return true;

}

function fieldName(param) {
    param = param.trim();
    if (!param) {
        window.top.Vue.prototype.$message({
            message: '请输入数据库字段名称',
            type: 'warning'
        });
        return false;
    } else {
        reg = /^[a-zA-Z0-9_]+$/;
        if (!reg.test(param)) {
            window.top.Vue.prototype.$message({
                message: '数据库字段名称由字母，数字，下划线组成',
                type: 'warning'
            });
            return false;
        }
        return true;

    }

}



function fieldType(param) {
    param = param.trim();
    if (!param) {
        window.top.Vue.prototype.$message({
            message: '请选择字段类型',
            type: 'warning'
        });
        return false;
    }
    return true;
}

function fieldLength(param, obj) {
    param = param.trim();
    obj.fieldType = obj.fieldType.toLowerCase();
    if (rule.dbtype == "DT_03") {
        if (obj.fieldType == "smallint" || obj.fieldType == "integer" || obj.fieldType == "real" || obj.fieldType == "double" || obj.fieldType == "precision" || obj.fieldType == "text" || obj.fieldType == "date") {
            if (param.length > 0) {
                window.top.Vue.prototype.$message({
                    message: '不能有精度或者长度',
                    type: 'warning'
                });
                return false;
            }
        } else if (obj.fieldType == "varchar") {
            if (!param) {
                window.top.Vue.prototype.$message({
                    message: '请输入字段长度或者精度',
                    type: 'warning'
                });
                return false;
            }
        } else if (obj.fieldType == "timestamp" || obj.fieldType == "time") {
            if (param.length > 0) {
                var reg = /^\d{1,9}$/
                if (!reg.test(param.trim())) {
                    window.top.Vue.prototype.$message({
                        message: '格式应该为:n',
                        type: 'warning'
                    });
                    return false;
                }
            } else {
                return true;
            }
        }
    }

    if (rule.dbtype == "DT_02") {
        if (obj.fieldType == "smallint" || obj.fieldType == "tinyint" || obj.fieldType == "mediumint" || obj.fieldType == "int" || obj.fieldType == "text" || obj.fieldType == "mediumtext" || obj.fieldType == "longtext" || obj.fieldType == "blob" || obj.fieldType == "mediumblob" || obj.fieldType == "longblob" || obj.fieldType == "date") {
            if (param.length > 0) {
                window.top.Vue.prototype.$message({
                    message: '不能有精度或者长度',
                    type: 'warning'
                });
                return false;
            }
        } else if (obj.fieldType == "varchar") {
            if (!param) {
                window.top.Vue.prototype.$message({
                    message: '请输入字段长度或者精度',
                    type: 'warning'
                });
                return false;
            }
        } else if (obj.fieldType == "float" || obj.fieldType == "double" || obj.fieldType == "decimal") {
            if (param.length > 0) {
                var reg = /^\d{1,3}[,，]+\d{1,3}$/
                if (!reg.test(param.trim())) {
                    window.top.Vue.prototype.$message({
                        message: '格式应该为:n,m。n表示整数，m表示小数位',
                        type: 'warning'
                    });
                    return false;
                }
            } else {
                return true;
            }
        } else if (obj.fieldType == "timestamp" || obj.fieldType == "time" || obj.fieldType == "datetime") {
            if (param.length > 0) {
                var reg = /^\d{1,9}$/
                if (!reg.test(param.trim())) {
                    window.top.Vue.prototype.$message({
                        message: '格式应该为:n',
                        type: 'warning'
                    });
                    return false;
                }
            } else {
                return true;
            }
        }
    }
    if (rule.dbtype == "DT_01") {
        if (obj.fieldType == "integer" || obj.fieldType == "blob" || obj.fieldType == "clob" || obj.fieldType == "date") {
            if (param.length > 0) {
                window.top.Vue.prototype.$message({
                    message: '不能有精度或者长度',
                    type: 'warning'
                });
                return false;
            }
        } else if (obj.fieldType == "varchar2" || obj.fieldType == "nvarchar2") {
            if (!param) {
                window.top.Vue.prototype.$message({
                    message: '请输入字段长度或者精度',
                    type: 'warning'
                });
                return false;
            }
        }
    }
    return true;
}

function ifFk(param, obj) {
    if (param == "1" && obj.fkTable == undefined) {
        window.top.Vue.prototype.$message({
            message: '请选择外键相关表格与字段',
            type: 'warning'
        });
        return false;
    }

    return true;
}

function fkTable(param, obj) {
    if (obj.ifFk == "1" && !param) {
        window.top.Vue.prototype.$message({
            message: '请选择外键相关表格与字段',
            type: 'warning'
        });
        return false;
    }
    return true;
}


function ifPk(param, obj) {
    if (obj.ifPk == "1" && obj.ifIndex == "1") {
        window.top.Vue.prototype.$message({
            message: '主键与索引冲突',
            type: 'warning'
        });
        return false;
    }
    if ((obj.fieldType == "blob" || obj.fieldType == "clob" || obj.fieldType == "mediumblob" || obj.fieldType == "longblob") && param == '1') {
        window.top.Vue.prototype.$message({
            message: '字段类型为blob,clob时，不能作为主键',
            type: 'warning'
        });
        return false;
    }
    return true;
}