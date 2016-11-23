package ru.kpfu.itis.dmitry_ivanov;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmitry on 21.11.2016.
 */
public class AddComment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Database db=new Database();
        if (request.getSession().getAttribute("user")!=null) {
            String id = request.getParameter("id");
            String user = (String) request.getSession().getAttribute("user");
            String text = request.getParameter("comment");
            Comment comment=new Comment(id, user, text);
            db.addComment(comment);
        }
        response.sendRedirect("/product_detail?id="+request.getParameter("id"));
    }
}
