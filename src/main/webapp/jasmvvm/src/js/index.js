(function (window, Vue) {
	window.VueInstIndex = new Vue({
		el: '#app',
		data: function () {
			return {
				isExpend: true,
				menuWith: 200,
				menusOpened: ['P-PRI-0020'],
				currentTap: 'P-PRI-0020',
				tabs: [], // 打开的标签页
				items: [] //菜单数组
			}
		},
		created: function () {
			var that = this;
			var url = jasTools.base.rootPath + '/jasframework/privilege/privilege/getAllUserFuntion.do';
			$.ajax({
				url: url + "?token=" + localStorage.getItem('token'),
				type: "POST",
				async: false,
				data: {
					"menutype": "0",
					"appId": "402894a152681ba30152681e8b320003",
					"language": "zh_CN"
				},
				// dataType: "json",
				success: function (data) {

					that.items = that._formatMenus(data);
					return;

					// "children": [{
					// 		"attributes": {
					// 			"functionid": "51cfd72f-46c2-4803-8ee1-70d0bbf7be5e",
					// 			"URL": "jasframework/workflow/myundotask.htm"
					// 		},
					// 		"id": "P-WF-0020",
					// 		"text": "待办工作"
					// 	},
					// 	{
					// 		"attributes": {
					// 			"functionid": "3fbe1787-7653-4ed3-94b5-79de80c68e49",
					// 			"URL": "jasframework/workflow/mytask.htm"
					// 		},
					// 		"id": "P-WF-0040",
					// 		"text": "已办工作"
					// 	}
					// ],
					// "id": "7efdfc31-afed-4d49-91df-e153d6f60300",
					// "text": "日常办公",
					// "state": "closed"
				}
			});


		},
		computed: {
			menuStyle: function () {
				return {
					width: this.isCollapse ? '64px' : (this.menuWith || 200) + 'px'
				}
			},
			isCollapse: function () {
				return !this.isExpend;
			}
		},
		methods: {
			_formatMenus: function (aMenu) {
				var _aMenu = JSON.parse(JSON.stringify(aMenu));
				var switcher = function (arr) {
					arr.length && arr.forEach(function (item) {
						item.index = item.id;
						item.icon = item.icon || 'el-icon-setting';
						item.title = item.text;
						if (item.attributes && item.attributes.URL) {
							item.link = jasTools.base.rootPath + '/' + item.attributes.URL;
						}
						item.subs = item.children;
						if (item.subs) {
							switcher(item.subs);
						}
					})
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
					location.href = './login.html';
				}
			}
		},
		mounted: function () {
			this._setWindowResizeEventToCloseMenu();
			this.tabs = this._createTabsArr(this.menusOpened, this.items);

			// Vue.compile('<div><span>{{ msg }}</span></div>')
			// setTimeout(function(){
			//   jasTools.showDialog()
			// },10000 )

		}
	})
})(window, Vue);