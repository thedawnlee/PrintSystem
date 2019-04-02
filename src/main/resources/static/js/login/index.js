/**
 * Created by wangqian on 2019/3/29.
 */
var vue = new Vue({
    el: "#app",
    data: {
        username: "",
        password: ""
    },
    methods: {
        handleClick: function () {
            this.$http.post('/user/login.do', {
                username: this.username,
                password: this.password
            }, {emulateJSON:true}).then(this.successCallback, this.errorCallback)
        },
        successCallback: function ( res ) {
            res = res.body;
            if( res.data && res.status == 0){
                window.location.href = './index.html';
            }else {
                util.errorTips( res.msg )
            }
        },
        errorCallback: function ( res ) {
            util.errorTips()
        }
    },
    created: function () {
        //this.getBlogInfo(1, util.pageSize());
    }
})