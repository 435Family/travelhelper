package servlet;

import Data.ResultInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import service.CategoryService;
import service.CategoryServiceImpl;
import service.ManagerServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet{
    private CategoryServiceImpl categoryService;
    {
        try {
            categoryService = new CategoryServiceImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getCategory (HttpServletRequest request, HttpServletResponse response)throws Exception {

        //1.获取信息
        ResultInfo info=new ResultInfo();//响应数据
        request.setCharacterEncoding("utf-8");//设置返回请求的字体类型
        request.setCharacterEncoding("text/html;charset=utf-8");
        String Category=request.getParameter("category");
        //2.查询
        try {
            ArrayList<String>list=categoryService.getCategory(Category);
            if(list==null)
            {
                info.setFlag(false);
                info.setErrorMsh("未找到景点");
            }else {
                info.setFlag(true);
                info.setCategorylist(list);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }
    public void getShortestRoad(HttpServletRequest request, HttpServletResponse response)throws Exception{
        //1.获取信息
        ResultInfo info=new ResultInfo();//响应数据
        request.setCharacterEncoding("utf-8");//设置返回请求的字体类型
        request.setCharacterEncoding("text/html;charset=utf-8");
        String[] categories = request.getParameterValues("category");

        //2.查询
        try {
            ArrayList<String>list=categoryService.getShortestRoad(categories);
            if(list==null)
            {
                info.setFlag(false);
                info.setErrorMsh("未选择景点");
            }else {
                info.setFlag(true);
                info.setCategorylist(list);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }
}
