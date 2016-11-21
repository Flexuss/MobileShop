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
    Database db = new Database();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        if (db.iscorrect(login, password)) {
            if (remember != null) {
                Cookie cookie = new Cookie("user_data", login + "&" + password);
                cookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(cookie);
            }
            HttpSession session = request.getSession();
            session.setAttribute("user", login);
            response.sendRedirect("/products");
        } else response.sendRedirect("/login");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        boolean isLogedIn = false;
        boolean isAdmin = false;
        if (request.getSession().getAttribute("user") == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("user_data")) {
                        Database db = new Database();
                        String[] strings = cookie.getValue().split("&");
                        if (db.iscorrect(strings[0], strings[1])) {
                            request.getSession().setAttribute("user", strings[0]);
                            if (request.getSession().getAttribute("user").equals("admin")) {
                                isAdmin = true;
                            }
                            isLogedIn = true;
                        }
                    }
                }
            }
        } else {
            isLogedIn = true;
            if (request.getSession().getAttribute("user").equals("admin")) {
                isAdmin = true;
            }
        }
        if (isLogedIn) {
            if (isAdmin) {
                request.getRequestDispatcher("/mainadmin.jsp").forward(request, response);
            } else request.getRequestDispatcher("/mainlogedin.jsp").forward(request, response);

        } else request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
