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
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <title>Registration</title>

    <script>
        function checkLogin() {
            var field = document.getElementById("login");
            var regExp = new RegExp("^([a-z]|[A-Z]|[0-9]|_|-){0,15}$");
            if (!regExp.test(field.value)) {
                field.classList.remove("alert-success");
                field.classList.add("alert-danger");
                return false;
            } else {
                field.classList.remove("alert-danger");
                field.classList.add("alert-success");
                return true;
            }
        }
        function checkConfirm() {
            var pass = document.getElementById("password");
            var confirm = document.getElementById("confirm");
            if(pass.value===confirm.value){
                confirm.classList.remove("alert-danger");
                confirm.classList.add("alert-success");
                return true;
            }else{
                confirm.classList.remove("alert-success");
                confirm.classList.add("alert-danger");
                return false;
            }
        }
        function checkPassword() {
            var field = document.getElementById("password");
            var regExp = new RegExp("^([a-z]|[A-Z]|[0-9]|_|-){0,15}$");
            if (!regExp.test(field.value)) {
                field.classList.remove("alert-success");
                field.classList.add("alert-danger");
                return false;
            } else {
                field.classList.remove("alert-danger");
                field.classList.add("alert-success");
                return true;
            }
        }
        function checkMail() {
            var field = document.getElementById("mail");
            var regExp = new RegExp("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            if (!regExp.test(field.value)) {
                field.classList.remove("alert-success");
                field.classList.add("alert-danger");
                return false;
            } else {
                field.classList.remove("alert-danger");
                field.classList.add("alert-success");
                return true;
            }
        }
        function checkFields() {
            if(checkPassword()&&checkMail()&&checkConfirm()&&checkLogin()){
                document.forms[0].submit()
            }
        }
    </script>
</head>
<body>
<% if (request.getSession().getAttribute("user") != null) {
    response.sendRedirect("/products");
}%>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <a class="navbar-brand">MobileShop</a>
        <div class="nav pull-right">
            <ul class="nav navbar-nav">
                <li><a href="/products" class="btn btn-link">Products</a></li>
                <li><a href="/login" class="btn btn-link">Login</a></li>
                <li class="active"><a href="/registration" class="btn btn-link">Registration</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <form class="form-group" role="form" action="registration" method="post">
        <h2 class="header-panel text-center">Registration</h2>
        <% if ("true".equals(request.getParameter("user_exist"))){%>
        <h5 id="error" class="text-center">Login already exist</h5><%}%>
        <input id="login" type="text" class="form-control" placeholder="Login" name="login" oninput="checkLogin()" required autofocus
               >
        <input id="password" type="password" class="form-control" placeholder="Password" name="password" oninput="checkPassword()" required>
        <input  id="confirm" type="password" class="form-control" placeholder="Confirm Password" name="confirmPassword" oninput="checkConfirm()" required>
        <input id="mail" type="text" class="form-control" placeholder="E-mail" oninput="checkMail()" name="e-mail" required>
        <button class="btn btn-block" onclick="checkFields()">Registration</button>
    </form>
</div>
</body>
</html>
