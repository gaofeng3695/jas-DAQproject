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
            if(!this.detail) return [];
            this.titles.forEach(function (item, index, arr) {
                if (index % that.columnNum === 0) {
                    var _arr = [];
                    for (var i = 0; i < that.columnNum; i++) {
                        if(arr[index + i] !== undefined){
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