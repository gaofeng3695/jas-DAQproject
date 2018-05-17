window.IndexConfig = {
  menuWith: 200,
  activeMenu: 'dash-board222112',
  menusOpened: ['dash-board222112'],
  menus: [{
      icon: 'el-icon-setting',
      index: 'dash-board',
      title: '自定义表单',
      subs: [{
        index: 'dash-board222112',
        title: '数据库表及字段维护',
        closable: false,
        link: './pages/table-operation/table-operation.html'
      }]
    },
    {

      index: 'dash-board32',
      title: '对话框',
      closable: false,
      link: './pages/dialog/dialog.html'
    },
    {

      index: 'dash-board2',
      title: '深圳采集系统',
      link: 'http://218.17.114.4:8989/szgasdcs/jasframework/login/login.htm'

    },
    {

      index: 'dash-board3',
      title: '百度',
      link: 'https://www.baidu.com/'
    }, {

      index: 'dash-board22',
      title: '巡线卫士',
      link: 'https://www.baidu.com/s?wd=巡线卫士'
    },
    {

      index: 'dash-board4',
      title: '表单组件',
      subs: [{

        index: 'dash-board2221',
        title: '自定义表单',
        link: './pages/form-awesome/form-awesome.html'
      }]
    },
    {

      index: 'product',
      title: '多级菜单',
      subs: [{

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

      index: 'application1',
      title: '图标示例',
      subs: [{

          index: 'applicamanage4',
          title: '图标前缀',
          subs: [{

              index: 'rvice2123112',
              title: '腾讯w'
            },
            {

              index: 'rvice2123221',
              title: '阿里e'
            },
            {

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