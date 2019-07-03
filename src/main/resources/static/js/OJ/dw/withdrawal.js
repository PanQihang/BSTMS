$(function() {
    index();
});

function index() {
    $.ajax({
        type: "",
        url: "/withdrawMn/index",
    })
}

function withdrawal() {
    var money = $("#money").val()
    if (validform().form()){
        swal({
                title: "确认取款?",
                text: "您的取款金额为"+money,
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
                        url: "/withdrawMn/withdraw",
                        dataType: "json",
                        data: {
                            "money": money*100
                        },
                        success: function (result) {
                            if(result==true)
                            {
                                swal("取款成功", "", "success");
                            }
                            else
                            {
                                swal("取款失败", "余额不足", "error");
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
                regex:/^[1-9]\d*00$/,
                maxlength: 10,
            }
        },
        messages: {
            money: {
                required: icon + "金额不能为空",
                regex: icon + "金额为100的整数倍",
                maxlength: icon + "金额太大，不能超过10位数"
            }
        }
    });
}