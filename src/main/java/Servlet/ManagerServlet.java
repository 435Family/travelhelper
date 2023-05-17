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
    ResultInfo info=new ResultInfo();//响应数据
    public void Getuser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取数据
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

    public void Getalluser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
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
    public void DeleteUser(HttpServletRequest request, HttpServletResponse response)throws Exception{
        //1.获取数据
        request.setCharacterEncoding("utf-8");//设置返回请求的字体类型
        request.setCharacterEncoding("text/html;charset=utf-8");
        String userid=request.getParameter("userid");//获取要查询的id
        //2.删除
        try {
            mangerDaoimpl.deleteUser(userid);//删除
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
