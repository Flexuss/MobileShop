package ru.kpfu.itis.dmitry_ivanov;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Dmitry on 08.11.2016.
 */
public class Database {
    private final String driver = "org.postgresql.Driver";
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "3255690";

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public void connect() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
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
        String query = "insert into users(login, password, mail) values('" + login + "', '" + password + "', '" + mail + "');";
        connect();
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    public boolean checkuser(String login) {
        String query = "select count(*) from users where login = '" + login + "';";
        connect();
        try {
            resultSet = statement.executeQuery(query);
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
        String query = "select count(*) from users where login='" + login + "' and password='" + password + "';";
        connect();
        try {
            resultSet = statement.executeQuery(query);
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
        String query = "select count(*) from products";
        connect();
        int count = 0;
        try {
            resultSet = statement.executeQuery(query);
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
        String query = "select * from products";
        connect();
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            resultSet = statement.executeQuery(query);
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
        String query = "select * from products where product_id=" + id;
        connect();
        Product product=new Product();
        try {
            resultSet = statement.executeQuery(query);
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

    public void addProduct(String quer, String value){
        String query = "insert into products("+quer+") VALUES("+value+");";
        connect();
        try {
            statement.executeUpdate(query);
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(String id){
        String query="delete from products where product_id="+id+";";
        connect();
        try {
            statement.execute(query);
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

}
