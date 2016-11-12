package ru.kpfu.itis.dmitry_ivanov;

import javafx.scene.chart.PieChart;

import javax.jms.Session;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Dmitry on 03.11.2016.
 */
public class Login extends javax.servlet.http.HttpServlet {
        Database db=new Database();
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String login=request.getParameter("login");
        String password=request.getParameter("password");
        String remember= request.getParameter("remember");
        if(db.iscorrect(login, password)){
            if(remember!=null){
                Cookie cookie = new Cookie("user_data", login+"&"+password);
                cookie.setMaxAge(60*60*24*7);
                response.addCookie(cookie);
            }
            HttpSession session= request.getSession();
            session.setAttribute("login", login);
            response.sendRedirect("/");
        }else response.sendRedirect("/login");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
