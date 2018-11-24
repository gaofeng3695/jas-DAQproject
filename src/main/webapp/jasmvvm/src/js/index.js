Vue.component('loading-bar', LoadingBar);
var gisMap = {
	drawLineByStakes: function (features, jasMap) {
		var that = this;
		//var features = source.getFeatures();
		var dataArray = []; //最终绘制数据
		var pipelineArray = []; //按照每一个管线的数组
		var lineidArray = []; //用于存储管线id
		for (var i = 0; i < features.length; i++) {
			var obj = {};
			var feature = features[i].values_;
			//var properties = feature.getProperties(); //属性信息
			//var geom = feature.getGeometry(); // Point
			var projectId=feature.project_oid;//先根据项目进行分组
			var lineId = feature.pipeline_oid;//再根据管线id进行分组
			obj.mileage = feature.mileage; //里程值
			obj.lineId = lineId; //线路段
			obj.projectId=projectId;//项目
			obj.coor = feature.geometry.flatCoordinates; //
//			obj.mileage = source[i].mileage;
//			obj.projectId = source[i].project_oid;
//			obj.lineId = source[i].pipeline_oid;
//			obj.coor = source[i].coor;
			pipelineArray.push(obj);
		}

		var map = {},
			dataArray = [];
		for (var i = 0; i < pipelineArray.length; i++) {
			var ai = pipelineArray[i];
			if (!map[ai.lineId]) {
				dataArray.push({
					lineId: ai.lineId,
					data: [ai]
				});
				map[ai.lineId] = ai;
			} else {
				for (var j = 0; j < dataArray.length; j++) {
					var dj = dataArray[j];
					if (dj.lineId == ai.lineId) {
						dj.data.push(ai);
						break;
					}
				}
			}
		}
		//各个线路段按照里程排序
		for (i = 0; i < dataArray.length; i++) {
			var pipeArr = dataArray[i].data;
			dataArray[i].data = pipeArr.sort(that.compare("mileage"));
		}
		//此时的dataArray 已经按照管线分组，并且按照里程进行排序
		jasMap.clearMapGraphics();
		//生成线
		for (i = 0; i < dataArray.length; i++) {
			var stakes = dataArray[i].data;
			var coors = [];
			for (var j = 0; j < stakes.length; j++) {
				var stake = stakes[j];
				coors.push(stake.coor);
			}
			var color = that.randomColor(i);
							console.log(color);
			jasMap.addPolylineGraphic(coors, {
				color: color,
				width: 3
			});
		}

	},
	randomColor: function (i) {
		var that = this;
		//, '', 
		var colorArr = ['#CC0000','#00FF00','#0066FF','#CC33FF','#CCFF00','#FF66FF','#FFFF00','#00FFFF','#00FFCC'];
		if (i < colorArr.length) {
			return colorArr[i];
		} else {
			return colorArr[that.random(0, 8)];
		}
	},
	random: function (min, max) {
		var Range = max - min;
		var Rand = Math.random();
		var num = min + Math.round(Rand * Range); //四舍五入
		// console.log(num);
		return num;
	},
	compare: function (prop) {
		return function (obj1, obj2) {
			var val1 = obj1[prop];
			var val2 = obj2[prop];
			if (!isNaN(Number(val1)) && !isNaN(Number(val2))) {
				val1 = Number(val1);
				val2 = Number(val2);
			}
			if (val1 < val2) {
				return -1;
			} else if (val1 > val2) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
window.app = new Vue({
	el: '#app',
	data: function () {
		return {
			username: localStorage.getItem('user') && JSON.parse(localStorage.getItem('user')).userName,
			userunit: localStorage.getItem('user') && JSON.parse(localStorage.getItem('user')).unitName,
			userImg: './src/images/enterlogo.png',
			progress: 0,
			error: false,
			direction: 'right',
			panelShowed: false,
			isExpend: true,
			menuWith: 200,
			menusOpened: ['statistics-01'],
			currentTap: 'statistics-01',
			tabs: [], // 打开的标签页
			items: [], //菜单数组
			isMapInited: false //地图未初始化
		}
	},
	computed: {
		menuStyle: function () {
			return {
				width: this.isCollapse ? '64px' : (this.menuWith || 200) + 'px'
			}
		},
		isCollapse: function () {
			return !this.isExpend;
		},
	},
	watch: {
		tabs: function (newval, oldval) {
			if (newval.length - oldval.length >= 0) {
				this.goProgess();
			}
		}
	},
	created: function () {
		// this.initJasMap();
	},
	mounted: function () {
		// this.$refs.resizer.panelShowed = false;
		this.goProgess();
		this._queryMenuData();
		this._listenWindowClose();
		this._setWindowResizeEventToCloseMenu();
		this._requestLoginInfo();
		this.requestFile();
	},
	methods: {
		_queryMenuData: function () {
			var that = this; // 获取左侧菜单
			var url = jasTools.base.rootPath + '/jasframework/privilege/privilege/getAllUserFuntion.do';
			$.ajax({
				url: url + "?token=" + localStorage.getItem('token'),
				type: 'get',
				async: true,
				data: {
					"menutype": "0",
					"appId": "402894a152681ba30152681e8b320003",
					"language": "zh_CN"
				},
				success: function (data, xhr, param) {
					if (typeof data === 'object' && data.length > 0) {
						that.items = that._formatMenus(data);
						that.tabs = that._createTabsArr(that.menusOpened, that.items);
					}

				}
			});
		},
		_formatMenus: function (aMenu) {
			var _aMenu = JSON.parse(JSON.stringify(aMenu));
			var switcher = function (arr) {
				if (typeof arr === "object") {
					arr.forEach(function (item) {
						item.index = item.id || '';
						item.icon = item.icon || 'fa fa-bookmark'; //fa-bars fa-bookmark
						item.title = item.text;
						if (item.attributes && item.attributes.URL) {
							item.link = jasTools.base.rootPath + '/' + item.attributes.URL;
						}
						item.subs = item.children;
						if (item.subs) {
							switcher(item.subs);
						}
					})
				}

			};
			switcher(_aMenu);
			return _aMenu;

		},
		selectMenu: function (index, indexPath) {
			var that = this;
			var newTap = '';
			this.tabs.forEach(function (item) { //循环打开的标签页，判断选中的菜单是否带开过
				if (item.name === index) {
					newTap = index;
				}
			});
			if (!newTap) {
				var aTab = this._createTabsArr([index], this.items);
				this.tabs.push(aTab[0]);
			}
			this.currentTap = index;
		},
		removeTab: function (targetName) {
			var tabs = this.tabs;
			var activeName = this.currentTap;
			//如果当前显示的tab页被删除，更改当前显示的tab页为下一页
			if (activeName === targetName) {
				tabs.forEach(function (tab, index) {
					if (tab.name === targetName) {
						var nextTab = tabs[index + 1] || tabs[index - 1];
						if (nextTab) {
							activeName = nextTab.name;
						}
					}
				});
			}
			//设定当前显示的tab页
			this.currentTap = activeName;
			//在原数组中删除这个要被删除的tab
			this.tabs = tabs.filter(function (tab) {
				return tab.name !== targetName;
			})
		},
		_getMenuInfoByIndex: function (index, aMenu) {
			var _icon = '';
			var _title = '';
			var _closable = true;
			var _link = '';
			var isGetResult = false;

			var getTitle = function (index, aMenu) {
				for (var i = 0; i < aMenu.length; i++) {
					var item = aMenu[i];

					if (item.subs) { //如果有子集递归处理

						if (!isGetResult) {
							_icon = item.icon;
							_title = item.title;
							getTitle(index, item.subs);
						}
					} else {
						if (index === item.index) {
							isGetResult = true;
							_icon = item.icon;
							_title = item.title;
							_link = item.link;
							_closable = item.closable !== false ? true : false;
							return;
						}
					}
				}
			};
			getTitle(index, aMenu);
			return {
				icon: _icon,
				title: _title,
				closable: _closable,
				link: _link || '',
			}
		},
		_setWindowResizeEventToCloseMenu: function () {
			var that = this;
			window.addEventListener('resize', function () {
				if (document.body.clientWidth < 900 && that.isExpend) {
					that.isExpend = false;
				}
			});
		},
		_listenWindowClose: function () {
			var that = this;
			$(window).bind("beforeunload", function (e) {
				var e = window.event || e;　　
				console.log(e)
				// e.returnValue = ("确定离开当前页面吗？");
				//that._loginOut();
			});
		},
		_loginOut: function () {
			var url = jasTools.base.rootPath + '/jasframework/login/logout.do';

			jasTools.ajax.get(url, {}, function (data) {
				localStorage.removeItem('token');
				localStorage.removeItem('user');
				location.href = './login.html';
			}, function () {
				localStorage.removeItem('token');
				localStorage.removeItem('user');
				location.href = './login.html';
			});
		},
		_goPage: function () {
			location.href = './onepage.html';
		},
		_createTabsArr: function (aIndex, aMenu) {
			var that = this;

			return aIndex.map(function (index) {
				var info = that._getMenuInfoByIndex(index, aMenu);
				return {
					title: info.title,
					name: index,
					link: info.link,
					icon: info.icon,
					closable: info.closable
				}
			});
		},
		handleCommand: function (command) {
			if (command === 'loginout') {
				this._loginOut();
			} else if (command === 'fullscreen') {
				this._goFullscreen();
			} else if (command === 'resetPassword') {
				this._resetPassword();
			}
		},
		_goFullscreen: function () {
			if (screenfull.enabled) {
				// screenfull.toggle($('.tabswrapper .el-tabs__content')[0]);
				screenfull.toggle();
			} else {
				window.top.Vue.prototype.$message({
					message: '您的浏览器不支持全屏',
					type: 'error'
				});
				// Ignore or do something else
			}
		},
		goProgess: function () {
			var that = this;
			that.progress = 10;
			clearInterval(that.timer);
			this.timer = setInterval(function () {
				that.progress += 5;
				if (that.progress >= 100) {
					clearInterval(that.timer);
				}
			}, 10);


		},
		errorDone: function () {
			this.error = false
		},
		progressDone: function () {
			this.progress = 0
		},
		_resetPassword: function () {
			var that = this;
			jasTools.dialog.show({
				title: '修改密码',
				width: '530px',
				height: '530px',
				src: 'resetword.html',
				cbForClose: function (param) {
					if (param === 1) {
						that._loginOut();
					}
				}
			});
		},
		_requestLoginInfo: function () {
			var that = this;
			var url = jasTools.base.rootPath + '/jasframework/log/login/getUserStatisticsInfo.do';

			jasTools.ajax.get(url, {}, function (data) {
				var obj = data.data;
				that.$notify({
					type: 'success',
					title: '登录成功',
					position: 'bottom-right',
					dangerouslyUseHTMLString: true,
					message: [
						'<div style="font-size:12px;">',
						'	<div><span style="color:#409EFF;font-weight: 700;">' + obj.userName + '</span>,您好！</div>',
						'	<div>登录次数：<span style="color:#409EFF;">' + obj.loginCount + '</span></div>',
						'	<div>累计在线时长：<span style="color:#409EFF;">' + obj.totalLoginDate + '</span></div>',
						'	<div>上次登录IP：<span style="color:#409EFF;">' + obj.clientIp + '</span></div>',
						'	<div>上次登录时间：<span style="color:#409EFF;">' + obj.lastLoginDate + '</span></div>',
						'</div>'
					].join('')
				});

			}, function () {

			});


		},
		paneresize: function () {
			this.jasMap && this.jasMap.resizeMap();
		},
		statuschanged: function (val) {
			var that = this;
			if (val) {
				if (!that.isMapInited) {
					that.isMapInited = true;
					setTimeout(function () {
						that.initJasMap();
					}, 300);
				}
			}
		},
		initJasMap: function (fn) {
			var that = this;
			var onCenterStakeLayerClicked = function (e) {
				//业务逻辑
			};
			this.jasMap = new JasMap({
				layersVisible: {
					daq_median_stake: true,
				},
				appConfig: './pages/map/config.json',
				onMapLoaded: function (e) {
					fn && fn();
					//that.addMapListener();

				},
				onError: function (e) {
					top.Vue.prototype.$message({
						message: e.data.message,
						type: 'error'
					});
				},
			    onOptionalLayersLoaded:function(){
	               that.addMapListener();
	            },
				onLayerAdded: function (e) {
					var layerId = e.data.layerId;
					if (layerId === "daq_median_stake") {
						//添加单个图层的点击事件
						this.addLayerClickEventListener(layerId, onCenterStakeLayerClicked);
					}
				}
			});
		},
		addMapListener: function () {
			var jasMap = this.jasMap;
		      var paramsArray = [] ;
              paramsArray.push({
                  layerId : 'daq_median_stake'
              });
              jasMap.queryFeatures(paramsArray,function(features){
                 // console.info(features);
               //   gisMap.drawLineByStakes(features, jasMap);
              });
              
			//jasMap.removeEventListener(this.layerListener);
			//进行中线桩数据的读取
//			var url = jasTools.base.rootPath + "/jdbc/commonData/medianStake/getPage.do";
//			jasTools.ajax.post(url, {
//					pageNo: 1,
//					pageSize: 100000,
//				},
//				function (data) {
//					var source = [];
//					data.rows.forEach(function (item) {
//						//						var projectId=properties.project_oid;//先根据项目进行分组
//						//						var lineId = properties.pipeline_oid;//再根据管线id进行分组
//						//						obj.mileage = properties.mileage; //里程值
//						source.push({
//							project_oid: item.projectOid,
//							pipeline_oid: item.pipelineOid,
//							mileage: item.mileage,
//							coor: [item.pointx, item.pointy]
//						});
//					});
//
//					gisMap.drawLineByStakes(source, jasMap);
//				});
		},
		locate: function (id, tableCode) {
			var that = this;
			if (!this.$refs.resizer.panelShowed) {
				this.$refs.resizer.panelShowed = true;
				//setTimeout(function () {
				if (!that.isMapInited) {
					that.isMapInited = true;
					that.initJasMap();
					top.Vue.prototype.$message({
						message: '正在初始化地图，请稍后定位',
						type: 'warning'
					});
				} else {
					that.jasMap.flashGraphic(id, tableCode);
				}
				//}, 300);
			} else {
				this.jasMap.flashGraphic(id, tableCode);
			}
		},
		//获取附件文件
		requestFile: function () {
			var that = this;
			var url = jasTools.base.rootPath + '/attachment/getInfo.do';
			var sBizId = JSON.parse(localStorage.getItem('user')).oid;
			jasTools.ajax.get(url, {
				businessType: 'photo',
				businessId: sBizId,
			}, function (data) {
				var arr = data.rows;
				if (data.rows.length > 0) {
					that.userImg = jasTools.base.rootPath + '/attachment/app/getImageBySize.do?oid=' + data.rows[0].oid + "&token=" + localStorage.getItem("token");
				}

			});
		},
	},
})