package Dao;

import Data.Userdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {

    Connection conn=null;
    public UserDaoImpl() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/first?serverTimezone=GMT%2B8","root","admin");
    }



    @Override
    public boolean findByUserid(String userid) throws Exception {
        PreparedStatement prep=conn.prepareStatement("select * from person where userid=?");
        prep.setString(1, userid);
        prep.execute();
        ResultSet resultSet= prep.executeQuery();
        if(resultSet.next())//已经存在该用户
        {
            return true;
        }
        return false;
    }

    @Override
    public void save(Userdata user) throws Exception {
        PreparedStatement prep=conn.prepareStatement("insert into person(userid,enterpassword,gender) values (?,?,?);");
        prep.setString(1,user.getUserid());
        prep.setString(2,user.getEnterpassword());
        prep.setString(3,user.getGender());
        prep.execute();
        return;
    }

    @Override
    public boolean checkoutpassword(String userid,String enterpassword) throws Exception {
        PreparedStatement prep=conn.prepareStatement("select * from person where userid=?");
        prep.setString(1, userid);
        prep.execute();
        ResultSet resultSet= prep.executeQuery();
        if(resultSet.next())//存在该用户
        {
            if(enterpassword.equals(resultSet.getString("enterpassword")))
            {
                return true;
            }else {
                return false;
            }
        }
        return false;
    }


    public boolean Register(String userid, String enterpassword, String gender) {
    }
}
