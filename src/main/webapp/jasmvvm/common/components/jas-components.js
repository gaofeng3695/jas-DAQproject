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
    data: function() {

        return {
            selfvisible: true,
        }
    },
    watch: {
        visible: function() {
            this.selfvisible = this.visible;
        },
        selfvisible: function(newValue) {
            this.$emit('update:visible', newValue);
        },
    },
    methods: {
        close: function() {
            // this.$emit('update:visible', false)
            this.$emit('close');
        },
        setDialogHeiht: function() {
            var dom = this.$el.querySelector('.el-dialog');
            var height = this.height.split('%')[0];
            if (height <= 0 || height >= 100) return {};
            dom.style['margin-top'] = (100 - height) / 2 + 'vh',
                dom.style['height'] = height + 'vh'
        }
    },
    mounted: function() {
        this.setDialogHeiht();
    },
    template: [
        '<el-dialog :ref="refSelf" class="jas-iframe-dialog" :title="title" :visible.sync="selfvisible" :width="width" @close="close" :fullscreen="false">',
        '  <iframe class="dialog-iframe" :src="iframeUrl" frameborder="0"></iframe>',
        '</el-dialog>'
    ].join(''),
});