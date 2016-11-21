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
    <title>Login</title>
</head>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
<body>
<% if(request.getSession().getAttribute("user")!=null){
    response.sendRedirect("/products");
}%>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
    <a class="navbar-brand">MobileShop</a>
    <div class="nav pull-right">
        <ul class="nav navbar-nav">
            <li><a href="/products" class="btn btn-link">Products</a></li>
            <li class="active"><a href="/login" class="btn btn-link">Login</a> </li>
            <li><a href="/registration" class="btn btn-link">Registration</a> </li>
        </ul>
    </div>
    </div>
</nav>

<div class="container">
    <form class="form-group" role="form" action="login" method="post">
        <h2 class="header-panel text-center">Login</h2>
        <input type="text" class="form-control" placeholder="Login" name="login" required autofocus>
        <input type="password" class="form-control" placeholder="Password" name="password" required>
        <label class="checkbox text-center">
            <input type="checkbox" name="remember">Remember me
        </label>
        <button class="btn btn-block" type="submit">Log in</button>
    </form>
</div>
</body>
</html>
