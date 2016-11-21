<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 05.11.2016
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
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
            <li><a href="/login" class="btn btn-link">Login</a> </li>
            <li class="active"><a href="/registration" class="btn btn-link">Registration</a> </li>
        </ul>
    </div>
    </div>
</nav>

<div class="container">
    <form class="form-group" role="form" action="registration" method="post">
        <h2 class="header-panel text-center">Registration</h2>
        <input type="text" class="form-control" placeholder="Login" name="login" required autofocus>
        <input type="password" class="form-control" placeholder="Password" name="password" required>
        <input type="password" class="form-control" placeholder="Confirm Password" name="confirmPassword" required>
        <input type="email" class="form-control" placeholder="E-mail" name="e-mail" required>
        <button class="btn btn-block" type="submit">Registration</button>
    </form>
</div>
</body>
</html>
