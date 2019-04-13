/**
 * Created by wangqian on 2019/4/9.
 */
var vue = new Vue({
    el: "#order",
    data: {
        orderNo: null,
        qrUrl: "http://image.qian.com/loading.gif",
        getQr: true,
        payMethods: true,
        paymentTimer: null,
        paySuccess: true,
        displayButton: "支付"
    },
    methods: {
        pay: function () {
            this.payMethods = false;
            this.getQr = false;
            console.log(" 请求支付... ")
            this.$http.get('/pay/pay.do', {params: {orderNo: this.orderNo }}).then(this.successCallback, this.errorCallback);
        },
        successCallback: function ( res ) {
            res = res.body;
            if( res.data && res.status == 0){
                this.orderNo = res.data.data.orderNo;
                this.qrUrl = res.data.data.qrUrl;
                this.listenOrderStatus();
            }else {
                util.errorTips( res.msg )
            }
        },
        errorCallback: function ( res ) {
            util.errorTips( "请求支付发生错误！" )
        },
        listenOrderStatus: function () {

            var _this = this;

            this.paymentTimer = window.setInterval(function(){
                _this.$http.get('/pay/query_order_pay_status.do', {params: {orderNo: _this.orderNo }}).then(function ( res ) {
                    console.log( res );
                    res = res.body;
                    console.log( res );
                    if( res.status == 0 ){
                        if ( res.data == true ){
                            window.clearInterval(_this.paymentTimer);
                            _this.paySuccess = false;
                        }else {
                            console.log( "轮询中，等待支付....");
                        }
                    }else {
                        util.errorTips( res.msg )
                    }
                }, function () {
                    util.errorTips( "查询支付状态失败！" )
                });
            },5000);

        },
        handlePayClick: function () {
            this.pay();
        },
        handleGoIndex: function () {
            window.location.href = '/index';
        },
        handleGoBackClick: function () {
            window.location.href = "/order/info";
        }
    },
    mounted: function () {
        this.orderNo = this.$refs.orderNoRef.value.trim();
        this.orderStatus = this.$refs.orderStatusRef.value.trim();

        switch (this.orderStatus) {
            case "未支付":
                this.displayButton = "确认支付";
                break;
            case "已付款":
                this.displayButton = "我要退款";
                break;
            case "待取货":
                this.displayButton = "取货码";
                break;
            case "店主拒绝":
                this.displayButton = "";
                break;
            case "订单完结":
                this.displayButton = "关闭订单";
                break;
            case "订单关闭":
                this.displayButton = "";
                break;
            case "已取消":
                this.displayButton = "";
                break;
        }

    }
})