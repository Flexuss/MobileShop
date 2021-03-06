<%@ page import="ru.kpfu.itis.dmitry_ivanov.database.Database" %>
<%@ page import="ru.kpfu.itis.dmitry_ivanov.entity.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.kpfu.itis.dmitry_ivanov.entity.Comment" %><%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 14.11.2016
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% Database db=new Database();%>
<head>
    <title><%=db.getProduct(request.getParameter("id")).name%> - MobileShop</title>
</head>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="js/script.js"></script>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
    <a class="navbar-brand">MobileShop</a>
    <div class="nav pull-right">
        <ul class="nav navbar-nav">
            <%if(request.getSession().getAttribute("user")!=null){%>
            <li><a class="navbar-brand" href="#"><%=request.getSession().getAttribute("user")%></a> </li>
            <li><a href="/products" class="btn btn-link">Products</a></li>
            <li><a href="/cart.jsp" class="btn btn-link">Cart</a></li>
            <li><a href="/logout" class="btn btn-link">Logout</a> </li>
            <%}else {
    boolean isAdmin=false;
    boolean isLogedIn=false;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user_data")) {
                String[] strings = cookie.getValue().split("&");
                if (db.iscorrect(strings[0], strings[1])) {
                    request.getSession().setAttribute("user", strings[0]);
                    if(request.getSession().getAttribute("user").equals("admin")){
                        isAdmin=true;
                    }
                    isLogedIn=true;
                }
            }
        }
    }
    if(isLogedIn){
        if(isAdmin){
            %><li><a class="navbar-brand" href="#"><%=request.getSession().getAttribute("user")%></a> </li>
            <li><a href="/add_product.jsp" class="btn btn-link">Add Product</a> </li>
            <li><a href="/products" class="btn btn-link">Products</a></li>
            <li><a href="/logout" class="btn btn-link">Logout</a> </li><%
        }else{%>
            <li><a class="navbar-brand" href="#"><%=request.getSession().getAttribute("user")%></a> </li>
            <li><a href="/products" class="btn btn-link">Products</a></li>
            <li><a href="/cart.jsp" class="btn btn-link">Cart</a></li>
            <li><a href="/logout" class="btn btn-link">Logout</a> </li>
        <%}
    }else{%>
            <li><a href="/products" class="btn btn-link">Products</a></li>
                <li><a href="/login" class="btn btn-link">Login</a></li>
                <li><a href="/registration" class="btn btn-link">Registration</a></li>
    <%}
            }%>
        </ul>
    </div>
    </div>
</nav>
<div class="container" id="about">
    <% Product product=new Product();
        product=db.getProduct(request.getParameter("id"));%>
    <h3 class="page-header text-center"><%=product.name%></h3>
    <ul class="list-inline">
        <li>
    <img class="img-rounded" width="250" src="<%=product.image%>">
        </li>
        <li>
    <ul class="list-unstyled">
        <li><b>Operating system: </b><%=product.os%></li>
        <li><b>Processor: </b><%=product.processor%></li>
        <li><b>Video processor: </b><%=product.video%></li>
        <li><b>RAM: </b><%=product.ram%></li>
        <li><b>ROM: </b><%=product.rom%></li>
        <li><b>Display type: </b><%=product.display%></li>
        <li><b>Diagonal: </b><%=product.diagonal%></li>
        <li><b>Resolution: </b><%=product.resolution%></li>
        <li><b>Battery: </b><%=product.battery%></li>
        <li><b>Camera: </b><%=product.camera%></li>
        <li><b>SIM: </b><%=product.sim%></li>
        <li><b>Weigth: </b><%=product.weight%></li>
    </ul>
        </li>
        <li>
        <h4><b>Cost: </b><%=product.cost%></h4>
            <% if(request.getSession().getAttribute("user")!=null){if(request.getSession().getAttribute("user").equals("admin")){%>
            <a href="/edit?id=<%=product.productId%>" class="btn btn-block">Edit</a>
            <a href="/remove?id=<%=product.productId%>" class="btn btn-block">Remove</a><%}else{%>
            <button class="btn btn-block" onclick="addToCart(<%=product.productId%>)">Add To Cart</button><%}}%>
        </li>
    </ul>
</div>
<div class="container">
    <% if(request.getSession().getAttribute("user")!=null){%>
    <h4>Write a comment:</h4>
            <form role="form" action="/add_comment?id=<%=request.getParameter("id")%>" method="post">
                    <textarea name="comment" class="form-control" rows="3"></textarea>
                <button type="submit" class="btn btn-block">Send comment</button>
            </form>
    <%}%>
            <h4>Comments:</h4>
            <ul class="list-group" id="comments">
                <% ArrayList<Comment> comments=db.getCommentList(request.getParameter("id"));
                for(int i=comments.size()-1;i>=0;i--){%>
                <li class="list-group-item">
                    <ul class="list-unstyled">
                        <li><a><h4><%=comments.get(i).getUserName(comments.get(i).user)%></h4></a></li>
                        <li><h5 id="comment"><%=comments.get(i).text%></h5></li>
                    </ul>
                </li>
    <%}%>
</div>
</body>
</html>
