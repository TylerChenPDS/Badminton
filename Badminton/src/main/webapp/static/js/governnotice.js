$(function() {
	$(".form_datetime").datetimepicker({
		format : 'yyyy-mm-dd',
		autoclose : true,
		todayBtn : true,
		language : "zh-CN"
	});
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

function submitsearch() {
	$("search_form").submit();
}

function openIssueView() {
	$(location).attr("href", "/Badminton/admin/issue_notice.html")
}

function delSure() {
	if (confirm("确认要删除这条记录吗")) {
		return true;
	} else
		return false;
}

function batchDel() {
	var checkboxs = $(".checkone:checked");
	console.log(checkboxs);
	if (checkboxs.length == 0) {
		alert("必须要勾选要删除的用户记录")
		return false;
	}
	if (delSure()) {
		var ids = new Array();
		checkboxs.each(function() {
			ids.push(this.value);
		})
		var datas = JSON.stringify(ids);
		// 通过ajax提交删除用户的请求
		$.ajax({
			url : "/Badminton/deleteNotices",
			type : "POST",
			data : {
				nids : datas
			},
			success : function(data) {
				if (data == "success")
				$(location).attr("href", "/Badminton/admin/governnotice.html");
			}
		});
	}
}
