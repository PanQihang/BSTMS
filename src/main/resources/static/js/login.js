$(function(){
    //绑定回车功能
    $(document).keydown(function(event){
        if(event.keyCode==13){
            userLogin();
        }
    })
});
//重置form内的标签
function resetForm() {
    $(".form-horizontal input").val("");
    $("#errorUsername").html("*");
    $("#errorUserID").html("*");
    $("#errorUserphone").html("*");
    $("#errorType").html("*");
    $("#errorPassword").html("*");
    $("#errorPassword2").html("*");
}
//用户登陆校验
function userLogin() {
    $.ajax({
        type: "POST",
        url: "/login/UserLogin",
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        data:JSON.stringify({
            "username" : $("#username").val(),
            "passwords" : $("#passwords").val(),
        }),
        success:function (result){
            if (result){
                window.location.href = "/index"
            } else{
                toastr.options = {
                    "closeButton": true,
                    "debug": false,
                    "progressBar": false,
                    "positionClass": "toast-top-center",
                    "onclick": null,
                    "showDuration": "400",
                    "hideDuration": "1000",
                    "timeOut": "2500",
                    "extendedTimeOut": "1000",
                    "showEasing": "swing",
                    "hideEasing": "linear",
                    "showMethod": "fadeIn",
                    "hideMethod": "fadeOut"
                }
                toastr.error("卡号密码错误","登陆失败！")
            }
        }
    })
}
function register() {
    resetForm();
}
//开户注册
function saveInfo() {
    var dialogUsername = document.getElementById('dialogUsername').value.trim();
    var dialogUserID = document.getElementById('dialogUserID').value.trim();
    var dialogUserphone = document.getElementById('dialogUserphone').value.trim();
    var dialogAddress = document.getElementById('dialogAddress').value.trim();
    var dialogType = document.getElementById('dialogType').value.trim();
    var dialogPassword = document.getElementById('dialogPassword').value.trim();
    var dialogPassword2 = document.getElementById('dialogPassword2').value.trim();
    if (validform().form()) {
        $.ajax({
            type: "POST",
            url: "/login/Register",
            dataType: "json",
            data: ({
                "userName": dialogUsername,
                "userID": dialogUserID,
                "userPhone": dialogUserphone,
                "Address": dialogAddress,
                "Type": dialogType,
                "Password": dialogPassword
            }),
            success: function (result) {
                if (result) {
                    $('#myModal5').modal('hide');
                    swal("开户成功！您的卡号是:" + result, "", "success");
                } else {
                    toastr.options = {
                        "closeButton": true,
                        "debug": false,
                        "progressBar": false,
                        "positionClass": "toast-top-center",
                        "onclick": null,
                        "showDuration": "400",
                        "hideDuration": "1000",
                        "timeOut": "2500",
                        "extendedTimeOut": "1000",
                        "showEasing": "swing",
                        "hideEasing": "linear",
                        "showMethod": "fadeIn",
                        "hideMethod": "fadeOut"
                    }
                    toastr.error("该用户已开卡", "开户失败！")
                }
            }
        })
    }
}

function validform() {
    jQuery.validator.addMethod("regex", //addMethod第1个参数:方法名称
        function(value,element,params){//addMethod第2个参数:验证方法，参数（被验证元素的值，被验证元素，参数）
            var exp = new RegExp(params);//实例化正则对象，参数为传入的正则表达式
            return exp.test(value);         //测试是否匹配
        },"格式错误");
    var icon = "<i class='fa fa-times-circle'></i>";
    return $("#dialogUserInfo").validate({
        rules: {
            dialogUsername: {
                required: true,
                maxlength: 20
            },
            dialogUserID: {
                required: true,
                maxlength: 18,
                regex:/^[0-9]{18}$/
            },
            dialogUserphone:{
                required: true,
                maxlength: 11,
                regex:/^[1][3,4,5,7,8][0-9]{9}$/
            },
            dialogPassword:{
                required: true,
                maxlength: 10,
            },
            dialogPassword2:{
                required: true,
                maxlength: 10,
                equalTo: "#dialogPassword"
            }
        },
        messages: {
            dialogUsername: {
                required: icon + "姓名不能为空",
                maxlength: icon + "姓名最大长度为20",

            },
            dialogUserID: {
                required: icon + "身份证号不能为空",
                maxlength: icon + "身份证最大长度为18",
                regex: icon + "身份证格式错误"
            },
            dialogUserphone: {
                required: icon + "手机号不能为空",
                maxlength: icon + "手机号最大长度为11",
                regex: icon + "手机号格式错误"
            },
            dialogPassword: {
                required: icon + "密码不能为空",
                maxlength: icon + "密码最大长度为10",
            },
            dialogPassword2: {
                required: icon + "再次输入密码不能为空",
                maxlength: icon + "密码最大长度为20",
                equalTo: icon + "两次密码输入不一致"
            }
        }
    });
}