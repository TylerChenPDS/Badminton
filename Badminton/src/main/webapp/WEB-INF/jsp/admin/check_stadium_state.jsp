<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<!-- <meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" /> -->
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
	href="${pageContext.request.contextPath}/static/css/check_stadium_state.css">
</head>

<body class="bg-success">
	<div class="container-fluid">
		<!--  <div class="row">
            <div class="serch_title_bar">
                查询某段时间的场馆状态
            </div>
        </div>
        <div class="row">
            <form id="search_form" class="form-inline">
                <div class="form-group  has-feedback">
                    <label for="">开始时间：</label>
                    <input type="text" required name="starttime" autocomplete="off"
                        class="form_datetime form-control input-sm"><span
                        class="iconfont icon-rili2 form-control-feedback"></span>
                </div>
                <div class="form-group  has-feedback">
                    <label for="">结束时间：</label>
                    <input type="text" required name="endtime" autocomplete="off"
                        class="form_datetime form-control input-sm"><span
                        class="iconfont icon-rili2 form-control-feedback"></span>
                </div>
                &nbsp;
                <div class="form-group">
                    <button id="find_btn" type="button" class="btn btn-default">查询</button>
                </div>
            </form>
        </div> -->
        
       <h3 class="text-center" >所有场馆约束信息（只显示今天之后的约束）</h3>
		<div id="search_btn" class="row text-right">
			<!-- <button type="button" data-toggle="modal" data-target="#addUserModal" class="btn btn-default">添加</button> -->
			<button type="button" onclick="batchdelete()" class="btn btn-default">删除</button>
		</div>
		<div class="row" id="search_table">
			<div class="table-responsive">
				<table class="table table-bordered table-hover">
					<tr>
						<td style="width: 20px;"><input type="checkbox"
							name="allcheck" id="checkall" onclick="checkall();"></td>
						<td>日期</td>
						<td>场馆</td>
						<td>被限制时段</td>
						<td style="width: 130px;">操作</td>
					</tr>
					<c:forEach var="booklimitation" items="${booklimitations.list }">
						<tr>
							<td><input value="${booklimitation.id}" type="checkbox" name="checkuser" class="checkone"
								onclick="checkone()"></td>
							<td>${booklimitation.date }</td>
							<td>${booklimitation.stadium.detail }</td>
							<td>${booklimitation.timeStrs }</td>
							<td><a onclick="return delsure();" href="${pageContext.request.contextPath }/admin/deleteStadiumState?id=${booklimitation.id}">删除</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="row" id="pager">
			<p class="pull-left">
				总共有 <span>${booklimitations.total }</span>条记录，当前页 <span>${booklimitations.pageNum }/${booklimitations.pages }</span> 页
			</p>
			<div class="btn-group pull-right">
				 <a
					href="${pageContext.request.contextPath}/admin/checkStadiumStateView.html"
					type="button" class="btn btn-default">首页</a> <a
					href="${pageContext.request.contextPath}/admin/checkStadiumStateView.html?pageNum=${booklimitations.prePage }"
					type="button" class="btn btn-default">上一页</a> <a
					href="${pageContext.request.contextPath}/admin/checkStadiumStateView.html?pageNum=${booklimitations.nextPage }"
					type="button" class="btn btn-default">下一页</a> <a
					href="${pageContext.request.contextPath}/admin/checkStadiumStateView.html?pageNum=${booklimitations.pages }"
					type="button" class="btn btn-default">尾页</a>
			</div>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/static/lib/js/jquery-1.12.4.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/lib/bootstrap/js/bootstrap.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/check_stadium_state.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/lib/datatimepicker/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/lib/datatimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js "
		charset="UTF-8"></script>
</body>

</html>