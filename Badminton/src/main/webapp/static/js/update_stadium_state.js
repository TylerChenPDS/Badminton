$(function () {
    $(".form_datetime").datetimepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        todayBtn: true,
        language:"zh-CN",
        minView: "month"
    });
    $(".selectpicker").selectpicker();
})