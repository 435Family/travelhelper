package Dao;

import Data.Userdata;

import java.util.ArrayList;

public interface ManagerDao {

    public ArrayList<Userdata> getAllUser()throws Exception;//获取所有的用户信息,返回Userdata类型的动态数组
    public Userdata getUser(String userid)throws Exception;//获取指定用户信息,返回Userdata
    public ArrayList<Userdata>getLikelyUser(String userid)throws Exception;//模糊获取用户信息,返回Userdata类型的动态数组
    public boolean KMP(String s,String t)throws Exception;
    public void deleteUser(String userid)throws  Exception;//从数据库中删除指定数据
}
