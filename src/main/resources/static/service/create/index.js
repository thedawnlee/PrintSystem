/**
 * Created by wangqian on 2019/4/4.
 */
var vue = new Vue({
    el: "#create",
    data: {
        color: true,
        single: true,
        black: true,
        double: true,
        fileText: "点击浏览本地文件",
        fileName: "",
        shopId: "",
        pageSizeList: [],
        phone: "",
        userId: null,
        username: ""
    },
    methods: {
        getFormInfo: function () {
            //此店铺表单信息，支不支持打印？
            this.$http.get('/order/get_form_info.do', {params: {id: this.shopId}}).then(this.successCallback, this.errorCallback);
        },
        successCallback: function ( res ) {
            res = res.body;
            if( res.data && res.status == 0){
                this.black = res.data.hasBlack;
                this.color  =  res.data.hasColorful;
                this.double  = res.data.hasDouble;
                this.single  =  res.data.hasSingle;
                this.pageSizeList =  res.data.pageSizeList;
                this.phone  = res.data.phone;
                this.userId  = res.data.userId;
                this.username  = res.data.username;
                console.log(res.data)
            }else {
                util.errorTips( res.msg );
                window.location.href = '/login';
            }
        },
        errorCallback: function () {
            util.errorTips("获取 店铺服务 时出现错误！")
        },
        handleFileUpload: function() {
            var filePath = this.$refs.fileUploadInput.value;
            if ( this.checkFileType(filePath) ) {
                var arr = filePath.split('\\');
                var fileName = arr[arr.length - 1];
                this.fileText = "更改选择文件";
                this.fileName = fileName;
            } else {
                this.fileName = "请选择正确的文件格式！";
                return false
            }

            console.log(this.$refs.fileUploadInput)
        },
        checkFileType: function(filePath) {
            if (filePath.indexOf("jpg") != -1 || filePath.indexOf("png") != -1 || filePath.indexOf("pdf") != -1 ||
                filePath.indexOf("doc") != -1 || filePath.indexOf("docx") != -1) {
                return true;
            } else {
                return false;
            }
        }
    },
    mounted: function () {
        this.shopId = this.$refs.shopIdRef.value;
        this.getFormInfo();
    }
});