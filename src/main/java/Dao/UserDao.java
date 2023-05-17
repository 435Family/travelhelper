package Dao;

import Data.Userdata;

public interface UserDao {
    public boolean findByUserid(String userid)throws Exception;//(注册/登录)寻找id
    public void save(Userdata user)throws Exception; //(注册)保存用户
    public boolean checkoutpassword(String userid,String enterpassword)throws Exception;//(登录)检验密码
}
