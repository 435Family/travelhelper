package Servlet;

import Dao.UserDaoImpl;
import Data.ResultInfo;
import Data.Userdata;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        //1.获取数据
        request.setCharacterEncoding("utf-8");//设置返回请求的字体类型
        request.setCharacterEncoding("text/html;charset=utf-8");
        String userid=request.getParameter("id").toString();//获取id
        String enterpassword=request.getParameter("password").toString();//获取密码
        String gender=request.getParameter("gender").toString();//获取性别
        //2.封装对象
        Userdata user=new Userdata();
        user.setEnterpassword(enterpassword);
        user.setUserid(userid);
        user.setGender(gender);
        //3.调用service注册
        try {
            UserServiceImpl service=new UserServiceImpl();
            boolean flag=service.Register(user);
            ResultInfo info=new ResultInfo();

            if(flag)
            {
                //注册成功
                info.setFlag(true);

            }else {
                //该用户已经存在
                info.setFlag(false);
                info.setErrorMsh("登录失败，该账户名称已经存在");
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
