$(function () {
    $(".form_datetime").datetimepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        todayBtn: true,
        language:"zh-CN"
    });
})

function submitsearch(){
    $("search_form").submit();
}
