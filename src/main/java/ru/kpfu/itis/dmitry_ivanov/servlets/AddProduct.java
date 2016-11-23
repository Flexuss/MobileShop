package ru.kpfu.itis.dmitry_ivanov.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.kpfu.itis.dmitry_ivanov.database.Database;
import ru.kpfu.itis.dmitry_ivanov.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by Dmitry on 18.11.2016.
 */

public class AddProduct extends HttpServlet {
    private Random random = new Random();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Database db=new Database();
        HashMap<String, String> parameters = new HashMap<String, String>();
        String path="";
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 1024);
        File tempDir = (File) getServletContext().getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(tempDir);
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();

            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (!item.isFormField()) {
                    path=processUploadedFile(item);
                }else{
                    if(!item.getFieldName().equals("Add Product")){
                        parameters.put(item.getFieldName(), item.getString());
                }}
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        if(request.getParameter("id")!=null){
          Product product= new Product(Integer.parseInt(request.getParameter("id")),parameters.get("name"),parameters.get("operating_system"),parameters.get("sim"),parameters.get("weight"),
                    parameters.get("display_type"),parameters.get("diagonal"),parameters.get("resolution"),parameters.get("camera"),parameters.get("processor"),
                    parameters.get("ram"),parameters.get("rom"),parameters.get("video"),
                    parameters.get("battery"), parameters.get("cost"),path);
            db.remove(request.getParameter("id"));
            db.EditProduct(product);
        }else {
        Product product=new Product(-1,parameters.get("name"),parameters.get("operating_system"),parameters.get("sim"),parameters.get("weight"),
                parameters.get("display_type"),parameters.get("diagonal"),parameters.get("resolution"),parameters.get("camera"),parameters.get("processor"),
                parameters.get("ram"),parameters.get("rom"),parameters.get("video"),
                parameters.get("battery"), parameters.get("cost"),path);
            db.addProduct(product);
        }
        response.sendRedirect("/products");
    }

    private String processUploadedFile(FileItem item) throws Exception {
        File uploadetFile = null;
        String path;
        do {
            path = getServletContext().getRealPath("/images/" + random.nextInt());
            uploadetFile = new File(path);
        }while (uploadetFile.exists());
        uploadetFile.createNewFile();
        item.write(uploadetFile);
        path="/images/"+uploadetFile.getName();
        return  path;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/add_product.jsp").forward(request, response);
    }
}
