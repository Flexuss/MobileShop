<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 13.11.2016
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ru.kpfu.itis.dmitry_ivanov.Database" %>
<%@ page import="ru.kpfu.itis.dmitry_ivanov.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
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
            <li><a class="navbar-brand" href="#"><%=request.getSession().getAttribute("user")%></a> </li>
            <li class="active"><a href="/products" class="btn btn-link">Products</a></li>
            <li><a href="/cart" class="btn btn-link">Cart</a></li>
            <li><a href="/logout" class="btn btn-link">Logout</a> </li>
        </ul>
    </div>
    </div>
</nav>

<div class="container">
    <ul class="list-group">
        <% Database db = new Database();
            ArrayList<Product> products = db.getProductList();
            int count = db.productsCount();
            int pagcount = count / 10;
            if (count % 10 > 0) {
                pagcount++;
            }
        %>
        <% if (request.getParameter("page") == null) {
            response.sendRedirect("/products?page=1");
        } else {
            int pagenum = Integer.parseInt(request.getParameter("page"));

            for (int i = count-(10*(pagenum-1))-1; (i >= count-(10*pagenum))&&(i>=0); i--) {%>
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
                        <button class="btn btn-block" onclick="addToCart(<%=products.get(i).productId%>)">Add To Cart</button>
                    </li>
                </ul>
                </li>
            </ul>
        </li>
        <%
            }
        %>

    </ul>
    <ul class="pagination">
        <%if(pagcount>1){
            for (Integer i = 1; i <= pagcount; i++) {
                String num = i.toString();
                if (request.getParameter("page").equals(num)) {%>
        <li class="active"><a href="/products?page=<%=i%>"><%=i%></a></li>
        <%} else {%>
        <li><a href="/products?page=<%=i%>"><%=i%></a></li>
        <%}
        }
        }
        %>
    </ul>
    <%}%>
</div>
</body>
</html>
