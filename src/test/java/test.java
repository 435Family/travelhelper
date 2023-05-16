import Dao.MangerDaoImpl;
import Data.Userdata;
import org.junit.jupiter.api.Test;
import service.UserServiceImpl;

import java.util.ArrayList;

public class test {
    @Test
     public void maintest() throws Exception{

        //da.Register("21221212","21","nan");
        //ArrayList<Userdata> usedata=da.getUser("22");
        //for(Userdata  each:usedata)
        //{
           // System.out.println(each.getUserid());
        //}
        Userdata user=new Userdata(1,"21212","21","nan");
        UserServiceImpl userService=new UserServiceImpl();
        if(userService.Register(user)){
            System.out.println("注册成功");
        }else {
            System.out.println("登陆失败");
        }


    }
}
