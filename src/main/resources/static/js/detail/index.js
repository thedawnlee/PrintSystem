/**
 * Created by wangqian on 2019/3/29.
 */
var vue = new Vue({
    el: "#detail",
    data: {
        priceStyle: {
            display: 'none'
        },
        contentStyle: {
            display: ''
        },
        contentActive: true,
        priceActive: false,
        bonusDescription: "",
        colorfulDouble: null,
        colorfulSingle: null,
        colorfulVariable: "",
        doubleColorfulVariable: "",
        doubleVariable: "",
        normalDouble: null,
        normalSingle: null,
        otherSizePriceList: [],
        shopId: null,
        otherShopList: []

    },
    methods: {
        getPriceInfo: function (id) {
            this.$http.get('/shop/shop_price.do', {params: {id: id}}).then(this.successCallback, this.errorCallback);
        },
        successCallback: function ( res ) {
            res = res.body;
            if( res.data && res.status == 0){
                this.bonusDescription = res.data.bonusDescription;
                this.bonusThreshold= res.data.bonusThreshold;
                this.bonusValue = res.data.bonusValue;
                this.colorfulDouble = res.data.colorfulDouble;
                this.colorfulSingle = res.data.colorfulSingle;
                this.colorfulVariable = res.data.colorfulVariable;
                this.doubleColorfulVariable = res.data.doubleColorfulVariable;
                this.doubleVariable = res.data.doubleVariable;
                this.normalDouble = res.data.normalDouble;
                this.normalSingle = res.data.normalSingle;
                this.otherSizePrice = res.data.otherSizePrice;
                //this.shopId = res.data.shopId;
                console.log(res.data)
            }else {
                util.errorTips( res.msg )
            }
        },
        errorCallback: function ( res ) {
            util.errorTips("获取店铺价格规格的时候出现错误！")
        },
        handlePriceItem: function (id) {
            this.contentStyle.display = 'none';
            this.priceStyle.display = '';
            this.contentActive = false;
            this.priceActive = true;
            this.getPriceInfo(id);
        },
        handleContentItem: function () {
            this.contentStyle.display = '';
            this.priceStyle.display = 'none';
            this.contentActive = true;
            this.priceActive = false;
        },
        getOtherShopList: function () {
            console.log(this.id)
            this.$http.get('/shop/other_shop.do', {params: {id: this.shopId}}).then(this.otherSuccessCallback, this.otherErrorCallback);
        },
        otherSuccessCallback: function( res ){
            res = res.body;
            if( res.data && res.status == 0){
                this.otherShopList = res.data;
                console.log(res.data)
            }else {
                util.errorTips( res.msg )
            }
        },
        otherErrorCallback: function (  ) {
            util.errorTips(" 获取其他店铺出现错误！ ")
        }
    },
    mounted: function () {
        this.shopId = this.$refs.shopIdRef.value;
        this.getOtherShopList();
    }
})