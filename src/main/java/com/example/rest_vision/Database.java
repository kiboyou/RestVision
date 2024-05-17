package com.example.rest_vision;

import java.sql.Connection;
import java.sql.DriverManager;

import static java.lang.Class.forName;

public class Database {
    public static Connection connectDd(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/rest_vision","root","");
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
