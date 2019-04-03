/**
 * Created by wangqian on 2019/4/4.
 */
var vue = new Vue({
    el: "#app",
    data: {
        fileText: "点击浏览本地文件",
        fileName: ""
    },
    methods: {
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
        },
    }
})