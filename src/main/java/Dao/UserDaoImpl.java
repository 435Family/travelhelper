package Dao;

import Data.Userdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao{

    Connection conn=null;
    public UserDaoImpl() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/first?serverTimezone=GMT%2B8","root","admin");
    }

    public void  testconnection()
    {
        if(conn==null)
        {
            System.out.println("数据库连接失败");
        }else {
            System.out.println("数据库连接成功");
        }
    }
    //关闭连接
    public void  closeconnection () throws Exception
    {
        conn.close();
    }


    @Override
    public boolean Register(String userid,String enterpassword,String gender) throws Exception
    {
        PreparedStatement prep1=conn.prepareStatement("select * from person where userid=?");
        prep1.setString(1,userid);
        prep1.execute();
        ResultSet resultSet= prep1.executeQuery();
        if(resultSet.next())
        {
           return false;
        }

        PreparedStatement prep2=conn.prepareStatement("insert into person(userid,enterpassword,gender) values (?,?,?);");
        prep2.setString(1,userid);
        prep2.setString(2,enterpassword);
        prep2.setString(3,gender);
        prep2.execute();
        return  true;
    }

    @Override
    public boolean Loginin(String userid,String enterpassword)throws Exception
    {
        PreparedStatement prep=conn.prepareStatement("select * from person where userid=?");
        prep.setString(1,userid);
        prep.execute();
        ResultSet resultSet= prep.executeQuery();
        if(resultSet.next())
        {
            String realpassword= resultSet.getString("enterpassword");
            if(enterpassword.equals(realpassword))
            {
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Userdata> getUser(String userid)throws Exception
    {
        ArrayList<Userdata> userlist=new ArrayList<Userdata>();
        PreparedStatement prep=conn.prepareStatement("select *from person where userid like ?;");
        prep.setString(1,"%"+userid+"%");
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
        return userlist;
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
}
