// dtagrid table 进行封装
/**
 * 2018-03-08
 */
Vue.component('el-expend-table', {
    name: "eltable",
    props: {
        tableattr: {
            type: [Object, Array],
            default: '',
        },
        tabledata: {
            type: [Object, Array],
            defaule: '',
        },
        tabledes: {
            type: [Object],
            defaule: '',
        }
    },
    template: [
        '<el-table  :data="tabledata" ',
        // ':border="tableattr.border" ',
        // ':height="tableattr.height" ',
        // ':max-height="tableattr.maxHeight" ',
        // ':stripe="tableattr.stripe" ',
        // ':size="tableattr.size" ',
        // ':fit="tableattr.fit" ',
        // ':show-header=tableattr.showHeader ',
        // ':highlight-current-row=tableattr.highlightCurrentRow ',
        // ':current-row-key=tableattr.currentRowKey ',
        // ':row-class-name=tableattr.rowClassName ',
        // ':row-style=tableattr.rowStyle ',
        // ':cell-class-name=tableattr.cellClassName ',
        // ':cell-style=tableattr.cellStyle ',
        // ':header-row-class-name=tableattr.headerRowClassName ',
        // ':header-row-style=tableattr.headerRowStyle ',
        // ':header-cell-class-name=tableattr.headerCellClassName ',
        // ':header-cell-style=tableattr.headerCellStyle ',
        // ':row-key=tableattr.rowKey ',
        // ':empty-text=tableattr.emptyText ',
        // ':default-expand-all=tableattr.defaultExpandAll ',
        // ':expand-row-keys=tableattr.expandRowKeys ',
        // ':default-sort=tableattr.defaultSort ',
        // ':tooltip-effect=tableattr.tooltipEffect ',
        // ':show-summary=tableattr.showSummary ',
        // ':sum-text=tableattr.sumText ',
        // ':summary-method=tableattr.summaryMethod ',
        // ':span-method=tableattr.spanMethod ',
        '@select="select" ',
        '@select-all="selectAll" ',
        '@selection-change="selectionChange" ',
        '@cell-mouse-enter="cellMouseEnter" ',
        '@cell-mouse-leave="cellMouseLeave" ',
        '@cell-click="cellClick" ',
        '@cell-dblclick="cellDblclick" ',
        '@row-click="rowClick" ',
        '@row-contentmenu="rowContentMenu" ',
        '@row-dblclick="rowDblclick" ',
        '@header-click="headerClick" ',
        '@header-contextmenu="headerContextMenu" ',
        '@sort-change="sortChange" ',
        '@current-change="currentChange" ',
        '@header-dragend="headerDragend" ',
        '@expand-change="expandChange"',
        '>',
        '<template v-for="(item,key) in tabledes.parent">',
        '<template  v-if="item.type==\'expand\'">',
        '<el-table-column :fixed="item.fixed" type="expand" :align="item.align" v-if="tabledes.child&&tabledes.child.parent&&tabledes.child.parent.length>0">',
        '<template slot-scope="props">',
        '<eltable :tabledata="props.row.childdata" :tabledes="tabledes.child" :tableattr="tableattr"></eltable>',
        '</template>',
        '</el-table-column>',
        '</template>',
        '<template v-else-if="item.type==\'operation\'">',
        '<el-table-column :width="item.width" :fixed="item.fixed" :label="item.name" :align="item.align">',
        '<template slot-scope="scope">',
        '<el-button v-for="btn in item.btns" @click="btn.click(scope.row)" type="text" size="small">{{btn.btnName}}</el-button>',
        '</template>',
        '</el-table-column>',
        '</template>',
        '<template  v-else>',
        '<el-table-column  :width="item.width" :type="item.type" :fixed="item.fixed" :index="item.indexMethod" :key="key"  :label="item.name" :prop="item.id" :align="item.align">',
        '</el-table-column>',
        '</template>',
        '</template>',
        '</el-table>',
    ].join(''),
    mounted: function () {

    },
    methods: {
        select: function (selection, row) {
            this.$emit('select', selection, row);
        },
        selectAll: function (selection) {
            this.$emit('selectall', selection);
        },
        selectionChange: function (selection) {
            this.$emit('selectionchange', selection);
        },
        cellMouseEnter: function (row, column, cell, event) {
            this.$emit('cellmouseenter', row, column, cell, event);
        },
        cellMouseLeave: function (row, column, cell, event) {
            this.$emit('cellmouseenter', row, column, cell, event);
        },
        cellClick: function (row, column, cell, event) {
            this.$emit('cellmouseenter', row, column, cell, event);
        },
        cellDblclick: function (row, column, cell, event) {
            this.$emit('cellmouseenter', row, column, cell, event);
        },
        rowClick: function (row, event) {
            this.$emit('rowclick', row, event);
        },
        rowContentMenu: function (row, event) {
            this.$emit('rowcontentmenu', row, event);
        },
        rowDblclick: function (row, event) {
            this.$emit('rowdblclick', row, event);
        },
        headerClick: function (column, event) {
            this.$emit('headerclick', column, event);
        },
        headerContextMenu: function (column, event) {
            this.$emit('headercontextmenu', column, event);
        },
        sortChange: function (obj) {
            this.$emit('sortchange', obj);
        },
        currentChange: function (currentRow, oldCurrentRow) {
            this.$emit('currentchange', currentRow, oldCurrentRow);
        },
        headerDragend: function (newWidth, oldWidth, column, event) {
            this.$emit('headerdragend', newWidth, oldWidth, column, event);
        },
        expandChange: function (row, expandedRows) {
            this.$emit('expandchange', row, expandedRows);
        },
    }
});

