package ru.kpfu.itis.dmitry_ivanov.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmitry on 13.11.2016.
 */
public class Logout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies=request.getCookies();
        for(int i=0;i<cookies.length;i++){
            if(cookies[i].getName().equals("user_data")){
                cookies[i].setMaxAge(0);
                response.addCookie(cookies[i]);
            }
        }
        if(request.getSession().getAttribute("user")!=null){
            request.getSession().removeAttribute("user");
        }
        response.sendRedirect("/products");
    }
}
