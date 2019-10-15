package com.github.feifuzeng.middleware.mybatis.jdbc;

/**
 * @author feifz
 * @version 1.0.0
 * @Description TODO
 * @createTime 2019年08月27日 20:42:00
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Fant.J.
 * 2018/3/3 13:35
 */
public class PreparedStatementTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/db_test?useSSL=false";
        String user = "root";
        String password = "root";

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            String sql = "update user set username=? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, "Fant.J");
            preparedStatement.setInt(2, 1);

            int result = preparedStatement.executeUpdate();
            preparedStatement.setString(1, "Fant.J reUseTest");
            preparedStatement.setInt(2, 2);
            preparedStatement.executeUpdate();


            String sql2 = "select * from user";

            ResultSet resultSet = preparedStatement.executeQuery(sql2);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String birthday = resultSet.getString("name");
                String sex = resultSet.getString("dept");
                String address = resultSet.getString("password");

                System.out.println("  " + username + " " + birthday + " " + sex
                        + " " + address);
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

