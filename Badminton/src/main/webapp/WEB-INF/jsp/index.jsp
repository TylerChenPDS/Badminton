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
<title>羽毛球场馆预定系统首页</title>
<link
	href="${pageContext.request.contextPath }/static/lib/bootstrap/css/bootstrap.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/font/iconfont.css" />
<!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath }/static/lib/js/html5shiv.min.js"></script>
      <script src="${pageContext.request.contextPath }/static/lib/js/respond.min.js"></script>
    <![endif]-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/css/main.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/css/index.css" />
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
					<li><a href="">管理员界面</a></li>
					<li><a href="">场馆预定信息</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${sessionScope.logineduser!=null}">
						<li><a>欢迎，<span>${sessionScope.logineduser.stuno }</span></a></li>
					</c:if>
					<c:if test="${sessionScope.logineduser==null}">
						<li><a href="${pageContext.request.contextPath }/login.html"><span
								class="glyphicon glyphicon-user"></span> 登陆</a></li>
						<li><a
							href="${pageContext.request.contextPath }/register.html"><span
								class="glyphicon glyphicon-log-out"></span> 注册</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
	<div id="carousel-qihang" class="carousel slide" data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#carousel-qihang" data-slide-to="0" class="active"></li>
			<li data-target="#carousel-qihang" data-slide-to="1"></li>
			<li data-target="#carousel-qihang" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner" role="listbox">
			<div
				data-lg-img="${pageContext.request.contextPath }/static/image/xuehaiwan2000410.jpg"
				data-xs-img="${pageContext.request.contextPath }/static/image/xuehaiwan760410.jpg"
				class="item active"></div>
			<div class="item"
				data-lg-img="${pageContext.request.contextPath }/static/image/nangong1_2000410.jpg"
				data-xs-img="${pageContext.request.contextPath }/static/image/nangong1_760410.jpg"></div>
			<div class="item"
				data-lg-img="${pageContext.request.contextPath }/static/image/nangong2_2000410.jpg"
				data-xs-img="${pageContext.request.contextPath }/static/image/nangong2_760410.jpg"></div>
		</div>
		<a class="left carousel-control" href="#carousel-qihang" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#carousel-qihang"
			role="button" data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
	<div class="container">
		<div class="row">
			<div class="page-header">
				<h3 class="text-center">
					最新公告<a href="#">全部公告 &gt;</a>
				</h3>
			</div>
			<div class="col-xs-24 col-md-12 tongzhi_h">
				<span class="tongtai">动态</span> <a href="#">关于12月14号羽毛球场馆占用通知。</a>
			</div>
			<div class="col-xs-24 col-md-12 tongzhi_h">
				<span class="tongtai">动态</span> <a href="#">关于12月14号羽毛球场馆占用通知。</a>
			</div>
			<div class="col-xs-24 col-md-12 tongzhi_h">
				<span class="tongtai">动态</span> <a href="#">关于12月14号羽毛球场馆占用通知。</a>
			</div>
			<div class="col-xs-24 col-md-12 tongzhi_h">
				<span class="tongtai">动态</span> <a href="#">关于12月14号羽毛球场馆占用通知。</a>
			</div>
			<div class="col-xs-24 col-md-12 tongzhi_h">
				<span class="tongtai">动态</span> <a href="#">关于12月14号羽毛球场馆占用通知。</a>
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
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/static/js/index.js"></script>
</body>

</html>