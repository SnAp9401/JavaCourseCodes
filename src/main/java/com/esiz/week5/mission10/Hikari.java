package com.esiz.week5.mission10;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Hikari {

    public static void main(String[] args) {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/wozaiguomaludeDB");
        hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("123456");
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(hikariConfig);
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        try{

            conn = ds.getConnection();
            statement = conn.createStatement();

            rs = statement.executeQuery("select id from t_base_log");

            if (rs.next()){
                System.out.println(rs.getString("id"));
            }

            conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
}
