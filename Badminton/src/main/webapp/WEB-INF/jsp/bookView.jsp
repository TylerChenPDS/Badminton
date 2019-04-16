<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
    <title>个人信息</title>
    <link href="${pageContext.request.contextPath }/static/lib/bootstrap/css/bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/font/iconfont.css" />
    <!--[if lt IE 9]>
      <script src="lib/js/html5shiv.min.js"></script>
      <script src="lib/js/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/main.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/lib/bootstrapSelect/css/bootstrap-select.min.css">
</head>

<body class="bg-success">
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
					</c:if>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${sessionScope.logineduser!=null}">
						<li><a>欢迎，<span id="logineduser">${sessionScope.logineduser.stuno }</span></a></li>
						<li><a
							href="${pageContext.request.contextPath }/logout.html"><span
								class="glyphicon glyphicon-log-out"></span> 退出登陆</a></li>
						<li><a href="${pageContext.request.contextPath }/adminloginview.html" target="_blank"><span
								class="glyphicon glyphicon-user"></span> 管理员登陆</a></li>
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
    <div class="container">
        <h2 class="text-center">预定场馆</h2>
        <div class="col-lg-6 col-xs-5"></div>
        <div class="col-lg-12 col-xs-14">
            <form action="${pageContext.request.contextPath }/booking.html" method="post">
                <input type="hidden" name="sid" value="${stadium.id}">
                <div class="form-group">
                    <label for="">场地：</label>
                    <input readonly value="${stadium.detail }" class="form-control" type="text" >
                </div>
                <div class="form-group">
                    <label for="">时间（只能当天选定）：</label>
                    <input readonly class="form-control" type="text" value="${today }">
                </div>
                <div class="form-group">
                        <label for="">时间段：</label>
                        <select required name="timecode" data-live-search="true" multiple class="selectpicker form-control">
                            <c:forEach var="canReachTime" items="${canReachTimes }">
                            	<c:if test="${canReachTime.islimits==true}">
                            		<option disabled value='${canReachTime.code}'>${canReachTime.timeStr}(不可用)</option>
                            	</c:if>
                            	<c:if test="${canReachTime.islimits==false}">
                            		<option value='${canReachTime.code}'>${canReachTime.timeStr}</option>
                            	</c:if>
                            </c:forEach>
                        </select>
                    </div>
                <div class="form-group" id="button">
                    <div class="col-lg-16 col-xs-8"></div>
                    <div class="col-lg-8 col-xs-16"><input type="submit" onclick="return book();" value="预定" class=" form-control btn btn-default"></div>
                </div>
            </form>
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
     <script src="${pageContext.request.contextPath }/static/js/bookView.js"></script>
    <script src="${pageContext.request.contextPath }/static/lib/bootstrapSelect/js/bootstrap-select.min.js"></script>
</body>

</html>