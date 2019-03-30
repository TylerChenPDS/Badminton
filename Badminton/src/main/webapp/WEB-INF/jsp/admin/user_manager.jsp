<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
<title>用户管理页面</title>
<link
	href="${pageContext.request.contextPath }/static/lib/bootstrap/css/bootstrap.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/font/iconfont.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/lib/datatimepicker/css/bootstrap-datetimepicker.css">
<!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath }/static/lib/js/html5shiv.min.js"></script>
      <script src="${pageContext.request.contextPath }/static/lib/js/respond.min.js"></script>
    <![endif]-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/css/user.css">
</head>

<body>
	<div class="container-fluid">
		<div class="row">
			<div class="serch_title_bar">搜索</div>
		</div>
		<div class="row">
			<form id="search_form" method="post" action="${pageContext.request.contextPath }/admin/searchUsersByUserInfo.html" class="form-inline">
				<div class="form-group has-feedback">
					<label>模糊查询：</label> <input type="text" placeholder="邮箱地址/手机号/学号"
						name="userinfo" required="required" class="form-control input-sm">
				</div>
				&nbsp;
				<div class="form-group">
					<button id="find_btn" type="button" class="btn btn-default">查询</button>
				</div>
			</form>
		</div>
		<div id="search_btn" class="row text-right">
			<button type="button" data-toggle="modal" data-target="#addUserModal"
				class="btn btn-default">添加</button>
			<button type="button" id="batchDelUserBtn" class="btn btn-default">删除</button>
			<button type="button" class="btn btn-default disabled" >导出</button>
		</div>
		<div class="row" id="search_table">
			<div class="table-responsive">

				<table class="table table-bordered table-hover">
					<tr>
						<td style="width: 20px;"><input type="checkbox"
							name="allcheck" id="checkall" onclick="checkall();"></td>
						<td>学号</td>
						<td>手机号</td>
						<td>邮箱地址</td>
						<td>角色</td>
						<td style="width: 130px;">操作</td>
					</tr>
					<c:forEach var="user" items="${userDatasByPager.list }">
						<tr>
							<td><input type="checkbox" name="checkuser" class="checkone"
								onclick="checkone()" value="${user.id }"></td>
							<td>${user.stuno }</td>
							<td>${user.telephone }</td>
							<td>${user.email }</td>
							<td><c:forEach var="role" items="${user.roles }">
									${role.rolename }
								</c:forEach>
								
								</td>
							<td><a href="${pageContext.request.contextPath}/admin/updateUserView.html?id=${user.id }" data-target="#updateUserModal" data-toggle="modal">编辑</a><a
								href="${pageContext.request.contextPath}/admin/deleteUser.html?id=${user.id }"
								onclick="return delSure()">删除</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="row" id="pager">
			<p class="pull-left">
				总共有 <span>${userDatasByPager.total }</span>条记录，当前页 <span>${userDatasByPager.pageNum }/${userDatasByPager.pages }</span>
				页
			</p>
			<div class="btn-group pull-right">
				<c:set var="path" value="admin/user_manager.html?zhanwei"></c:set>
				
				<c:if test="${userpage!=null }">
					<c:set var="path" value="admin/searchUsersByUserInfo.html?userinfo=${userinfo }"></c:set>
				</c:if>
			
				<a
					href="${pageContext.request.contextPath}/${path}"
					type="button" class="btn btn-default">首页</a> <a
					href="${pageContext.request.contextPath}/${path}&pageNum=${userDatasByPager.prePage }"
					type="button" class="btn btn-default">上一页</a> <a
					href="${pageContext.request.contextPath}/${path}&pageNum=${userDatasByPager.nextPage }"
					type="button" class="btn btn-default">下一页</a> <a
					href="${pageContext.request.contextPath}/${path}&pageNum=${userDatasByPager.pages }"
					type="button" class="btn btn-default">尾页</a>
			</div>
		</div>
	</div>



	<!-- Modal  添加用户-->
	<div class="modal fade" id="addUserModal" tabindex="-1">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加用户</h4>
				</div>
				<div class="modal-body">
					<form id="adduserform" action="${pageContext.request.contextPath }/admin/addUser.html" method="post">
						<div class="form-group">
							<label>学号：</label> <input placeholder="学号" type="text"
								name="stuno" class="form-control">
						</div>
						<div class="form-group">
							<label for="">密码：</label> <input placeholder="密码"
								class="form-control" type="password" name="password">
						</div>
						<div class="form-group">
							<label for="">手机号：</label> <input placeholder="常用手机号"
								class="form-control" type="text" name="telephone">
						</div>
						<div class="form-group">
							<label for="">邮箱：</label> <input placeholder="用户名"
								placeholder="邮箱" class="form-control" type="text"
								name="username">
						</div>
						<div class="form-group ">
							<label for="">身份：</label>
							<c:forEach var="role" items="${allroles }">
								<div class="radio radio-inline addradio" style="margin-top: 0px;">
									<label> <input type="radio" checked="checked" name="roleid" value="${role.id }"
										> ${role.rolename }
									</label>
								</div>
							</c:forEach>

						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="inmodaladduserbtn" class="btn btn-primary">添加用户</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal  编辑用户-->
	<div class="modal fade" id="updateUserModal" tabindex="-1">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				
			</div>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath }/static/lib/js/jquery-1.12.4.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/lib/bootstrap/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath }/static/js/user.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/lib/datatimepicker/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/static/lib/datatimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js "
		charset="UTF-8"></script>
</body>
</html>