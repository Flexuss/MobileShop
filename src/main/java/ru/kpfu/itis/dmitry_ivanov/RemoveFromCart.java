package ru.kpfu.itis.dmitry_ivanov;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmitry on 20.11.2016.
 */
public class RemoveFromCart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String products= (String) request.getSession().getAttribute("cart");
        String[] strings=products.split("&");
        products="";
        for(int i=0;i<strings.length;i++){
            if(i!=Integer.parseInt(request.getParameter("id"))){
                if(products.equals("")){
                    products=strings[i];
                }else products=products+"&"+strings[i];
            }
        }
        if(products.length()==0){
            request.getSession().removeAttribute("cart");
        }else request.getSession().setAttribute("cart",products);
        response.sendRedirect("/cart");
    }
}
