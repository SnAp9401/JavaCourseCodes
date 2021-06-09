package com.esiz.week5.mission10;

import java.sql.*;

public class Jdbc {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wozaiguomaludeDB", "root", "123456");
            boolean autoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("");
            statement.execute("delete from t_base_log ");
            connection.commit();
            connection.setAutoCommit(autoCommit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
