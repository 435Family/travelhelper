package service;

import Dao.UserDao;
import Dao.UserDaoImpl;
import Data.ResultInfo;
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
    public ResultInfo Loginin(Userdata user)throws Exception
    {
        ResultInfo info=new ResultInfo();
        if(userDao.findByUserid(user.getUserid()))
        {
            if(userDao.checkoutpassword(user.getUserid(),user.getEnterpassword()))
            {
                info.setFlag(true);
                return info;
            }else {
                //密码错误
                info.setFlag(false);
                info.setErrorMsh("密码错误");
                return info;
            }
        }else {
            //未注册
            info.setFlag(false);
            info.setErrorMsh("用户不存在");
            return info;
        }
    }
}
