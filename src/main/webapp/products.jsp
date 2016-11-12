<%@ page import="ru.kpfu.itis.dmitry_ivanov.Database" %><%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 11.11.2016
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
<% if(request.getAttribute("page")==null){%>
    <% for(int i=1;i<11;i++){%>
        <li>Элемент №<%=i%></li>
    <%} %>
    <% }else
        if(request.getAttribute("page").equals("2")){
        for(int i=11;i<21;i++){%>
    <li>Элемент №<%=i%></li>
        <%}}%>
</ul>
<a onclick='this.setAttribute()' href="">2</a>
</body>
</html>
