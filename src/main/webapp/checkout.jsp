<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 23.11.2016
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Checkout</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<% if(request.getSession().getAttribute("user")==null){
    response.sendRedirect("/products");
}%>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <a class="navbar-brand">MobileShop</a>
        <div class="nav pull-right">
            <ul class="nav navbar-nav">
                <li><a class="navbar-brand" href="#"><%=request.getSession().getAttribute("user")%></a> </li>
                <li><a href="/products" class="btn btn-link">Products</a></li>
                <li class="active"><a href="/cart" class="btn btn-link">Cart</a></li>
                <li><a href="/logout" class="btn btn-link">Logout</a> </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <h3 id="checkout" class="page-header text-center">You make a purchase at <%=request.getParameter("cost")%> rubles</h3>
</div>
</body>
</html>
