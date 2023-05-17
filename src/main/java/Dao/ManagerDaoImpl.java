package Dao;

import Data.Userdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ManagerDaoImpl implements ManagerDao {
    Connection conn;
    public ManagerDaoImpl() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/first?serverTimezone=GMT%2B8","root","admin");
    }

    @Override
    public Userdata getUser(String userid)throws Exception
    {
        //1.建立Userdata对象
        Userdata user=new Userdata();
        //2.建立数据库连接获取数据
        PreparedStatement prep=conn.prepareStatement("select *from person where userid is ?;");
        prep.setString(1,"%"+userid+"%");
        prep.execute();
        ResultSet resultSet= prep.executeQuery();
        //3.赋值并返回
        while(resultSet.next())
        {
            user.setNum(resultSet.getInt("num"));
            user.setUserid(resultSet.getString("userid")) ;
            user.setEnterpassword(resultSet.getString("enterpassword"));
            user.setGender(resultSet.getString("gender"));
        }
        return user;
    }

    @Override
    public ArrayList<Userdata> getAllUser()throws Exception{
        ArrayList<Userdata> userlist=new ArrayList<Userdata>();
        PreparedStatement prep=conn.prepareStatement("select * from person;");
        prep.execute();
        ResultSet resultSet= prep.executeQuery();

        while(resultSet.next())
        {
            int num=resultSet.getInt("num");
            String id= resultSet.getString("userid");
            String enterpassword= resultSet.getString("enterpassword");
            String gender = resultSet.getString("gender");
            userlist.add(new Userdata(num,id,enterpassword,gender));
        }
        return  userlist;
    }
    @Override
    public void deleteUser(String userid) throws Exception {
        PreparedStatement prep=conn.prepareStatement("delete from person where userid is ?;");
        prep.setString(1,"%"+userid+"%");
        prep.execute();
    }
}
