/**
 * Created by wangqian on 2019/4/10.
 */
var vue = new Vue({
    el: "#user",
    data: {
        dealItem: true,
        accountItem: true,
        fileItem: true,
        userItem: true,
        orderDeal: true,
        item: "user", /* 获取后端用户接口的时候会把item进行赋值，如果是user就展示基本信息，file就展示文件信息 */
        answer: "",
        email: "",
        headerPic: "",
        integral: "0",
        phone: "",
        question: "",
        username: "",
        list: [],
        total: null,
        noOrder: true, /* 如果没有订单，控制显示 无订单 的提示图片 */
        noIntegral: true,/* 如果没有积分，控制显示 无交易 的提示图片 */
        noFile: true
    },
    methods: {
        getUserInfo: function () {
            this.$http.post('/user/get_user_info.do',{emulateJSON:true}).then(function ( res ) {
                res = res.body;
                if( res.data && res.status == 0){
                    this.username = res.data.username;
                    this.question = res.data.question;
                    this.integral = res.data.integral;
                    this.headerPic = res.data.headerPic;
                    this.email = res.data.email;
                    this.answer = res.data.answer;
                    this.phone = res.data.phone;
                }else {
                    util.errorTips( res.msg );
                }
            }, function () {
                util.errorTips( "获取用户信息失败" );
            })
        },
        getDealOrderInfo: function (pageNum, pageSize) {
            this.$http.get('/order/get_order_list.do', {params: {pageNum: pageNum, pageSize: pageSize}}).then(function ( res ) {
                res = res.body;
                if( res.data && res.status == 0){
                    this.list = res.data.list;
                    this.total = res.data.total;
                    if ( (res.data.total) == 0) {
                        this.noOrder = false;
                    }
                    console.log( res.data )
                }else {
                    util.errorTips( res.msg );
                }
            }, function () {
                util.errorTips( "获取订单信息失败" );
            })
        },
        getFileInfo: function (pageNum, pageSize) {
            this.$http.get('/file/get_file_list.do', {params: {pageNum: pageNum, pageSize: pageSize}}).then(function ( res ) {
                res = res.body;
                if( res.data && res.status == 0){
                    this.list = res.data.list;
                    this.total = res.data.total;
                }else {
                    util.errorTips( res.msg );
                }
            }, function () {
                util.errorTips( "获取订单信息失败" );
            })
        },
        handleOrderDetailClick: function ( value ) {
            console.log("handleOrderDetailClick:", value)
        },
        handleShareClick: function ( value ) {
            console.log("点击去分享:", value)
        },
        handleUserClick: function () {
            this.showValueItem("user");
        },
        handleDealClick: function () {
            this.getDealOrderInfo(1, 5);
            this.showValueItem("deal");
        },
        handleFileClick: function () {
            this.getFileInfo(1, 5);
            this.showValueItem("file");
        },
        handleAccountClick: function () {
            this.showValueItem("account");
        },
        handleIntegralSelect: function () {
            this.orderDeal = false
        },
        handleOrderSelect: function () {
            this.orderDeal = true
        },
        showValueItem: function ( value ) {
            if ( value == "user" ) {
                this.resetItemDisplay();
                this.userItem = false;
            }else if ( value == "deal" ) {
                this.resetItemDisplay();
                this.dealItem = false;
            }else if ( value == "file" ) {
                this.resetItemDisplay();
                this.fileItem = false;
            }else if ( value == "account" ){
                this.resetItemDisplay();
                this.accountItem = false;
            }else {
                this.resetItemDisplay();
                this.userItem = false;
            }
        },
        resetItemDisplay: function () {
            this.dealItem = true;
            this.fileItem = true;
            this.accountItem = true;
            this.userItem = true;
        },
    },
    filters: {
        getKeyFilter: function ( value ) {

            if ( value == null ){
                return ""
            }else {
                return "取货码：" + value;
            }

        },
        refuseReasonFilter: function( value ){

            if ( value == null ){
                return ""
            }else {
                return "拒绝理由：" + value;
            }

        },
        orderStatusFilter: function ( value ) {

            if ( value == "未支付" ){
                return "去支付"
            }else if ( value == "已付款" ){
                return "退款"
            }else if ( value == "订单完结" ){
                return "关闭"
            }else {
                return ""
            }

        },
        fileShareFilter: function ( value ) {

            if ( value == "0" ){
                return "未分享"
            }else {
                return "已分享  "
            }

        },
        fileShareButtonFilter: function ( value ) {

            if ( value == "0" ){
                return "分享"
            }else {
                return "取消分享"
            }

        },
        integralFilter: function ( value ) {

            if ( value == null ){
                return ""
            }else {
                return value + "分"
            }

        }
    },
    mounted: function () {
        this.getUserInfo();
        this.item = this.$refs.itemValue.value.trim();
        switch (this.item) {
            case "user":
                this.showValueItem("user");
                break;
            case "file":
                //this.getFileInfo();
                this.showValueItem("file");
                break;
            case "account":
                //this.getAccountInfo();
                this.showValueItem("account");
                break;
            case "dealOrder":
                this.getDealOrderInfo(1, 5);
                this.showValueItem("deal");
                break;
            case "dealIntegral":
                //this.geDealInfo();
                this.showValueItem("deal");
                break;
        }
    }
});