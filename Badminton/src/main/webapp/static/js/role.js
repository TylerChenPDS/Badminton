$(function () {
    $(".form_datetime").datetimepicker({
        format: 'yyyy-mm-dd hh:ii:ss',
        autoclose: true,
        todayBtn: true,
        language:"zh-CN",
        minView: "month"
    });
})
function checkall(){
    $(".checkone").prop("checked",$("#checkall").prop("checked"));
}
function  checkone(){
    var flag = true;
    $(".checkone").each(function(index, el){
        if( $(el).prop("checked") == false){
            flag = false;
        }
    })
    if(flag == true){
        $("#checkall").prop("checked", true);
    }else{
        $("#checkall").prop("checked", false);
    }

}