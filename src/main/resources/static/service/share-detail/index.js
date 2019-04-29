/**
 * Created by wangqian on 2019/4/24.
 */
var vue = new Vue({
    el: "#share-detail",
    data: {
    },
    methods: {
        prepareForDownload: function ( value ) {
            this.$http.get('/share/before', {params: {id: value }}).then(function ( res ) {
                res = res.body;
                if( res.data && res.status == 0){

                    if (confirm("积分消耗后无法撤回，是否继续？")){
                        location.href = "/share/download/" + value;
                    }

                }else {
                    util.errorTips( res.msg )
                }
            }, function () {

            })
        },
        handleDownloadClick: function ( value ) {
            this.prepareForDownload(value);

        }
    },
    created: function () {
    }
});
