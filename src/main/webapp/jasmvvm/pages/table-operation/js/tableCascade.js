Vue.component('table-cascader', {
    props: {
        value: {
            type: [Array, String],
            default: ''
        },
        currentfields: {
            type: [Array, Object, String],
            default: ''
        },
        index: {
            type: [Array, Object, String,Number],
            default: 0
        }
    },
    data: function () {
        return {
            props: {
                label: 'label',
                value: 'value',
                children: 'child',
            },
            tableOptions: [],
            selectValue: '', //表示选中的
            currentSelectTableName: '',
        }
    },
    template: [' <el-cascader clearable size="small" ',
        ':value="defaultValue" ',
        ':options="tableOptions" ',
        '@change="change" ',
        '@active-item-change="handleItemChange" ',
        ':props="props" clearable></el-cascader>'
    ].join(''),
    computed: {
        defaultValue: function () {
            var array = [];
            array.push(this.currentfields.fkTableId);
            array.push(this.currentfields.fkTableField);
            return this.selectValue || array;
        }
    },
    mounted: function () {
        if (Vue.prototype.$isRequestField) {
            return;
        }
        Vue.prototype.$isRequestField = true;
        this.requestTable();
    },
    methods: {
        requestTable: function () {
            var that = this;
            // if (Vue.prototype.tableOptions) {
            //     that.tableOptions = Vue.prototype.tableOptions
            //     return;
            // } else {
            $.ajax({
                type: "get",
                url: jasTools.base.rootPath + "/tableInfo/getCustomTableNames.do?token=" + localStorage.getItem("token"),
                contentType: "application/json",
                dataType: "json",
                success: function (data, status) {
                    if (data.status == 1) {
                        data.data.forEach(function (item) {
                            var obj = {
                                label: item.tableName,
                                value: item.oid,
                                child: []
                            };
                            that.tableOptions.push(obj);
                        });
                        // Vue.prototype.tableOptions = that.tableOptions;
                    }
                },
                complete: function () {
                    Vue.prototype.$isRequestField = false;

                }
            });
            // }
            if (that.currentfields.fkTableField) {
                that.requestFieldsByid(that.currentfields.tableId);
            }
        },
        handleItemChange: function (val) { //此时获取到的只是表的id
            this.requestFieldsByid(val[0]);
        },
        requestFieldsByid: function (id) {
            var that = this;
            $.ajax({
                type: "post",
                url: jasTools.base.rootPath + "/tableField/getPage.do?token=" + localStorage.getItem("token"),
                contentType: "application/json",
                data: JSON.stringify({
                    "rows": 100,
                    "page": 1,
                    "tableId": id
                }),
                dataType: "json",
                success: function (data, status) {
                    if (data.status == 1) {
                        var child = [];
                        data.rows.forEach(function (item) {
                            var obj = {
                                label: item.fieldName,
                                value: item.fieldName,
                            };
                            child.push(obj);
                        });
                        that.tableOptions.forEach(function (item) {
                            if (item.value == id) {
                                item.child = child;
                                that.currentSelectTableName = item.label;
                                return;
                            }
                        });
                    }
                }
            });
        },

        change: function (val) {
            this.selectValue = val;
            this.$emit('change',this.index, this.currentSelectTableName, val);
        }
    }
});