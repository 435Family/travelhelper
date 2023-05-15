package Servlet;

import Dao.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet (name="LoginServlet")
public class LoginServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//设置返回请求的字体类型
        request.setCharacterEncoding("text/html;charset=utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out;

        String userid=request.getParameter("id").toString();//获取用户名
        String enterpassword=request.getParameter("password").toString();//获取密码

        try {
            UserDaoImpl userDaoImpl =new UserDaoImpl();
            out=response.getWriter();
            if(userDaoImpl.Loginin(userid,enterpassword))
            {
                out.close();
                //成功登录
            }else {
                //登录失败，检查密码是否正确或者账户是否存在
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            //程序出错
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
