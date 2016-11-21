package ru.kpfu.itis.dmitry_ivanov;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmitry on 20.11.2016.
 */
@WebServlet(name = "AddToCart")
public class AddToCart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("cart")==null){
            request.getSession().setAttribute("cart",request.getParameter("product"));
        }else
        request.getSession().setAttribute("cart",request.getSession().getAttribute("cart")+"&"+request.getParameter("product"));
        response.sendRedirect("/products");
    }
}
