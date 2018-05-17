(function (window, Vue, config) {
	window.VueInstIndex = new Vue({
		el: '#app',
		data: function () {
			return {
				isExpend: true,
				menuWith: config.menuWith,
				currentTap: config.activeMenu,
				tabs: [],
				items: config.menus
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
			}
		},
		methods: {

			selectMenu: function (index, indexPath) {
				var that = this;
				var newTap = '';
				this.tabs.forEach(function (item) {
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
			this.tabs = this._createTabsArr(config.menusOpened, this.items);

			// Vue.compile('<div><span>{{ msg }}</span></div>')
			// setTimeout(function(){
			//   jasTools.showDialog()
			// },10000 )

		}
	})
})(window, Vue, IndexConfig);