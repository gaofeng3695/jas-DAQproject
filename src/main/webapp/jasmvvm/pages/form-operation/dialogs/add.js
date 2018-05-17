Vue.use(window.VueCodemirror)

var vm = new Vue({
  el: '#app',
  data: function () {
    return {
      mirrorConfig: {
        mode: 'text/x-sql',
        lineWrapping: true,
        indentWithTabs: true,
        smartIndent: true,
        lineNumbers: true,
        matchBrackets: true,
        viewportMargin: Infinity,
        extraKeys: {
          "Ctrl-Space": "autocomplete"
        },

      },
      indexPage: 0,
      ruleForm: {
        name: '',
        source: '',
        ifAttachment: '0',
        select: '',
        delete: '',
        add: '',
        update: '',
        detail: '',
        isSqlError: false,
      },
      currentSql: '',
      isLoadingSql: false,
      isLoadingFieldInfo: false,
      firstRules: {
        name: [{
          required: true,
          message: '功能名称不能为空',
        }],
        source: [{
          required: true,
          message: '数据来源不能为空',
        }]
      },
      headStyle: {
        'background-color': '#f5f7fa ',
      },
      privateTable: [],
      filterTable: [],
      uiTypeOption: [],
      regexpOption: [],
      activeName: 'first',
      // 分组相关
      unsortList: [],
      listList: [],
      queryList: [],
      rows: [ //
        {
          name: '基础表单',
          list: [],
        }
      ],
      dragOptions: {
        group: 'description',
      }
    }
  },
  computed: {
    selectNames: function () {
      var that = this;
      return this.filterTable.filter(function (item) {
        return that.isSqlSelect(item.uiType)
      });
    }
  },
  created: function () {
    var that = this;

    // 获取数据库字段替换配置
    $.getJSON('./../js/functionConfig.json', function (json) {
      that.fieldParams = json.fieldParams;
      // console.log(json)
    });

    // 获取正则配置
    $.getJSON('./../js/regexp.json', function (json) {
      that.regexpOption = json.rows;
    });

    // 获取功能详情
    this.functionId = jasTools.base.getParamsInUrl(location.href).functionId;
    if (this.functionId) {
      this._requestFunctionDetail(this.functionId);
    }


  },
  mounted: function () {
    var that = this;

    jasTools.base.showLoading();
  },
  methods: {
    isUi: function (type) {
      return !!type;
    },
    isText: function (type) {
      return type === 'UT_01' && this.isUi(type);
    },
    isSql: function (row) {
      var type = row.uiType;
      var sql = ['UT_05', 'UT_07', 'UT_09', 'UT_11'];
      return sql.indexOf(type) !== -1 && this.isUi(type);
    },
    isUpdateable: function (row) {
      var type = row.uiType;
      return +row.ifUpdate && this.isUi(type);
    },
    isNumber: function (row) {
      var type = row.uiType;
      return type === 'UT_14' && this.isUi(type);
    },
    isPlaceholder: function (row) {
      var type = row.uiType;
      var selectBox = ['UT_05', 'UT_06', 'UT_07', 'UT_08'];
      return selectBox.indexOf(type) === -1 && this.isUi(type);
    },
    isJson: function (row) {
      var type = row.uiType;
      var json = ['UT_06', 'UT_08', 'UT_10', 'UT_12'];
      return json.indexOf(type) !== -1 && this.isUi(type);
    },
    isSqlSelect: function (type) {
      return type === 'UT_11' && this.isUi(type);
    },
    isUrl: function (row) {
      var type = row.uiType;
      return this.isSqlSelect(type) && !!row.childField;
    },
    isTableSource: function (row) {
      var type = row.fieldSource;
      return type === 'table';
    },
    selectSqlCard: function (card) {
      if (card) {
        this.currentSql = card;
      }
      this.$refs['cm_' + card].refresh();
    },

    _requestFunctionDetail: function (oid) { // 请求功能详情
      var that = this;
      jasTools.ajax.get(jasTools.base.rootPath + '/functionConfiguration/get.do', {
        id: oid
      }, function (data) {
        that.ruleForm.name = data.data.functionName;
        that.ruleForm.ifAttachment = data.data.ifAttachment;
        that.ruleForm.source = data.data.tableName;
        that.ruleForm.select = data.data.querySql;
        that.ruleForm.delete = data.data.deleteSql;
        that.ruleForm.update = data.data.updateSql;
        that.ruleForm.detail = data.data.detailsSql;
        that.ruleForm.add = data.data.saveSql;
      });

    },
    renderTableSelect: function (queryString, cb) { // 渲染数据来源可选项
      var that = this;

      var filterTableSourceArr = function (str, arr) {
        if (str) {
          var val01 = str.toUpperCase();
          var val02 = str.toLowerCase();
          return arr.filter(function (item) {
            return item.value.indexOf(val01) !== -1 || item.value.indexOf(val02) !== -1;
          });
        } else {
          return arr.slice(0, 20);
        }
      };

      if (this.tableArr) {
        var arr = filterTableSourceArr(queryString, this.tableArr);
        cb(arr);
      } else {
        jasTools.ajax.get(jasTools.base.rootPath + '/tableInfo/getDbTableNames.do', {
          keyword: ''
        }, function (data) {
          that.tableArr = data.data.map(function (item) {
            return {
              value: item
            }
          });
          var arr = filterTableSourceArr(queryString, that.tableArr);
          cb(arr);
        });
      }
    },
    chooseTableSelected: function (item) { // 选中数据来源,请求相关字段
      var that = this;
      that.isLoadingSql = true;
      var tableName = item.value;
      jasTools.ajax.get(jasTools.base.rootPath + '/tableField/getDbColumnInfo.do', {
        tableName: tableName
      }, function (data) {
        that.isLoadingSql = false;
        if (data.data.length > 0) {
          that.ruleForm.isSqlError = 0;
          that._formatSqlByFields(tableName, data.data);
        } else {
          window.top.Vue.prototype.$message({
            message: '该数据库没有字段，请重新选择',
            type: 'error'
          });
        }
      });
    },

    _formatSqlByFields: function (tableName, fields) { // 通过字段数组，格式化sql
      var that = this;
      that.tableName = tableName;
      var mainField = ''; //主键
      that.fieldArr = fields.map(function (item) {
        if (item.ifPrimaryKey) {
          mainField = item.columnName;
        }
        return item.columnName
      });

      var modifyStr = that.fieldArr.map(function (item) {
        var newItem = item;
        if (that.fieldParams && that.fieldParams[item]) {
          newItem = that.fieldParams[item];
        }
        return newItem;
      });
      var formatUpdateStr = function () {
        var arr = that.fieldArr.map(function (field, index) {
          return field + '=:' + modifyStr[index];
        });
        return arr.join(', ')
      };

      that.ruleForm.select = [
        'SELECT ',
        that.fieldArr.join(', '),
        ' FROM ',
        tableName,
        ' WHERE 1 = 1 ',
        that.fieldArr.indexOf('active') > -1 ? ' AND active = 1' : '',
        that.fieldArr.indexOf('ACTIVE') > -1 ? ' AND ACTIVE = 1' : '',
        ' @where_append ',
      ].join('');

      that.ruleForm.delete = [
        'UPDATE ',
        tableName,
        ' SET ',
        that.fieldArr.indexOf('active') > -1 ? 'active' : 'ACTIVE',
        ' = 0',
        ' WHERE ',
        mainField,
        ' = :',
        mainField
      ].join('');

      that.ruleForm.add = [
        'INSERT INTO ',
        tableName,
        '( ',
        that.fieldArr.join(', '),
        ' ) VALUES ( :',
        modifyStr.join(', :'),
        ' )'
      ].join('');


      that.ruleForm.update = [
        'UPDATE ',
        tableName,
        ' SET ',
        formatUpdateStr(),
        ' WHERE ',
        mainField,
        ' = :',
        mainField,
        that.fieldArr.indexOf('active') > -1 ? ' AND active = 1' : '',
        that.fieldArr.indexOf('ACTIVE') > -1 ? ' AND ACTIVE = 1' : ''
      ].join('');

      that.ruleForm.detail = [
        'SELECT ',
        that.fieldArr.join(', '),
        ' FROM ',
        tableName,
        ' WHERE ',
        mainField,
        ' = :',
        mainField,
        that.fieldArr.indexOf('active') > -1 ? ' AND active = 1' : '',
        that.fieldArr.indexOf('ACTIVE') > -1 ? ' AND ACTIVE = 1' : ''
      ].join('');
    },

    saveFunctionResource: function (isClose, event) { // 保存功能数据源配置
      var that = this;
      var obj = this.ruleForm;
      var oData = {
        functionName: obj.name,
        ifAttachment: obj.ifAttachment,
        tableName: obj.source,
        querySql: obj.select,
        deleteSql: obj.delete,
        saveSql: obj.add,
        updateSql: obj.update,
        detailsSql: obj.detail
      };
      this.$refs.ruleForm.validate(function (valid) {
        if (valid) {

          if (that.functionId) { // 修改
            oData.oid = that.functionId;
            var url = jasTools.base.rootPath + '/functionConfiguration/update.do';
          } else { // 新增
            var url = jasTools.base.rootPath + '/functionConfiguration/save.do';
          }

          jasTools.ajax.post(url, oData, function (data) {
            that.functionId = data.data;
            that.ruleForm.isSqlError = 0;
            that.isLoadingFieldInfo = true;
            if (isClose == 1) {
              window.top.Vue.prototype.$message({
                message: '保存成功',
                type: 'success'
              });
              window.top.jasTools.dialog.close(1);
            } else {
              that._getFunctionFieldInfo(that.functionId);
              that.indexPage++;
            }
          }, function (data) {
            if (data.code === 'QUERY_SQL_ERR') {
              that.ruleForm.isSqlError = 1;
            }
            window.top.Vue.prototype.$message({
              message: data.msg,
              type: 'error'
            });
          });


        } else {
          return false;
        }
      });
    },
    _getFunctionFieldInfo: function (fId) { // 获取功能字段信息
      var that = this;

      jasTools.ajax.get(jasTools.base.rootPath + '/functionFields/getFunConfigurationFields.do', {
        functionId: fId
      }, function (data) {
        that.isLoadingFieldInfo = false;
        that.privateTable = data.data.map(function (obj) {
          return {
            functionId: fId,
            fieldName: obj.fieldName,
            fieldNameCn: obj.fieldNameCn,
            fieldSource: obj.fieldSource,
            ifSave: obj.ifSave || "0",
            ifUpdate: obj.ifUpdate || "0",
            ifQuery: obj.ifQuery || "0",
            ifList: obj.ifList || "0",
            ifDetails: obj.ifDetails || "0",
            fieldLength: obj.fieldLength,
            fieldType: obj.fieldType,
            uiType: obj.uiType || "",
            domain: obj.domain || null,
            regularExpression: obj.regularExpression || null,
            groupName: obj.groupName || null,
            placeholder: obj.placeholder || "",
            childField: obj.childField || null,
            requestPath: obj.requestPath || null,
            ifRequired: obj.ifRequired || "0",
            ifPrimaryKey: obj.ifPrimaryKey,
            groupIndex: obj.groupIndex,
            ifPrimaryKey: obj.ifPrimaryKey,
            rowIndex: obj.rowIndex,
            listIndex: obj.listIndex,
            queryIndex: obj.queryIndex,
            updateable: obj.updateable,
            min: obj.min,
            max: obj.max,
          };
        });
      });

    },
    changeFieldIfAdd: function (row) {
      row.ifDetails = row.ifSave;
      row.ifUpdate = row.ifSave;
      row.ifList = row.ifSave;
    },
    goToUiTable: function () {

      var arr = [];
      var error = 0;
      this.privateTable.forEach(function (item) {
        if (+item.ifQuery || +item.ifSave || +item.ifUpdate) {
          arr.push(item);
        }
        if (+item.ifQuery || +item.ifSave || +item.ifUpdate || +item.ifList || +item.ifDetails) {
          if (!item.fieldNameCn) {
            error = 1;
          }
        }
      });
      if (error) {
        window.top.Vue.prototype.$message({
          message: '需要展示的字段，字段别名不能为空',
          type: 'error'
        });
        return;
      }
      this.indexPage++;
      this.filterTable = arr;
      this._requestUiTypes();
    },
    _requestUiTypes: function () { //获取ui类型阈值
      var that = this;
      jasTools.ajax.post(jasTools.base.rootPath + '/customDict/getListByDictType.do', {
        dictTypeList: ['ui_type']
      }, function (data) {
        that.uiTypeOption = data.data.map(function (item) {
          return {
            id: item.dictCode,
            name: item.dictValue,
          }
        });
      });
    },

    goToSort: function () { // 前往字段分组排序页面
      var error = 0;
      //验证UI类型是否为空
      this.filterTable.forEach(function (item) {
        if (!item.uiType) error = 1;
      });
      if (error) {
        window.top.Vue.prototype.$message({
          message: 'UI类型为必填项',
          type: 'error'
        });
        return;
      }
      this._formatSortArray();
      this.indexPage++;
    },

    _formatSortArray: function () { // 整理分组列表
      var arr = []; // 存放未分组的字段
      var listList = []; // 存放列表字段
      var queryList = []; // 存放搜索字段
      var sortObj = {}; // 按组名对字段进行分组存放
      var ifHasSortable = false;
      this.privateTable.forEach(function (item) {
        if (+item.ifDetails || +item.ifSave || +item.ifUpdate) {
          var group = item.groupName;
          if (!group) { // 整理未分组字段
            arr.push({
              fieldName: item.fieldName,
              fieldNameCn: item.fieldNameCn,
              rowIndex: item.rowIndex
            });
          } else { // 整理分组的字段
            ifHasSortable = true;
            if (sortObj[group]) {
              sortObj[group].push({
                fieldName: item.fieldName,
                fieldNameCn: item.fieldNameCn,
                rowIndex: item.rowIndex
              });
            } else {
              sortObj[group] = [{
                fieldName: item.fieldName,
                fieldNameCn: item.fieldNameCn,
                rowIndex: item.rowIndex
              }];
            }
          }
        }
        if (+item.ifQuery) {
          queryList.push({
            fieldName: item.fieldName,
            fieldNameCn: item.fieldNameCn,
            queryIndex: item.queryIndex
          });
        }
        if (+item.ifList) {
          listList.push({
            fieldName: item.fieldName,
            fieldNameCn: item.fieldNameCn,
            listIndex: item.listIndex
          });
        }
      });


      // 未分组字段排序
      arr.sort(function (a, b) {
        return a.rowIndex - b.rowIndex
      });
      this.unsortList = arr;

      // 列表字段排序
      listList.sort(function (a, b) {
        return a.listIndex - b.listIndex
      });
      this.listList = listList;

      // 查询字段排序
      queryList.sort(function (a, b) {
        return a.queryIndex - b.queryIndex
      });
      this.queryList = queryList;


      // 格式化已分组的字段
      if (ifHasSortable) {
        this.rows = [];
        for (var item in sortObj) {
          if (sortObj.hasOwnProperty(item)) {
            sortObj[item].sort(function (a, b) { // 字段排序
              return a.rowIndex - b.rowIndex
            });
            this.rows.push({
              name: item,
              list: sortObj[item],
            })
          }
        }
        this.rows.sort(function (a, b) {
          if (a.list[0] && b.list[0]) {
            return b.list[0].groupIndex - a.list[0].groupIndex
          }
        });
      } else {
        this.rows = [{
          name: '基础表单',
          list: [],
        }]
      }

    },
    addSortRow: function () { // 添加分组
      this.rows.push({
        name: '',
        list: [],
      });
    },
    deleteSortRow: function (index) { // 删除分组
      if (this.rows[index].list.length > 0) {
        // this.unsortList.push.apply(null,this.rows[index].list);
        this.unsortList = this.unsortList.concat(this.rows[index].list);
      }
      this.rows.splice(index, 1);
    },

    _formatSortInfo: function () { // 将分组信息合并到原始字段数据上
      var that = this;
      var error = false;
      this.rows.forEach(function (row) {
        if (!row.name) {
          error = true;
          window.top.Vue.prototype.$message({
            message: '请填写分组名称',
            type: 'error'
          });
        }
      });
      if (error) return false;

      var rowIndexObj = {}; // 排序的map

      this.rows.forEach(function (row, groupIndex) {
        row.list.forEach(function (item, rowIndex) {
          rowIndexObj[item.fieldName] = rowIndex + 1;
        });
      });

      this.unsortList.forEach(function (item, rowIndex) {
        rowIndexObj[item.fieldName] = rowIndex + 1;
      });
      this.queryList.forEach(function (item, index) {
        rowIndexObj[item.fieldName + '_query'] = index + 1;
      });
      this.listList.forEach(function (item, index) {
        rowIndexObj[item.fieldName + '_list'] = index + 1;
      });

      this.privateTable.forEach(function (item) {
        item.groupName = '';
        item.groupIndex = '';
        item.rowIndex = '';
        item.listIndex = '';
        item.queryIndex = '';

        var name = item.fieldName;
        item.rowIndex = rowIndexObj[item.fieldName];
        item.listIndex = rowIndexObj[item.fieldName + '_list'];
        item.queryIndex = rowIndexObj[item.fieldName + '_query'];
        var isInRow = false;

        that.rows.forEach(function (row, groupIndex) { // 判断字段在哪个分组里
          if (JSON.stringify(row).indexOf(name) !== -1) {
            item.groupName = row.name;
            item.groupIndex = groupIndex + 1;
            isInRow = true;
          }
        });

        if (!isInRow) {
          item.groupName = '';
          item.groupIndex = '';
          item.rowIndex = '';
        }

      });
      return true;
    },
    close: function () {
      window.parent.jasTools.dialog.close();
    },

    saveFieldConfig: function () { // 保存字段配置信息
      var isSort = this._formatSortInfo();
      if (isSort) {
        jasTools.ajax.post(jasTools.base.rootPath + '/functionFields/save.do', {
          funFunctionFieldsForms: this.privateTable,
        }, function (data) {
          parent.window.jasTools.dialog.close(1);
          window.top.Vue.prototype.$message({
            message: '保存成功',
            type: 'success'
          });
        });
      }
    },

  },
});