Vue.component('el-common-table', {
    props: {
        tableattr: {
            type: [Object, Array],
            default: '',
        },
        tabledata: {
            type: [Object, Array],
            default: '',
        },
        tabledes: {
            type: [Object, Array],
            default: '',
        },
        tablepage: {
            type: [Object, String, Array],
            default: "",
        }
    },
    template: [
        '<div><el-table :data="tabledata" ',
        ':border="tableattr.border" ',
        ':height="tableattr.height" ',
        ':max-height="tableattr.maxHeight" ',
        ':stripe="tableattr.stripe" ',
        ':size="tableattr.size" ',
        ':fit="tableattr.fit" ',
        ':show-header=tableattr.showHeader ',
        ':highlight-current-row=tableattr.highlightCurrentRow ',
        ':current-row-key=tableattr.currentRowKey ',
        ':row-class-name=tableattr.rowClassName ',
        ':row-style=tableattr.rowStyle ',
        ':cell-class-name=tableattr.cellClassName ',
        ':cell-style=tableattr.cellStyle ',
        ':header-row-class-name=tableattr.headerRowClassName ',
        ':header-row-style=tableattr.headerRowStyle ',
        ':header-cell-class-name=tableattr.headerCellClassName ',
        ':header-cell-style=tableattr.headerCellStyle ',
        ':row-key=tableattr.rowKey ',
        ':empty-text=tableattr.emptyText ',
        ':default-expand-all=tableattr.defaultExpandAll ',
        ':expand-row-keys=tableattr.expandRowKeys ',
        ':default-sort=tableattr.defaultSort ',
        ':tooltip-effect=tableattr.tooltipEffect ',
        ':show-summary=tableattr.showSummary ',
        ':sum-text=tableattr.sumText ',
        ':summary-method=tableattr.summaryMethod ',
        ':span-method=tableattr.spanMethod ',
        '@select="select"',
        '@select-all="selectAll"',
        '@selection-change="selectionChange"',
        '@cell-mouse-enter="cellMouseEnter"',
        '@cell-mouse-leave="cellMouseLeave"',
        '@cell-click="cellClick"',
        '@cell-dblclick="cellDblclick"',
        '@row-click="rowClick"',
        '@row-contentmenu="rowContentMenu"',
        '@row-dblclick="rowDblclick"',
        '@header-click="headerClick"',
        '@header-contextmenu="headerContextMenu"',
        '@sort-change="sortChange"',
        '@current-change="currentChange"',
        '@header-dragend="headerDragend"',
        '@expand-change="expandChange"',
        '>',
        '<template  v-for="item in tabledes">',
        '<template v-if="item.type==\'operation\'">',
        '<el-table-column :width="item.width" :fixed="item.fixed" :label="item.name" :align="item.align">',
        '<template slot-scope="scope">',
        '<el-button v-for="(btn,index) in item.btns" @click="btn.click(scope.row)" type="text" size="small">{{btn.btnName}}</el-button>',
        '</template>',
        '</el-table-column>',
        '</template>',
        '<template v-else>',
        '<el-table-column ',
        ':width="item.width"',
        ':type="item.type" ',
        ':index="item.indexMethod"',
        ':sortable="item.sortable" ',
        ':sort-method="item.sort" ',
        ':fixed="item.fixed" ',
        ':label="item.name" ',
        ':render-header="item.renderHeader" ',
        ':sort-by="item.sortBy" ',
        ':resizable="item.resizable" ',
        ':formatter="item.formatter" ',
        ':show-overflow-tooltip="item.showOverflowTooltip" ',
        ':header-align="item.headerAlign" ',
        ':class-name="item.className" ',
        ':label-class-name="item.labelClassName" ',
        ':selectable="item.selectable" ',
        ':reserve-selection="item.reserveSelection" ',
        ':filters="item.filters" ',
        ':filter-placement="item.filterPlacement" ',
        ':filter-multiple="item.filterMultiple" ',
        ':filter-method="item.filterMethod" ',
        ':filtered-value="item.filteredValue" ',
        ':prop="item.id" ',
        ':align="item.align">',
        '</el-table-column>',
        '</template>',
        '</template>',
        '</el-table>',
        '<el-pagination style="text-align:right;padding-top:5px"',
        '@size-change="sizeChange"',
        '@current-change="currentPageChange"',
        ':current-page="tablepage.currentPage"',
        ':page-sizes="tablepage.pageSizes"',
        ':page-size="tablepage.pageSize"',
        'layout="total, sizes, prev, pager, next, jumper"',
        ':total="tablepage.total " ',
        ':small="tablepage.small" ',
        ':background="tablepage.background " ',
        ':prevText="tablepage.prevText " ',
        ':nextText="tablepage.nextText">',
        '</el-pagination>',
        '</div>',
    ].join(''),
    watch: {
        // tabledes: function() {
        //     console.log(this.tabledes);
        // }
    },
    methods: {
        sizeChange: function (val) {
            this.tablepage.sizeChange(val);
        },
        currentPageChange: function (val) {
            this.tablepage.currentChange(val);
        },
        select: function (selection, row) {
            this.$emit('select', selection, row);
        },
        selectAll: function (selection) {
            this.$emit('selectall', selection);
        },
        selectionChange: function (selection) {
            this.$emit('selectionchange', selection);
        },
        cellMouseEnter: function (row, column, cell, event) {
            this.$emit('cellmouseenter', row, column, cell, event);
        },
        cellMouseLeave: function (row, column, cell, event) {
            this.$emit('cellmouseenter', row, column, cell, event);
        },
        cellClick: function (row, column, cell, event) {
            this.$emit('cellmouseenter', row, column, cell, event);
        },
        cellDblclick: function (row, column, cell, event) {
            this.$emit('cellmouseenter', row, column, cell, event);
        },
        rowClick: function (row, event) {
            this.$emit('rowclick', row, event);
        },
        rowContentMenu: function (row, event) {
            this.$emit('rowcontentmenu', row, event);
        },
        rowDblclick: function (row, event) {
            this.$emit('rowdblclick', row, event);
        },
        headerClick: function (column, event) {
            this.$emit('headerclick', column, event);
        },
        headerContextMenu: function (column, event) {
            this.$emit('headercontextmenu', column, event);
        },
        sortChange: function (obj) {
            this.$emit('sortchange', obj);
        },
        currentChange: function (currentRow, oldCurrentRow) {
            this.$emit('currentchange', currentRow, oldCurrentRow);
        },
        headerDragend: function (newWidth, oldWidth, column, event) {
            this.$emit('headerdragend', newWidth, oldWidth, column, event);
        },
        expandChange: function (row, expandedRows) {
            this.$emit('expandchange', row, expandedRows);
        },
    }
});

