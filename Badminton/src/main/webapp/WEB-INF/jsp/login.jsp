<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
<title>登陆账号</title>
<link href="${pageContext.request.contextPath }/static/lib/bootstrap/css/bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/font/iconfont.css" />
<!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath }/static/lib/js/html5shiv.min.js"></script>
      <script src="${pageContext.request.contextPath }/static/lib/js/respond.min.js"></script>
    <![endif]-->
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/main.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/login.css">
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
					<li><a href="#"><span class="glyphicon glyphicon-log-out"></span>
							注册</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div id="login" class="container">
		<div class="row">
			<div class="col-md-7 col-sm-6 col-xs-2"></div>
			<div class="col-md-10 col-sm-14 col-xs-20 login-col">
				<div class="title text-center">登陆账号</div>
				<div id="loginform" class="tab-pane fade in active">
					<form action="#" method="POST">
						<div class="form-group">
							<label for="">账号：</label> <input placeholder="邮箱/手机号/学号"
								class="form-control" type="text" name="username">
						</div>
						<div class="form-group">
							<label for="">密码：</label> <input placeholder="密码"
								class="form-control" type="password" name="password">
						</div>
						<div class="checkbox">
							<label><input type="checkbox" name="remeberme">10天内自动登陆</label>
						</div>
						<div class="form-group">
							<input type="submit" value="登陆"
								class="form-control btn btn-primary">
						</div>
						<div class="form-group">
							<p>
								<a href="#">找回密码</a> | 还没有账号？ <a href="#">注册账号</a>
							</p>
							<p class="text-right">使用第三方账号登陆:</p>
							<p class="text-right">
								<a href="#"><span class="iconfont icon-qq1"></span></a> <a
									href="#"><span class="iconfont icon-weixin2"></span></a>
							</p>
						</div>
					</form>
				</div>
			</div>
			<div class="col-md-7 col-sm-6 col-xs-5"></div>
		</div>
	</div>
	<div class="footer hidden-xs">
		<div class="text-center footericon">
			<span class="iconfont icon-yumaoqiu1"></span>
		</div>
		<p class="text-center">2019 科技立项项目 All Right Reserved</p>
		<p class="text-center cr">
			njit在线 版权有限 | <a href="#">工具下载</a> | <a href="#">资料下载</a> | <a
				href="#">视频下载</a> | <a href="#">问题反馈</a> | <a href="#">帮助</a>
		</p>
	</div>
	<script src="${pageContext.request.contextPath }/static/lib/js/jquery-1.12.4.min.js"></script>
	<script src="${pageContext.request.contextPath }/static/lib/bootstrap/js/bootstrap.js"></script>
</body>

</html>