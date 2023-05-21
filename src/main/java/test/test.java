package test;

import Dao.ManagerDao;
import Dao.ManagerDaoImpl;
import Dao.UserDao;
import Dao.UserDaoImpl;
import Data.ResultInfo;
import Data.Userdata;
import org.junit.jupiter.api.Test;
import service.*;

import java.util.ArrayList;


public class test {

    @Test
    public void test()throws Exception{
        CategoryService service=new CategoryServiceImpl();
        ResultInfo info=new ResultInfo();
        String[]list1={"旅游码头","国家与森林公园","丁汝昌寓所","甲午海战纪念馆"};
        ArrayList<String> list=service.getShortestRoad(list1);
        for (String each:list)
        {
            System.out.println(each);
        }



    }
}