/**
 * 使用方法，只需要传入数据源和绑定值域的对象
 * 例如：调用
 *  <form-add :datasource="dataSource" :form="form"></form-add>
 * dataSource数据格式为
 *    var dataSource = {
            tabCol: 4, //form表单列数
            rows: [{
                "title": "", //是否分类
                data: [{
                    id: "projectName",
                    name: "项目名称",
                    type: "input",
                    placeholder: "请输入项目名称",
                    options: [],
                    col: "",
                    verify: [{
                        required: true,
                        message: '项目名称不能为空',
                        trigger: 'blur'
                    }, {
                        min: 3,
                        max: 5,
                        message: '长度在 3 到 5 个字符',
                        trigger: 'blur'
                    }],
                }] }
 */

Vue.component('form-add', {
    props: {
        datasource: {
            type: [Object, String],
            default: ''
        },
        form: {
            type: [Object, String],
            default: ''
        },
        labelwidth: {
            type: [Object, String],
            default: '80px'
        }
    },
    template: [
        '<div class="hide">',
        '<template v-for="item in datasource.rows">',
        '<form-group :dataform="item"  :tabcol="datasource.tabCol" :form="form" :labelwidth="labelwidth"></form-group>',
        '</template>',
        '</div>',
    ].join(''),
    watch: {
        datasource: function () {

        },
    },
});

