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

      }
    },
    computed: {

    },
    template: [
      '<el-dialog :ref="refSelf" class="jas-iframe-dialog" :title="title" :visible.sync="visible" :width="width" :fullscreen="false">',
      '  <iframe class="dialog-iframe" :src="iframeUrl" frameborder="0"></iframe>',
      '</el-dialog>'
    ].join(''),
  });