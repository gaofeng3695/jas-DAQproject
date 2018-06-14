Vue.component('jas-base-group-title', {
	props: {
		name: {
			default: '表单分组',
			type: String
		},
	},
	template: [
		'<div style="border-bottom: 1px solid rgb(228, 231, 237);margin-bottom:10px;">',
		'	<span style="height: 32px;line-height:32px;display:inline-block;border-bottom: 2px solid rgb(64, 158, 255)">{{name}}</span>',
		'</div>'
	].join(''),
});

Vue.component('jas-file-upload', {
	props: {
		name: {
			default: '表单分组',
			type: String
		},
	},
	template: [
		'<el-upload :accept=".xls,.xlsx" :limit="1" :auto-upload="false" :file-list="fileList" ',
		':on-success="fileUploaded" :on-remove="removeFile" ',
		':on-exceed="uploaodNumberlimit" :action="uploadurl">',
		'	<el-button slot="trigger" size="small" type="primary" plain>选取文件</el-button>',
		'	<span style="padding-left: 10px;" class="el-upload__tip" slot="tip">最多上传5个附件</span>',
		'</el-upload>',
	].join(''),
	mounted : function(){

	},

});

Vue.component('jas-iframe-dialog', {
	props: {
		refSelf: {
			default: 'jas-iframe-dialog',
			type: String
		},
		width: {
			default: '80%',
			type: String
		},
		height: {
			default: '80%',
			type: String
		},
		iframeUrl: {
			default: '',
			type: String
		},
		visible: {
			default: false,
			type: Boolean
		},
		title: {
			default: '提示',
			type: String
		}

	},
	data: function () {

		return {
			selfvisible: true,
		}
	},
	watch: {
		visible: function () {
			this.selfvisible = this.visible;
		},
		selfvisible: function (newValue) {
			this.$emit('update:visible', newValue);
		},
	},
	methods: {
		close: function () {
			// this.$emit('update:visible', false)
			this.$emit('close');
		},
		setDialogHeiht: function () {
			var dom = this.$el.querySelector('.el-dialog');
			if (this.height.indexOf('%') !== -1) {
				var height = this.height.split('%')[0];
				if (height <= 0 || height >= 100) return {};
				dom.style['margin-top'] = (100 - height) / 2 + 'vh';
				dom.style['height'] = height + 'vh';
			} else if (this.height.indexOf('px') !== -1) {
				var height02 = this.height.split('px')[0];
				var wrap_height = document.documentElement.clientHeight;
				if (height02 < wrap_height) {
					var toper = (wrap_height - height02) / 2;
					dom.style['margin-top'] = toper + 'px';
				}
				dom.style['height'] = this.height;
			}
		}
	},
	mounted: function () {
		this.setDialogHeiht();
	},
	template: [
		'<el-dialog :ref="refSelf" class="jas-iframe-dialog" :title="title" :visible.sync="selfvisible" :width="width" @close="close" :fullscreen="false">',
		'  <iframe class="dialog-iframe" :src="iframeUrl" frameborder="0"></iframe>',
		'</el-dialog>'
	].join(''),
});


Vue.component('jas-detail-table', {
	props: {
		titles: {
			type: Array,
			required: true
		},
		columnNum: {
			default: 2,
			type: Number
		},
		detail: {

		}
	},
	data: function () {
		return {}
	},
	computed: {
		formatTitle: function () {
			var that = this;
			var newTitle = [];
			if (!this.detail) return [];
			this.titles.forEach(function (item, index, arr) {
				if (index % that.columnNum === 0) {
					var _arr = [];
					for (var i = 0; i < that.columnNum; i++) {
						if (arr[index + i] !== undefined) {
							_arr.push(arr[index + i]);
						}
					}
					newTitle.push(_arr)
				}
			});
			console.log()
			return newTitle;
		}
	},
	template: [
		'<div v-show="detail" class="jas-detail-table">',
		'<table class="table_wrap">',
		'    <template v-for="item in formatTitle">',
		'        <tr>',
		'            <template v-for="subitem in item">',
		'                <th>{{subitem.name}}</th>',
		'                <td>{{detail[subitem.field]}}</td>',
		'            </template>',
		'        </tr>',
		'    </template>',
		'</table>',
		'</div>'
	].join(''),
});


Vue.component('jas-list-wrapper', {
	template: [
		'<div style="padding: 15px;box-sizing: border-box;height: 100%;" class="jas-flex-box is-vertical">',
		'	<div style="padding-bottom: 10px;border-bottom: 1px solid #e4e7ed;">',
		'      <slot name="search"></slot>',
		'	</div>',
		'	<div class="jas-flex-box is-vertical is-grown">',
		'      <slot name="list"></slot>',
		'	</div>',


		'</div>',
	].join(''),
});


