Vue.component('jas-base-group-title', {
	props: {
		name: {
			default: '表单分组',
			type: String
		},
	},
	template: [
		'<div style="margin:0px 0 6px;line-height:32px;padding-top:10px;border-bottom:1px solid #ecf5ff;">',
		'	<span style="padding:0px 4px 0px 4px;height: 22px;line-height:22px;display:inline-block;background: #ecf5ff;border-left: 2px solid rgb(64, 158, 255)">{{name}}</span>',
		'</div>'
	].join(''),
});

Vue.component('jas-base-el-multi-select', { //value 值 支持逗号分隔 的多选下拉框
	props: {
		value: {},
		options: {
			type: Array
		},
		item: {
			type: Object //{field,name}
		}
	},
	computed: {
		_value: {
			get: function () {
				return this.value ? this.value.split(',') : [];
			},
			set: function (newVal) {
				this.$emit('input', newVal.join(','));
			}
		}
	},
	template: [
		'<el-select multiple v-model="_value" clearable :placeholder="\'请选择\'+item.name" size="small" @visible-change="visibleChange($event,item.field)"  @change="fatherSelectChanged($event,item.field)">',
		'	<el-option v-for="option in options" :key="option.key" :label="option.value" :value="option.key"></el-option>',
		'</el-select>',
	].join(''),
	methods: {
		visibleChange: function ($event) {
			this.$emit('visible-change', $event);
		},
		fatherSelectChanged: function ($event) {
			this.$emit('change', $event);
		},
	}
});

Vue.component('jas-base-el-cascader', { //value 值 支持逗号分隔 的多选下拉框
	props: {
		value: {},
		options: {
			type: Array,
			default: function () {
				return []
			}
		},
		item: {
			type: Object //{field,name}
		},
		props: {

		}
	},
	computed: {
		_value: {
			get: function () {
				if (!this.options || !this.value || !this.options.length) return [];
				return jasTools.base.getIdArrFromTree(this.options, this.value);
			},
			set: function (newValue) {
				this.$emit('input', newValue[newValue.length - 1]);
			}
		}
	},
	template: [
		'<el-cascader :options="options" :props="props" v-model="_value" :show-all-levels="false" @visible-change="visibleChange($event,item.field)"',
		'	change-on-select clearable :placeholder="\'请选择\'+item.name"  size="small">',
		'</el-cascader>',
	].join(''),
	methods: {
		visibleChange: function ($event) {
			this.$emit('visible-change', $event);
		},
	}
});

Vue.component('jas-file-upload', {
	props: {
		limit: {
			default: 5,
			type: Number
		},
		fileTypes: {
			default: function () {
				return []
			},
			type: Array
		}
	},
	data: function () {
		return {
			fileList: [],
			uploadurl: '',
		}
	},
	computed: {
		accept: function () {
			return this.fileTypes.length > 0 ? '.' + this.fileTypes.join(',.') : '';
		}
	},
	template: [
		'<el-upload ref="upload" :accept="accept" :limit="limit" :auto-upload="false" :file-list="fileList" ',
		':on-change="changeFiles" :on-success="fileUploaded" :on-remove="removeFile" ',
		':on-exceed="uploaodNumberlimit" :action="uploadurl" style="padding-bottom:10px;">',
		'	<el-button slot="trigger" size="small" type="primary" plain>选取文件</el-button>',
		'	<span style="padding-left: 10px;" class="el-upload__tip" slot="tip">{{"最多上传"+ limit +"个附件"}}</span>',
		'</el-upload>',
	].join(''),
	methods: {
		requestFile: function (bizId) {
			var that = this;
			var url = jasTools.base.rootPath + "/attachment/getInfo.do";
			jasTools.ajax.get(url, {
				businessType: 'file',
				businessId: bizId
			}, function (data) {
				data.rows.forEach(function (item) {
					var file = {
						name: item.fileName,
						size: item.size,
						oid: item.oid
					};
					that.fileList.push(file);
				});
				that.fileListlength = that.fileList.length;
			});
		},
		fileUploaded: function (response, file, fileList) {
			var that = this;
			that.indexOfFileToSubmit++;
			if (that.indexOfFileToSubmit >= that.lengthOfFileToSubmit) {
				this.$emit('success', response, file, fileList)
			}
		},
		removeFile: function (file, fileList) {
			if (file.status === 'success' && file.oid) {
				if (this.filesToBeDelete) {
					this.filesToBeDelete.push(file.oid);
				} else {
					this.filesToBeDelete = [file.oid];
				}
			}
		},
		uploadFile: function (oid) {
			var that = this;
			that._deleteFilesToServer(function () {
				that.uploadurl = jasTools.base.rootPath + "/attachment/upload.do?token=" + localStorage.getItem("token") +
					"&businessId=" + oid + "&businessType=file";
				that.$nextTick(function () {
					var afileToSubmit = that.$refs.upload.uploadFiles.filter(function (item) {
						return !item.oid
					});
					that.lengthOfFileToSubmit = afileToSubmit.length;
					that.indexOfFileToSubmit = 0;
					if (afileToSubmit.length > 0) {
						that.$refs.upload.submit();
					} else {
						that.fileUploaded();
					}
				});
			});
		},
		_deleteFilesToServer: function (cb) {
			var that = this;
			if (!that.filesToBeDelete) {
				cb && cb();
				return;
			}
			jasTools.ajax.get(jasTools.base.rootPath + "/attachment/delete.do", {
				oids: that.filesToBeDelete.join(',')
			}, function (data) {
				cb && cb();
			});
		},
		changeFiles: function (file, fileList) {
			var that = this;
			if (file.status === "ready") {
				var aFileName = file.name.split('.');
				var fileTypes = this.fileTypes;
				var type = aFileName[aFileName.length - 1 || 1];
				if (!type || (fileTypes.length > 0 && fileTypes.indexOf(type) === -1)) { // 不是规定格式的文件
					top.Vue.prototype.$message('请上传规定格式的文件');
					var index = fileList.indexOf(file);
					fileList.splice(index, 1);
				}
			}
		},
		uploaodNumberlimit: function () {
			top.Vue.prototype.$message("最多上传" + this.limit + "个附件")
		},
	}

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
		'<el-dialog :ref="refSelf" class="jas-iframe-dialog" :close-on-click-modal="false" :title="title" :visible.sync="selfvisible" :width="width" @close="close" :fullscreen="false">',
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
		detail: {

		}
	},
	data: function () {
		return {
			columnNum: 2,
		}
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
			if (this.titles.length === 1) {
				this.columnNum = 1;
			}
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
		'                <td :ref="subitem.field" v-text="formatValue(detail[subitem.field],subitem.formatter)"></td>',
		'            </template>',
		'        </tr>',
		'    </template>',
		'</table>',
		'</div>'
	].join(''),
	methods: {
		formatValue: function (value, formatter) {
			if (formatter) {
				return formatter('', '', value, '');
			}
			return value;
		},
		resizeColumn: function () {
			var that = this;

			var width = that.$el.clientWidth;
			if (width < 660) {
				that.columnNum = 1;
			} else if (width < 1400) {
				that.columnNum = 2;
			} else {
				that.columnNum = 3;
			}
		}
	},
	mounted: function () {
		var that = this;
		this.resizeColumn();
		$(window).on('resize', function () {
			that.resizeColumn();

		});
	},
});

Vue.component('jas-list-wrapper', {
	data: function () {
		return {
			isClosed: false,
		}
	},
	template: [
		'<div style="padding:  0 15px 15px;box-sizing: border-box;height: 100%;" class="jas-flex-box is-vertical">',
		'	<div ref="searchWrapper" style="border-bottom: 1px solid #e4e7ed;overflow:hidden;box-sizing:border-box;">',
		'      <div style="padding:15px 0 10px;"><slot name="search"></slot></div>',
		'	</div>',
		'	<div class="jas-flex-box is-vertical is-grown">',
		'      <slot name="list"></slot>',
		'	</div>',


		'</div>',
	].join(''),
	mounted: function () {
		var that = this;
	},
	methods: {

		toggleSearch: function () {
			if (this.$refs.searchWrapper.clientHeight < 10) {
				this.isClosed = false;
				this.$refs.searchWrapper.style.height = 'auto';
				this.$refs.searchWrapper.style.borderBottom = '1px solid #e4e7ed';
			} else {
				this.isClosed = true;
				this.$refs.searchWrapper.style.borderBottom = 'none';
				this.$refs.searchWrapper.style.height = 0;
			}
		}
	}
});

