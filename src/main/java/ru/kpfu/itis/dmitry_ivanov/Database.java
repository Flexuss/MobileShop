package ru.kpfu.itis.dmitry_ivanov;

import java.sql.*;

/**
 * Created by Dmitry on 08.11.2016.
 */
public class Database {
    private final String driver = "org.postgresql.Driver";
    private final String url="jdbc:postgresql://localhost:5432/postgres";
    private final String user="postgres";
    private final String password="3255690";

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

    public void disconnect(){
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String login, String password, String mail){
        String query = "insert into users(login, password, mail) values('"+login+"', '"+password+"', '"+mail+"');";
        connect();
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    public boolean checkuser(String login){
        String query= "select count(*) from users where login = '"+login+"';";
        connect();
        try {
           resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                if(resultSet.getInt(1)==0) {
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

    public boolean iscorrect(String login, String password){
        String query= "select count(*) from users where login='"+login+"' and password='"+password+"';";
        connect();
        try {
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                if(resultSet.getInt(1)==1) {
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

}