Vue.component('jas-search-for-list', {
	model: {
		prop: 'form',
		event: 'input'
	},
	props: {
		form: {
			type: Object,
			required: true
		},
		fields: {
			type: Array,
			required: true
			// [{
			// 	name : '名称',
			// 	field:'title',
			// 	type:'text', // text、time
			// }]
		}
	},
	data: function () {
		return {
			tip: '请输入',
			qtty: 0,
			btnSize: {
				sm: 0,
				md: 0,
				lg: 0,
				xl: 0,
			}
		}
	},
	computed: {

	},
	mounted: function () {
		var nFields = this.fields.length;
		this.btnSize.sm = 24 - (12 * nFields) % 24;
		this.btnSize.md = 24 - (8 * nFields) % 24;
		this.btnSize.lg = 24 - (6 * nFields) % 24;
		this.btnSize.xl = 24 - (6 * nFields) % 24;

	},
	methods: {
		changeField: function (field, value) {
			var obj = this.form;
			obj[field] = value;
		},
		search: function () {
			this.$emit('search', this.fields);
		},
		reset: function () {
			var obj = this.form;
			this.fields.forEach(function (item) {
				obj[item.field] = '';
			});
			this.$emit('input', obj);
			this.search();
		},
	},
	template: [
		'<el-form :model="form" label-width="100px">',
		'		<el-row>',
		'				<el-col v-for="field in fields" :xs="24" :sm="12" :md="8" :lg="6" :xl="6">',
		'						<el-form-item :label="field.name" :prop="field.field" style="margin-bottom: 0px;">',
		'								<el-input size="small"  :value="form[field.field]" @input="changeField(field.field,$event)" :placeholder="tip+field.name" clearable></el-input>',
		'						</el-form-item>',
		'				</el-col>',
		'				<el-col :xs="24" :sm="btnSize.sm" :md="btnSize.md" :lg="btnSize.lg" :xl="btnSize.xl">',
		'						<el-form-item style="float:right;margin-bottom: 0px;">',
		'								<el-button size="small" type="primary" @click="search">查询</el-button>',
		'								<el-button size="small" @click="reset">重置</el-button>',
		'						</el-form-item>',
		'				</el-col>',
		'		</el-row>',
		'</el-form>',

	].join(''),
});

Vue.component('jas-table-for-list', {
	props: {
		form: {
			type: Object,
			required: true
		},
		fields: {
			type: Array,
			required: true
			// [{
			// 	name : '名称',
			// 	field:'title',
			// 	type:'text', // text、time
			// }]
		},
		searchPath : {
			type: String,
			required: true
		},
		detailUrl: {},
		addUrl: {},
		editUrl: {},
	},

	data: function () {
		return {
			headStyle: {
				'background-color': '#f5f7fa ',
			},
			tableData: [],
			currentPage: 1,
			loading: true,
			total: 0,
			pageSize: 10,
		}
	},
	computed: {

	},
	template: [
		'<div  class="jas-flex-box is-vertical is-grown">',
		'<div style="padding: 15px 0;">',
		'	<el-button size="small" plain type="primary" icon="fa fa-plus" @click="add">增加</el-button>',
		'	<el-button class="fr" size="small" icon="el-icon-refresh" @click="refresh"></el-button>',
		'</div>',
		'<div class="is-grown">',
		'	<el-table v-loading="loading" height="100%" :data="tableData" border :header-cell-style="headStyle" style="width: 100%">',
		'		<el-table-column label="序号" type="index" align="center" width="50">',
		'		</el-table-column>',
		'		<el-table-column v-for="item in fields" :label="item.name" :prop="item.field" align="center">',
		'		</el-table-column>',
		'		<el-table-column label="操作" align="center" width="240">',
		'			<template slot-scope="scope">',
		'				<el-button @click="preview(scope.row)" type="text" size="small">查看</el-button>',
		'				<el-button @click="edit(scope.row)" type="text" size="small">编辑</el-button>',
		'				<el-button @click="deleteItem(scope.row)" type="text" size="small">删除</el-button>',
		'			</template>',
		'		</el-table-column>',
		'	</el-table>',
		'</div>',
		'<div style="padding: 15px 0 0;" class="clearfix">',
		'	<el-pagination class="fr" @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"',
		'		:page-sizes="[10, 20, 50, 100]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">',
		'	</el-pagination>',
		'</div>',
		'</div>',
	].join(''),
	mounted: function () {
		this.search();
	},
	methods: {
		search: function () {
			this._requestTable();
		},
		refresh: function () {
			this.search();
		},
		add: function () {
			var that = this;
			if (!this.addUrl) return;
			top.jasTools.dialog.show({
				width: '60%',
				height: '80%',
				title: '增加',
				src: this.addUrl,
				cbForClose: function () {
					that.refresh()
				}
			});
		},
		preview: function (row) {
			var that = this;
			if (!this.detailUrl) return;
			var url = jasTools.base.setParamsToUrl(this.detailUrl, row)
			top.jasTools.dialog.show({
				width: '60%',
				height: '80%',
				title: '查看',
				src: url,
			});
		},
		_requestTable: function (str, cb) {
			var that = this;
			that.loading = true;
			var obj = jasTools.base.extend({}, {
				pageNo: this.currentPage,
				pageSize: this.pageSize,
			}, this.form);
			var url = jasTools.base.rootPath + this.searchPath;
			jasTools.ajax.post(url, obj, function (data) {
				setTimeout(function () {
					that.loading = false;
				}, 300);
				that.tableData = data.rows;
				that.total = data.total;
			});
		},
		edit: function (row) {
			var that = this;
			var url = jasTools.base.setParamsToUrl(this.addUrl, row)
			top.jasTools.dialog.show({
				width: '60%',
				height: '80%',
				title: '修改',
				src: url,
				cbForClose: function () {
					that.refresh()
				}
			});
		},
		deleteItem: function (row) {
			var that = this;
			window.top.Vue.prototype.$confirm('您确定要删除本条数据吗？', '提示', {
				type: 'warning',
				callback: function (action) {
					if (action === 'confirm') {
						that._deleteItem(row.oid);
					}
				}
			});
		},
		_deleteItem: function (oid) {
			var that = this;
			var url = jasTools.base.rootPath + "/daq/medianStake/delete.do";
			jasTools.ajax.post(url, {
				oid: oid
			}, function (data) {
				top.Vue.prototype.$message({
					type: 'success',
					message: '删除成功'
				});
				that.refresh();
			});
		},
		handleSizeChange: function (val) {
			this.pageSize = val;
			this.search();
		},
		handleCurrentChange: function (val) {
			this.currentPage = val;
			this.search();
		}
	},

});