<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
    <title>后台管理界面</title>
    <link href="${pageContext.request.contextPath }/static/lib/bootstrap/css/bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/font/iconfont.css" />
    <!--[if lt IE 9]>
          <script src="${pageContext.request.contextPath }/static/lib/js/html5shiv.min.js"></script>
          <script src="${pageContext.request.contextPath }/static/lib/js/respond.min.js"></script>
        <![endif]-->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/admin.css">
</head>

<body>
    <div id="admin_top" class="container-fluid">
        <div class="row">
            <div class="navbar navbar-inverse navbar-static-top">
                <div class="navbar-header col-md-8">
                    <span class="navbar-brand"><span class="iconfont icon-yumaoqiu1"></span> 羽毛球场馆预订系统后台管理界面</span>
                </div>
                <div class="col-md-8 col-md-offset-8 login_info text-right">
                    <span class="iconfont icon-character"></span>
                    admin
                    <span class="iconfont icon-rili"></span>
                    2018-2-1
                    <a class="pull-right" href=""><span class="iconfont icon-power-bold-01"></span></a>
                </div>
            </div>
        </div>
    </div>
    <div id="sidle_bar">
        <div class="sidlebar_title">
            <p>
                <span>导航模块/</span>
                <span>Nav Module</span>
            </p>
        </div>
        <div class="sidlebar_container navbar-fixed-bottom">
            <a href="#collapse_system" data-toggle="collapse"><span class="iconfont icon-jiahao"></span> 系统设置</a>
            <ul id="collapse_system" class="collapse collapse_all">
                <li><a data-iframe="${pageContext.request.contextPath }/admin/user_manager.html" href=""><span class="iconfont icon-tubiaozhizuo-"></span>用户管理</a></li>
                <li><a data-iframe="${pageContext.request.contextPath }/admin/role_manager.html" href=""><span class="iconfont icon-tubiaozhizuo-"></span>角色管理</a></li>
                <li><a data-iframe="${pageContext.request.contextPath }/admin/resource_manager.html" href=""><span class="iconfont icon-tubiaozhizuo-"></span>资源管理</a></li>
            </ul>
            <br>
            <a href="#collapse_course" data-toggle="collapse"><span class="iconfont icon-jiahao"></span> 普通管理员</a>
            <ul id="collapse_course" class="collapse collapse_all">
                <li><a data-iframe="${pageContext.request.contextPath }/admin/issue_notice.html" href=""><span class="iconfont icon-tubiaozhizuo-"></span>发布通知</a></li>
                <li><a data-iframe="${pageContext.request.contextPath }/admin/governnotice.html" href=""><span class="iconfont icon-tubiaozhizuo-"></span>管理通知</a></li>
            </ul>
            <br>
        </div>
    </div>
    <div id="path_nav">
        <ol class="breadcrumb">
            <li><a href="#">后台首页</a></li>
            <li><a href="#">系统管理</a></li>
            <li class="active">系统信息</li>
        </ol>
    </div>
    <script src="${pageContext.request.contextPath }/static/lib/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/lib/bootstrap/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/admin.js"></script>
</body>

</html>
<iframe id="iframe-content" class="navbar-fixed-bottom" frameborder="no" scrolling="auto" width="100%" height="100%"
    allowfullscreen="true" src="${pageContext.request.contextPath }/admin/welcome.html" frameborder="0"></iframe>
<!-- allowfullscreen="true" src="./welcome.html" frameborder="0"></iframe> 
        role_manager.html
        resource_manager.html
    -->