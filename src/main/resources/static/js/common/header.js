/**
 * Created by wangqian on 2019/3/29.
 */
var vue = new Vue({
    el: "#header",
    data: {
        profileStyle: {
            display: 'none'
        },
        controlStyle: {
            display: 'none'
        },
        isShow: false
    },
    methods: {
        getUserInfo: function () {
            this.$http.post('/user/get_user_info.do',{emulateJSON:true}).then(this.successCallback, this.errorCallback)
        },
        successCallback: function ( res ) {
            res = res.body;
            if( res.data && res.status == 0){
                this.controlStyle.display = 'none'
                this.profileStyle.display = ''
                console.log(res.data)
            }else {
                // do nothing
                this.profileStyle.display = 'none'
                this.controlStyle.display = ''
            }
        },
        errorCallback: function ( res ) {
            util.errorTips()
        },
        handleUserClick: function () {
            this.isShow = !this.isShow
        },
        handleLoginOut: function () {
            this.$http.post('/user/logout.do',{emulateJSON:true}).then(this.outSuccessCallback, this.errorCallback)
        },
        outSuccessCallback: function ( res ) {
            res = res.body;
            if( res.status == 0){
                window.location.href = util.getUrlParam('redirect') || './index.html';
                console.log(res.data)
            }else {
                util.errorTips( res.msg )
            }
        }
    },
    created: function () {
        this.getUserInfo();
    }
})