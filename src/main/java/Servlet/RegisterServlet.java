package Servlet;

import Dao.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
@WebServlet(name="RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//设置返回请求的字体类型
        request.setCharacterEncoding("text/html;charset=utf-8");
        response.setContentType("text/html;charset=utf-8");

        String userid=request.getParameter("id").toString();//获取id
        String enterpassword=request.getParameter("password").toString();//获取密码
        String enterpassword2=request.getParameter("password2").toString();//确认密码
        String gender=request.getParameter("gender").toString();//获取性别

        if(!enterpassword.equals(enterpassword2))
        {
            //两次密码不一致
            return;
        }
        try {
            UserDaoImpl userDaoImpl =new UserDaoImpl();
            if(userDaoImpl.Register(userid,enterpassword,gender))
            {
                //注册成功
            }else {
                //该用户已经存在
            }
            userDaoImpl.closeconnection();
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
