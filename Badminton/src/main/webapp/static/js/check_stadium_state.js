$(function () {
    $(".form_datetime").datetimepicker({
        format: 'yyyy-mm-dd',
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


function delsure(){
	if (confirm("确认要删除这条记录吗")) {
		return true;
	} else
		return false;
}


function batchdelete(){
	var checkboxs = $(".checkone:checked");
	console.log(checkboxs);
	console.log("asdaasd");
	if (checkboxs.length == 0){
		alert("必须要勾选要删除的用户记录")
		return false;
	}
	else {
		// 获取勾选了的复选框的value
		var ids = new Array();
		checkboxs.each(function() {
			ids.push(this.value);
		})
		var datas = JSON.stringify(ids);
		var flag = delsure();
		if (flag) {
			// 通过ajax提交删除用户的请求
			$.ajax({
				url : "/Badminton/admin/deleteStadiumStates",
				type : "POST",
				data : {
					ids : datas
				},
				success : function(data) {
					if(data == "success")
						$(location).attr("href","/Badminton/admin/checkStadiumStateView.html");
				}
			});

		}
	}
}