Vue.component('form-group', {
    props: {
        dataform: {
            type: [Object, String],
            default: ''
        },
        form: {
            type: [Object, String],
            default: ''
        },
        tabcol: {
            type: [String, Number],
            default: ''
        },
        labelwidth: {
            type: [Object, String],
            default: '80px'
        },
    },
    data: function () {
        return {
            // checkAll: false,
            // isIndeterminate: true
        }
    },
    template: [
        '<div class="group" :style="borStyle">',
        '<span class="groupName">{{dataform.title}}</span>',
        '<el-form ref="form" :model="form" :label-width=labelwidth :style="pad">',
        '<el-row :gutter="10">',
        '<template v-for="(child,index) in dataform.data" >',
        '<el-col :xs="child.col||xs" :sm="child.col||sm" :md="child.col||md" :lg="child.col||lg" :xl="child.col||xl">',
        '<el-form-item :label="child.name" :prop="child.id" :rules="child.verify" >',
        '<el-input clearable v-model="form[child.id]" v-if="child.type==\'input\'" :placeholder="child.placeholder" :size="child.size" @input="updateValue(child.id,child.inputRule)" ></el-input>',
        '<el-select  v-model="form[child.id]" v-if="child.type==\'select\'" :placeholder="child.placeholder" :size="child.size" style="width:100%">',
        '<el-option v-for="option in child.options" :key="option.value" :label="option.label" :value="option.value">',
        '</el-option>',
        '</el-select>',
        '<el-select  v-model="form[child.id]" v-if="child.type==\'select-custom\'" :placeholder="child.placeholder" :size="child.size" style="width:100%">',
        '<el-option v-for="option in child.options" :key="option.index" :label="option.label" :value="option.index">',
        '<span style="float: left">{{ option.label }}</span>',
        '<span style="float: right; color: #8492a6; font-size: 13px">{{option.rightLabel }}</span>',
        '</el-option>',
        '</el-select>',
        '<template  v-if="child.type==\'checkbox\'">',
        '<form-checkbox :index=index  :checkvalue="form[child.id]" :checkoption="child.checklist" @changeoption="changeOption"></form-checkbox>',
        '</template>',
        '<el-date-picker v-if="child.type==\'date\'" type="date" :placeholder="child.placeholder" v-model="form[child.id]" :size="child.size"  style="width: 100%;"></el-date-picker>',
        '<el-input v-if="child.type==\'textarea\'" type="textarea" :rows="2"  :size="child.size"  v-model="form[child.id]" :rules="child.rules"></el-input>',
        '<el-upload v-if="child.type==\'file\'" class="upload-demo" ref="upload" action="https://jsonplaceholder.typicode.com/posts/" :on-preview="handlePreview" :on-remove="handleRemove" :file-list="fileList" :auto-upload="false">',
        '<el-button slot="trigger" size="small" type="primary">选取文件</el-button>',
        '</el-upload>',
        '</el-form-item>',
        '</el-col>',
        '</template>',
        '</el-row>',
        '</el-form>',
        '</div>'
    ].join(''),

    computed: {
        borStyle: function () {
            if (this.dataform.title != "") {
                return {
                    'border-style': 'dashed',
                    'border-color': ' #c0c4cc',
                    'border-width': '1px'
                };
            }
            return {};
        },
        pad: function () {
            if (this.dataform.title != "") {
                return {
                    'padding': '0px 5px',

                };
            }
            return {};
        }
    },
    data: function () {
        return {
            xs: 24,
            sm: 12,
            md: 12,
            lg: 12,
            xl: 12,
            fileList: []
        };
    },
    mounted: function () {
        this.calculate();
        this.checkboxList();

    },
    methods: {
        updateValue: function (id, type) { //不能输入汉字
            if (type && type == "noTtext") {
                var that = this;
                Vue.nextTick(function () {
                    that.form[id] = that.form[id].replace(/[\W]/g, '');
                })
            }
        },
        checkboxList: function () {

        }, //获取多选框的选项
        calculate: function () {
            if (this.tabcol == 3) {
                this.lg = 8;
                this.xl = 8;
            }
            if (this.tabcol == 4) {
                this.lg = 6;
                this.xl = 6;
            }
            if (this.tabcol == 1) {
                this.lg = 24;
                this.xl = 24;
            }
        },
        submitUpload: function () {
            this.$refs.upload.submit();
        },
        handleRemove: function (file, fileList) {
            console.log(file, fileList);
        },
        handlePreview: function (file) {
            console.log(file);
        },
        changeOption: function (index, value) {
            var that = this;
            that.form[that.dataform.data[index].id] = value;
        }
    }
});

