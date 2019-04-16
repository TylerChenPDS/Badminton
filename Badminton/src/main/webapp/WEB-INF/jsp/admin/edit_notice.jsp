<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <!-- <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" /> -->
    <title>更改通知</title>
    <link href="${pageContext.request.contextPath }/static/lib/bootstrap/css/bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/font/iconfont.css" />
    <!--[if lt IE 9]>
      <script src="lib/js/html5shiv.min.js"></script>
      <script src="lib/js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="bg-success">
    <div class="container-fluid bg-success">
        <h2 class="text-center">详细信息</h2>
        <div class="col-lg-6 col-xs-5"></div>
        <div class="col-lg-12 col-xs-14">
            <form action="${pageContext.request.contextPath }/updateNotice" method="post">
            	<input type="hidden" name="id" value="${notice.id }">
                <div class="form-group">
                    <label for="">标题：</label>
                    <input class="form-control" value="${notice.title }" type="text" name="title">
                </div>
                <div class="form-group">
                    <label for="">发布时间：</label>
                    <input class="form-control" value="${notice.time }" readonly="readonly" type="text" name="time">
                </div>
                <div class="form-group" >
                        <label for="">内容：</label>
                        <textarea class="form-control" name="text" id="" cols="30" rows="10">${notice.text }</textarea>
                </div>
                <div class="form-group" style="margin-bottom: 80px!important;">
                	<div class="col-xs-11"><a onclick="return delsure();" href="${pageContext.request.contextPath }/deleteNotice?nid=${notice.id}" class="form-control btn btn-default">删除</a></div>
                    <div class="col-xs-12"></div>
                    <div class="col-xs-11"><input type="submit" value="修改" class="form-control btn btn-default"></div>
                </div>
            </form>
        </div>
    </div>
    <script src="${pageContext.request.contextPath }/static/lib/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/lib/bootstrap/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/edit_notice.js"></script>
</body>
</html>