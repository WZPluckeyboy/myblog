package com.bit.blog.util;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class DBUtilTest {

    @Test
    public void testConnection(){
        Connection connection = DBUtil.getConnection();
//        System.out.println(connection);
        Assert.assertNotNull(connection);
    }

    @Test
    public void test(){
        System.out.println(new Integer[]{});
        System.out.println(new String[]{});

    }
}
