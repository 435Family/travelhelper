package Servlet;

import Data.Userdata;
import service.ManagerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/manager/*")
public class ManagerServlet extends BaseServlet{
    protected void Getuser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取数据
        request.setCharacterEncoding("utf-8");//设置返回请求的字体类型
        request.setCharacterEncoding("text/html;charset=utf-8");
        String Userid=request.getParameter("userid");//获取要查询的id

        try {
            ManagerServiceImpl mangerDaoimpl=new ManagerServiceImpl();
            ArrayList<Userdata> userlist=mangerDaoimpl.getUser(Userid);//获取动态数组
            for(Userdata user :userlist)
            {
                int num=user.getNum();//获得序号
                String gender=user.getGender();//获得性别
                String userid=user.getUserid();//获得id
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void Getalluser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ManagerServiceImpl mangerDaoimpl=new ManagerServiceImpl();
            ArrayList<Userdata> userlist=mangerDaoimpl.getAllUser();
            for(Userdata user :userlist)//获取动态数组里面的所有数据
            {
                int num=user.getNum();//获得序号
                String gender=user.getGender();//获得性别
                String userid=user.getUserid();//获得id
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
