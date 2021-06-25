package com.esiz.week7;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlExecute {

    public static void main(String[] args) {
        func1();
        func2();
    }

    //全部提交
    public static void func1() {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/esizJDBC");
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
            long start = System.currentTimeMillis();
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            statement = conn.createStatement();
            for (int i = 0; i < 1000000; i++) {
                statement.addBatch("insert into customer_inf (customer_id, customer_name, identity_card_type, identity_card_no, mobile_phone, customer_email, " +
                        "gender, user_point, register_time, birthday, customer_level, user_money, modified_time)" +
                        "VALUES ("+i+", 1, 1, 111111111111, 188, 1, 1, 0, 20000101, 20200101, 1, 0, 20200101; ");

            }
            statement.executeBatch();
            conn.commit();
            System.out.println("耗时" + (System.currentTimeMillis() - start));
            conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


    //单次提交
    public static void func2() {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/esizJDBC");
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
            long start = System.currentTimeMillis();
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            statement = conn.createStatement();
            for (int i = 0; i < 1000000; i++) {
                statement.addBatch("insert into customer_inf (customer_id, customer_name, identity_card_type, identity_card_no, mobile_phone, customer_email, " +
                        "gender, user_point, register_time, birthday, customer_level, user_money, modified_time)" +
                        "VALUES ("+i+", 1, 1, 111111111111, 188, 1, 1, 0, 20000101, 20200101, 1, 0, 20200101; ");
                 statement.executeBatch();
            }
            statement.executeBatch();
            conn.commit();
            System.out.println("耗时" + (System.currentTimeMillis() - start));
            conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
