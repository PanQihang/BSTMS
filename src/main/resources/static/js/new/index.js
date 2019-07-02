$(function() {
    index();
});

function index() {
    $.ajax({
        type: "",
        url: "/balanceMn/index",
    })
}

function balance() {
    $.ajax({
        type: "",
        url: "/balanceMn/index",
    })
    $.ajax({
        type: "GET",
        url: "/balanceMn/balance",
        dataType: "json",
        success: function (result) {
            console.log(result)
            if(result=="0")
            {
                swal("您的余额为","0.00", "success");
            }
            else{
                swal("您的余额为",result.toString(), "success");
            }
        }
    })
}

