<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 03.11.2016
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<% if(request.getSession().getAttribute("login")!=null){
    response.sendRedirect("/");
}%>
<h1> Hello </h1>
<form action="clogin" method="post">
Login<input type="text" name="login"><br>
Password<input type="password" name="password"><br>
    <input type="checkbox" name="remember">Remember me<br>
<button type="submit">Log In</button>
</form>
</body>
</html>
