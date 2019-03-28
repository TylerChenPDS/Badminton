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
    <title>用户管理页面</title>
    <link href="${pageContext.request.contextPath }/static/lib/bootstrap/css/bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/font/iconfont.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/lib/datatimepicker/css/bootstrap-datetimepicker.css">
    <!--[if lt IE 9]>
      <script src="lib/js/html5shiv.min.js"></script>
      <script src="lib/js/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/governnotice.css">
</head>

<body>
    <div class="container-fluid">
        <div class="row">
            <div class="serch_title_bar">
                搜索
            </div>
        </div>
        <div class="row">
            <form id="search_form" class="form-inline">
                <div class="form-group has-feedback">
                    <label>开始时间: </label>
                    <input type="text" name="starttime" class="form_datetime form-control input-sm"> <span
                        class="iconfont icon-rili2 form-control-feedback"></span>
                </div>
                <div class="form-group has-feedback">
                    <label>结束时间: </label>
                    <input type="text" name="endtime" class="form_datetime form-control input-sm"> <span
                        class="iconfont icon-rili2 form-control-feedback"></span>
                </div>
                &nbsp;
                <div class="form-group">
                    <button  onclick="submitsearch()" type="button" class="btn btn-default">查询</button>
                </div>
            </form>
        </div>
        <div id="search_btn" class="row text-right">
            <button type="button" onclick="openIssueView()" class="btn btn-default">添加</button>
            <button type="button" onclick="batchDel()" class="btn btn-default">删除</button>
        </div>
        <div class="row" id="search_table">
            <div class="table-responsive">
                <table class="table table-bordered table-hover">
                    <tr>
                        <td style="width: 20px;"><input type="checkbox" name="allcheck" id="checkall"
                                onclick="checkall();"></td>
                        <td>时间</td>
                        <td>标题</td>
                        <td style="width: 130px;">操作</td>
                    </tr>
                    
                    <c:forEach var="notice" items="${notices.list }">
                    	<tr>
	                        <td><input type="checkbox" name="checkuser" value="${notice.id}" class="checkone" onclick="checkone()"></td>
	                        <td>${notice.time }</td>
	                        <td>${notice.title }</td>
	                        <td><a href="${pageContext.request.contextPath }/updateNoticeView?nid=${notice.id}">编辑/详情</a>
	                        	<a onclick="return delSure()" href="${pageContext.request.contextPath }/deleteNotice?nid=${notice.id}">删除</a> </td>
                    	</tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div class="row" id="pager">
            <p class="pull-left">
                总共有 <span>${notices.total}</span>条记录，当前页 <span>${notices.pageNum }/${notices.pages }</span> 页
            </p>
            <div class="btn-group pull-right">
                <a
					href="${pageContext.request.contextPath}/admin/governnotice.html"
					type="button" class="btn btn-default">首页</a> <a
					href="${pageContext.request.contextPath}/admin/governnotice.html?pageNum=${notices.prePage }"
					type="button" class="btn btn-default">上一页</a> <a
					href="${pageContext.request.contextPath}/admin/governnotice.html?pageNum=${notices.nextPage }"
					type="button" class="btn btn-default">下一页</a> <a
					href="${pageContext.request.contextPath}/admin/governnotice.html?pageNum=${notices.pages }"
					type="button" class="btn btn-default">尾页</a>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath }/static/lib/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/lib/bootstrap/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/governnotice.js"></script>
    <script src="${pageContext.request.contextPath }/static/lib/datatimepicker/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/lib/datatimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js "
        charset="UTF-8"></script>
</body>
</html>