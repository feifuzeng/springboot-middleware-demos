package com.github.feifuzeng.middleware.mybatis.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author feifz
 * @version 1.0.0
 * @Description PreparedStatement简单demo
 * @createTime 2019年08月29日 20:21:00
 */
public class PrepareStatementSimpleDemo {

    public static void main(String[] args) throws Exception {

        /** 1. init PreparedStatement*/
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/db_test?useSSL=false";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, user, password);
        String sql = "update user set username=? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        /** 2. prepare param*/
        preparedStatement.setString(1, "feifz");
        preparedStatement.setInt(2, 2);

        /** 3. execute update*/
        int result = preparedStatement.executeUpdate();
        System.out.printf("更新记录数："+result+"\n");

        /** 4. execute select*/

        String sql2 = "select * from user";
        ResultSet resultSet = preparedStatement.executeQuery(sql2);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String dept = resultSet.getString("dept");
            System.out.println("id:"+id +"username->"+ username + ",dept-> " + dept );
        }


    }
}
