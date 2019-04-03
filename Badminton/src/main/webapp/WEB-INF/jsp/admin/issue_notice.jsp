<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
    <title>发布通知</title>
    <link href="${pageContext.request.contextPath }/static/lib/bootstrap/css/bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/font/iconfont.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/lib/datatimepicker/css/bootstrap-datetimepicker.css">
    <!--[if lt IE 9]>
      <script src="lib/js/html5shiv.min.js"></script>
      <script src="lib/js/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/issue_notice.css">
</head>

<body class="bg-success">
    <div class="container-fluid bg-success">
        <h2 class="text-center">发布通知</h2>
        <div class="col-lg-6 col-xs-5"></div>
        <div class="col-lg-12 col-xs-14">
            <form action="${pageContext.request.contextPath }/issueNotice" method="post">
                <div class="form-group">
                    <label for="">标题：</label>
                    <input autocomplete="off" class="form-control" type="text" name="title">
                </div>
                <div class="form-group">
                    <label for="">时间：</label>
                    <input type="text" name="time" readonly="readonly" value="${today }" class="form-control input-sm"/>
                </div>
                <div class="form-group">
                        <label for="">内容：</label>
                        <textarea class="form-control" name="text" cols="30" rows="10"></textarea>
                </div>
                <div class="form-group" style="margin-bottom: 80px!important;">
                    <div class="col-xs-16"></div>
                    <div class="col-xs-8"><input type="submit" value="发布" class="form-control btn btn-default"></div>
                </div>
            </form>
        </div>
    </div>
    <script src="${pageContext.request.contextPath }/static/lib/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/lib/bootstrap/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/issue_notice.js"></script>
    <script src="${pageContext.request.contextPath }/static/lib/datatimepicker/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/lib/datatimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js "
        charset="UTF-8"></script>
</body>
</html>