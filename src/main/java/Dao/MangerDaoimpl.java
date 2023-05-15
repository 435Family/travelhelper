package Dao;

import Data.Userdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

public class MangerDaoimpl implements MangerDao{
    Connection conn;
    public MangerDaoimpl() throws Exception {
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
