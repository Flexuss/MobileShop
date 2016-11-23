package ru.kpfu.itis.dmitry_ivanov.entity;

import ru.kpfu.itis.dmitry_ivanov.database.Database;

/**
 * Created by Dmitry on 21.11.2016.
 */
public class Comment {

    Database db=new Database();

    public String user;
    public String text;
    public String id;

    public Comment(String id, String user, String text){
        this.user=user;
        this.text=text;
        this.id=id;
    }

    public Comment(){

    }

    public String getUserName(String id){
        return db.getUserName(id);
}
}