Vue.component('form-checkbox', {
    props: {
        checkvalue: {
            type: [String, Array],
            default: ""
        },
        checkoption: {
            type: [String, Array],
            default: [],
        },
        index: {
            type: [String, Number],
            default: 0,
        }
    },
    data: function () {
        return {
            isIndeterminate: true,
            checkall: false,
            defaultCheck: []
        }
    },
    mounted: function () {
        var that = this;
        that.checkvalue.forEach(function (item) {
            that.defaultCheck.push(item);
        });
    },
    template: ['<div><el-checkbox   :indeterminate="isIndeterminate" v-model="checkall"  @change="handleCheckAllChange">全选</el-checkbox>',
        '<div  style="margin: 15px 0;"></div>',
        '<el-checkbox-group v-model="defaultCheck"   @change="handleCheckedChange">',
        '<el-checkbox v-for="morField in checkoption"  :label="morField.id">{{morField.value}}</el-checkbox>',
        '</el-checkbox-group></div>',
    ].join(' '),
    methods: {
        /**根据id 进行设置全选还是单选的处理 */
        handleCheckAllChange: function (val) {
            var that = this;
            var check = [];
            if (val) {
                that.checkoption.forEach(function (item) {
                    check.push(item.id);
                });
            } else {
                check = [];
            }
            that.isIndeterminate = false;
            that.defaultCheck = check;
            that.$emit("changeoption", that.index, check)
        },
        handleCheckedChange: function (val) {
            var that = this;
            var checkCount = val.length;
            that.checkall = checkCount == that.checkoption.length;
            that.isIndeterminate = checkCount > 0 && checkCount < that.checkoption.length;
            that.$emit("changeoption", that.index, that.defaultCheck);
        },
    }
});

Vue.component('form-detail', {
    props: {
        datasource: {
            type: [Object, String],
            default: ''
        },
        form: {
            type: [Object, String],
            default: ''
        }
    },
    template: [
        '<div>',
        '<div v-for="item in datasource.rows">',
        '<form-detail-group :dataform="item"  :tabcol="datasource.tabCol" :form="form" ></form-detail-group>',
        '</div>',
        '</div>',
    ].join(''),
    watch: {
        datasource: function () {

        },
    },
});


