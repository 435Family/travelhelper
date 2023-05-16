package Servlet;

import Dao.MangerDaoImpl;
import Data.Userdata;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name="GetallUserServlet")

public class GetallUserServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        request.setCharacterEncoding("utf-8");//设置返回请求的字体类型
        request.setCharacterEncoding("text/html;charset=utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out;


        try {
            out=response.getWriter();
            MangerDaoImpl mangerDaoimpl=new MangerDaoImpl();
            ArrayList<Userdata> userlist=mangerDaoimpl.getAllUser();
            for(Userdata user :userlist)
            {
                int num=user.getNum();//获得序号
                String gender=user.getGender();//获得性别
                String userid=user.getUserid();//获得id
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

