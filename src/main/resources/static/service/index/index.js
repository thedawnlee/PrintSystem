/**
 * Created by wangqian on 2019/3/29.
 */
var vue = new Vue({
    el: "#content",
    data: {
        list: [],
        image: "",
        pageNum: '',
        pages: '',
        hasNextPage: null,
        hasPreviousPage: null,
        isFirstPage: null,
        isLastPage: null,
        navigatepageNums: [],
        imageList: [],
        total: null,
        shopId: ""
    },
    methods: {
        getShopList: function (pageNum, pageSize) {
            this.$http.get('/shop/shop_list.do', {params: {pageNum: pageNum, pageSize: pageSize}}).then(this.successCallback, this.errorCallback);
        },
        successCallback: function ( res ) {
            res = res.body;
            if( res.data && res.status == 0){
                this.list = res.data.list;
                this.pageNum = res.data.pageNum;
                this.hasNextPage = res.data.hasNextPage;
                this.hasPreviousPage = res.data.hasPreviousPage;
                this.isFirstPage = res.data.isFirstPage;
                this.isLastPage = res.data.isLastPage;
                this.total = res.data.total;
                this.pages = res.data.pages;
                this.navigatepageNums = res.data.navigatepageNums;
            }else {
                util.errorTips( res.msg )
            }
        },
        errorCallback: function ( res ) {
            util.errorTips( res.msg )
        }
    },
    filters: {
        onService: function ( value ) {
            if (value != "-1"){
                return value + "元";
            }else {
                return "无此服务";
            }
        }
    },
    created: function () {
        this.getShopList();
    }
})