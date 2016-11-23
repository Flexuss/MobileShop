<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 16.11.2016
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product</title>
</head>
<body>
<% if(request.getSession().getAttribute("user")==null){ response.sendRedirect("/products");}else{if(!request.getSession().getAttribute("user").equals("admin")) {
    response.sendRedirect("/products");
}}%>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <a class="navbar-brand">MobileShop</a>
        <div class="nav pull-right">
            <ul class="nav navbar-nav">
                <li><a class="navbar-brand" href="#"><%=request.getSession().getAttribute("user")%></a> </li>
                <li class="active"><a href="add_product.jsp" class="btn btn-link">Add Product</a> </li>
                <li><a href="/products" class="btn btn-link">Products</a></li>
                <li><a href="/logout" class="btn btn-link">Logout</a> </li>
            </ul>
        </div>
    </div>
</nav>
    <div class="container">
        <form class="form-group" action="/add_product" method="post" enctype="multipart/form-data">
            <h2 href="#" class="header-panel text-center">Add Product</h2>
            <input type="text" class="form-control" placeholder="Product Name" name="name" required autofocus>
            <input type="text" class="form-control" placeholder="Operating System" name="operating_system" required>
            <input type="text" class="form-control" placeholder="Processor" name="processor" required>
            <input type="text" class="form-control" placeholder="Video processor" name="video" required>
            <input type="text" class="form-control" placeholder="RAM" name="ram" required>
            <input type="text" class="form-control" placeholder="ROM" name="rom" required>
            <input type="text" class="form-control" placeholder="Display" name="display_type" required>
            <input type="text" class="form-control" placeholder="Diagonal" name="diagonal" required>
            <input type="text" class="form-control" placeholder="Resolution" name="resolution" required>
            <input type="text" class="form-control" placeholder="Battery" name="battery" required>
            <input type="text" class="form-control" placeholder="Camera" name="camera" required>
            <input type="text" class="form-control" placeholder="SIM" name="sim" required>
            <input type="text" class="form-control" placeholder="Weigth" name="weight" required>
            <input type="text" class="form-control" placeholder="Cost" name="cost" required>
            <input type="file" name="image" class="form-control btn btn-block" multiple accept="image/*" required>
            <input class="btn btn-block" type="submit" name="Add Product">
        </form>
    </div>
</body>
</html>
