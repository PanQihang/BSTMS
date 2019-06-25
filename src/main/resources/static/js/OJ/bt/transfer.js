$(function() {
    index();
});

function index() {
    $.ajax({
        type: "",
        url: "/transferMn/index",
    })
}

function transfer() {
    var money = $("#money").val()
    var account = $("#account").val()
    if (validform().form()){
        swal({
                title: "确认转账?",
                text: "转账卡号为:"+account+" 转账金额为"+money,
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确认",
                cancelButtonText: "取消",
                closeOnConfirm: false,
                closeOnCancel: false
            },
            function (isConfirm) {
                if (isConfirm) {
                    $.ajax({
                        type: "POST",
                        url: "/transferMn/transfer",
                        dataType: "json",
                        data: {
                            "money": money*100,
                            "account": account
                        },
                        success: function (result) {
                            if(result=="0")
                            {
                                swal("转账成功", "", "success");
                            }
                            else if(result=="1")
                            {
                                swal("转账失败", "账号不存在", "error");
                            }
                            else if(result=="2")
                            {
                                swal("转账失败","余额不足","error");
                            }
                            else
                            {
                                swal("转账失败","","error");
                            }
                        }
                    })
                }else {
                    swal("已取消", "", "error");
                }
            });
    }
}

function validform() {
    jQuery.validator.addMethod("regex", //addMethod第1个参数:方法名称
        function(value,element,params){//addMethod第2个参数:验证方法，参数（被验证元素的值，被验证元素，参数）
            var exp = new RegExp(params);//实例化正则对象，参数为传入的正则表达式
            return exp.test(value);         //测试是否匹配
        },"格式错误");
    var icon = "<i class='fa fa-times-circle'></i>";
    return $("#commentForm").validate({
        rules: {
            money: {
                required: true,
                maxlength: 10,
            },
            account:{
                required: true,
                maxlength: 15,
                regex:/^[0-9]{15}$/
            }
        },
        messages: {
            money: {
                required: icon + "金额不能为空",
                maxlength: icon + "金额太大，不能超过6位数"
            },
            account: {
                required: icon + "转账卡号不能为空",
                maxlength: icon + "转账卡号最大长度为15",
                regex: icon + "转账卡号格式错误"
            }
        }
    });
}