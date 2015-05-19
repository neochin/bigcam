<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="zh-CN">
    <title>上传视频 - BiGCam</title>
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

<div class="container" ng-app="bigcam-app">
    <header ng-include="'static/templates/nav.html'"></header>
    <div ui-view></div>
</div>



<script type="application/javascript" src="./static/js/jquery-2.1.3.min.js"></script>
<script type="application/javascript" src="./static/js/angular.min.js"></script>
<script type="application/javascript" src="./static/js/angular-ui-router.js"></script>
<script type="application/javascript" src="./static/js/angular-resource.min.js"></script>
<script type="application/javascript" src="./static/js/jquery.uploadfile.min.js"></script>
<script type="application/javascript" src="./static/scripts/bigcam-app.js"></script>
<script type="application/javascript" src="./static/scripts/controllers/uploadCtrl.js"></script>
<script type="application/javascript" src="./static/scripts/controllers/listCtrl.js"></script>
<script type="application/javascript" src="./static/scripts/controllers/suggestionsCtrl.js"></script>

</body>
</html>