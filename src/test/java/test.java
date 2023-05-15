import Dao.MangerDaoimpl;
import Dao.UserDaoImpl;
import Data.Userdata;
import org.junit.jupiter.api.Test;

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
        try {
            MangerDaoimpl mangerDaoimpl=new MangerDaoimpl();
            ArrayList<Userdata> userlist=mangerDaoimpl.getAllUser();
            for(Userdata user :userlist)
            {
                int num=user.getNum();//获得
                String gender=user.getGender();
                String userid=user.getUserid();
                System.out.println(num+" "+gender+" "+userid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
