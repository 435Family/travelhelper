package servlet;

import Data.ResultInfo;
import Data.Userdata;
import com.fasterxml.jackson.databind.ObjectMapper;
import service.ManagerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/manager/*")
public class ManagerServlet extends BaseServlet{
    private ManagerServiceImpl mangerDaoimpl;
    {
        try {
            mangerDaoimpl = new ManagerServiceImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //
    //获取所有对象
    //
    public void Getalluser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.建立返回对象
        ResultInfo info=new ResultInfo();//响应数据
        try {
            //2.获取数据
            info.setUserlist(mangerDaoimpl.getAllUser());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }


    //
    //寻找对象(精准)
    //
    public void Getuser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取数据
        ResultInfo info=new ResultInfo();//响应数据
        request.setCharacterEncoding("utf-8");//设置返回请求的字体类型
        request.setCharacterEncoding("text/html;charset=utf-8");
        String userid=request.getParameter("userid");//获取要查询的id

        //2.查询
        try {
            Userdata user=mangerDaoimpl.getUser(userid);//获取返回
            if(user==null)
            {
                info.setFlag(false);
                info.setErrorMsh("未找到该用户");
            }else {
                info.setFlag(true);
                info.setUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }


    //
    //寻找对象（模糊）
    //
    public void GetLikelyUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.获取数据
        ResultInfo info=new ResultInfo();//响应数据
        request.setCharacterEncoding("utf-8");//设置返回请求的字体类型
        request.setCharacterEncoding("text/html;charset=utf-8");
        String userid=request.getParameter("userid");//获取要查询的id
        //2.寻找对象
        try {
            info.setUserlist(mangerDaoimpl.getAllUser());
            if(info.getUserlist()==null)
            {
                info.setFlag(false);
                info.setErrorMsh("未找到相似用户");
            }else {
                info.setFlag(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }


    //
    //删除对象
    //
    public void DeleteUser(HttpServletRequest request, HttpServletResponse response)throws Exception{
        //1.获取数据
        ResultInfo info=new ResultInfo();//响应数据
        request.setCharacterEncoding("utf-8");//设置返回请求的字体类型
        request.setCharacterEncoding("text/html;charset=utf-8");
        String userid=request.getParameter("userid");//获取要查询的id
        //2.删除
        try {
            mangerDaoimpl.deleteUser(userid);//删除
            info.setFlag(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }

}
