<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
<title>用户管理页面</title>
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/font/iconfont.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/lib/datatimepicker/css/bootstrap-datetimepicker.css">
<!--[if lt IE 9]>
      <script src="lib/js/html5shiv.min.js"></script>
      <script src="lib/js/respond.min.js"></script>
    <![endif]-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/user.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/lib/bootstrapSelect/css/bootstrap-select.min.css">
</head>

<body>
	<div class="container-fluid">
		<div class="row">
			<div class="serch_title_bar bg-primary">搜索</div>
		</div>
		<div class="row">
			<form action="${pageContext.request.contextPath }/admin/userSearch.html" method="post" id="search_form" class="form-inline">
				<div class="form-group has-feedback">
					<label>报名时间：</label> <input type="text" name="regCourseStartTime"
						class="form_datetime form-control input-sm"> <span
						class="iconfont icon-rili2 form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<label>至：</label> <input type="text" name="regCourseEndTime"
						class="form_datetime form-control input-sm"> <span
						class="iconfont icon-rili2 form-control-feedback"></span>
				</div>
				&nbsp;
				<div class="form-group">
					<label>报名课程：</label> <input placeholder="课程名" type="text"
						name="regCourse" class="form-control input-sm">
				</div>
				&nbsp;
				<div class="form-group">
					<label>学员信息：</label> <input placeholder="用户名/手机号" type="text"
						name="userInfo" class="form-control input-sm ">
				</div>
			</form>
		</div>
		<div id="search_btn" class="row text-right">
			<button id="search_btn" style="margin-top: 0px;" type="button" class="btn btn-default">查询</button>
			<button type="button" data-toggle="modal" data-target="#addUserModal"
				class="btn btn-default">添加</button>
			<button id="batchDelUserBtn" type="button" class="btn btn-default">删除</button>
			<button type="button" class="btn btn-default">导入</button>
			<button type="button" class="btn btn-default">导出</button>
		</div>
		<div class="row" id="search_table">
			<div class="table-responsive">
				<table class="table table-bordered table-hover">
					<tr>
						<td style="width: 20px;"><input type="checkbox"
							name="allcheck" id="checkall" onclick="checkall();"></td>
						<td>用户名</td>
						<td>手机号</td>
						<td>邮箱地址</td>
						<td>角色</td>
						<td>用户状态</td>
						<td style="width: 130px;">操作</td>
					</tr>
					
					<c:forEach var="user" items="${userDatasByPager.list }">
						<tr>
							<td><input value="${user.id }" type="checkbox" name="checkuser" class="checkone"
								onclick="checkone()"></td>
							<td>${user.username }</td>
							<td>${user.phone }</td>
							<td>${user.email }</td>
							<td>
								<c:forEach items="${user.roles}" var="role">
									${role.name }&nbsp;
								</c:forEach>
							</td>
							<td>
								<c:set var="enable" value="${user.enable}"/>
								<c:if test="${enable==1}">已激活</c:if>
								<c:if test="${enable==0}">未激活</c:if>
							</td>
							<td><a href="${pageContext.request.contextPath}/admin/updateUserView.html?id=${user.id}" data-target="#updateUserModal" data-toggle="modal">编辑</a><a
								href="${pageContext.request.contextPath}/admin/deleteUser.html?id=${user.id}" onclick="return delSure()">删除</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="row" id="pager">
			<p class="pull-left">
				总共有 <span>${userDatasByPager.total }</span>条记录，当前页 <span>${userDatasByPager.pageNum }/${userDatasByPager.pages }</span> 页
			</p>
			<div class="btn-group pull-right">
				<a href="${pageContext.request.contextPath}/admin/userManager.html?pageNum=1&size=5" type="button" class="btn btn-default">首页</a>
				<a href="${pageContext.request.contextPath}/admin/userManager.html?pageNum=${userDatasByPager.prePage }&size=5" type="button" class="btn btn-default">上一页</a>
				<a href="${pageContext.request.contextPath}/admin/userManager.html?pageNum=${userDatasByPager.nextPage }&size=5" type="button" class="btn btn-default">下一页</a>
				<a href="${pageContext.request.contextPath}/admin/userManager.html?pageNum=${userDatasByPager.pages }&size=5" type="button" class="btn btn-default">尾页</a>
			</div>
		</div>
	</div>





	<!-- Modal  添加-->
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
					<form id="addUserForm" action="${pageContext.request.contextPath }/admin/addUser.html" method="POST">
						<div class="form-group">
							<label>用户名：</label> <input placeholder="用户名" type="text"
								name="username" class="form-control">
						</div>
						<div class="form-group">
							<label for="">密码：</label> <input placeholder="密码"
								class="form-control" type="password" name="password">
						</div>
						<div class="form-group">
							<label for="">手机号：</label> <input placeholder="常用手机号"
								class="form-control" type="text" name="phone">
						</div>
						<div class="form-group">
							<label for="">邮箱：</label> <input placeholder="邮箱"
								placeholder="邮箱" class="form-control" type="text"
								name="email">
						</div>
						<div class="form-group">
							<label>关联的角色：</label> 
							<select name="roleids" data-live-search="true" multiple class="selectpicker   form-control">
								<c:forEach items="${allRoles }" var="role">
									<option value="${role.id}">${role.name }</option>
								</c:forEach>
							</select>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="addUserFormBtn" type="button" class="btn btn-primary">添加用户</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal  修改-->
	<div class="modal fade" id="updateUserModal" tabindex="-1">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				
			</div>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/static/lib/js/jquery-1.12.4.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/lib/bootstrap/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/user.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/lib/bootstrapSelect/js/bootstrap-select.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/lib/bootstrapSelect/js/i18n/defaults-zh_CN.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/lib/datatimepicker/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/lib/datatimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js "
		charset="UTF-8"></script>
</body>
</html>