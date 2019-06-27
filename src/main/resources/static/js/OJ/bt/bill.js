$(function() {
    index();
});

function index() {
    $.ajax({
        type: "GET",
        url: "/billMn/getMapList",
        success:function (result) {
            var dataTable = $('#ipInfoTable');
            if ($.fn.dataTable.isDataTable(dataTable)) {
                dataTable.DataTable().destroy();
            }
            dataTable.DataTable({
                "serverSide": false,
                "autoWidth" : false,
                "bSort": false,
                "data" : result,
                "columns" : [{
                    "data" : "TradeTime"
                },{
                    "data" : "AffairType"
                },{
                    "data" : "TradeBalance"
                }]
            });
        }
    })
}

