<%@ page import="ru.kpfu.itis.dmitry_ivanov.Database" %>
<%@ page import="ru.kpfu.itis.dmitry_ivanov.Product" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 13.11.2016
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
</head>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
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
<ul class="list-group">
    <% Database db = new Database();
        String productList= (String) request.getSession().getAttribute("cart");
        if(productList!=null){
        ArrayList<Product> products = db.getCartProductList((String) request.getSession().getAttribute("cart"));
        int count = products.size();
        int totalCount=0;
        for(int i=count-1;i>=0;i--){
            totalCount=totalCount+Integer.parseInt(products.get(i).cost);%>
    <li class="list-group-item">
        <ul class="list-inline list-unstyled">
            <li class="top pull-left"><img class="img-rounded" src="<%=products.get(i).image%>" height="150"></li>
            <li class="top center-block"><ul class="list-unstyled">
                <li><h3><%=products.get(i).name%></h3></li>
                <li>Operating system:   <%=products.get(i).os%></li>
                <li>Processor:  <%=products.get(i).processor%></li>
                <li>Diagonal:  <%=products.get(i).diagonal%></li>
                <li>Resolution: <%=products.get(i).resolution%></li>
                <li>Camera: <%=products.get(i).camera%></li>
            </ul>
            </li>
            <li class="pull-right top">   <ul class="list-unstyled">
                <li><h3><%=products.get(i).cost%> rub.</h3></li>
                <li>
                    <a href="/product_detail?id=<%=products.get(i).productId%>" class="btn btn-block">More</a>
                    <a href="/remove_from_cart?id=<%=i%>" class="btn btn-block">Delete</a>
                </li>
            </ul>
            </li>
        </ul>
    </li>
<%}%>
</ul>

    <h4 class="text-center">Total cost: <%=totalCount%></h4>
    <a href="/checkout?cost=<%=totalCount%>" class="btn btn-block pull-right">Buy</a>
    </div>
<%}else%><h1 class="page-header text-center">Cart Empty</h1>
</body>
</html>