Vue.component('form-detail-group', {
    props: {
        dataform: {
            type: [Object, String],
            default: ''
        },
        form: {
            type: [Object, String],
            default: ''
        },
        tabcol: {
            type: [String, Number],
            default: ''
        },
    },
    template: [
        '<div class="group" :style="borStyle">',
        '<span class="groupName">{{dataform.title}}</span>',
        '<el-form ref="form" :model="form" label-width="80px">',
        '<el-row :gutter="10">',
        '<div v-for="(child,index) in dataform.data" :key="index">',
        '<el-col  :xs="child.col||xs" :sm="child.col||sm" :md="child.col||md" :lg="child.col||lg" :xl="child.col||xl">',
        '<el-form-item :label="child.name" :prop="child.id" :rules="child.verify">',
        '<el-input clearable v-model="form[child.id]" v-if="child.type==\'input\'" ></el-input>',
        '<el-select v-model="form[child.id]" v-if="child.type==\'select\'">',
        '<el-option v-for="option in child.options" :key="option.value" :label="option.label" :value="option.value">',
        '</el-option>',
        '</el-select>',
        '<el-date-picker v-if="child.type==\'date\'" type="date"  v-model="form[child.id]" style="width: 100%;"></el-date-picker>',
        '<el-input v-if="child.type==\'textarea\'" type="textarea" :rows="2"  v-model="form[child.id]"></el-input>',
        '<el-upload v-if="child.type==\'file\'" class="upload-demo" ref="upload" action="https://jsonplaceholder.typicode.com/posts/"  :file-list="fileList" >',
        '</el-upload>',
        '</el-form-item>',
        '</el-col>',
        '</div>',
        '</el-row>',
        '</el-form>',
        '</div>'
    ].join(''),
    computed: {
        borStyle: function () {
            if (this.dataform.title != "") {
                return {
                    'border-style': 'dashed',
                    'border-color': ' #c0c4cc',
                    'border-width': '1px'
                };
            }
            return {};
        }
    },
    watch: {},
    data: function () {
        return {
            xs: 24,
            sm: 12,
            md: 12,
            lg: 12,
            xl: 12,
            fileList: [{
                name: 'food.jpeg',
                url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
            }, {
                name: 'food2.jpeg',
                url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
            }]

        };
    },
    mounted: function () {
        this.calculate();
    },
    methods: {
        calculate: function () {
            if (this.tabcol == 3) {
                this.lg = 8;
                this.xl = 8;
            }
            if (this.tabcol == 4) {
                this.lg = 6;
                this.xl = 6;
            }
            if (this.tabcol == 1) {
                this.lg = 24;
                this.xl = 24;
            }
        },

    }
});


Vue.component('dialog-form', {
    props: {
        title: {
            type: [String],
            default: ""
        },
        isshow: {
            type: [String, Boolean],
            default: true,
        },
        ismask: {
            type: [Boolean, String],
            defaule: false
        },
        src: {
            type: [String],
            default: "",
        },
        addformjson: {
            type: [Object, String],
            default: ""
        },
        formdata: {
            type: [Object, String],
            default: "",
        },
        width: {
            type: [Number, String],
            default: ""
        },
        height: {
            type: [Number, String],
            default: ""
        },
        name: {
            type: [Number, String],
            default: ""
        }
    },
    data: function () {
        return {
            add: false,
            whStyle: {},
        }
    },
    template: [
        '<div v-if="isshow">',
        '<div class="dialog" :style="maskstyle">',
        '<div class="dialog-content changeWidth" :style="whStyle" >',
        '<el-card class="changeWidth" :style="whStyle">',
        '<div class="header" :style="wStyle">',
        '<span>{{title}}</span>',
        '</div>',
        '<div class="dialog-body">',
        '<iframe v-if="src"class="iframe" :src="src" frameborder="0" height="100%" width="100%"></iframe>',
        '<template v-else>',
        ' <form-add :datasource="addformjson" :form="formdata"></form-add>',
        '</template>',
        '</div>',
        '</el-card>',
        '</div>',
        '</div>',
        '</div>'
    ].join(''),
    computed: {
        maskstyle: function () {
            if (this.ismask == 'true') {
                return {
                    'left': 0,
                    'background': 'rgba(0,0,0,0.5)',
                    'width': '100%'
                }
            }
            return {}
        },
        wStyle: function () {
            if (this.width.indexOf("%") > -1) {
                return {
                    'width': this.width,
                }
            }
            return {
                'width': this.width + "px",
            }
        },
    },
    watch: {
        isshow: function () {
            var that = this;
            if (that.isshow) {
                var obj = {};
                if (this.height.indexOf("%") > -1) {
                    obj.height = this.height;
                } else {
                    obj.height = this.height + "px";
                }
                if (this.width.indexOf("%") > -1) {
                    obj.width = this.width;
                } else {
                    obj.width = this.width + "px";
                }
                setTimeout(function () {
                    that.whStyle = obj;
                }, 20);
            }
        },
        width: function () {
            var that = this;
            if (that.width == "0px") {
                that.whStyle.width = "0px";
                setTimeout(function () {
                    that.$emit("cancel");
                }, 2000);
            }
        }
    },

    methods: {

    }
});


