package com.github.feifuzeng.middleware.mybatis.jdbc;

import com.github.feifuzeng.middleware.mybatis.jdbc.orm.sql.Replace;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author feifz
 * @version 1.0.0
 * @Description jdbc-PreparedStatement 操作db
 * @createTime 2019年08月26日 10:13:00
 */


public class Mypreparedstatement {
    public static void main(String[] args) throws Exception {
        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?useSSL=false", "root", "root");
        //3.获得预处理对象
        Replace replace = new Replace();
        replace.setTableName("user");
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("username", "李四啊啊啊啊");
        String replaceSql = replace.toStatementString();
        replaceSql = "replace into user (id,name) values (?,?) ";
        String sql = "update sort set sname=? where sid=?";
        PreparedStatement stat = con.prepareStatement(replaceSql);
        //4.SQL语句占位符设置实际参数
        stat.setInt(1, 1);//索引参数1代表着sql中的第一个？号，也就是我需要将条件sid所对应的sname数据更新为“儿童玩具测试”
        stat.setString(2, "aaa1");//索引参数2代表着sql中的第二个？号，也就是条件是sid为3
        stat.addBatch();
        stat.setInt(1, 2);//索引参数1代表着sql中的第一个？号，也就是我需要将条件sid所对应的sname数据更新为“儿童玩具测试”
        stat.setString(2, "bbb1");//索引参数2代表着sql中的第二个？号，也就是条件是sid为3
        stat.addBatch();
        //5.执行SQL语句
//        int[] line = stat.executeBatch();
        int executeBatchRowCount = Arrays.stream(stat.executeBatch()).sum();
        System.out.println("更新记录数" + executeBatchRowCount);
        //6.释放资源
        stat.close();
        con.close();
    }


}