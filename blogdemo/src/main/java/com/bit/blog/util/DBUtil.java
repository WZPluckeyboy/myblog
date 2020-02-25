package com.bit.blog.util;

import com.bit.blog.exception.SystemException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/blogdemo?characterEncoding=UTF-8";

    private static final String USER_NAME = "root";
    private static final String PASSWORD = "wzp961208";

    private static volatile DataSource DATASOURCE;

    private DBUtil(){

    }

    public static DataSource getDATASOURCE(){
        if(DATASOURCE == null){
//            synchronized (DATASOURCE.getClass()){
//            synchronized (DATASOURCE){
            synchronized (DBUtil.class){
                if(DATASOURCE == null){
                    DATASOURCE = new MysqlDataSource();
                    ((MysqlDataSource) DATASOURCE).setUrl(URL);
                    ((MysqlDataSource) DATASOURCE).setUser(USER_NAME);
                    ((MysqlDataSource) DATASOURCE).setPassword(PASSWORD);
                }
            }
        }
        return DATASOURCE;
    }

    public static Connection getConnection(){
        try {
            return getDATASOURCE().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Connection connection, PreparedStatement
                             preparedStatement, ResultSet resultSet){
        try {
            if(resultSet != null){
                resultSet.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SystemException("数据库错误");
        }

    }
}
