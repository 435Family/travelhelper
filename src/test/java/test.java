import Dao.UserDaoImpl;
import org.junit.jupiter.api.Test;

public class test {
    @Test
     public void maintest() throws Exception{
        UserDaoImpl da=new UserDaoImpl();
        //da.Register("21221212","21","nan");
        //ArrayList<Userdata> usedata=da.getUser("22");
        //for(Userdata  each:usedata)
        //{
           // System.out.println(each.getUserid());
        //}
        if(da.Register("212121212121","21","nan"))
        {
            System.out.println("ok");
        }else {
                System.out.println("该用户不存在");
        }
        da.closeconnection();
    }
}
