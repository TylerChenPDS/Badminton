<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
    <title>启航课堂首页</title>
    <link href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/font/iconfont.css" />
    <!--[if lt IE 9]>
      <script src="lib/js/html5shiv.min.js"></script>
      <script src="lib/js/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login.css">
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
                <a href="#" class="navbar-brand"><span class="iconfont icon-chuan"></span> 启航课堂</a>
            </div>
            <div class="collapse navbar-collapse" id="qihangnavbar">
                <ul class="nav navbar-nav ">
                    <li class="active"><a href="">首页</a></li>
                    <li><a href="">全部课程</a></li>
                    <li><a href="">问题讨论</a></li>
                    <li><a href="">学习路线</a></li>
                    <li><a href="">资料下载</a></li>
                </ul>
                <div class="navbar-form navbar-left">
                    <form action="" method="get">
                        <div class="input-group" style="width: 180px;">
                            <input type="text" name="search_str" class="form-control" placeholder="课程名称..." />
                            <span class="input-group-btn">
                                <button type="button" class="btn btn-primary">
                                    <span class="glyphicon glyphicon-search"></span>
                                </button>
                            </span>
                        </div>
                    </form>
                </div>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="#"><span class="glyphicon glyphicon-user"></span> 登陆</a>
                    </li>
                    <li>
                        <a href="#"><span class="glyphicon glyphicon-log-out"></span> 注册</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div id="login" class="container">
        <div class="row">
            <div class="col-md-7 col-sm-6 col-xs-5"></div>
            <div class="col-md-10 col-sm-12 col-xs-14  login-col">
                <ul class="nav nav-tabs">
                    <li class="col-xs-12 active"><a class="text-center" data-toggle="tab" href="#loginform">登陆账号</a></li>
                    <li class="col-xs-12"><a class="text-center" data-toggle="tab" href="#regform">注册账号</a></li>
                </ul>
                <div class="tab-content">
                    <div id="loginform" class="tab-pane fade in active">
                        <form action="${pageContext.request.contextPath }/login.html" method="POST">
                            <div class="form-group">
                                <label for="">账号：</label>
                                <input placeholder="邮箱/手机号/用户名" class="form-control" type="text" name="userInfo">
                            </div>
                            <div class="form-group">
                                <label for="">密码：</label>
                                <input placeholder="密码" class="form-control" type="password" name="password">
                            </div>
                            <div class="checkbox">
                                <label><input type="checkbox" name="remeberme">10天内自动登陆</label>
                            </div>
                            <div class="form-group">
                                <input type="submit" value="登陆" class="form-control btn btn-primary">
                            </div>
                            <div class="form-group">
                                <p><a href="#">找回密码</a> | 还没有账号？ <a href="#">注册账号</a></p>
                                <p class="text-right">使用第三方账号登陆:</p>
                                <p class="text-right">
                                    <a href="#"><span class="iconfont icon-qq1"></span></a>
                                    <a href="#"><span class="iconfont icon-weixin2"></span></a>
                                </p>
                            </div>
                        </form>
                    </div>
                    <div id="regform" class="tab-pane fade">
                        <form action="#" method="post">
                            <div class="form-group">
                                <label for="">手机：</label>
                                <input placeholder="常用手机号" class="form-control" type="text" name="telephoneNumber">
                            </div>
                            <div class="form-group">
                                <label for="">用户名</label>
                                <input placeholder="用户名" class="form-control" type="text" name="username">
                            </div>
                            <div class="form-group">
                                <label for="">密码：</label>
                                <input placeholder="密码" class="form-control" type="text" name="username">
                            </div>
                            <div class="form-group">
                                <label for="">手机验证码：</label>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <input type="text" name="phoneCode" class="form-control">
                                    </div>
                                    <div class="col-xs-12">
                                        <input type="button" class="form-control btn btn-default" value="点击获取验证码">
                                    </div>
                                </div>


                            </div>
                            <div class="form-group">
                                <input type="submit" value="登陆" class="form-control btn btn-primary">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-md-7 col-sm-6 col-xs-5"></div>
        </div>

    </div>
    <div class="footer hidden-xs">
        <div class="text-center footericon"><span class="iconfont icon-chuanship"></span></div>
        <p class="text-center">Copyright 2018 njit. All Right Reserved</p>
        <p class="text-center cr">njit在线 版权有限 | <a href="#">工具下载</a> | <a href="#">资料下载</a> | <a href="#">视频下载</a> | <a
                href="#">问题反馈</a> | <a href="#">帮助</a></p>
    </div>
    <script src="${pageContext.request.contextPath}/static/lib/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/lib/bootstrap/js/bootstrap.js"></script>
</body>

</html>