window.IndexConfig = {
	menuWith: 200,
	activeMenu: 'P-PRI-0020',
	menusOpened: ['P-PRI-0020'],
	menus: [{
			icon: 'el-icon-setting',
			index: 'dash-board', //id
			title: '自定义表单', //text
			subs: [{ //children
				index: 'dash-board222112',
				title: '数据库表及字段维护',
				closable: true,
				link: './pages/table-operation/table-operation.html' //attributes.URL
			}, {
				index: 'dash-board22211223',
				title: '自定义表单配置',
				closable: true,
				link: './pages/form-operation/form-operation.html'
			}, {
				index: 'dash_board22211223123',
				title: '表实体生成',
				closable: true,
				link: './pages/entity-operation/entity.html'
			}, {
				index: 'dash_board23',
				title: '域值维护',
				closable: true,
				link: './pages/domain-operation/domainList.html'
			}]
		},
		{
			icon: 'fa fa-users',
			index: 'dash-board32',
			title: '权限控制',
			link: './../jasframework/privilege/adsync/adsyn.htm'
		},
		{
			icon: 'fa fa-suitcase',
			index: 'dash-board2',
			title: '深圳采集系统',
			link: './pages/template/attachment.html'
		},
		{
			icon: 'fa fa-users',
			index: 'dash-board3',
			title: '百度',
			link: './pages/template/template.html?menuCode=fun61'
		}, {
			icon: 'fa fa-photo',
			index: 'dash-board22',
			title: '巡线卫士',
			link: './pages/template/template.html?menuCode=fun3'
		},
		{
			icon: 'fa fa-wifi',
			index: 'dash-board4',
			title: '表单组件',
			subs: [{
				icon: 'fa fa-photo',
				index: 'dash-board2221',
				title: '自定义表单',
				link: './pages/form-awesome/form-awesome.html'
			}]
		},
		{
			icon: 'el-icon-menu',
			index: 'product',
			title: '多级菜单',
			subs: [{
				icon: 'fa fa-bullseye',
				index: 'service1',
				title: '中国',
				subs: [{
					index: 'service11',
					title: '北京市',
					subs: [{
						index: 'service111',
						title: '海淀区',
						subs: [{
							index: 'service11111',
							title: '学院路'
						}]
					}]
				}]
			}]
		},
		{
			icon: 'el-icon-menu',
			index: 'application1',
			title: '图标示例',
			subs: [{
					icon: 'fa fa-beer',
					index: 'applicamanage4',
					title: '图标前缀',
					subs: [{
							icon: 'fa fa-bus',
							index: 'rvice2123112',
							title: '腾讯w'
						},
						{
							icon: 'fa fa-cab',
							index: 'rvice2123221',
							title: '阿里e'
						},
						{
							icon: 'fa fa-bug',
							index: 'rvice21233221',
							title: '百度r'
						},
					]
				},
				{
					index: 'application-manage41',
					title: '不加前缀',
					subs: [{
							index: 'service212311',
							title: '腾讯'
						},
						{
							index: 'service212321',
							title: '阿里'
						},
						{
							index: 'service212331',
							title: '百度'
						},
					]
				}
			]
		},

	]
};