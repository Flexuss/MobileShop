package ru.kpfu.itis.dmitry_ivanov.servlets;

import ru.kpfu.itis.dmitry_ivanov.database.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmitry on 09.11.2016.
 */
@WebServlet(name = "SRegistration")
public class Registration extends HttpServlet {

    Database db=new Database();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        String cpass = request.getParameter("confirmPassword");
        String mail = request.getParameter("e-mail");
        if (db.checkuser(login)) {
            if (pass.equals(cpass)) {
                db.addUser(login, pass, mail);
                response.sendRedirect("/login");
            } else response.sendRedirect("/registration");
        } else response.sendRedirect("/registration?user_exist=true");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isLogedIn=false;
        boolean isAdmin=false;
        if (request.getSession().getAttribute("user") == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("user_data")) {
                        Database db = new Database();
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
        }else request.getRequestDispatcher("/registration.jsp").forward(request, response);
    }
}
