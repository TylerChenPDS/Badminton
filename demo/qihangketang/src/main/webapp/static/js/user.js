$(function() {
	$(".form_datetime").datetimepicker({
		format : 'yyyy-mm-dd hh:ii:ss',
		autoclose : true,
		todayBtn : true,
		language : "zh-CN"
	});
	$("#addUserFormBtn").on("click", function() {
		// 真正项目中需要做两个事情，一个是前端数据有效性的校验
		// 提交表单数据
		$("#addUserForm").submit();
	})
	$("#updateUserModal").on("hidden.bs.modal", function() {
		$(this).removeData("bs.modal");
	})
	$("#updateUserModal").on("shown.bs.modal", function() {
		$("#updateUserModal .selectpicker").selectpicker();
	})
	$("#batchDelUserBtn").on("click", function() {
		var checkboxs = $(".checkone:checked");
		console.log(checkboxs);
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
			var flag = delSure();
			if (flag) {
				// 通过ajax提交删除用户的请求
				$.ajax({
					url : "/qihangketang/admin/batchDelUsers.html",
					type : "POST",
					data : {
						uids : datas
					},
					success : function(data) {
						if(data == "success")
							alert("success");
							$(location).attr("href","/qihangketang/admin/userManager.html");
					}
				});

			}
		}
	})
	$("#search_btn").on("click",function(){
		$("#search_form").submit();
	})
})
function checkall() {
	$(".checkone").prop("checked", $("#checkall").prop("checked"));
}
function checkone() {
	var flag = true;
	$(".checkone").each(function(index, el) {
		if ($(el).prop("checked") == false) {
			flag = false;
		}
	})
	if (flag == true) {
		$("#checkall").prop("checked", true);
	} else {
		$("#checkall").prop("checked", false);
	}
}
function updateUserFormSubmit() {
	$("#updateUserForm").submit();
}

function delSure() {
	if (confirm("确认要删除这条记录吗")) {
		return true;
	} else
		return false;
}
