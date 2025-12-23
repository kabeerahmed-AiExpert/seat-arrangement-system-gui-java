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
import java.sql.SQLException;

public class DBConectForStudants {
// if you have a password put it here

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        // ⚠️ Make sure this URL matches your XAMPP setup
        // 3306 = port, studentsdatabase = your database name
        String url = "jdbc:mysql://localhost:3306/students_database?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "";

        return DriverManager.getConnection(url, user, pass);
    }

}
