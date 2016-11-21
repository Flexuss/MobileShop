<%@ page import="ru.kpfu.itis.dmitry_ivanov.Database" %>
<%@ page import="ru.kpfu.itis.dmitry_ivanov.Product" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>
        MobileShop
    </title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <a class="navbar-brand">MobileShop</a>
        <div class="nav pull-right">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/products" class="btn btn-link">Products</a></li>
                <li><a href="/login" class="btn btn-link">Login</a> </li>
                <li><a href="/registration" class="btn btn-link">Registration</a> </li>
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
                int thispage=Integer.parseInt(request.getParameter("page"));
                if(thispage-4==1){
                    %><li><a href="/products?page=1">1</a></li><%
            for (Integer i = thispage-3; (i<= thispage+3)&&(i<=pagcount); i++) {
            if (thispage==i) {%>
            <li class="active "><a href="/products?page=<%=i%>"><%=i%></a></li>
            <%} else {%>
            <li><a href="/products?page=<%=i%>"><%=i%></a></li>
            <%}}}
            if((thispage-4)>1){%>
                    <li><a href="/products?page=1">1</a> </li>
                    <li><a href="#">...</a></li> <%
            for (Integer i = thispage-3; (i<= thispage+3)&&(i<=pagcount); i++) {
                if (thispage==i) {%>
            <li class="active "><a href="/products?page=<%=i%>"><%=i%></a></li>
            <%} else {%>
            <li><a href="/products?page=<%=i%>"><%=i%></a></li>
            <%}}}
                if(thispage-4<1){
            for (Integer i = 1; (i<= thispage+3)&&(i<=pagcount); i++) {
                if (thispage==i) {%>
            <li class="active "><a href="/products?page=<%=i%>"><%=i%></a></li>
            <%} else {%>
            <li><a href="/products?page=<%=i%>"><%=i%></a></li>
            <%}
                }

                    }
                if(thispage+4<pagcount){
            %><li><a href="#">...</a></li>

            <li><a href="/products?page=<%=pagcount%>"><%=pagcount%></a></li><%
                }
                }
            %>
        </ul>
    <%}%>
</div>
</body>
</html>
