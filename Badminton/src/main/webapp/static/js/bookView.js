$(function () {
    // $(".form_datetime").datetimepicker({
    //     format: 'yyyy-mm-dd',
    //     autoclose: true,
    //     todayBtn: true,
    //     language:"zh-CN",
    //     minView: "month"
    // });
    $(".selectpicker").selectpicker();
})


function book(){
	var a = document.getElementById("logineduser");
	if(a == null){ //用户未登录
		alert("请先登陆");
		return false;
	}else{
		return true;
	}
}