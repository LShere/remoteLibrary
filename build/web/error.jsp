<%-- 
    Document   : error
    Created on : 2019-5-8, 15:18:08
    Author     : MACHENIKE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            window.onload = function() {
                alert("不允许传入相同的ISBN号请重试");
                history.go(-1);
        }
        </script>
    </head>
    <body>
        <!--<h1>不允许传入相同的ISBN号请重试</h1>-->
<!--        <button onclick="alert('不允许传入相同的ISBN号请重试') history.go(-1);">返回上级</button>-->
    </body>
</html>
