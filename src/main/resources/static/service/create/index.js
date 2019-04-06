/**
 * Created by wangqian on 2019/4/4.
 */
var vue = new Vue({
    el: "#create",
    data: {
        color: false,  /* 根据此字段判断用户是否有彩打服务 */
        single: false, /* 根据此字段判断用户是否有单页服务 */
        black: false, /* 根据此字段判断用户是否有黑白服务 */
        double: false, /* 根据此字段判断用户是否有双页服务 */
        colorActive: false, /* 点击彩打，通过此字段来激活样式 */
        singleActive: false, /* 点击单页，通过此字段来激活样式 */
        blackActive: false, /* 点击黑白，通过此字段来激活样式 */
        doubleActive: false, /* 点击双页，通过此字段来激活样式 */
        fileText: "点击浏览本地文件", /* 控制选择上传文件的样式 */
        fileName: "", /* 控制选择上传文件的样式 */
        shopId: "",
        pageSizeList: [],
        phone: "",
        userId: null,
        username: "",
        file: '',
        singleOrDouble: null, /* 提交表单时，选择的是单页还是双页 */
        PageSize: null, /* 提交表单时，选择的页面尺寸 */
        colorOrBlack: null, /* 提交表单时，选择的颜色 */
        userDesc: "", /* 缓存用户评论 */
        fileQuantity: null, /* 文件份数 */
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
            }else {
                util.errorTips( res.msg );
                window.location.href = '/login';
            }
        },
        errorCallback: function () {
            util.errorTips("获取 店铺服务 时出现错误！")
        },
        handleFileUpload: function(event) {
            this.file = event.target.files[0]; // get input file object
            console.log("get input file object", this.file);
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
        },
        checkFileType: function(filePath) {
            if (filePath.indexOf("jpg") != -1 || filePath.indexOf("png") != -1 || filePath.indexOf("pdf") != -1 ||
                filePath.indexOf("doc") != -1 || filePath.indexOf("docx") != -1) {
                return true;
            } else {
                return false;
            }
        },
        handleUploadSubmitClick: function () {
            var file = this.file;
            var formData = new FormData();
            formData.append("upload_file", file);
            this.$http.post('/file/upload.do', formData, {
                headers: { 'Content-Type': 'multipart/form-data' }
            }).then(this.fileUploadSuccessCallback, this.fileUploadErrorCallback);
        },
        fileUploadSuccessCallback: function ( res ) {
            res = res.body;
            if( res.data && res.status == 0 ){
                console.log(res.data);
                alert("成功");
            }else {
                util.errorTips( res.msg );
            }
        },
        fileUploadErrorCallback: function () {
            util.errorTips( "上传文件发送错误！" );
        },
        handleSingleClick: function () {
            this.doubleActive = false;
            this.singleActive = true;
            this.singleOrDouble = 0;
        },
        handleDoubleClick: function () {
            this.singleActive = false;
            this.doubleActive = true;
            this.singleOrDouble = 1;
        },
        handleColorClick: function () {
            this.blackActive = false;
            this.colorActive = true;
            this.colorOrBlack = 1;
        },
        handleBlackClick: function () {
            this.colorActive = false;
            this.blackActive = true;
            this.colorOrBlack = 0;
        },
        handleFileSizeChange: function (){

        },
        handleQuantityChange: function (){

        },
        handleTextAreaChange: function (){

        }
    },
    mounted: function () {
        this.shopId = this.$refs.shopIdRef.value;
        this.getFormInfo();
    }
});