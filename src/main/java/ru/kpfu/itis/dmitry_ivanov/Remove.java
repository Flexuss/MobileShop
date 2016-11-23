package ru.kpfu.itis.dmitry_ivanov;

import javafx.scene.chart.PieChart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmitry on 20.11.2016.
 */
public class Remove extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/products");
        } else {
            if (request.getSession().getAttribute("user").equals("admin")) {
                Database db = new Database();
                db.remove(request.getParameter("id"));
                response.sendRedirect("/products");
            }else response.sendRedirect("/products");
        }

    }
}
