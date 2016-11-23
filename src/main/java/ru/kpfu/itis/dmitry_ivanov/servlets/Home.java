package ru.kpfu.itis.dmitry_ivanov.servlets;

import ru.kpfu.itis.dmitry_ivanov.database.Database;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmitry on 13.11.2016.
 */
public class Home extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isLogedIn=false;
        boolean isAdmin=false;
        Database db =new Database();
        if (request.getSession().getAttribute("user") == null) {
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
        } else {
            isLogedIn=true;
            if(request.getSession().getAttribute("user").equals("admin")){
                isAdmin=true;
            }
        }
        if(isLogedIn){
            if(isAdmin) {
                request.getRequestDispatcher("/mainadmin.jsp").forward(request, response);
            }else request.getRequestDispatcher("/mainlogedin.jsp").forward(request, response);
        }else request.getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
