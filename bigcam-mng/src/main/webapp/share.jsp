<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String uid = request.getParameter("uid");
    String url = "http://player.youku.com/embed/" + uid;
%>
<!DOCTYPE html>
<html>
<head lang="zh-CN">
    <title>分享视频 - BiGCam</title>
    <link rel="shortcut icon" href="./static/images/logo.png"/>
    <link href="./static/css/bootstrap.min.css" rel="stylesheet">

    <%--<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">--%>
    <style type="text/css">
        table {
            table-layout: fixed;
            word-wrap: break-word;
        }
    </style>
</head>
<body>
<div class="container" ng-app="bigcam-share" ng-controller="ShareController">
    <div class="row">
        <div class="col-xs-12 col-md-8 col-md-offset-2">
            <iframe class='video_iframe' height=500 width=100% frameborder=0 src='<%=url %>' allowfullscreen></iframe>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-md-12">
            <h1>内容简介</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-md-12">
            <p id="video_desc"></p>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-md-12" id="video_comments">

        </div>
    </div>
</div>



<script type="application/javascript" src="./static/js/jquery-2.1.3.min.js"></script>
<script type="application/javascript" src="./static/js/angular.min.js"></script>
<script type="application/javascript" src="./static/scripts/bigcam-share.js"></script>

</body>
</html>