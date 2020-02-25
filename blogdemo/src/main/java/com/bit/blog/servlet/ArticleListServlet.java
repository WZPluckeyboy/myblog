package com.bit.blog.servlet;

import com.bit.blog.entity.Article;
import com.bit.blog.exception.ParameterException;
import com.bit.blog.util.DBUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArticleListServlet extends BaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Article> articles = new ArrayList<>();
        // 处理前端请求数据
        String sid = req.getParameter("id");
        Integer id = null;
        try {
            id = Integer.parseInt(sid);
        }catch (Exception e){
            throw new ParameterException("id错误（"+sid+")");
        }


        // 处理业务及数据库操作
        try {
            conn = DBUtil.getConnection();
            String sql = "select a.id,a.title,a.content,a.create_time " +
                    "from article a join user u " +
                    "on a.user_id = u.id where u.id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs       = ps.executeQuery();
            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setCreateTime(rs.getTimestamp("create_time"));
                articles.add(article);
            }
            System.out.println(articles);
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        return articles;
    }
}
