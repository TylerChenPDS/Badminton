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
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/index.css" />
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
            <a href="${pageContext.request.contextPath}/login.html"><span class="glyphicon glyphicon-user"></span> 登陆</a>
          </li>
          <li>
            <a href="#"><span class="glyphicon glyphicon-log-out"></span> 注册</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <div id="carousel-qihang" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#carousel-qihang" data-slide-to="0" class="active"></li>
      <li data-target="#carousel-qihang" data-slide-to="1"></li>
      <li data-target="#carousel-qihang" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      <div data-lg-img="${pageContext.request.contextPath}/static/image/slide_01_2000x410.jpg" data-xs-img="${pageContext.request.contextPath}/static/image/slide_01_768x410.jpg" class="item active"></div>
      <div class="item" data-lg-img="${pageContext.request.contextPath}/static/image/slide_02_2000x410.jpg" data-xs-img="${pageContext.request.contextPath}/static/image/slide_02_768x410.jpg"></div>
      <div class="item" data-lg-img="${pageContext.request.contextPath}/static/image/slide_03_2000x410.jpg" data-xs-img="${pageContext.request.contextPath}/static/image/slide_03_768x410.jpg"></div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#carousel-qihang" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#carousel-qihang" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
  <div class="container">

    <div class="row">
      <div class="page-header">
        <h3 class="text-center">最新发布的课程<a href="#">全部课程 &gt;</a></h3>
      </div>
      <div class="col-xs-12 col-md-6">
        <div class="thumbnail">
          <a href="#">
            <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course" />
          </a>
          <div class="caption">
            <h3><a href="#">java系列课程技术JVM调优</a></h3>
            <p>
              ￥50.00
              <a href="#">其他课程</a>
            </p>
          </div>
        </div>
      </div>
      <div class="col-xs-12 col-md-6">
        <div class="thumbnail">
          <a href="#">
            <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course" />
          </a>
          <div class="caption">
            <h3><a href="#">java系列课程技术JVM调优</a></h3>
            <p>
              ￥50.00
              <a href="#">其他课程</a>
            </p>
          </div>
        </div>
      </div>
      <div class="col-xs-12 col-md-6">
        <div class="thumbnail">
          <a href="#">
            <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course" />
          </a>
          <div class="caption">
            <h3><a href="#">java系列课程技术JVM调优</a></h3>
            <p>
              ￥50.00
              <a href="#">其他课程</a>
            </p>
          </div>
        </div>
      </div>
      <div class="col-xs-12 col-md-6">
        <div class="thumbnail">
          <a href="#">
            <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course" />
          </a>
          <div class="caption">
            <h3><a href="#">java系列课程技术JVM调优</a></h3>
            <p>
              ￥50.00
              <a href="#">其他课程</a>
            </p>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
        <div class="page-header">
          <h3 class="text-center">JAVASE课程<a href="#">全部课程 &gt;</a></h3>
        </div>
        <div class="col-xs-12 col-md-6">
          <div class="thumbnail">
            <a href="#">
              <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course" />
            </a>
            <div class="caption">
              <h3><a href="#">java系列课程技术JVM调优</a></h3>
              <p>
                ￥50.00
                <a href="#">其他课程</a>
              </p>
            </div>
          </div>
        </div>
        <div class="col-xs-12 col-md-6">
          <div class="thumbnail">
            <a href="#">
              <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course" />
            </a>
            <div class="caption">
              <h3><a href="#">java系列课程技术JVM调优</a></h3>
              <p>
                ￥50.00
                <a href="#">其他课程</a>
              </p>
            </div>
          </div>
        </div>
        <div class="col-xs-12 col-md-6">
          <div class="thumbnail">
            <a href="#">
              <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course" />
            </a>
            <div class="caption">
              <h3><a href="#">java系列课程技术JVM调优</a></h3>
              <p>
                ￥50.00
                <a href="#">其他课程</a>
              </p>
            </div>
          </div>
        </div>
        <div class="col-xs-12 col-md-6">
          <div class="thumbnail">
            <a href="#">
              <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course" />
            </a>
            <div class="caption">
              <h3><a href="#">java系列课程技术JVM调优</a></h3>
              <p>
                ￥50.00
                <a href="#">其他课程</a>
              </p>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
          <div class="page-header">
            <h3 class="text-center">JAVAEE课程<a href="#">全部课程 &gt;</a></h3>
          </div>
          <div class="col-xs-12 col-md-6">
            <div class="thumbnail">
              <a href="#">
                <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course" />
              </a>
              <div class="caption">
                <h3><a href="#">java系列课程技术JVM调优</a></h3>
                <p>
                  ￥50.00
                  <a href="#">其他课程</a>
                </p>
              </div>
            </div>
          </div>
          <div class="col-xs-12 col-md-6">
            <div class="thumbnail">
              <a href="#">
                <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course" />
              </a>
              <div class="caption">
                <h3><a href="#">java系列课程技术JVM调优</a></h3>
                <p>
                  ￥50.00
                  <a href="#">其他课程</a>
                </p>
              </div>
            </div>
          </div>
          <div class="col-xs-12 col-md-6">
            <div class="thumbnail">
              <a href="#">
                <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course" />
              </a>
              <div class="caption">
                <h3><a href="#">java系列课程技术JVM调优</a></h3>
                <p>
                  ￥50.00
                  <a href="#">其他课程</a>
                </p>
              </div>
            </div>
          </div>
          <div class="col-xs-12 col-md-6">
            <div class="thumbnail">
              <a href="#">
                <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course" />
              </a>
              <div class="caption">
                <h3><a href="#">java系列课程技术JVM调优</a></h3>
                <p>
                  ￥50.00
                  <a href="#">其他课程</a>
                </p>
              </div>
            </div>
          </div>
        </div>
  </div>
  <div class="footer hidden-xs">
    <div class="text-center footericon"><span class="iconfont icon-chuanship"></span></div>
    <p class="text-center">Copyright 2018 njit. All Right Reserved</p>
    <p class="text-center cr">njit在线 版权有限 | <a href="#">工具下载</a> | <a href="#">资料下载</a> | <a href="#">视频下载</a> | <a href="#">问题反馈</a> | <a href="#">帮助</a></p>
  </div>
  <script src="${pageContext.request.contextPath}/static/lib/js/jquery-1.12.4.min.js"></script>
  <script src="${pageContext.request.contextPath}/static/lib/bootstrap/js/bootstrap.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/index.js"></script>
</body>

</html>