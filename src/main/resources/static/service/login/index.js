/**
 * Created by wangqian on 2019/3/29.
 */
var vue = new Vue({
    el: "#app",
    data: {
        username: "",
        password: "",
        url: ""
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
                if ( this.url != null ){
                    this.$http.get(this.url).then(this.otherSuccessCallback, this.errorCallback);
                }else {
                    window.location.href = './index.html';
                }
            }else {
                util.errorTips( res.msg )
            }
        },
        errorCallback: function ( res ) {
            util.errorTips()
        },
        otherSuccessCallback: function ( res ) {

        }
    },
    mounted: function () {
        this.url = this.$refs.urlRef.value;
        console.log( this.url )
    }
});