Vue.component('jas-search-for-list', {
	model: {
		prop: 'form',
		event: 'input'
	},
	props: {
		fields: {
			type: Array,
		},
		fieldsConfig: {
			type: Object,
		},
		form: {
			type: Object,
		},
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
			},
			fatherSelectList: [],
			childSelectList: [],
			isClosed: false,
		}
	},
	template: [
		'<el-form class="jas-search-for-list" :model="form" label-width="120px">',
		'		<el-row v-show="!isClosed">',
		'	    <template v-for="item in fields">',
		'	    	<el-col :xs="24" :sm="12" :md="8" :lg="6" :xl="6">',
		'	    		<el-form-item :label="item.name"  :prop="item.field" :rules="fieldsConfig[item.field] && fieldsConfig[item.field].rules" style="margin-bottom: 15px ">',
		'	    			<template v-if="fieldsConfig[item.field].type == \'select\'">',
		'	    				<el-select :ref="item.field" v-model="form[item.field]" clearable :placeholder="\'请选择\'+item.name" size="small" @visible-change="visibleChange($event,item.field)"  @change="fatherSelectChanged($event,item.field)">',
		'	    					<el-option v-for="(option,index) in fieldsConfig[item.field].options" :key="index" :label="option.value" :value="option.key"></el-option>',
		'	    				</el-select>',
		'	    			</template>',
		'	    			<template v-if="fieldsConfig[item.field].type == \'input\'">',
		'	    				<el-input v-model.trim="form[item.field]" :placeholder="\'请输入\'+item.name" size="small" clearable></el-input>',
		'	    			</template>',
		'	    			<template v-if="fieldsConfig[item.field].type == \'number\'">',
		'							<el-input-number v-model="form[item.field]" :precision="fieldsConfig[item.field].precision" :step="1" :max="fieldsConfig[item.field].max" controls-position="right" clearable :placeholder="\'请输入\'+item.name" size="small"></el-input-number>',
		'	    			</template>',
		'	    			<template v-if="fieldsConfig[item.field].type == \'number\'">',
		'							<el-input-number v-model="form[item.field]" :precision="fieldsConfig[item.field].precision" :step="1" :max="fieldsConfig[item.field].max" controls-position="right" clearable :placeholder="\'请输入\'+item.name" size="small"></el-input-number>',
		'	    			</template>',
		'	    			<template v-if="fieldsConfig[item.field].type == \'date\'">',
		'	    				<el-date-picker clearable value-format="yyyy-MM-dd HH:mm:ss" type="date" :placeholder="\'请选择\'+item.name" v-model="form[item.field]" size="small" style="width: 100%;"></el-date-picker>',
		'	    			</template>',
		'	    		</el-form-item>',
		'	    	</el-col>',
		'	    </template>',
		'			<el-col :xs="24" :sm="btnSize.sm" :md="btnSize.md" :lg="btnSize.lg" :xl="btnSize.xl">',
		'					<el-form-item style="float:right;margin-bottom: 0px;">',
		'							<el-button size="small" type="primary" @click="search">查询</el-button>',
		'							<el-button size="small" @click="reset">重置</el-button>',
		// '							<el-button size="small" type="text" @click="isClosed=!isClosed" >收起</el-button>',
		'					</el-form-item>',
		'			</el-col>',
		'		</el-row>',
		// '		<el-row v-show="isClosed">',
		// '			<div style="float:left;">搜索栏</div>',
		// '			<div style="float:right;">',
		// '				<el-button size="small" type="text" @click="isClosed=!isClosed" style="padding:0;">展开</el-button>',
		// '			</div>',
		// '		</el-row>',

		'</el-form>',

	].join(''),
	mounted: function () {
		this.setFieldsPattern();
		this.resetFieldsConfig(this.fields, this.fieldsConfig);
	},
	watch: {
		fields: function () {
			this.setFieldsPattern();
			this.resetFieldsConfig(this.fields, this.fieldsConfig);
		}
	},
	methods: {
		setFieldsPattern: function () {
			var nFields = this.fields.length;
			this.btnSize.sm = 24 - (12 * nFields) % 24;
			this.btnSize.md = 24 - (8 * nFields) % 24;
			this.btnSize.lg = 24 - (6 * nFields) % 24;
			this.btnSize.xl = 24 - (6 * nFields) % 24;
		},
		search: function () {
			this.$emit('search', this.fields);
		},
		reset: function () {
			var obj = this.form;
			this.fields.forEach(function (item) {
				obj[item.field] = '';
			});
			this.triggerFatherSelectsChange();
			this.searchOnce();
		},
		triggerFatherSelectsChange: function (fatherSelectList) {
			var that = this;
			var SelectList = fatherSelectList || that.fatherSelectList;
			setTimeout(function () {
				SelectList.forEach(function (item) {
					that.$refs[item][0].$emit('change', true)
				});
			}, 0);
		},
		resetFieldsConfig: function (fields, fieldsConfig) {
			var that = this;
			var rulesObj = {};
			var fieldArr = [];
			var fieldNameArr = [];
			fields.forEach(function (item) {
				fieldArr.push(item.field);
				fieldNameArr.push(item.name);
			});
			this.fieldArr = fieldArr;
			for (var field in fieldsConfig) {
				var fieldIndex = fieldArr.indexOf(field);
				if (fieldIndex > -1 && fieldsConfig.hasOwnProperty(field)) {
					var config = fieldsConfig[field];
					/* 初始化赋值 */
					if (!config.options) {
						that.$set(config, 'options', []);
						that.$set(config, 'rules', []);
					}
					if (config.type === 'select' && config.childSelect && config.childSelect.length > 0) {
						that.childSelectList.push.apply(that.childSelectList, config.childSelect);
						that.fatherSelectList.push(field);
					}

					/* 请求阈值 */
					if (config.domainName) {
						(function (field, config) {
							that.requestDomainFromDomainTable(config.domainName, function (options) {
								config.options = options;
							});
						})(field, config)
					}
					if (config.optionUrl) {

						(function (field, config) {
							var obj = {};
							if (config.requestParams) {
								obj = jasTools.base.extend(obj, config.requestParams);
							}
							jasTools.ajax.post(jasTools.base.rootPath + "/" + config.optionUrl, obj, function (data) {
								config.options = data.rows;
							});
						})(field, config)
					}
				}
			}

			that.fatherSelectList = that.fatherSelectList.filter(function (field) {
				return that.childSelectList.indexOf(field) === -1;
			});

		},
		visibleChange: function (isShowOptions, currentField) {
			if (!isShowOptions) return;
			var fieldArr = [];
			var fieldNameArr = [];
			var fieldsConfig = this.fieldsConfig;

			this.fields.forEach(function (item) {
				fieldArr.push(item.field);
				fieldNameArr.push(item.name);
			});
			for (var field in fieldsConfig) {
				var fieldIndex = fieldArr.indexOf(field);
				if (fieldIndex > -1 && fieldsConfig.hasOwnProperty(field)) {
					if (fieldsConfig[field].childSelect && fieldsConfig[field].childSelect.indexOf(currentField) > -1) {
						if (!this.form[field]) {
							top.Vue.prototype.$message({
								message: '请先选择' + fieldNameArr[fieldIndex],
								type: 'warning'
							});
						}
					}
				}
			}
		},
		fatherSelectChanged: function (isInit, fatherField) {
			if (isInit != true) {
				isInit = false;
			}
			var that = this;
			var fieldConfig = this.fieldsConfig[fatherField];
			var form = this.form;
			var setChildOptionsAndValue = function (childField, options) { // 入参下拉选项
				that.fieldsConfig[childField].options = options;
				//form[childField] = ''
				!isInit && (form[childField] = '');
				// if (options.length === 1) { //只有一个选项就自动复制
				// 	form[childField] = options[0].key;
				that.$refs[childField][0].$emit('change', isInit);
				//}

			};

			var getAndSet = function (fatherField, fatherValue, childField, requestUrl) {
				if (fatherValue) { //进行子级的查找 后台请求
					var obj = {
						"rows": 100,
						"page": 1,
					};
					var fieldConfig = that.fieldsConfig[childField];
					if (fieldConfig.requestParams) {
						obj = jasTools.base.extend(obj, fieldConfig.requestParams);
					}
					obj[fatherField] = fatherValue;
					jasTools.ajax.post(jasTools.base.rootPath + "/" + requestUrl, obj, function (data) {
						setChildOptionsAndValue(childField, data.rows)
					});
				} else {
					setChildOptionsAndValue(childField, []);
				}
			};

			fieldConfig.childSelect && fieldConfig.childSelect.forEach(function (childField, index) {
				if (that.fieldArr.indexOf(childField) < 0 || !fieldConfig.childUrl || fieldConfig.childUrl.length === 0) return;
				var url = fieldConfig.childUrl[index] || fieldConfig.childUrl[0];
				getAndSet(fatherField, form[fatherField], childField, url);
			});

			this.searchOnce();
		},
		searchOnce: function () {
			var that = this;
			if (this.timerSearch) return;
			this.timerSearch = setTimeout(function () {
				that.search();
				that.timerSearch = null;
			}, 100);
		},
		requestDomainFromDomainTable: function (domainName, cb) {
			var that = this;
			var url = jasTools.base.rootPath + "/jasframework/sysdoman/getDoman.do";
			jasTools.ajax.get(url, {
				"domainName": domainName
			}, function (data) {
				var aDomain = data.map(function (item) {
					return {
						key: item.codeId,
						value: item.codeName,
					}
				});
				cb && cb(aDomain);
			});
		},
		requestDomainFromBizTable: function (url, obj, cb) {
			var that = this;
			var url = jasTools.base.rootPath + url;
			jasTools.ajax.post(url, obj, function (data) {
				cb && cb(data.rows);
			}, function () {
				cb && cb([]);
			});
		},
	},

});

