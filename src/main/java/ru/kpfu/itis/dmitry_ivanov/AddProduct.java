package ru.kpfu.itis.dmitry_ivanov;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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
//        String productName=request.getParameter("product_name");
//        String operatingSystem =request.getParameter("os");
//        String processor=request.getParameter("processor");
//        String ram= request.getParameter("ram");
//        String rom= request.getParameter("rom");
//        String display= request.getParameter("display");
//        String diagonal= request.getParameter("diagonal");
//        String resolution= request.getParameter("resolution");
//        String battery= request.getParameter("battery");
//        String camera=request.getParameter("camera");
//        String sim= request.getParameter("sim");
//        String weigth= request.getParameter("wigth");
//        String cost =request.getParameter("cost");
//        String image="/images/"+request.getParameter("product_name");
//        String video=request.getParameter("video");
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
                    processUploadedFile(item);
                }else{
                    if(!item.getFieldName().equals("Add Product")){
                    getParameters(item);
                }}
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        db.addProduct(query.substring(1), value.substring(1));
        query="";
        value="";
        response.sendRedirect("/products");
    }

    String query="";
    String value="";

    private void getParameters(FileItem item){
        query=query+", "+item.getFieldName();
        value=value+", '"+item.getString()+"'";
    }

    private void processUploadedFile(FileItem item) throws Exception {
        File uploadetFile = null;
        String path;
        do {
            path = getServletContext().getRealPath("/images/" + random.nextInt());
            uploadetFile = new File(path);
        }while (uploadetFile.exists());
        query=query+", image";
        value=value+", 'images/"+uploadetFile.getName()+"'";
        uploadetFile.createNewFile();
        item.write(uploadetFile);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/add_product.jsp").forward(request, response);
    }
}
