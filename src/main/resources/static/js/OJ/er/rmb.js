$(function() {
    index();
});

function index() {
    $.ajax({
        type: "",
        url: "/rmbMn/index",
    })
}

function rmb() {
    var money = $("#money").val();
    if (validform().form()) {
        $.ajax({
            type: "POST",
            url: "/exchangeMn/exchange",
            dataType: "json",
            data: {
                "money": money * 100
            },
            success: function (result) {
                if(result=="1")
                {
                    swal("兑换成功", "", "success");
                }
                else
                {
                    swal("兑换失败", "余额不足", "error");
                }
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
                regex:/^[1-9]\d{2,3}$/
            }
        },
        messages: {
            money: {
                required: icon + "金额不能为空",
                regex: icon + "金额为100-10000"
            }
        }
    });
}