<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 05.11.2016
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Привет <%=request.getParameter("login")%>
    <%=request.getParameter("password")%>
</body>
</html>
