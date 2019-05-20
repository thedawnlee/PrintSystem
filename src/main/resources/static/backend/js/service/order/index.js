/**
 * Created by wangqian on 2019/4/20.
 */

var vue = new Vue({
    el: "#order",
    data: {
        getKeyItem: false,
        getKey: "",
        orderNo: ""
    },
    methods: {
        handleGetFileCLick: function () {
            /* 点击通知取货 */
            this.noticeGetFile();
        },
        handleGetKeyItemClose: function () {
            this.getKeyItem = false
        },
        handleRefuseOrder: function () {

        },
        handleAcceptOrder: function () {

            console.log(" accept ")
            this.getKeyItem = true;

        },
        handleNoticePicking: function () {

        },
        noticeGetFile: function () {
            this.$http.get("/store/order/notice.do", {params: {getKey: this.getKey, orderNo: this.orderNo}}).then(function ( res ) {
                res = res.body;
                if( res.status == 0){

                    alert(" 通知用户成功！ ");

                    location.href = "/store/order/detail/" + this.orderNo;

                }else {
                    util.errorTips( res.msg );
                    if ( (res.msg) == "NEED_LOGIN") {
                        location.href = "/store/login"
                    }
                }
            }, function () {
                util.errorTips(" 请求通知用户取货码失败，请稍后再试！ ");
            });
        }
    },
    mounted: function(){
        this.orderNo = this.$refs.orderNo.value;
    }
});