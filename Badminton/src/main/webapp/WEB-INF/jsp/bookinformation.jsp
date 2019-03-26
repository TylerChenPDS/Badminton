<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
    <title>个人信息</title>
    <link href="${pageContext.request.contextPath }/static/lib/bootstrap/css/bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/font/iconfont.css" />
    <!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath }/static/lib/js/html5shiv.min.js"></script>
      <script src="${pageContext.request.contextPath }/static/lib/js/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/main.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/bookinfomation.css">
</head>

<body>
    <nav id="main_nav" class="navbar navbar-default navbar-static-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#qihangnavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="#" class="navbar-brand"><span class="iconfont icon-yumaoqiu1"></span>&nbsp;羽毛球场馆预定</a>
            </div>
            <div class="collapse navbar-collapse" id="qihangnavbar">
                <ul class="nav navbar-nav">
                     <li class="active"><a href="${pageContext.request.contextPath }/index.html">首页</a></li>
					<li><a href="${pageContext.request.contextPath }/checkstadium.html">羽毛球场馆动态</a></li>
					<c:if test="${logineduser!=null}">
						<li><a href="${pageContext.request.contextPath }/bookinformation.html">场馆预定信息</a></li>
						<c:if test="${logineduser.roles[0].rolecode=='manager'}">
							<li><a href="${pageContext.request.contextPath }/admin/admin.html">管理员界面</a></li>
						</c:if>
					</c:if>
                </ul>
                <ul class="nav navbar-nav navbar-right">
					<c:if test="${sessionScope.logineduser!=null}">
						<li><a>欢迎，<span>${sessionScope.logineduser.stuno }</span></a></li>
						<li><a
							href="${pageContext.request.contextPath }/logout.html"><span
								class="glyphicon glyphicon-log-out"></span> 退出登陆</a></li>
					</c:if>
				</ul>
            </div>
        </div>
    </nav>
    <div class="container">
        <div class="row">
            <div class="page-header">
                <h3 class="text-center">
                    预定信息
                </h3>
            </div>
            <div id="booktable" class="table-responsive">
                <table class="table table-hover table-striped">
                    <tr>
                        <td>预定时间</td>
                        <td>场馆</td>
                        <td>总价</td>
                        <td>操作</td>
                    </tr>
                    <tr>
                        <td>预定时间</td>
                        <td>场馆</td>
                        <td>总价</td>
                        <td><a href="">退订</a></td>
                    </tr>
                    <tr>
                        <td>预定时间</td>
                        <td>场馆</td>
                        <td>总价</td>
                        <td><a href="">退订</a></td>
                    </tr>
                    <tr>
                        <td>预定时间</td>
                        <td>场馆</td>
                        <td>总价</td>
                        <td><a href="">退订</a></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div class="footer hidden-xs ">
        <div class="text-center footericon">
            <span class="iconfont icon-yumaoqiu1"></span>
        </div>
        <p class="text-center">2019 科技立项项目 All Right Reserved</p>
        <p class="text-center cr">
            njit在线 版权有限 | <a href="#">工具下载</a> |
            <a href="#">资料下载</a> | <a href="#">视频下载</a> |
            <a href="#">问题反馈</a> | <a href="#">帮助</a>
        </p>
    </div>
    <script src="${pageContext.request.contextPath }/static/lib/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/lib/bootstrap/js/bootstrap.js"></script>
</body>

</html>