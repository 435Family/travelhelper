package Servlet;

import Data.ResultInfo;
import Data.Userdata;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet (name="LoginServlet")

public class LoginServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取数据
        request.setCharacterEncoding("utf-8");//设置返回请求的字体类型
        request.setCharacterEncoding("text/html;charset=utf-8");
        String userid=request.getParameter("id").toString();//获取id
        String enterpassword=request.getParameter("password").toString();//获取密码

        //2.封装对象
        Userdata user=new Userdata();
        user.setEnterpassword(enterpassword);
        user.setUserid(userid);

        //3.调用service完成登录
        try {
            UserServiceImpl service=new UserServiceImpl();
            boolean flag=service.Loginin(user);
            ResultInfo info=new ResultInfo();
            if(flag)
            {   //成功登录
                info.setFlag(true);
            }else {
                info.setFlag(false);
                info.setErrorMsh("登录失败，检查密码是否正确或者账户是否存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //程序出错
        }
    }
    ObjectMapper mapper=new ObjectMapper();



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
