$(function () {
    
    //清除模态框中的数据
    $("#updateUserModal").on("hidden.bs.modal", function() {
		$(this).removeData("bs.modal");
	})
	
	$("#addUserModal").on("hidden.bs.modal", function() {
		$(this).removeData("bs.modal");
	})
//	$("#updateUserModal").on("shown.bs.modal", function() {
//		$("#updateUserModal .selectpicker").selectpicker();
//	})
    
	$("#find_btn").on("click",function(){
		$("#search_form").submit();
	});
	
	$("#inmodaladduserbtn").on("click",function(){
		$("#adduserform").submit();
	});
	
	$(".addradio  input").first().prop("checked","checked")
	
    $("#batchDelUserBtn").on("click", function() {
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
			var flag = delSure();
			if (flag) {
				// 通过ajax提交删除用户的请求
				$.ajax({
					url : "/Badminton/admin/deleteUsers.html",
					type : "POST",
					data : {
						uids : datas
					},
					success : function(data) {
						if(data == "success")
							$(location).attr("href","/Badminton/admin/user_manager.html");
					}
				});
			}
		}
	})
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

function delSure() {
	if (confirm("确认要删除这条记录吗")) {
		return true;
	} else
		return false;
}


function updateSubmit(){
	$("#updateUserForm").submit();
}