package service;

import Data.Userdata;

import java.util.ArrayList;

public interface ManagerService {
    public ArrayList<Userdata> getAllUser()throws Exception;//获取所有的用户信息,返回Userdata类型的动态数组
    public Userdata getUser(String userid)throws Exception;//获取指定用户信息,返回Userdata类型的动态数组
    public void  deleteUser(String userid)throws Exception;//删除指定用户信息
}
