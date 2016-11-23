package ru.kpfu.itis.dmitry_ivanov;

import java.sql.*;
import java.util.ArrayList;
import java.util.IdentityHashMap;

/**
 * Created by Dmitry on 08.11.2016.
 */
public class Database {
    private final String driver = "org.postgresql.Driver";
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "3255690";

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public void connect() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String login, String password, String mail) {
        connect();
        try {
            statement = connection.prepareStatement("insert into users(login, password, mail) values(?,?,?);");
            statement.setString(1,login);
            statement.setString(2,password);
            statement.setString(3,mail);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    public boolean checkuser(String login) {
        connect();
        try {
            statement = connection.prepareStatement("select count(*) from users where login =?;");
            statement.setString(1,login);
            resultSet=statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(1) == 0) {
                    disconnect();
                    return true;
                }
            }
            disconnect();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean iscorrect(String login, String password) {
        connect();
        try {
            statement = connection.prepareStatement("select count(*) from users where login=? and password=?;");
            statement.setString(1,login);
            statement.setString(2,password);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(1) == 1) {
                    disconnect();
                    return true;
                }
            }
            disconnect();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int productsCount() {
        connect();
        int count = 0;
        try {
            statement = connection.prepareStatement("select count(*) from products");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            disconnect();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public ArrayList<Product> getProductList() {
        connect();
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            statement = connection.prepareStatement("select * from products");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getInt("product_id"), resultSet.getString("name"), resultSet.getString("operating_system"),
                        resultSet.getString("sim"), resultSet.getString("weight"), resultSet.getString("display_type"),
                        resultSet.getString("diagonal"), resultSet.getString("resolution"), resultSet.getString("camera"),
                        resultSet.getString("processor"), resultSet.getString("ram"), resultSet.getString("rom"),
                        resultSet.getString("video"), resultSet.getString("battery"), resultSet.getString("cost"), resultSet.getString("image"));
                products.add(product);
            }
            disconnect();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product getProduct(String id) {
        connect();
        Product product=new Product();
        try {
            statement = connection.prepareStatement("select * from products where product_id=?;");
            statement.setInt(1,Integer.parseInt(id));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                product = new Product(resultSet.getInt("product_id"), resultSet.getString("name"), resultSet.getString("operating_system"),
                        resultSet.getString("sim"), resultSet.getString("weight"), resultSet.getString("display_type"),
                        resultSet.getString("diagonal"), resultSet.getString("resolution"), resultSet.getString("camera"),
                        resultSet.getString("processor"), resultSet.getString("ram"), resultSet.getString("rom"),
                        resultSet.getString("video"), resultSet.getString("battery"), resultSet.getString("cost"), resultSet.getString("image"));
            }
            disconnect();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void addProduct(Product product){
        connect();
        try {
            statement = connection.prepareStatement("insert into products(name, processor,diagonal,display_type," +
                    " sim, ram, rom, resolution, video, battery, camera, cost, weight, image, operating_system) VALUES(?" +
                    ",?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            statement.setString(1,product.name);
            statement.setString(2,product.processor);
            statement.setString(3,product.diagonal);
            statement.setString(4,product.display);
            statement.setString(5,product.sim);
            statement.setString(6,product.ram);
            statement.setString(7,product.rom);
            statement.setString(8,product.resolution);
            statement.setString(9,product.video);
            statement.setString(10,product.battery);
            statement.setString(11,product.camera);
            statement.setString(12,product.cost);
            statement.setString(13,product.weight);
            statement.setString(14,product.image);
            statement.setString(15,product.os);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void EditProduct(Product product){
        connect();
        try {
            statement = connection.prepareStatement("insert into products( name, processor,diagonal,display_type," +
                    " sim, ram, rom, resolution, video, battery, camera, cost, weight, image, operating_system, product_id) VALUES(?" +
                    ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            statement.setInt(16,product.productId);
            statement.setString(1,product.name);
            statement.setString(2,product.processor);
            statement.setString(3,product.diagonal);
            statement.setString(4,product.display);
            statement.setString(5,product.sim);
            statement.setString(6,product.ram);
            statement.setString(7,product.rom);
            statement.setString(8,product.resolution);
            statement.setString(9,product.video);
            statement.setString(10,product.battery);
            statement.setString(11,product.camera);
            statement.setString(12,product.cost);
            statement.setString(13,product.weight);
            statement.setString(14,product.image);
            statement.setString(15,product.os);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(String id){
        connect();
        try {
            statement = connection.prepareStatement("delete from products where product_id=?;");
            statement.setInt(1,Integer.parseInt(id));
            statement.executeUpdate();
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> getCartProductList(String products){
        ArrayList<Product> productList=new ArrayList<Product>();
            String[] strings = products.split("&");
            for (int i = 0; i < strings.length; i++) {
                productList.add(getProduct(strings[i]));
            }
        return productList;
    }

    public ArrayList<Comment> getCommentList(String product){
        ArrayList<Comment> comments=new ArrayList<Comment>();
        connect();
        Comment comment=new Comment();
        try {
            statement = connection.prepareStatement("select * from comments where product_id=?");
            statement.setInt(1,Integer.parseInt(product));
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                comment=new Comment(resultSet.getString("product_id"),resultSet.getString("user_id"),resultSet.getString("text"));
                comments.add(comment);
            }
            resultSet.close();
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public int getUserId(String name){
        int user=0;
        connect();
        try {
            statement = connection.prepareStatement("select user_id from users where login=?;");
            statement.setString(1,name);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                user=resultSet.getInt(1);
            }
            resultSet.close();
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public String getUserName(String id){
        String user="";
        connect();
        try {
            statement = connection.prepareStatement("select login from users where user_id=?;");
            statement.setInt(1,Integer.parseInt(id));
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                user=resultSet.getString(1);
            }
            resultSet.close();
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void addComment(Comment comment){
        connect();
        try {
            statement = connection.prepareStatement("insert into comments values(?,?, ?);");
            statement.setInt(1,Integer.parseInt(comment.id));
            statement.setInt(2,Integer.parseInt(comment.user));
            statement.setString(3, comment.text);
            statement.executeUpdate();
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
