$(function() {
    balance();
});

function index() {
    $.ajax({
        type: "",
        url: "/balanceMn/index",
    })
}

function balance() {
    $.ajax({
        type: "GET",
        url: "/balanceMn/balance",
        dataType: "json",
        success: function (result) {
            console.log(result)
            if(result=="0")
            {
                $("#money").html("0.00");
            }
            else{
                $("#money").html(result.toString()+".00");
            }
        }
    })
}

