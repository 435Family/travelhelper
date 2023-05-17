package service;

import Dao.UserDao;
import Dao.UserDaoImpl;
import Data.Userdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserServiceImpl implements UserService{
    private UserDao userDao=new UserDaoImpl();

    public UserServiceImpl() throws Exception {
    }

    @Override
    public boolean Register(Userdata user) throws Exception
    {
        //根据用户名查询对象
        if(userDao.findByUserid(user.getUserid()))
        {
            //已经存在用户
            return false;
        }
        //未存在就注册
        userDao.save(user);
        return  true;
    }

    @Override
    public boolean Loginin(Userdata user)throws Exception
    {
        if(userDao.findByUserid(user.getUserid()))
        {
            if(userDao.checkoutpassword(user.getUserid(),user.getEnterpassword()))
            {
                return true;
            }else {
                //密码错误
                return false;
            }
        }else {
            //未注册
            return false;
        }
    }
}