Vue.component('jas-table-for-list', {
	props: {
		privilegeCode: {
			type: [String, Array],
		},
		selfBtns: {
			type: Array, // [locate]
			default: function () {
				return []
			}
		},
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
		searchPath: {
			type: String,
			required: true
		},
		upcallPath: {
			type: String,
		},
		deletePath: {
			type: String,
			required: true
		},
		detailUrl: {},
		addUrl: {},
		editUrl: {},
		templateCode: {},
		className: {},
		importConfig: {},
		ruleUrl: ""
	},
	data: function () {
		return {
			headStyle: {
				'background-color': '#f5f7fa ',
			},
			functionCode: '',
			_templateCode: '',
			_exportTemplateCode: '',
			_className: '',
			_classNameQuery: '',
			isApprove: '',
			privilege: [], //权限数组 bt_add,bt_update,bt_delete,bt_select,bt_export,bt_import,bt_position
			tableData: [],
			currentPage: 1,
			loading: true,
			total: 0,
			pageSize: 10,
			oids: [],
			rows: [],
			isClosed: false,
			_privilegeCode: '',
			orderBy:null
		}
	},
	computed: {
		reportRows: function () {
			var that = this;
			return this.rows.filter(function (row) {
				return !that.frozenBtn(row);
			});
		},
		approveRows: function () {
			var that = this;
			return this.rows.filter(function (row) {
				return (row.approve_status == '待审核' || row.approveStatus == 1);
			});
		},
	},
	template: [
		'<div  class="jas-flex-box is-vertical is-grown">',
		'<div style="padding: 15px 0;">',
		'	<el-button size="small" plain type="primary" icon="fa fa-plus" v-if="isHasRule()"  @click="addRule">规则</el-button>',
		'	<el-button size="small" plain type="primary" icon="fa fa-plus" v-if="isHasPrivilege(' + "'bt_add'" + ')"  @click="add">增加</el-button>',
		'	<el-button size="small" plain type="primary" icon="fa fa-level-up" v-if="isHasPrivilege(' + "'bt_submit'" + ')" :disabled="reportRows.length==0"  @click="upSubmit">提交</el-button>',
		'	<el-button size="small" plain type="primary" icon="fa fa-level-up" v-if="isApprove&&isHasPrivilege(' + "'bt_report'" + ')"  :disabled="reportRows.length==0" @click="upcall">上报</el-button>',
		'	<el-button size="small" plain type="primary" icon="fa fa-check" v-if="isApprove&&isHasPrivilege(' + "'bt_approve'" + ')" :disabled="approveRows.length==0" @click="approve">审核</el-button>',
		'<jas-import-export-btns  @refreshtable="refresh" :is-import="isHasPrivilege(' + "'bt_import'" + ')" :is-export="isHasPrivilege(' + "'bt_export'" + ')" ',
		'		:form="form" :oids="oids" :import-config="importConfig" :template-code="_templateCode" :export-template-code="_exportTemplateCode" :function-code="functionCode" :class-name="_classNameQuery"></jas-import-export-btns>',

		'  <span class="fr">',
		'		 <el-tooltip class="item" content="刷新" placement="top">',
		'       <el-button size="small" icon="el-icon-refresh" @click="refresh"></el-button>',
		'		 </el-tooltip>',
		'		 <el-tooltip v-show="isClosed" class="item" content="展开搜索" placement="top">',
		'	     <el-button size="small" icon="el-icon-arrow-down" @click="toggleSearch"></el-button>',
		'		 </el-tooltip>',
		'		 <el-tooltip v-show="!isClosed" class="item" content="收起搜索" placement="top">',
		'	     <el-button size="small" icon="el-icon-arrow-up" @click="toggleSearch"></el-button>',
		'		 </el-tooltip>',
		'  </span>',
		'</div>',
		'<div class="is-grown">',
		'	<el-table ref="mytable" @selection-change="handleSelectionChange" @row-dblclick="preview" @row-click="checkRow" v-loading="loading" height="100%" :data="tableData" border :header-cell-style="headStyle" style="width: 100%" stripe @sort-change="sortChange">',
		'    <el-table-column type="selection" width="55" align="center" fixed></el-table-column>',
		'		<el-table-column label="序号" type="index" align="center" width="50" fixed>',
		'</el-table-column>',
		'<template  v-for="item,index in fields">',
		'<el-table-column  v-if="isShowStatus(item)":key="item.oid" :fixed="index=== 0?true:false" :label="item.name" :prop="item.field" :formatter="item.formatter" min-width="130px" show-overflow-tooltip align="center" sortable="custom" >',
		'<template slot-scope="scope" >',
		'<el-tag  :type="isShowType(scope)" size="medium">{{ scope.row.approveStatusName }}</el-tag>',
		'</template>',
		'</el-table-column>',
		'<el-table-column v-else   :key="item.oid" :fixed="index=== 0?true:false" :label="item.name" :prop="item.field" :formatter="item.formatter" min-width="165px" show-overflow-tooltip align="center" sortable="custom" >',

		'</el-table-column>',
		'</template>',
		'		<el-table-column label="操作" align="center" width="180" fixed="right">',
		'			<template slot-scope="scope">',
		'				<el-button @click="locate(scope.row)"  v-if="isHasPrivilege(' + "'bt_position'" + ')" type="text" size="small">定位</el-button>',
		'				<el-button @click="preview(scope.row)"  v-if="isHasPrivilege(' + "'bt_select'" + ')" type="text" size="small">查看</el-button>',
		'				<el-button @click="edit(scope.row)"  :disabled="frozenBtn(scope.row)" v-if="isHasPrivilege(' + "'bt_update'" + ')"  type="text" size="small">编辑</el-button>',
		'				<el-button @click="deleteItem(scope.row)" :disabled="frozenBtn(scope.row)" v-if="isHasPrivilege(' + "'bt_delete'" + ')"   type="text" size="small">删除</el-button>',
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
	watch: {
		privilegeCode: function () {
			this._requestPrivilege(this._privilegeCode);
			// this.search();
		}
	},
	created: function () {
		var param = window.jasTools.base.getParamsInUrl(location.href);
		this.isApprove = param.isApprove;
		this._className = this.className || param.className;
		this._classNameQuery = this.classNameQuery || param.classNameQuery;
		this._templateCode = this.templateCode || param.templateCode;
		this._exportTemplateCode = this.exportTemplateCode || param.exportTemplateCode;
		this.functionCode = param.menuCode || param.functionCode;
		this._privilegeCode = this.privilegeCode || param.privilegeCode;
	},
	mounted: function () {
		this._requestPrivilege(this._privilegeCode);
		this.search();
	},
	methods: {
		isHasRule: function () {
			if (this.ruleUrl){
				return true;
			}
			return false;
		},
		addRule: function () {
			var that = this;
			if (!this.addUrl) return;
			top.jasTools.dialog.show({
				width: '60%',
				height: '80%',
				title: '规则制定',
				src: this.ruleUrl,
				cbForClose: function () {

				}
			});
		},
		toggleSearch: function () {
			this.$parent.toggleSearch();
			this.isClosed = this.$parent.isClosed;
		},
		frozenBtn: function (row) {
			if (row.approveStatus > 0) {
				return true;
			}
			if(row.commitStatus&&row.commitStatus=='1'){
				return true;
			}
			return false;
		},
		upcall: function () {

			var that = this;
			var oids = this.reportRows.map(function (item) {
				return item.oid;
			});
			if (oids.length === 0) return;
			var url = jasTools.base.rootPath + '/daq/dataApprove/save.do';
			jasTools.ajax.post(url, {
				businessOid: oids,
				approveStatus: 1, //status 2 通过 -1 驳回
				className: this._className,
				functionCode: this.functionCode,
				privilegeCode:this._privilegeCode,
			}, function (data) {
				top.Vue.prototype.$message({
					type: 'success',
					message: '上报成功'
				});
				//top.app.requestNumber("","refresh");
				that.refresh();
			});
		},
		upSubmit:function(){
			var that = this;
			var oids = this.reportRows.map(function (item) {
				return item.oid;
			});
			if (oids.length === 0) return;
			var url = jasTools.base.rootPath + '/daq/daqcommit/changeCommitStatus.do';
			jasTools.ajax.post(url, {
				businessOid: oids,
				className:this._className,
				functionCode: this.functionCode,
			}, function (data) {
				top.Vue.prototype.$message({
					type: 'success',
					message: '提交成功'
				});
				that.refresh();
			});	
		},
		approve: function () {
			var that = this;
			var oids = this.approveRows.map(function (item) {
				return item.oid;
			});
			if (oids.length === 0) {
				return;
			} else if (oids.length === 1) {

				var src = jasTools.base.setParamsToUrl(this.detailUrl, {
					approveType: 2,
					className: this._className,
					menuCode: this.functionCode || '',
					privilegeCode:this._privilegeCode||'',
				});
				var url = jasTools.base.setParamsToUrl(src, this.approveRows[0]);
				top.jasTools.dialog.show({
					width: '60%',
					height: '80%',
					title: '审核',
					src: url,
					cbForClose: function (param) {
						if (param == 'success') {
							top.app.requestNumber("refresh");
							that.refresh();
						}
					}
				});
			} else {
				
				var src = jasTools.base.setParamsToUrl('./pages/template/dialogs/approveTemplate.html', {
					approveType: 2,
					className: this._className,
					menuCode: this.functionCode || '',
					privilegeCode:this._privilegeCode,
				});
				var url = jasTools.base.setParamsToUrl(src, {
					oids: oids.join(',')
				});
				top.jasTools.dialog.show({
					width: '600px',
					height: '400px',
					title: '批量审核',
					src: url,
					cbForClose: function (param) {
						if (param == 'success') {
							top.app.requestNumber("refresh");
							that.refresh();
						}
					}
				});
			}
		},
		handleSelectionChange: function (val) {
			this.oids = val.map(function (item) {
				return item.oid;
			});
			this.rows = val;
		},
		locate: function (item) {
			this.$emit('locate', item)
		},
		isHasPrivilege: function (sName) {
			if (this._privilegeCode && this.privilege.indexOf(sName) === -1) {
				return false;
			}
			return true;
		},
		_requestPrivilege: function (privilegeCode) {
			var that = this;
			if (!privilegeCode) return;
			if ((typeof privilegeCode) === 'string') {
				var url = jasTools.base.rootPath + "/jasframework/privilege/privilege/getFunctionConfig.do";
				jasTools.ajax.get(url, {
					privilegeCode: privilegeCode, //菜单权限编号
					appId: "402894a152681ba30152681e8b320003" //应用id，值默认
				}, function (data) {
					that.privilege = data.rows.map(function (item) {
						return item.functionType;
					});
				});
			} else {
				that.privilege = privilegeCode;
			}
		},
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
		checkRow: function (row) {
			this.$refs['mytable'].toggleRowSelection(row)
		},
		preview: function (row) {
			var that = this;
			if (!this.detailUrl) return;
			var url = this.detailUrl;
			if (this.isApprove) {
				url = jasTools.base.setParamsToUrl(this.detailUrl, {
					approveType: 1
				});
			}
			url = jasTools.base.setParamsToUrl(url, row);
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
				orderBy:this.orderBy
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
		sortChange: function (column, prop, order) {
			var orderBy = null;
			if (column.prop != null) {
				orderBy = column.prop + " " + (column.order === 'ascending' ? 'asc' : 'desc');
				orderBy = orderBy.replace(/([A-Z])/g, "_$1").toLowerCase()
			}
			this.orderBy = orderBy;
			var that = this;
			that.loading = true;
			var obj = jasTools.base.extend({}, {
				pageNo: this.currentPage,
				pageSize: this.pageSize,
				orderBy: orderBy,
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
			var url = jasTools.base.rootPath + this.deletePath;
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
		isShowStatus: function (item) {

			if (item.field == 'approveStatus') {
				return true;
			} else {
				return false;
			}
		},
		isShowType: function (scope) {
			if (scope.row.approveStatus == '0') {
				scope.row.approveStatusName = "未上报"
				return 'info';
			}
			if (scope.row.approveStatus == '1') {
				scope.row.approveStatusName = "待审核"
				return 'warning';
			}
			if (scope.row.approveStatus == '2') {
				scope.row.approveStatusName = "审核通过"
				return 'success';
			}
			if (scope.row.approveStatus == '-1') {
				scope.row.approveStatusName = "驳回"
				return 'danger';
			}
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

Vue.component('jas-file-list', {
	props: {
		bizId: {
			type: String,
			required: true
		},
	},
	data: function () {
		return {
			fileList: [],
			isrequest: true,
		}
	},
	watch: {
		bizId: function () {
			if (this.bizId) {
				this._requestFiles(this.bizId);
			}
		}
	},
	template: [
		'<div class="jas-file-list" v-show="!isrequest">',
		' <div v-show="fileList.length === 0" >无</div>',
		'	<div v-for="file in fileList"  class="el-upload-list__item">',
		'		<a class="el-upload-list__item-name">',
		'			<i class="el-icon-document"></i>{{file.fileName}}',
		'		</a>',
		'		<i class="el-icon-download tipBtn" @click="download(file.oid)" style="right:10px;"></i>',
		'		<i class="el-icon-view tipBtn" @click="preview(file.oid)" style="right:35px;"></i>',
		'	</div>',
		'</div>'
	].join(''),
	created: function () {
		if (this.bizId) {
			this._requestFiles(this.bizId);
		}
	},
	methods: {
		download: function (oid) {
			var that = this;
			jasTools.ajax.downloadByIframe('post', jasTools.base.rootPath + "/attachment/download.do", {
				oid: oid
			});
		},
		_requestFiles: function (oid) {
			var that = this;
			var url = jasTools.base.rootPath + "/attachment/getInfo.do";
			jasTools.ajax.get(url, {
				businessType: 'file',
				businessId: oid
			}, function (data) {
				that.fileList = data.rows;
				that.isrequest = false;
			});
		},
		preview: function (oid) {
			var that = this;
			top.jasTools.dialog.show({
				width: '80%',
				height: '90%',
				title: '预览模板',
				src: './pages/template/pdfjs_1.10.88/web/viewer.html?oid=' + oid,
			});
		},

	},

});

Vue.component('jas-dialog-wrapper', {
	props: {},
	data: function () {
		return {

		}
	},
	template: [
		'<div class="jas-flex-box is-vertical">',
		'  <div class="is-grown" style="padding: 0 20px;overflow: auto;">',
		'    <div><slot></slot></div>',
		'  </div>',
		'  <div style="text-align: center;padding-top:10px;margin: 0 20px;border-top: 1px solid #e4e7ed;">',
		'    <slot name="footer"></slot>',
		'  </div>',
		'</div>'
	].join(''),
});

Vue.component('jas-two-panel-resizer', {
	props: {
		layout: { // horizontal
			type: String,
		},
		length: {
			type: String,
		},
		showed: {
			default: true,
			type: Boolean,
		},
	},
	data: function () {
		return {
			panelMoving: false,
			panelShowed: true,
			mainPanelStyle: {},
			closeClass: '',
			openClass: '',
			_length: '',
		}
	},
	computed: {
		closePanelStyle: function () {
			if (this.layout === 'horizontal') {
				return {
					height: this.panelShowed ? this._length : '0%',
					minHeight: '0%',
					maxHeight: '100%',
				}
			} else {
				return {
					width: this.panelShowed ? this._length : '0%',
					minWidth: '0%',
					maxWidth: '100%',
				}
			}
		},

	},
	watch: {
		panelShowed: function (val) {
			this.$emit('statuschanged', val);
		}
	},
	template: [
		'<multipane @paneresizestart="panelMoving = true" @paneresize="paneresize" @paneresizestop="paneresizestop" class="foo" :layout="layout">',
		'	<div v-loading="panelMoving" class="resizepanel" :style="closePanelStyle" element-loading-spinner="11" element-loading-background="rgba(0, 0, 0, 0)">',
		'		<slot name="closePanel"></slot>',
		'	</div>',
		'	<multipane-resizer>',
		'		<div class="resizertap" @click="panelShowed=!panelShowed">',
		'			<i v-show="panelShowed" :class="closeClass"></i>',
		'			<i v-show="!panelShowed" :class="openClass"></i>',
		'		</div>',
		'	</multipane-resizer>',
		'	<div v-loading="panelMoving" :style="[{ flexGrow: 1},mainPanelStyle]" element-loading-spinner="11" element-loading-background="rgba(0, 0, 0, 0)">',
		'		<slot name="mainPanel"></slot>',
		'	</div>',
		'</multipane>'
	].join(''),
	methods: {
		paneresize: function () {
			this.$emit('paneresize');
		},
		paneresizestop: function (pane, resizer, size) {
			this.panelMoving = false;
			this._length = size;
		}
	},
	created: function () {
		this._length = this.length;
		this.panelShowed = this.showed;
	},
	mounted: function () {
		if (this.layout === 'horizontal') {
			this.mainPanelStyle = {
				height: 0
			};
			this.closeClass = 'fa fa-angle-up';
			this.openClass = 'fa fa-angle-down';
		} else {
			this.mainPanelStyle = {
				width: 0
			};
			this.closeClass = 'fa fa-angle-left';
			this.openClass = 'fa fa-angle-right';
		}

	}
});

Vue.component('jas-import-export-btns', {
	props: {
		templateCode: { // horizontal
			type: String,
		},
		exportTemplateCode: { // horizontal
			type: String,
		},
		functionCode: {
			type: String,
		},
		oids: {
			type: Array,
		},
		form: {
			type: Object,
		},
		isImport: {
			type: Boolean,
			default: true,
		},
		isExport: {
			type: Boolean,
			default: true,
		},
		importConfig: {}
	},
	data: function () {
		return {}
	},
	template: [
		'<span style="margin-left: 10px;" v-show="templateCode">',
		'<el-button size="small" v-if="isImport" type="primary" plain="plain" icon="fa fa-mail-forward" @click="bt_import">导入</el-button>',
		'<el-button size="small" :disabled="oids.length==0" v-if="isExport" type="primary" plain="plain" icon="fa fa-mail-reply" @click="bt_export">导出已选</el-button>',
		'<el-button size="small" v-if="isExport" type="primary" plain="plain" icon="fa fa-mail-reply-all" @click="bt_export_all">导出全部</el-button>',
		'<el-button size="small" v-if="isImport" type="primary" plain="plain" icon="fa fa-download" @click="bt_download">下载模板</el-button>',
		'</span>',
	].join(''),
	methods: {
		bt_import: function () { // 导入
			var that = this;
			var src = './pages/template/dialogs/upload.html?templateCode=' + this.templateCode;
			if (that.importConfig && that.importConfig.importUrl) {
				src += "&importUrl=" + that.importConfig.importUrl;
			}
			top.jasTools.dialog.show({
				title: '导入',
				width: '600px',
				height: '600px',
				src: src,
				cbForClose: function () {
					that.$emit('refreshtable');
				}
			});
		},
		bt_export: function (obj) {
			var that = this;
			var url = jasTools.base.rootPath + '/importExcelController/exportExcel.do';
			if (that.importConfig && that.importConfig.exportUrl) {
				url = jasTools.base.rootPath + that.importConfig.exportUrl;
			}
			jasTools.ajax.post(url, {
				templateCode: this.exportTemplateCode,
				functionCode: this.functionCode, //"F000043", // 自定义表单功能编码
				keywords: {
					oids: this.oids
				}
			}, function (data) {
				that._downloadExportFile(data.data);
			});
		},
		bt_export_all: function (obj) { // 导出全部
			var that = this;
			var url = jasTools.base.rootPath + '/importExcelController/exportExcel.do';
			//			for(var key in this.form){
			//				if(this.form[key]&&JSON.stringify(this.form[key]).indexOf("min")>-1){
			//					this.form[key]=[Number(this.form[key].min),Number(this.form[key].max)];
			//				}
			//			}
			if (that.importConfig && that.importConfig.exportUrl) {
				url = jasTools.base.rootPath + that.importConfig.exportUrl;
			}
			jasTools.ajax.post(url, {
				templateCode: this.exportTemplateCode,
				functionCode: this.functionCode, // 自定义表单功能编码
				keywords: this.form
			}, function (data) {
				that._downloadExportFile(data.data);
			});
		},
		_downloadExportFile: function (id) {
			var that = this;
			var url = jasTools.base.rootPath + "/importExcelController/downloadExcel.do";
			jasTools.ajax.downloadByIframe('post', url, {
				fileId: id
			});
			// jasTools.ajax.downloadByPost(url, {
			// 	fileId: id
			// });
		},
		bt_download: function () { // 下载模板
			var that = this;
			jasTools.ajax.downloadByIframe('post', jasTools.base.rootPath + "/jasframework/excelTemplate/download.do", {
				excelTemplateCode: this.templateCode
			});
		},

	},
	mounted: function () {
		//console.log(this.importConfig);
	}
});

Vue.component('jas-form-items', {
	props: {
		fields: { // horizontal
			type: Array,
		},
		fieldsConfig: {
			type: Object,
		},
		form: {
			type: Object,
		},
	},
	data: function () {
		return {
			fatherSelectList: [],
			childSelectList: [],
		}
	},
	template: [
		'<el-row>',
		'	<template v-for="item in fields">',
		'		<el-col :xs="24" :sm="12" :md="12" :lg="12" :xl="8">',
		'			<el-form-item :ref="item.field + 123" :label="item.name"  :prop="item.field" :rules="fieldsConfig[item.field] && fieldsConfig[item.field].rules" style="margin-bottom: 15px ">',
		'				<template v-if="fieldsConfig[item.field].type == \'select\'">',
		'					<el-select v-bind:disabled=fieldsConfig[item.field].disabled :ref="item.field" v-model="form[item.field]" clearable :placeholder="\'请选择\'+item.name" size="small" @visible-change="visibleChange($event,item.field)"  @change="fatherSelectChanged($event,item.field)">',
		'						<el-option v-for="option in fieldsConfig[item.field].options" :key="option.key" :label="option.value" :value="option.key"></el-option>',
		'					</el-select>',
		'				</template>',
		'				<template v-if="fieldsConfig[item.field].type == \'multiSelect\'">',
		'				  <jas-base-el-multi-select :ref="item.field" v-model="form[item.field]" :item="item" :options="fieldsConfig[item.field].options" @visible-change="visibleChange($event,item.field)" @change="fatherSelectChanged($event,item.field)"></jas-base-el-multi-select>',
		'				</template>',

		'				<template v-if="fieldsConfig[item.field].type == \'input\'">',
		'					<el-input @change="fieldChanged(item.field)" v-model.trim="form[item.field]" :placeholder="\'请输入\'+item.name" size="small" clearable></el-input>',
		'				</template>',
		'	    	<template v-if="fieldsConfig[item.field].type == \'number\'">',
		'					<el-input-number @change="fieldChanged(item.field)" v-model="form[item.field]" :precision="precision(fieldsConfig[item.field].precision)" :step="1" :max="fieldsConfig[item.field].max || 999999" controls-position="right" clearable :placeholder="\'请输入\'+item.name" size="small"></el-input-number>',
		'	    	</template>',
		'				<template v-if="fieldsConfig[item.field].type == \'date\'">',
		'					<el-date-picker clearable value-format="yyyy-MM-dd" type="date" :picker-options="fieldsConfig[item.field].pickerOptions" :placeholder="\'请选择\'+item.name" @change="fieldChanged(item.field)" v-model="form[item.field]" size="small" style="width: 100%;"></el-date-picker>',
		'				</template>',
		'			</el-form-item>',
		'		</el-col>',
		'	</template>',
		'</el-row>',
	].join(''),
	methods: {
		precision: function (value) {
			if (value == 0) return 0;
			if (!value) return 3;
			return value;
		},
		triggerFatherSelectsChange: function (fatherSelectList) {
			var that = this;
			var SelectList = fatherSelectList || that.fatherSelectList;
			setTimeout(function () {
				SelectList.forEach(function (item) {
					that.$refs[item][0].$emit('change', true)
				});
			}, 0);
		},
		labelWidth: function (name) {
			return name.length > 5 ? '220px' : '100px';
		},
		resetFieldsConfig: function (fields, fieldsConfig) {
			var that = this;
			var rulesObj = {};
			var fieldArr = [];
			var fieldNameArr = [];
			fields.forEach(function (item) {
				fieldArr.push(item.field);
				fieldNameArr.push(item.name);
			});

			for (var field in fieldsConfig) {
				var fieldIndex = fieldArr.indexOf(field);
				if (fieldIndex > -1 && fieldsConfig.hasOwnProperty(field)) {
					var config = fieldsConfig[field];
					/* 初始化赋值 */
					if (!config.options) {
						that.$set(config, 'options', []);
					}
					if (!config.rules) {
						that.$set(config, 'rules', []);
					}
					if (config.type === 'select' && config.childSelect && config.childSelect.length > 0) {
						that.childSelectList.push.apply(that.childSelectList, config.childSelect);
						that.fatherSelectList.push(field);
					}

					/* 设置验证规则 */
					if (config.isRequired) {
						config.rules.push({
							required: true,
							message: fieldNameArr[fieldIndex] + '为必填项',
							//trigger: 'change'
						});
					}

					/* 请求阈值 */
					if (config.domainName) {
						(function (field, config) {
							that.requestDomainFromDomainTable(config.domainName, function (options) {
								config.options = options;
							});
						})(field, config)
					}
					if (config.optionUrl) {
						(function (field, config) {
							jasTools.ajax.post(jasTools.base.rootPath + "/" + config.optionUrl, {}, function (data) {
								config.options = data.rows;
								if (config.isInit) {
									that.form[field] = config.options[0].key;
									that.$refs[field][0].$emit('change', false);
								}
							});
						})(field, config)
					}
				}
			}

			that.fatherSelectList = that.fatherSelectList.filter(function (field) {
				return that.childSelectList.indexOf(field) === -1;
			});

		},
		visibleChange: function (isShowOptions, currentField) {

			if (!isShowOptions) return;
			var fieldArr = [];
			var fieldNameArr = [];
			var fieldsConfig = this.fieldsConfig;

			this.fields.forEach(function (item) {
				fieldArr.push(item.field);
				fieldNameArr.push(item.name);
			});

			for (var field in fieldsConfig) {
				var fieldIndex = fieldArr.indexOf(field);
				if (fieldIndex > -1 && fieldsConfig.hasOwnProperty(field)) {
					if (fieldsConfig[field].childSelect && fieldsConfig[field].childSelect.indexOf(currentField) > -1) {
						if (!this.form[field]) {
							top.Vue.prototype.$message({
								message: '请先选择' + fieldNameArr[fieldIndex],
								type: 'warning'
							});
						}
					}
				}
			}
		},
		fatherSelectChanged: function (isInit, fatherField) {
			if (isInit != true) {
				isInit = false;
			}
			var that = this;
			var fieldConfig = this.fieldsConfig[fatherField];
			var form = this.form;
			var setChildOptionsAndValue = function (childField, options) { // 入参下拉选项
				if (that.form.weldOid != "" && childField == "weldOid" && !that.form.isNoAddOid) { //that.form.isNoAddOid  如果不需要增加oid，则页面配置该属性 并且为 true
					options.push({
						key: that.form.weldOid,
						value: that.form.weldCode
					});
					that.fieldsConfig[childField].options = options;
				} else {
					that.fieldsConfig[childField].options = options;
				}
				var length = that.fieldsConfig[childField].options.length;
				!isInit && (form[childField] = '');
				if (!form[fatherField]) {
					form[childField] = '';
					that.fieldsConfig[childField].options = [];
				}
				if (that.fieldsConfig[childField].isInit) {
					if (options.length > 0 && !that.form[childField]) that.form[childField] = options[0].key;
					that.$refs[childField][0].$emit('change', true);
				}


			};

			var getAndSet = function (fatherField, fatherValue, childField, requestUrl) {
				if (fatherValue) { //进行子级的查找 后台请求
					var obj = {
						"rows": 100,
						"page": 1,
					};
					var fieldConfig = that.fieldsConfig[childField];
					if (fieldConfig.requestParams) {
						obj = jasTools.base.extend(obj, fieldConfig.requestParams);
					}
					obj[fatherField] = fatherValue;
					jasTools.ajax.post(jasTools.base.rootPath + "/" + requestUrl, obj, function (data) {
						setChildOptionsAndValue(childField, data.rows)
					});
				} else {
					setChildOptionsAndValue(childField, []);
				}
			};

			fieldConfig.childSelect && fieldConfig.childSelect.forEach(function (childField, index) {
				if (!fieldConfig.childUrl || fieldConfig.childUrl.length === 0) return;
				var url = fieldConfig.childUrl[index] || fieldConfig.childUrl[0];
				getAndSet(fatherField, form[fatherField], childField, url);
			});
			this.fieldChanged(fatherField)
		},

		fieldChanged: function (field) {
			var that = this;
			this.$refs[field + 123][0].form.validateField(field);
			if (that.fieldsConfig[field].type == 'date') {
				if (that.fieldsConfig[field].lessDateScope && that.fieldsConfig[field].lessDateScope.length > 0) {
					that.fieldsConfig[field].lessDateScope.forEach(function (item) {
						that.fieldsConfig[item].pickerOptions = Object.assign({}, that.fieldsConfig[item].pickerOptions, {
							disabledDate: function (time) {
								var day = new Date(that.form[field]).getTime() - 1000 * 24 * 60 * 60; //获取天数
								if (that.fieldsConfig[item].isLessToday) {
									if (!that.form[field]) {
										return time.getTime() > new Date().getTime()
									}
									return time.getTime() < new Date(day).getTime() || time.getTime() > new Date().getTime();
								}
								return time.getTime() < new Date(day).getTime();
							}
						})
					});
				}
				if (that.fieldsConfig[field].maxDateScope && that.fieldsConfig[field].maxDateScope.length > 0) {
					that.fieldsConfig[field].maxDateScope.forEach(function (item) {
						that.fieldsConfig[item].pickerOptions = Object.assign({}, that.fieldsConfig[item].pickerOptions, {
							disabledDate: function (time) {
								if (that.fieldsConfig[item].isLessToday) {
									if (!that.form[field]) {
										return time.getTime() > new Date().getTime()
									}
									return time.getTime() > new Date(that.form[field]).getTime() || time.getTime() > new Date().getTime();
								}
								return time.getTime() > new Date(that.form[field]).getTime();
							}
						})
					});
				}
			}
		},

		requestDomainFromDomainTable: function (domainName, cb) {
			var that = this;
			var url = jasTools.base.rootPath + "/jasframework/sysdoman/getDoman.do";
			jasTools.ajax.get(url, {
				"domainName": domainName
			}, function (data) {
				var aDomain = data.map(function (item) {
					return {
						key: item.codeId,
						value: item.codeName,
					}
				});
				cb && cb(aDomain);
			});
		},
		requestDomainFromBizTable: function (url, obj, cb) {
			var that = this;
			var url = jasTools.base.rootPath + url;
			jasTools.ajax.post(url, obj, function (data) {
				cb && cb(data.rows);
			}, function () {
				cb && cb([]);
			});
		},
	},
	created: function () {},
	mounted: function () {
		this.resetFieldsConfig(this.fields, this.fieldsConfig);
	}
});

Vue.component('jas-form-items-group', {
	props: {
		namesGroup: {
			type: Array,
		},
		fieldsGroup: { // horizontal
			type: Array,
		},
		fieldsConfig: {
			type: Object,
		},
		form: {
			type: Object,
		},
	},
	data: function () {
		return {
			fatherSelectList: [],
			childSelectList: [],
		}
	},
	template: [

		'<div>',
		'<div v-for="fields,index in fieldsGroup" v-if="fields.length>0">',
		'	<jas-base-group-title :name="namesGroup[index]"></jas-base-group-title>',

		'<el-row>',
		'	<template v-for="item in fields">',
		'		<el-col :xs="24" :sm="12" :md="12" :lg="12" :xl="8">',
		'			<el-form-item :ref="item.field + 123" :label="item.name"  :prop="item.field" :rules="fieldsConfig[item.field] && fieldsConfig[item.field].rules" style="margin-bottom: 15px ">',
		'				<template v-if="fieldsConfig[item.field].type == \'select\'">',
		'					<el-select v-bind:disabled=fieldsConfig[item.field].disabled :ref="item.field" v-model="form[item.field]" clearable :placeholder="\'请选择\'+item.name" size="small" @visible-change="visibleChange($event,item.field)"  @change="fatherSelectChanged($event,item.field)">',
		'						<el-option v-for="(option,index) in fieldsConfig[item.field].options" :key="index" :label="option.value" :value="option.key"></el-option>',
		'					</el-select>',
		'				</template>',
		'				<template v-if="fieldsConfig[item.field].type == \'multiSelect\'">',
		'				  <jas-base-el-multi-select :ref="item.field" v-model="form[item.field]" :item="item" :options="fieldsConfig[item.field].options" @visible-change="visibleChange($event,item.field)" @change="fatherSelectChanged($event,item.field)"></jas-base-el-multi-select>',
		'				</template>',

		'				<template v-if="fieldsConfig[item.field].type == \'cascader\'">',
		'				  <jas-base-el-cascader :ref="item.field" v-model="form[item.field]" :props="fieldsConfig[item.field].props" :item="item" :options="fieldsConfig[item.field].options" @visible-change="visibleChange($event,item.field)"></jas-base-el-cascader>',
		'				</template>',

		'				<template v-if="fieldsConfig[item.field].type == \'input\'">',
		'					<el-input v-bind:disabled=fieldsConfig[item.field].disabled  @change="fieldChanged(item.field)" v-model.trim="form[item.field]" :placeholder="\'请输入\'+item.name" size="small" clearable></el-input>',
		'				</template>',
		'	    	<template v-if="fieldsConfig[item.field].type == \'number\'">',
		'					<el-input-number v-bind:disabled=fieldsConfig[item.field].disabled  @change="fieldChanged(item.field)" v-model="form[item.field]" :precision="fieldsConfig[item.field].precision" :step="1" :max="fieldsConfig[item.field].max || 999999" controls-position="right" clearable :placeholder="\'请输入\'+item.name" size="small"></el-input-number>',
		'	    	</template>',
		'				<template v-if="fieldsConfig[item.field].type == \'date\'">',
		'					<el-date-picker clearable value-format="yyyy-MM-dd" type="date" :picker-options="fieldsConfig[item.field].pickerOptions" :placeholder="\'请选择\'+item.name" @change="fieldChanged(item.field)" v-model="form[item.field]" size="small" style="width: 100%;"></el-date-picker>',
		'				</template>',
		'			</el-form-item>',
		'		</el-col>',
		'	</template>',
		'</el-row>',
		'</div>',
		'</div>',
	].join(''),
	methods: {
		triggerFatherSelectsChange: function (fatherSelectList) {
			var that = this;
			var SelectList = fatherSelectList || that.fatherSelectList;
			setTimeout(function () {
				SelectList.forEach(function (item) {
					that.$refs[item][0].$emit('change', true)
				});
			}, 0);
		},
		resetFieldsConfig: function (fields, fieldsConfig) {
			var that = this;
			var rulesObj = {};
			var fieldArr = [];
			var fieldNameArr = [];
			fields.forEach(function (item) {
				fieldArr.push(item.field);
				fieldNameArr.push(item.name);
			});

			for (var field in fieldsConfig) {
				var fieldIndex = fieldArr.indexOf(field);
				if (fieldIndex > -1 && fieldsConfig.hasOwnProperty(field)) {
					var config = fieldsConfig[field];
					/* 初始化赋值 */
					if (!config.options) {
						that.$set(config, 'options', []);
					}
					if (!config.rules) {
						that.$set(config, 'rules', []);
					}
					if (config.type === 'select' && config.childSelect && config.childSelect.length > 0) {
						that.childSelectList.push.apply(that.childSelectList, config.childSelect);
						that.fatherSelectList.push(field);
					}

					/* 设置验证规则 */
					if (config.isRequired) {
						config.rules.push({
							required: true,
							message: fieldNameArr[fieldIndex] + '为必填项',
							//	trigger: 'change'
						})
					}

					/* 请求阈值 */
					if (config.domainName) {
						(function (field, config) {
							that.requestDomainFromDomainTable(config.domainName, function (options) {
								config.options = options;
							});
						})(field, config)
					}
					if (config.optionUrl) {
						(function (field, config) {
							var obj = {};
							if (config.requestParams) {
								obj = jasTools.base.extend(obj, config.requestParams);
							}
							jasTools.ajax.post(jasTools.base.rootPath + "/" + config.optionUrl, obj, function (data) {
								//下拉框是否进行初始化显示
								config.options = data.rows;
								if (config.isInit && config.options.length > 0) {
									that.form[field] = config.options[0].key;
									that.$refs[field][0].$emit('change', false);
								}
							});
						})(field, config)
					}
				}
			}

			that.fatherSelectList = that.fatherSelectList.filter(function (field) {
				return that.childSelectList.indexOf(field) === -1;
			});

		},
		visibleChange: function (isShowOptions, currentField) {
			if (!isShowOptions) return;
			var fieldArr = [];
			var fieldNameArr = [];
			var fieldsConfig = this.fieldsConfig;

			this.allFields.forEach(function (item) {
				fieldArr.push(item.field);
				fieldNameArr.push(item.name);
			});
			for (var field in fieldsConfig) {
				var fieldIndex = fieldArr.indexOf(field);
				if (fieldIndex > -1 && fieldsConfig.hasOwnProperty(field)) {
					if (fieldsConfig[field].childSelect && fieldsConfig[field].childSelect.indexOf(currentField) > -1) {
						if (!this.form[field]) {
							top.Vue.prototype.$message({
								message: '请先选择' + fieldNameArr[fieldIndex],
								type: 'warning'
							});
						}
					}
				}
			}
		},
		fatherSelectChanged: function (isInit, fatherField) {
			if (isInit != true) {
				isInit = false;
			}
			var that = this;
			var fieldConfig = this.fieldsConfig[fatherField];
			var form = this.form;
			var setChildOptionsAndValue = function (childField, options) { // 入参下拉选项
				that.fieldsConfig[childField].options = options;
				!isInit && (form[childField] = '');
				// if (options.length === 1) { //只有一个选项就自动复制
				// 	form[childField] = options[0].key;

				// }else{
				// 	form[childField] = '';
				// }
				if (!form[fatherField]) {
					form[childField] = '';
					that.fieldsConfig[childField].options = [];
				}
				if (that.fieldsConfig[childField].isInit) {
					if (options.length > 0 && !that.form[childField]) that.form[childField] = options[0].key;
					that.$refs[childField][0].$emit('change', true);
				}

			};

			var getAndSet = function (fatherField, fatherValue, childField, requestUrl) {
				if (fatherValue) { //进行子级的查找 后台请求
					var obj = {
						"rows": 100,
						"page": 1,
					};
					var fieldConfig = that.fieldsConfig[childField];
					if (fieldConfig.requestParams) {
						obj = jasTools.base.extend(obj, fieldConfig.requestParams);
					}
					obj[fatherField] = fatherValue;
					jasTools.ajax.post(jasTools.base.rootPath + "/" + requestUrl, obj, function (data) {
						setChildOptionsAndValue(childField, data.rows)
					});
				} else {
					setChildOptionsAndValue(childField, []);
				}
			};

			fieldConfig.childSelect && fieldConfig.childSelect.forEach(function (childField, index) {
				if (!fieldConfig.childUrl || fieldConfig.childUrl.length === 0) return;
				var url = fieldConfig.childUrl[index] || fieldConfig.childUrl[0];
				getAndSet(fatherField, form[fatherField], childField, url);
			});
			if (that.fieldsConfig[fatherField].type == 'date') {
				this.fieldChanged(fatherField);
			}
			if (that.fieldsConfig[fatherField].subDefault) { //subDefault仅限于 数据填充来源于  select  options
				var optionsObj = {}; //表示选中的值
				that.fieldsConfig[fatherField].options.forEach(function (item) {
					if (item.key == that.form[fatherField]) {
						optionsObj = item;
					}
				});
				for (var key in that.fieldsConfig[fatherField].subDefault) {
					that.form[key] = optionsObj[that.fieldsConfig[fatherField].subDefault[key].value];
				}
			}
		},
		fieldChanged: function (field) { //进行日期的配置  此处进行配置规则的渲染
			var that = this;
			this.$refs[field + 123][0].form.validateField(field);
			//if() 进行判断是否有规则 需要规则字段 规则值
			if (that.fieldsConfig[field].type == 'date') {
				if (that.fieldsConfig[field].lessDateScope && that.fieldsConfig[field].lessDateScope.length > 0) {
					that.fieldsConfig[field].lessDateScope.forEach(function (item) {
						that.fieldsConfig[item].pickerOptions = Object.assign({}, that.fieldsConfig[item].pickerOptions, {
							disabledDate: function (time) {
								var day = new Date(that.form[field]).getTime() - 1000 * 24 * 60 * 60; //获取天数
								if (that.fieldsConfig[item].isLessToday) {
									if (!that.form[field]) {
										return time.getTime() > new Date().getTime()
									}
									return time.getTime() < new Date(day).getTime() || time.getTime() > new Date().getTime();
								}
								return time.getTime() < new Date(day).getTime();
							}
						})
					});
				}
				if (that.fieldsConfig[field].maxDateScope && that.fieldsConfig[field].maxDateScope.length > 0) {
					that.fieldsConfig[field].maxDateScope.forEach(function (item) {
						that.fieldsConfig[item].pickerOptions = Object.assign({}, that.fieldsConfig[item].pickerOptions, {
							disabledDate: function (time) {
								if (that.fieldsConfig[item].isLessToday) {
									if (!that.form[field]) {
										return time.getTime() > new Date().getTime()
									}
									return time.getTime() > new Date(that.form[field]).getTime() || time.getTime() > new Date().getTime();
								}
								return time.getTime() > new Date(that.form[field]).getTime();
							}
						})
					});
				}
			}

		},
		requestDomainFromDomainTable: function (domainName, cb) {
			var that = this;
			var url = jasTools.base.rootPath + "/jasframework/sysdoman/getDoman.do";
			jasTools.ajax.get(url, {
				"domainName": domainName
			}, function (data) {
				var aDomain = data.map(function (item) {
					return {
						key: item.codeId,
						value: item.codeName,
					}
				});
				cb && cb(aDomain);
			});
		},
		requestDomainFromBizTable: function (url, obj, cb) {
			var that = this;
			var url = jasTools.base.rootPath + url;
			jasTools.ajax.post(url, obj, function (data) {
				cb && cb(data.rows);
			}, function () {
				cb && cb([]);
			});
		},
	},
	created: function () {},
	mounted: function () {
		this.allFields = Array.prototype.concat.apply([], this.fieldsGroup);
		this.resetFieldsConfig(this.allFields, this.fieldsConfig);
	}
});

Vue.component('jas-sub-form-group', {
	props: {
		groupName: {
			type: String,
		},
		fields: { // horizontal
			type: Array,
			default: function () {
				return []
			}
		},
		fieldsConfig: {
			type: Object,
		},
		formList: {
			type: Array,
			default: function () {
				return []
			}
		},
		formDefault: {
			type: Object,
		},
	},
	data: function () {
		return {}
	},
	template: [
		'<div>',
		'	<div v-show="formList.length === 0">',
		'		<div style="padding-top: 10px;float: right;">',
		'			<el-button @click="addFormGroup(formList)" type="text" size="small">新增</el-button>',
		'		</div>',
		'		<jas-base-group-title :name="groupName"></jas-base-group-title>',
		'		<el-row>无</el-row>',
		'	</div>',
		'	<div v-for="row,index in formList" >',
		'		<div style="padding-top: 10px;float: right;">',
		'			<el-button v-show="formList.length-1==index" @click="addFormGroup(formList)"  type="text" size="small">新增</el-button>',
		'			<el-button v-show="row.operationFlag != -1" @click="removeFormGroup(index)" type="text" size="small">删除</el-button>',
		'			<el-button v-show="row.operationFlag == -1" @click="resetFormGroup(index)" type="text" size="small">恢复</el-button>',
		'		</div>',
		'		<jas-base-group-title :name="groupName+(index+1)"></jas-base-group-title>',
		'		<jas-form-items v-show="row.operationFlag != -1" :form="row" :fields="fields" :fields-config="fieldsConfig"></jas-form-items>',
		'		<div v-show="row.operationFlag === -1" >已被删除</div>',
		'	</div>',
		'</div>',
	].join(''),
	methods: {
		resetFormGroup: function (index) {
			var that = this;
			that.formList[index].operationFlag = 2; // -1（删除），0（不变），1（新增），2（修改）
			that.$set(that.formList, index, that.formList[index]);
		},
		removeFormGroup: function (index) {
			var that = this;
			if (that.formList[index].oid) {
				that.formList[index].operationFlag = -1;
				that.$set(that.formList, index, that.formList[index]);
			} else {
				that.formList.splice(index, 1);
			}
		},
		addFormGroup: function (formList) {
			var obj = {};
			for (var item in this.formDefault) {
				if (this.formDefault.hasOwnProperty(item)) {
					obj[item] = this.formDefault[item]
				}
			}
			obj.operationFlag = 1;
			formList.push(obj);
		},
	},
});

Vue.component('jas-sub-detail-group', {
	props: {
		groupName: {
			type: String,
		},
		fields: { // horizontal
			type: Array,
		},
		detailList: {
			type: Array,
		},
	},
	data: function () {
		return {}
	},
	template: [
		'<div>',
		'	 <div v-show="detailList && detailList.length == 0">',
		'	 	<jas-base-group-title :name="groupName"></jas-base-group-title>',
		'	 	<div>无</div>',
		'	 </div>',
		'	 <div v-for="detail,index in detailList">',
		'	 	<jas-base-group-title :name="groupName+(index+1)"></jas-base-group-title>',
		'	 	<jas-detail-table :titles="fields" :detail="detail"></jas-detail-table>',
		'	 </div>',
		'</div>',
	].join(''),
	methods: {},
});

Vue.component('jas-approve-dialog', {
	props: {
		oid: {
			type: String
		},
		type: {
			type: Number, // 0 无审批功能 1 查看审批  2 审核审批
			default: 0
		},
		className: {
			type: String
		},
		functionCode: {
			type: String
		},
	},
	data: function () {
		return {
			total: 0,
			_oid: this.oid,
			_type: 0,
			_className: this.className,
			_functionCode: this.functionCode,
			searchform: {
				page: 1,
				rows: 10,
			},
			headStyle: {
				'background-color': '#f5f7fa ',
			},
			activeName: 'first',
			tableData: [],
			searchform: {
				page: 1,
				rows: 10,
			},
			loading: {
				table: false
			},
			remarks: '',
			_privilegeCode:""
		}
	},
	template: [
		'				<jas-dialog-wrapper v-if="_type==0">',
		'					<slot></slot>',
		'				</jas-dialog-wrapper>',
		'<el-tabs v-else class="jas-approve-dialog jas-flex-box is-vertical" v-model="activeName" @tab-click="handleClick" >',
		'  <el-tab-pane label="详情信息" name="first">',
		'    <div style="height: 100%;overflow: auto;">',
		'				<jas-dialog-wrapper>',
		'					<div><slot></slot></div>',
		'					<div v-if="_type==2" slot="footer">',
		'						<div>',
		'							<div>',
		'								<el-form label-width="80px">',
		'									<el-form-item label="审批意见">',
		'										<el-input type="textarea" :autosize="{ minRows: 2, maxRows: 6 }" :rows="2" size="small" v-model.trim="remarks"></el-input>',
		'									</el-form-item>',
		'								</el-form>',
		'							</div>',
		'							<div>',
		'								<el-button size="small" @click="close">取 消</el-button>',
		'								<el-button size="small" type="warning" @click="requestApprove(-1)">驳 回</el-button>',
		'								<el-button size="small" type="primary" @click="requestApprove(2)">通 过</el-button>',
		'							</div>',
		'						</div>',
		'					</div>',
		'				</jas-dialog-wrapper>',
		'			</div>',
		'  </el-tab-pane>',
		'  <el-tab-pane label="审核信息" name="second">',
		'    <div class="jas-flex-box is-vertical" style="margin: 0 20px;">',
		'      <el-table v-loading="loading.table" class="is-grown" :data="tableData" height="100" style="width: 100%;" :header-cell-style="headStyle" border stripe>',
		'        <el-table-column type="index" label="序号" width="50" align="center" fixed></el-table-column>',
		'        <el-table-column prop="approveStatus" :formatter="formatter" label="操作类型" width="120" align="center"></el-table-column>',
		'        <el-table-column prop="approveOpinion" label="审批意见" align="center"></el-table-column>',
		'        <el-table-column prop="createDatetime" label="操作时间" width="160" align="center"></el-table-column>',
		'        <el-table-column prop="createUserName" label="操作人员" width="160" align="center"></el-table-column>',
		'      </el-table>',
		'      <el-pagination style="text-align: right;margin-top:15px" @size-change="handleSizeChange" @current-change="handleCurrentChange" ',
		'        :current-page="searchform.page" :page-sizes="[10,20,50,100]" :page-size="searchform.rows" layout="total, sizes, prev, pager, next, jumper" :total="total">',
		'      </el-pagination>',
		'    </div>',
		'  </el-tab-pane>',
		'</el-tabs>',
	].join(''),
	created: function () {
		var param = window.jasTools.base.getParamsInUrl(location.href);
		this._oid = param.oid || this.oid;
		this._type = param.approveType || this.type;
		this._className = param.className || this.className;
		this._functionCode = param.menuCode || this.functionCode;
		this._privilegeCode= param.privilegeCode || this.privilegeCode;
	},
	mounted: function () {

	},
	methods: {
		formatter: function (a, b, c) {
			if (c == 1) return '上报';
			if (c == 2) return '通过';
			if (c == -1) return '驳回';
		},
		handleClick: function (vm) {
			if (vm.name === 'second') {
				this.requestTableList();
			}
		},
		handleSizeChange: function (val) {
			this.searchform.rows = val;
			this.requestTableList();
		},
		handleCurrentChange: function (val) {
			this.searchform.page = val;
			this.requestTableList();
		},
		requestTableList: function () {
			var that = this;
			that.loading.table = true;
			var url = jasTools.base.rootPath + '/jdbc/commonData/dataApprove/getPage.do';
			jasTools.ajax.post(url, {
				businessOid: this._oid
			}, function (data) {
				setTimeout(function () {
					that.loading.table = false;
				}, 300)
				that.tableData = data.rows;
				that.total = data.total;
			});
		},
		requestApprove: function (status) {
			var that = this;

			if (status == -1 && !this.remarks) {
				top.Vue.prototype.$message({
					type: 'error',
					message: '驳回状态下，审批意见必填'
				});
				return;
			}

			var url = jasTools.base.rootPath + '/daq/dataApprove/save.do';
			jasTools.ajax.post(url, {
				businessOid: [this._oid],
				approveOpinion: this.remarks,
				approveStatus: status, //status 2 通过 -1 驳回
				className: this._className,
				functionCode: this._functionCode,
				privilegeCode:this._privilegeCode,
			}, function (data) {
				top.Vue.prototype.$message({
					type: 'success',
					message: '审核成功'
				});
				top.jasTools.dialog.close('success');
				top.app.requestNumber("refresh");
			});
		},
		close: function () {
			top.jasTools.dialog.close();
		},
	}
});


Vue.component('jas-remarks', {
	props: {
		remarks: {
			type: String
		}
	},
	data: function () {
		return {
			remarksDesc: 200,
			remark: this.remarks
		}
	},
	watch: {
		remarks: function () {
			if (this.remarks) {
				this.remark = this.remarks;
				this.remarksDesc = 200 - this.remarks.length;
			}
		}
	},
	template: [
		'<el-form-item label="备注">',
		'<el-input type="textarea"  :autosize="{ minRows: 2, maxRows: 6 }" :rows="2" size="small" v-model.trim="remark"',
		':maxLength="200"  @input="instructionNum"></el-input>',
		'<p style="text-align:right;color:#999;">您还可以输入<span v-text="remarksDesc"></span>字</p>',
		'</el-form-item>'
	].join(''),
	mounted: function () {


	},
	methods: {
		instructionNum: function () {
			if (this.remark) {
				var num = 200 - this.remark.trim().length;
				this.remarksDesc = num;
				this.$emit('changevalue', this.remark.trim());
			}else{
				this.$emit('changevalue', '');
			}

		}
	}
})




Vue.component('jas-detail-table-link', {
	props: {
		titles: {
			type: Array,
			required: true
		},
		detail: {

		}
	},
	data: function () {
		return {
			columnNum: 2,
		}
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
			if (this.titles.length === 1) {
				this.columnNum = 1;
			}
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
		'                <td v-if="subitem.isLink":ref="subitem.field" style="color: blue; cursor: pointer;"  @click="linkForDetail(subitem.link,detail[subitem.detailOid],subitem.detailTitle)" v-text="formatValue(detail[subitem.field],subitem.formatter)"></td>',
		'                <td v-else :ref="subitem.field" v-text="formatValue(detail[subitem.field],subitem.formatter)"></td>',
		'            </template>',
		'        </tr>',
		'    </template>',
		'</table>',
		'</div>'
	].join(''),
	methods: {
		formatValue: function (value, formatter) {
			if (formatter) {
				return formatter('', '', value, '');
			}
			return value;
		},
		resizeColumn: function () {
			var that = this;

			var width = that.$el.clientWidth;
			if (width < 660) {
				that.columnNum = 1;
			} else if (width < 1400) {
				that.columnNum = 2;
			} else {
				that.columnNum = 3;
			}
		},
		linkForDetail: function (src, oid, title) {
			if (!oid) return;
			var url = jasTools.base.setParamsToUrl(src, {
				oid: oid
			});
			top.jasTools.dialog.show({
				width: '55%',
				height: '80%',
				title: title,
				src: url,
			});
		}
	},
	mounted: function () {
		var that = this;
		this.resizeColumn();
		$(window).on('resize', function () {
			that.resizeColumn();

		});
	},
});


/*
 *针对自定义添加项目群的概念---select
 */

Vue.component('jas-project-select', {
	props: {
		projectarray: {
			type: Array
		},
		selprojectoids: {
			type: Array
		},
		label: {
			type: String
		},
		url: {
			type: String
		}
	},
	data: function () {
		var that = this;
		return {
			projectOids: [],
			projectArray: [{
				key: that.label,
				value: that.label
			}],
			oldOptions: [], //表示上次选中的值
			ids: [that.label, ], //表示所有下拉选的id
		}
	},
	template: [
		'<el-select size="mini" v-model="projectOids" collapse-tags multiple placeholder="请选择" @change="select">',
		'<el-option v-for="project in projectArray" :key="project.key" :label="project.value" :value="project.key">',
		'</el-option>',
		'</el-select>'
	].join(''),
	watch: {
		projectarray: function () {
			var that = this;
			if (that.projectarray && that.projectArray.length == 1) {
				that.requestProject();
			}

		}
	},
	mounted: function () {
		this.projectOids = this.selprojectoids;
		if (!this.projectarray) {
			this.requestProject();
		}
	},
	methods: {
		requestProject: function () {
			var that = this;
			if (that.projectarray) {
				if (that.projectarray.length > 0) {
					that.projectarray.forEach(function (item) {
						that.ids.push(item.key);
						that.projectArray.push(item);
					});
					that.projectOids = that.ids;
					that.oldOptions = that.projectOids;
					var ids = that.projectOids.filter(function (item) {
						return item != that.label
					});
					that.$emit("requestnet", ids);
				} else {
					var ids = [""];
					that.$emit("requestnet", ids);
				}
				return;
			}
			var url = jasTools.base.rootPath + that.url;
			jasTools.ajax.post(url, {}, function (data) {
				data.rows.forEach(function (item) {
					that.ids.push(item.key);
					that.projectArray.push(item);
				});
				that.projectOids = that.ids;
				that.oldOptions = that.projectOids;
				var ids = that.projectOids.filter(function (item) {
					return item != that.label
				});
				if (ids.length == 0) {
					ids[0] = '';
				}
				that.$emit("requestnet", ids);
			});
		},
		select: function (val) {
			var that = this;
			if (val.length == that.projectArray.length || (val.length == 1 && val[0] == that.label) || (val.length == 0)) { //表示肯定是全选
				that.projectOids = that.ids;
				that.oldOptions = that.projectOids;
				var ids = that.projectOids.filter(function (item) {
					return item != that.label
				});
				that.$emit("requestnet", ids);
				return;
			}
			if (val.indexOf(that.label) < 0) {
				if (that.oldOptions.length - 1 == val.length) {} else
				if (val.length == that.projectArray.length - 1) {
					that.projectOids = that.ids;
					that.oldOptions = that.projectOids;
				}
			} else { //表示此时包含0
				if (that.oldOptions.indexOf(that.label) > -1) { //此时表示取消全选操作
					that.projectOids = [];
					that.oldOptions = [];
					val.forEach(function (item) {
						if (item != that.label) {
							that.projectOids.push(item);
							that.oldOptions.push(item);
						}
					});
				} else {
					that.projectOids = that.ids;
					that.oldOptions = that.projectOids;
				}
			}
			var ids = that.projectOids.filter(function (item) {
				return item != that.label
			});
			that.$emit("requestnet", ids);
		}
	}

});


// 统计标题
Vue.component('statistic-group-project', { //项目群的分组
	props: {
		title: {
			type: String
		},
		search: {
			type: Object
		},
		datetype: {
			type: Object
		},
		projectarray: {
			type: Array
		}
	},
	data: function () {
		var that = this;
		return {
			projectOids: that.search.projectOids,
			date: that.search.date
		}
	},
	template: [
		'<el-row style="background:#ececec;">',
		'<div style="float:left;padding:10px 10px 5px 10px;">',
		'{{title}}',
		'</div>',
		'<div style="float:right;padding:5px 10px;">',
		'<jas-project-select @requestnet="requesttable" label="项目群" url="/daq/privilege/getProjectList.do" :projectarray="projectarray" :selprojectoids="projectOids" ></jas-project-select>',
		'<slot name="unit"></slot>',
		'<el-date-picker v-model="date" :picker-options="datetype.pickerOptions" :type="datetype.type" :value-format="datetype.format" :placeholder="datetype.placeholder" style="padding-left:10px" size="mini" @change="select">',
		'</el-date-picker>',
		'</div>',
		' </el-row>'
	].join(''),
	mounted: function () {

	},
	methods: {
		requesttable: function (oids) {
			var that = this;
			that.projectOids = oids;
			that.$emit("requestnet", that.projectOids, that.date);
		},
		select: function () {
			var that = this;
			that.$emit("requestnet", that.projectOids, that.date);
		}
	}
});

Vue.component('statistic-group', { //项目分组
	props: {
		title: {
			type: String
		},
		search: {
			type: Object
		},
		projectarray: {
			type: Array
		},
		datetype: {
			type: Object
		}
	},
	data: function () {
		var that = this;
		return {
			projectOids: that.search.projectOid,
			projectArray: [],
			date: that.search.date
		}
	},
	template: [
		'<el-row style="background:#ececec;">',
		'<div style="float:left;padding:10px 10px 5px 10px;">',
		'{{title}}',
		'</div>',
		'<div style="float:right;padding:5px 10px;">',
		'<el-select size="mini" v-model="projectOids"  placeholder="请选择" @change="select">',
		'<el-option v-for="project in projectArray" :key="project.key" :label="project.value" :value="project.key">',
		'</el-option>',
		'</el-select>',
		'<el-date-picker v-model="date" :picker-options="datetype.pickerOptions" placeholder="选择日期" :type="datetype.type" :value-format="datetype.format"  style="padding-left:10px" size="mini"  @change="select">',
		'</el-date-picker>',
		'</div>',
		' </el-row>'
	].join(''),
	watch: {
		projectarray: function () {
			var that = this;
			if (that.projectarray && that.projectArray.length == 0) {
				that.requestProject();
			}
		}
	},
	mounted: function () {
		if (!this.projectarray) {
			this.requestProject();
		}
	},
	methods: {
		requestProject: function () {
			var that = this;
			if (that.projectarray) {
				if (that.projectarray.length > 0) {
					that.projectArray = that.projectarray;
					that.projectOids = that.projectarray[0].key;
					that.$emit("requestnet", that.projectOids, that.date);

				} else {
					that.$emit("requestnet", '', that.date);
				}
				return;
			}
			var url = jasTools.base.rootPath + "/daq/privilege/getProjectList.do";
			jasTools.ajax.post(url, {}, function (data) {
				data.rows.forEach(function (item) {
					that.projectArray.push(item);
				});
				if (that.projectArray.length > 0) {
					that.projectOids = that.projectArray[0].key;
				} else {
					that.projectOids = "";
				}
				that.$emit("requestnet", that.projectOids, that.date);
			});

		},
		select: function () {
			var that = this;
			that.$emit("requestnet", that.projectOids, that.date);
		}
	}
});