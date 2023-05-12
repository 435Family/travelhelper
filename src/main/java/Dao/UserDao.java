package Dao;

import Data.Userdata;

import java.util.ArrayList;

public interface UserDao {
    public boolean Register(String userid,String enterpassword,String gender)throws Exception;//注册，返回boolen值
    public boolean Loginin(String userid,String enterpassword)throws Exception; //登录,返回boolen值
    public ArrayList<Userdata> getAllUser()throws Exception;//获取所有的用户信息,返回Userdata类型的动态数组
    public ArrayList<Userdata> getUser(String userid)throws Exception;//获取指定用户信息,返回Userdata类型的动态数组
}
