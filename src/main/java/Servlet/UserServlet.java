package Servlet;

import Data.ResultInfo;
import Data.Userdata;
import com.fasterxml.jackson.databind.ObjectMapper;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{

    protected void Regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        //1.获取数据
        request.setCharacterEncoding("utf-8");//设置返回请求的字体类型
        request.setCharacterEncoding("text/html;charset=utf-8");
        String userid=request.getParameter("userid").toString();//获取userid
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
    protected void Loginin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1.获取数据
        request.setCharacterEncoding("utf-8");//设置返回请求的字体类型
        request.setCharacterEncoding("text/html;charset=utf-8");
        String userid=request.getParameter("userid").toString();//获取userid
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
}

