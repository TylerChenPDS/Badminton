<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
<title>所有通知</title>
<link
	href="${pageContext.request.contextPath }/static/lib/bootstrap/css/bootstrap.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/font/iconfont.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/lib/datatimepicker/css/bootstrap-datetimepicker.css">
<!--[if lt IE 9]>
      <script src="lib/js/html5shiv.min.js"></script>
      <script src="lib/js/respond.min.js"></script>
    <![endif]-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/css/main.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/css/allnotice.css">
</head>

<body>
	<nav id="main_nav" class="navbar navbar-default navbar-static-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#qihangnavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a href="#" class="navbar-brand"><span
					class="iconfont icon-yumaoqiu1"></span>&nbsp;羽毛球场馆预定</a>
			</div>
			<div class="collapse navbar-collapse" id="qihangnavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="">首页</a></li>
					<li><a href="">羽毛球场馆动态</a></li>
					<li><a href="">场馆预定信息</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="hidden-xs">
		<img src="image/banner330-2.jpg" height="100%" width="100%" alt="">
	</div>
	<div class="container-fluid">
		<div class="row">
			<form id="search_form" action="${pageContext.request.contextPath }/" method="get" class="form-inline">
				<div class="form-group has-feedback">
					<label>开始时间: </label> <input type="text" name="starttime"
						class="form_datetime form-control input-sm"> <span
						class="iconfont icon-rili2 form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<label>开始时间: </label> <input type="text" name="endtime"
						class="form_datetime form-control input-sm"> <span
						class="iconfont icon-rili2 form-control-feedback"></span>
				</div>
				&nbsp;
				<div class="form-group">
					<button id="search_btn" onclick="submitsearch()" type="button"
						class="btn btn-default">查询</button>
				</div>
			</form>
		</div>
		<div class="row" id="search_table">
			<div class="table-responsive">
				<table class="table table-bordered table-hover">
					<tr>
						<td>时间</td>
						<td>标题</td>
						<td>详情</td>
					</tr>
					<c:forEach var="notice" items="${notices.list }">
						<tr>
							<td>${notice.time }</td>
							<td>${notice.title }</td>
							<td><a href="${pageContext.request.contextPath }/checknotice.html?nid=${notice.id}">详情</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="row" id="pager">
			<p class="pull-left">
				总共有 <span>${notices.total }</span>条记录，当前页 <span>${notices.pageNum }/${notices.pages }</span> 页
			</p>
			<c:set var="path" value="allnotice.html?zhanwei"></c:set>
            <c:if test="${searchpage!=null}">
            	<c:set var="path" value="searchallnotice.html?starttime=${starttime }&endtime=${endtime }"></c:set>
            </c:if>
            <div class="btn-group pull-right">
                <a
					href="${pageContext.request.contextPath}/${path}"
					type="button" class="btn btn-default">首页</a> <a
					href="${pageContext.request.contextPath}/${path}&pageNum=${notices.prePage }"
					type="button" class="btn btn-default">上一页</a> <a
					href="${pageContext.request.contextPath}/${path}&pageNum=${notices.nextPage }"
					type="button" class="btn btn-default">下一页</a> <a
					href="${pageContext.request.contextPath}/${path}&pageNum=${notices.pages }"
					type="button" class="btn btn-default">尾页</a>
            </div>
		</div>

	</div>
	<div class="footer hidden-xs ">
		<div class="text-center footericon">
			<span class="iconfont icon-yumaoqiu1"></span>
		</div>
		<p class="text-center">2019 科技立项项目 All Right Reserved</p>
		<p class="text-center cr">
			njit在线 版权有限 | <a href="#">工具下载</a> | <a href="#">资料下载</a> | <a
				href="#">视频下载</a> | <a href="#">问题反馈</a> | <a href="#">帮助</a>
		</p>
	</div>
	<script
		src="${pageContext.request.contextPath }/static/lib/js/jquery-1.12.4.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/lib/bootstrap/js/bootstrap.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/allnotice.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/lib/datatimepicker/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/static/lib/datatimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js "
		charset="UTF-8"></script>
</body>
</html>