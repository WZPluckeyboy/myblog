package com.bit.blog.servlet;

import com.bit.blog.exception.BusinessException;
import com.bit.blog.exception.ParameterException;
import com.bit.blog.util.DBUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/articleDelete")
public class ArticleDeleteServlet extends BaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        // ids=9,
        String   ids     = req.getParameter("ids");//->int[]
        int[]    intIds = null;
        try {
            String[] idArray = ids.split(",");
            intIds  = new int[idArray.length];
            for (int i = 0; i < idArray.length; i++) {
                intIds[i] = Integer.parseInt(idArray[i]);
            }
        }catch (Exception e){
            throw new ParameterException("请求参数错误ids="+ids);
        }

        Connection conn = null;
        PreparedStatement ps = null;
        // 处理业务及数据库操作
        try {
            conn = DBUtil.getConnection();
            StringBuilder sql = new StringBuilder(
                    "delete from article where id in(");
            for(int i = 0; i<intIds.length; i++){
                if(i==0){
                    sql.append("?");
                }else{
                    sql.append(",?");
                }
            }
            sql.append(")");
            System.out.println("sql="+sql);

            ps = conn.prepareStatement(sql.toString());

            for(int i = 0; i<intIds.length; i++){
                ps.setInt(i+1, intIds[i]);
            }

            int r = ps.executeUpdate();
            if(r > 0){
//                return null;
                return r;
            }else{
                throw new BusinessException("没有该用户");
            }
        }finally {
            DBUtil.close(conn,ps,null);
        }
    }
}
