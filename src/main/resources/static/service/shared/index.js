/**
 * Created by wangqian on 2019/4/22.
 */

var vue = new Vue({
    el: "#shared",
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
        total: null
    },
    methods: {
        getShareList: function (pageNum, pageSize) {
            this.$http.get('/share/list.do', {params: {pageNum: pageNum, pageSize: pageSize}}).then(function ( res ) {
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
                    console.log(res.data)
                }else {
                    util.errorTips( res.msg )
                }
            }, function () {
                util.errorTips("获取列表失败！")
            })
        },
        handleGoShareCLick: function () {
            location.href = "/share/create";
        },
        handleShareDetail: function ( value ){
            location.href = "/share/detail/" + value;
        },
        handlePostgraduateClick: function () {

        },
        handleExaminationClick: function () {

        },
        handlePaperClick: function () {

        },
        handleCivilClick: function () {

        },
        handleOtherClick: function () {

        },
        handleCETClick: function () {

        }
    },
    filters: {
        headImgFilter: function ( value ) {
            if (value != ""){
                return value;
            }else {
                return util.getImgHost() + "default_header.jpg";
            }
        }
    },
    created: function () {
        this.getShareList(1, 10);
    }
});