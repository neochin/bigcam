<%--
  Created by IntelliJ IDEA.
  User: tony.yang
  Date: 2014/8/31
  Time: 0:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>登录 - BiGCam</title>
  <link rel="shortcut icon" href="./static/images/logo.png"/>
  <link rel="stylesheet" href="static/css/bootstrap.min.css">
  <style type="text/css">
    .pace-index {
      position:fixed;
      top: 50%;
      left: 50%;
      width:30em;
      height:18em;
      margin-top: -9em; /*set to a negative number 1/2 of your height*/
      margin-left: -15em; /*set to a negative number 1/2 of your width*/
    }
  </style>
  <script type="application/javascript" src="./static/js/jquery-2.1.3.min.js"></script>
</head>
<body>
<div class="container pace-index">
  <form class="form-inline" action="/user/upload_login" method="post">
    <div class="form-group">
      <label class="sr-only" for="username">用户名/昵称</label>
      <input type="text" class="form-control" id="username" placeholder="用户名/昵称" name="username">
    </div>
    <button type="submit" class="btn btn-default">Sign in</button>
  </form>
</div>

<script type="application/javascript">
  $(function(){
    $('form').submit(function(e){
      e.preventDefault();
      var formObj = $(this);
      $.ajax( {
        url: formObj.attr('action'),
        type: formObj.attr('method'),
        data: new FormData( this ),
        processData: false,
        contentType: false,
        success: function(data){
          if(data.success) {
            window.location.replace("/index.jsp");
          } else{
            alert(data.msg);
          }
        }
      } );
    });
  })
</script>
</body>
</html>