/**
 * 搜索框的封装使用,根据传入的form表单不同进行渲染
 */
Vue.component('search-tool', {
    props: {
        searchform: {
            type: [String, Object],
            default: ""
        },
        searchdata: {
            type: [Array, Object],
            default: ""
        }
    },

    template: ['<el-row :gutter="10">',
        '<el-form ref="searchform" :model="searchform" label-width="100px">',
        '<template v-for="(item) in searchdata">',
        '<el-col :xs="24" :sm="12" :md="12" :lg="6" :xl="6">',
        '<el-form-item :label="item.name" v-if="item.type!=\'opertion\'">',
        '<el-input v-if="item.type==\'input\'" :placeholder="item.placeholder" v-model="searchform[item.id]" :size="item.size" clearable @input="updateValue(item.id,item.inputRule)">',
        '</el-input>',
        '<el-select v-model="searchform[item.id]" v-if="item.type==\'select\'" style="width:100%" :size="item.size" @change="selectChange(item.isQuery)" clearable>',
        '<template  v-for="option in item.options"><el-option  :key="option.value" :label="option.label" :value="option.value">',
        '</el-option></template>',
        '</el-select>',
        '<el-date-picker v-if="item.type==\'date\'" v-model="searchform[item.id]" :size="item.size" type="date" placeholder="选择日期" clearable @change="selectChange(item.isQuery)">',
        '</el-date-picker>',
        '<el-date-picker  v-if="item.type==\'datetimerange\'"  v-model="searchform[item.id]" type="datetimerange"  range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" align="right">',
        '</el-date-picker>',
        '<el-checkbox-group v-if="item.type==\'chekcbox\'" v-model="searchform[item.id]" clearable>',
        '<template v-for="child in item.options" ><el-checkbox :label="child.value" >{{child.value}}</el-checkbox></template>',
        '</el-checkbox-group>',
        '<el-radio-group v-if="item.type==\'radio\'" v-model="searchform[item.id]">',
        '<template v-for="child in item.options"><el-radio  :label="child.id" >{{child.value}}</el-radio></template>',
        '</el-radio-group>',
        '<el-radio-group  v-if="item.type==\'changeStatus\'" v-model="searchform[item.id]" size="small" @change="item.change">',
        '<template v-for="child in item.options"><el-radio-button  :label="child.value">{{child.label}}</el-radio-button></template>',
        '</el-radio-group>',
        '</el-form-item>',
        '</el-col>',
        '<el-col v-if="item.type==\'opertion\'" :xs="24" :sm="12" :md="12" :lg="6" :xl="6" style="text-align:right">',
        '<template   v-for="child in item.options" ><el-button ',
        ':type="child.type" ',
        ':size="child.size" @click="child.click">{{child.name}}</el-button></template>',
        '</el-col>',
        '</template>',
        '</el-form>',
        '</el-row>',
    ].join(''),
    data: function () {
        return {
            isOperation: false,
            isInput: false,
        }
    },
    mounted: function () {
        //进行初始化的时候，调用此方法
        // this.calculate();
        console.log(this.searchdata);
    },
    methods: {
        updateValue: function (id, type) { //不能输入汉字
            if (type && type == "noTtext") {
                this.searchform[id] = this.searchform[id].replace(/[\W]/g, '');
            }
        },
        selectChange: function (falg) { //val 选中的id
            var that = this;
            if (falg) {
                that.query();
            }
        },
        query: function () {
            var that = this;
            that.searchdata.forEach(function (item) {
                if (item.type == "opertion") {
                    item.options.forEach(function (child) {
                        if (child.id == 'query') {
                            child.click();
                            return;
                        }
                    });
                }
            });
        }
    }

});

/**
 * 搜索框的封装使用,根据传入的form表单不同进行渲染
 */
