package com.bit.blog.servlet;

import com.bit.blog.entity.Article;
import com.bit.blog.exception.BusinessException;
import com.bit.blog.util.DBUtil;
import com.bit.blog.util.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ArticleAddServlet extends BaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        // 处理前端请求数据{"userAccout":"stu",...}
        // application/json数据，需要使用request.getInputStream来获取
//        String userAccount = req.getParameter("userAccout");
//        String title = req.getParameter("title");
//        String content = req.getParameter("content");
        // 获取JSON类型的请求数据
        Article article = JSONUtil.get(req, Article.class);

        // 处理业务及数据库操作
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into article(title, content, user_id) " +
                    "select ?, ?, user.id, now() from user " +
                    "where user.name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, article.getTitle());
            ps.setString(2, article.getContent());
            ps.setString(3, article.getUserAccout());
            int r = ps.executeUpdate();
            if(r > 0){
//                return null;
                return r;
            }else{
                throw new BusinessException("没有该用户"+article.getUserAccout());
            }
        }finally {
            DBUtil.close(conn,ps,null);
        }
    }
}
