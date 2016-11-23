<%@ page import="ru.kpfu.itis.dmitry_ivanov.Product" %>
<%@ page import="ru.kpfu.itis.dmitry_ivanov.Database" %><%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 23.11.2016
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product</title>
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
    <% Database db=new Database();
        Product product=db.getProduct(request.getParameter("id"));
    %>
    <form class="form-group" action="/add_product?id=<%=product.productId%>" method="post" enctype="multipart/form-data">
        <h2 href="#" class="header-panel text-center">Add Product</h2>
        <input type="text" class="form-control" value="<%=product.name%>" placeholder="Product Name" name="name" required autofocus>
        <input type="text" class="form-control" value="<%=product.os%>" placeholder="Operating System" name="operating_system" required>
        <input type="text" class="form-control" value="<%=product.processor%>" placeholder="Processor" name="processor" required>
        <input type="text" class="form-control" value="<%=product.video%>" placeholder="Video processor" name="video" required>
        <input type="text" class="form-control" value="<%=product.ram%>" placeholder="RAM" name="ram" required>
        <input type="text" class="form-control" value="<%=product.rom%>" placeholder="ROM" name="rom" required>
        <input type="text" class="form-control" value="<%=product.display%>" placeholder="Display" name="display_type" required>
        <input type="text" class="form-control" value="<%=product.diagonal%>" placeholder="Diagonal" name="diagonal" required>
        <input type="text" class="form-control" value="<%=product.resolution%>" placeholder="Resolution" name="resolution" required>
        <input type="text" class="form-control" value="<%=product.battery%>" placeholder="Battery" name="battery" required>
        <input type="text" class="form-control" value="<%=product.camera%>" placeholder="Camera" name="camera" required>
        <input type="text" class="form-control" value="<%=product.sim%>" placeholder="SIM" name="sim" required>
        <input type="text" class="form-control" value="<%=product.weight%>" placeholder="Weigth" name="weight" required>
        <input type="text" class="form-control" value="<%=product.cost%>"placeholder="Cost" name="cost" required>
        <input type="file" name="image" class="form-control btn btn-block" multiple accept="image/*" required>
        <input class="btn btn-block" type="submit" name="Add Product">
    </form>
</div>
</body>
</html>
