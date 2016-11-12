<%@ page import="java.sql.Connection" %>
<%@ page import="ru.kpfu.itis.dmitry_ivanov.Database" %>
<html>
<head>
    <title>
        Welcome to the Shop
    </title>
</head>
<body>
<form action="/login">
   Hello <% if(request.getSession().getAttribute("login")==null){%>
    <%  Cookie[] cookies=request.getCookies();
        if(cookies==null){%>
    Anonimus<br><%}else{
                for(Cookie cookie: cookies){
                if(cookie.getName().equals("user_data")){
                    Database db=new Database();
                String[] strings=cookie.getValue().split("&");
                if(db.iscorrect(strings[0],strings[1])){
                request.getSession().setAttribute("login", strings[0]);%>
     <%=request.getSession().getAttribute("login")%><br>
              <%  }
                }else%> Anonimus <br>
               <% }}
    }else{%> <%=request.getSession().getAttribute("login")%><br><%}%>
<button>
    Login
</button>
</form>
<form action="/registration">
    <button>
        Registration
    </button>
</form>
</body>
</html>
