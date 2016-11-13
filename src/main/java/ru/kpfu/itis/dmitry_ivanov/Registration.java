package ru.kpfu.itis.dmitry_ivanov;

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
        String login=request.getParameter("Login");
        String pass=request.getParameter("Password");
        String cpass=request.getParameter("ConfirmPassword");
        String mail=request.getParameter("e-mail");
        if(db.checkuser(login)){
            if(pass.equals(cpass)) {
                db.addUser(login, pass, mail);
                response.sendRedirect("/login");
            }else response.sendRedirect("/registration");
        }else response.sendRedirect("/registration");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isLogedIn=false;
        if (request.getSession().getAttribute("user") == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("user_data")) {
                        Database db = new Database();
                        String[] strings = cookie.getValue().split("&");
                        if (db.iscorrect(strings[0], strings[1])) {
                            request.getSession().setAttribute("user", strings[0]);
                            isLogedIn=true;
                        }
                    }
                }
            }
        } else {
            isLogedIn=true;
        }
        if(isLogedIn){
            response.sendRedirect("/");
        }else request.getRequestDispatcher("/registration.jsp").forward(request, response);
    }
}
