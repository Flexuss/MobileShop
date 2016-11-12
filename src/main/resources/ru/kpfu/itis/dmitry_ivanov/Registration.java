package ru.kpfu.itis.dmitry_ivanov;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

    }
}
