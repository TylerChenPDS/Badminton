<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
    <title>用户管理页面</title>
    <link href="${pageContext.request.contextPath }/static/lib/bootstrap/css/bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/font/iconfont.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/lib/datatimepicker/css/bootstrap-datetimepicker.css">
    <!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath }/static/lib/js/html5shiv.min.js"></script>
      <script src="${pageContext.request.contextPath }/static/lib/js/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/user.css">
</head>

<body>
    <div class="container-fluid">
        <div id="search_btn" class="row text-right">
            <button type="button" data-toggle="modal" data-target="#addUserModal" class="btn btn-default">添加</button>
            <button type="button" class="btn btn-default">删除</button>
            <button type="button" class="btn btn-default">导出</button>
        </div>
        <div class="row" id="search_table">
            <div class="table-responsive">
                <table class="table table-bordered table-hover">
                    <tr>
                        <td style="width: 20px;"><input type="checkbox" name="allcheck" id="checkall" onclick="checkall();"></td>
                        <td>资源路径</td>
                        <td>资源描述</td>
                        <td style="width: 130px;">操作</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" name="checkuser" class="checkone" onclick="checkone()"></td>
                        <td>资源路径</td>
                        <td>资源描述</td>
                        <td><a href="#updateUserModal" data-toggle="modal">编辑</a><a href="">删除</a> </td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" name="checkuser" class="checkone" onclick="checkone()"></td>
                        <td>资源路径</td>
                        <td>资源描述</td>
                        <td><a href="#updateUserModal" data-toggle="modal">编辑</a><a href="">删除</a></td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" name="checkuser" class="checkone" onclick="checkone()"></td>
                        <td>资源路径</td>
                        <td>资源描述</td>
                        <td><a href="#updateUserModal" data-toggle="modal">编辑</a><a href="">删除</a></td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="row" id="pager">
            <p class="pull-left">
                总共有 <span>90</span>条记录，当前页 <span>1/9</span> 页
            </p>
            <div class="btn-group pull-right">
                <button type="button" class="btn btn-default">首页</button>
                <button type="button" class="btn btn-default">上一页</button>
                <button type="button" class="btn btn-default">下一页</button>
                <button type="button" class="btn btn-default">尾页</button>
            </div>
        </div>
    </div>
    <!-- Modal  添加-->
    <div class="modal fade" id="addUserModal" tabindex="-1">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">添加资源</h4>
                </div>
                <div class="modal-body">
                    <form action="#">
                        <div class="form-group">
                            <label for="">资源路径：</label>
                            <input placeholder="资源路径" class="form-control" type="text" name="path">
                        </div>
                        <div class="form-group">
                            <label for="">资源描述：</label>
                            <input placeholder="用户名" placeholder="邮箱" class="form-control" type="text" name="detail">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary">添加资源</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="updateUserModal" tabindex="-1">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">编辑资源</h4>
                </div>
                <div class="modal-body">
                    <form action="#">
                        <div class="form-group">
                            <label for="">资源路径：</label>
                            <input placeholder="资源路径" class="form-control" type="text" name="path">
                        </div>
                        <div class="form-group">
                            <label for="">资源描述：</label>
                            <input placeholder="用户名" placeholder="邮箱" class="form-control" type="text" name="detail">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary">编辑用户</button>
                </div>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath }/static/lib/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/lib/bootstrap/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/resourse.js"></script>
    <script src="${pageContext.request.contextPath }/static/lib/datatimepicker/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/lib/datatimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js " charset="UTF-8"></script>
</body>

</html>