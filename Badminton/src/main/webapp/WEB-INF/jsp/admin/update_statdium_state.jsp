<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/update_stadium_state.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/lib/bootstrapSelect/css/bootstrap-select.min.css">
</head>

<body>
    <div class="container-fluid bg-success">
        <h2 class="text-center">添加场馆约束</h2>
        <div class="col-lg-6 col-xs-5"></div>
        <div class="col-lg-12 col-xs-14">

            <form action="${pageContext.request.contextPath }/admin/addStadiumState" method="post">
                <div class="form-group  has-feedback">
                    <label for="">开始时间：<font color="red">${err }</font></label>
                    <input type="text" required name="starttime" autocomplete="off"
                        class="form_datetime form-control input-sm"><span
                        class="iconfont icon-rili2 form-control-feedback"></span>
                </div>
                <div class="form-group  has-feedback">
                    <label for="">结束时间：</label>
                    <input type="text" required name="endtime" autocomplete="off"
                        class="form_datetime form-control input-sm"><span
                        class="iconfont icon-rili2 form-control-feedback"></span>
                </div>
                <div class="form-group">
                        <label for="">场地：</label>
                        <select name="sids" required data-live-search="true" multiple class="selectpicker form-control">
                            <option value="-1">全部场地</option>
                            <c:forEach var="stadium" items="${stadiums }">
                            	<option value='${stadium.id }'>${stadium.detail }</option>
                            </c:forEach>
                        </select>
                    </div>
                <div class="form-group">
                    <label for="">时间段：</label>
                    <select name="timecodes" required data-live-search="true" multiple class="selectpicker form-control">
                        <option value="-1">全天</option>
                        <c:forEach var="time" items="${times }">
                        	<option value='${time.code }'>${time.timeStr }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group" id="button">
                    <input type="submit" value="确认添加" class=" form-control btn btn-default">
                </div>
                <p class="attention">注意事项：</p>
                <ul class="attentionlist">
                    <li>1，可以选择一段时间，也可以选择某一天，选择某一天的时候需开始时间和结束时间相同</li>
                    <li>2，时间段可以选择全天，表示全天某个场馆不可以使用。或者选择以一个或多个全天以外的其他选项</li>
                    <li>3，添加约束将覆盖原来的约束</li>
                </ul>
            </form>
        </div>
    </div>
    <script src="${pageContext.request.contextPath }/static/lib/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/lib/bootstrap/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/update_stadium_state.js"></script>
    <script src="${pageContext.request.contextPath }/static/lib/datatimepicker/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/lib/datatimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js "
        charset="UTF-8"></script>
    <script src="${pageContext.request.contextPath }/static/lib/bootstrapSelect/js/bootstrap-select.min.js"></script>
</body>

</html>