Vue.component('search-tool-self', {
    props: {
        searchform: {
            type: [String, Object],
            default: ""
        },
        searchdata: {
            type: [Array, Object],
            default: ""
        }
    },
    data: function () {
        return {
            setCol: {
                xs: 12,
                sm: 12,
                md: 9,
                lg: 9,
                xl: 9
            },
            btnCol: {
                xs: 24,
                sm: 24,
                md: 6,
                lg: 6,
                xl: 6
            }
        }
    },
    template: ['<el-row :gutter="10">',
        '<el-form ref="searchform" :model="searchform" label-width="100px">',
        '<template v-for="item in searchdata">',
        '<el-col v-if="item.type!=\'opertion\'" :xs="item.col||setCol.xs" :sm="item.col||setCol.sm" :md="item.col||setCol.md" :lg="item.col||setCol.lg" :xl="item.col||setCol.xl">',
        '<el-form-item :label="item.name">',
        '<el-input v-if="item.type==\'input\'" :placeholder="item.placeholder" v-model="searchform[item.id]" :size="item.size" clearable>',
        '</el-input>',
        '<el-select v-model="searchform[item.id]" v-if="item.type==\'select\'" style="width:100%" :size="item.size" @change="selectChange(item.isQuery)" clearable>',
        '<template v-for="option in item.options"><el-option  :key="option.value" :label="option.label" :value="option.value">',
        '</el-option></template>',
        '</el-select>',
        '<el-date-picker v-if="item.type==\'date\'" v-model="searchform[item.id]" :size="item.size" type="date" placeholder="选择日期" clearable @change="selectChange(item.isQuery)">',
        '</el-date-picker>',
        '<el-date-picker  v-if="item.type==\'datetimerange\'"  v-model="searchform[item.id]" type="datetimerange"  range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" align="right">',
        '</el-date-picker>',
        '<el-checkbox-group v-if="item.type==\'chekcbox\'" v-model="searchform[item.id]" clearable>',
        '<template v-for="child in item.options" ><el-checkbox :label="child.value" >{{child.value}}</el-checkbox></template>',
        '</el-checkbox-group>',
        '<el-radio-group v-if="item.type==\'radio\'" v-model="searchform[item.id]">',
        '<template v-for="child in item.options"><el-radio  :label="child.id" >{{child.value}}</el-radio></template>',
        '</el-radio-group>',
        '</el-form-item>',
        '</el-col>',
        '<el-col v-if="item.type==\'opertion\'" :xs="item.col||btnCol.xs" :sm="item.col||btnCol.sm" :md="item.col||btnCol.md" :lg="item.col||btnCol.lg" :xl="item.col||btnCol.xl" style="text-align: right">',
        '<template  v-for="child in item.options"><el-button ',
        ':type="child.type" ',
        ':size="child.size" @click="child.click">{{child.name}}</el-button></template>',
        '</el-col>',
        '</template>',
        '</el-form>',
        '</el-row>',
    ].join(''),
    mounted: function () {
        //进行初始化的时候，调用此方法
        this.calculate();
    },
    methods: {
        calculate: function () {
            if (this.searchdata.length % 2 == 0) {
                this.setCol.md = 12;
                this.setCol.lg = 12;
                this.setCol.xl = 12;
                this.btnCol.xs = 12;
                this.btnCol.sm = 12;
                this.btnCol.md = 12;
                this.btnCol.lg = 12;
                this.btnCol.xl = 12;
            }
            if (this.searchdata.length % 2 == 1 && this.searchdata.length != 3) {
                this.setCol.md = 12;
                this.setCol.lg = 12;
                this.setCol.xl = 12;
                this.btnCol.md = 24;
                this.btnCol.lg = 24;
                this.btnCol.xl = 24;
            }
        },
        selectChange: function (falg) { //val 选中的id
            var that = this;
            if (falg) {
                that.query();
            }
        },
        query: function () {
            var that = this;
            that.searchdata.forEach(function (item) {
                if (item.type == "opertion") {
                    item.options.forEach(function (child) {
                        if (child.id == 'query') {
                            child.click();
                            return;
                        }
                    });
                }
            });
        }
    }

});

/*
 *按钮组的可配置使用
 */
Vue.component('btns-tool', {
    props: {
        btns: {
            type: [Array, String],
            deafult: ""
        }
    },
    template: [
        '<el-row>',
        '<template v-for="item in btns">',
        '<el-button  ',
        ':size="item.size" ',
        ':type="item.type" ',
        ':plain="item.plain" ',
        ':round:="item.round" ',
        ':loading="item.loading"',
        ':disabled="item.disabled" ',
        ':icon="item.icon" ',
        ':autofocus="item.autofocus" ',
        '@click="item.functionName">{{item.title}}</el-button></template>',
        '</el-row>'
    ].join(''),
    methods: {

    }
});