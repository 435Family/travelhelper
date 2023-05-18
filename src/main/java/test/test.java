package test;

import Dao.ManagerDao;
import Dao.ManagerDaoImpl;
import Dao.UserDao;
import Dao.UserDaoImpl;
import Data.ResultInfo;
import Data.Userdata;
import org.junit.jupiter.api.Test;
import service.ManagerService;
import service.ManagerServiceImpl;
import service.UserService;
import service.UserServiceImpl;

import java.util.ArrayList;


public class test {

    @Test
    public void test()throws Exception{
        Userdata userdata=new Userdata();
        userdata.setUserid("212121");
        userdata.setEnterpassword("212121232");
        ArrayList<Userdata>list=new ArrayList<>();
        UserService userService=new UserServiceImpl();
        ManagerService managerService=new ManagerServiceImpl();
        ResultInfo info=new ResultInfo();

        list=managerService.getLikelyUser("2122");
        for(Userdata user:list)
        {
            System.out.println(user.getUserid()+"\n");
        }



    }
}
