/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author KABIR BALOCH
 */

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
import java.sql.SQLException;
public class DBConnect {
    public static void main(String []args){}
    private static final String URL = "jdbc:mysql://localhost:3306/authentication_database?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Zaroori hai ki yeh BLANK ho, jaisa ki aapka test bata raha hai
    
    // Yeh method connection bana kar return karega
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Driver load karein
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // Connection bana kar return karein
        return DriverManager.getConnection(URL, USER, PASSWORD);
        
    }
}