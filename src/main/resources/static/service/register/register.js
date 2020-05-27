var vue = new Vue({
    el:"#register",
    data:{
        username:"",
        password:"",
        phone:"",
        email:"",
        CheckPassword:"",
        question:"",
        answer:"",
        isChecked:false,
        msg:""

    },
    methods:{

        checkUserName:function(){
            console.log('用户名校验')
            var regUser = /^[a-zA_Z][a-zA-Z0-9_]{4,19}$/
            if (!regUser.test(this.username)){
                this.msg='用户名非法，请以字母开头，可以包含下划线或者数字'
                this.isChecked=true
            } else {
                this.msg=''
                this.isChecked=false
                this.$http.get('/user/checkUser.do',{
                    params:{
                        username:this.username
                    }
                }).then(this.checkUserSuccess)
            }

        },
        checkUserSuccess:function(res){
            res=res.body

            if(!res.success){
                this.msg=res.msg
                this.isChecked=true
            }else{
                this.msg=''
                this.isChecked=false
            }
        },
        /*
         handleClick: function () {
            this.$http.post('/user/login.do', {
                username: this.username,
                password: this.password
            }, {emulateJSON:true}).then(this.successCallback, this.errorCallback)
        },
        successCallback: function ( res ) {
            res = res.body;
            if( res.data && res.status == 0){
                console.log("this.url > 0:" + (this.url.length > 0));
                if ( this.url.length > 0 ){
                    window.location.href = this.url ;
                }else {
                    window.location.href = '/index';
                }
            }else {
                util.errorTips( res.msg )
            }
        },
         */
        handleClick:function(){
          console.log("点击注册")
            this.$http.post('/user/register.do',{
                username:this.username,
                password:this.password,
                email:this.email,
                phone:this.phone,
                question:this.question,
                answer:this.answer
            }, {emulateJSON:true}).then(this.successCallback, this.errorCallback)
        },
        successCallback:function(res){
            res = res.body
            if(res.status === 0){
                // console.log("this.url > 0:" + (this.url.length > 0));
                // if ( this.url.length > 0 ){
                //     window.location.href = this.url ;
                // }else {
                //     window.location.href = '/index';
                // }
                alert("注册成功即将跳转,请授权登陆")
                window.location.href='/index'
            }else {
                this.msg="注册失败"
                this.isChecked=true
            }
        },
        errorCallback: function ( res ) {
            util.errorTips()
        },
        checkPasswordDifference:function () {
            console.log("检测密码")
            if (this.password!==this.CheckPassword){
                this.msg="两次输入密码不一致"
                this.isChecked=true

            }else{
                this.isChecked=false
            }

        },
        checkEmail:function () {
            var regEmail = /^\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/;
            console.log("检测email")
           if (this.email===""){
               this.msg="email不能为空"
               this.isChecked=true
           } else if(!regEmail.test(this.email)){
               this.msg="email格式错误"
               this.isChecked=true
           }else {
               this.isChecked=false
           }
        },
        checkPhone:function () {
            var reg = /^1[3|4|5|7|8][0-9]\d{8}$/
            console.log("检测手机号")
            if (this.phone===""){
                this.msg="手机号不能为空"
                this.isChecked=true
            } else if(!reg.test(this.phone)){
                this.msg="手机号格式非法"
                this.isChecked=true
            }else {
                this.isChecked=false


            }
        }
    }
})