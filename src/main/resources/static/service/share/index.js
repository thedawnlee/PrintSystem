/**
 * Created by wangqian on 2019/4/19.
 */
var vue = new Vue({
    el: "#share",
    data: {
        fileUploadDisplay: false,/* 未选择文件时候，上传不能点击 */
        fileText: "点击浏览本地文件", /* 控制选择上传文件的样式 */
        fileName: "", /* 控制选择上传文件的样式 */
        file: '',
        isUpload: false, /* 是否上传？ */
        filePageCount: null,
        fileNewName: null,
        fileId: null,
        tagValue: 0,
        uploadFile: true, /* true 代表当前上传文件为true 而不是我的文件 */
    },
    methods: {
        getUserInfo: function () {
            this.$http.post('',{emulateJSON:true}).then(function ( res ) {
                res = res.body;
                if( res.data && res.status == 0){
                }else {
                    util.errorTips( res.msg );
                }
            }, function () {
                util.errorTips( "获取信息失败" );
            })
        },
        handleFileUpload: function(event) {
            /* 监听 file input，发生改变获取上传的文件以及改变样式！ */
            this.file = event.target.files[0]; // get input file object
            var filePath = this.$refs.fileUploadInput.value;
            if ( util.checkFileType(filePath.toLowerCase())) {
                var arr = filePath.split('\\');
                var fileName = arr[arr.length - 1];
                this.fileText = "更改选择文件";
                this.fileName = fileName;
            } else {
                this.fileName = "请选择正确的文件格式！";
                return false
            }

            if ( this.isUpload ){
                if (confirm("文件已经上传成功，是否更改文件？")){
                    location.reload();
                }
            }else {
                this.fileUploadDisplay = true;
            }

        },
        handleUploadSubmitClick: function () {
            /* form标签上面 @submit.prevent 触发的事件 */
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
                this.isUpload = true;
                this.filePageCount = res.data.pageNum;
                this.fileNewName = res.data.fileName;
                this.fileId = res.data.fileId;
                this.fileUploadDisplay = false;
                this.$refs.fileUploadButtonText.value = "上传成功";
            }else {
                util.errorTips( res.msg );
            }
        },
        fileUploadErrorCallback: function () {
            util.errorTips( "发生错误, 请选择文件重新上传！" );
        },
        selectUploadFile: function () {
            this.uploadFile = true
        },
        selectMyFile: function () {
            this.uploadFile = false
        },
        handleBackIndex: function () {
            location.href = "/index";
        }
    },
    filters: {

        getKeyFilter: function ( value ) {

            if ( value == null ){
                return ""
            }else {
                return "取货码：" + value;
            }

        }
    },
    mounted: function () {
    }
});