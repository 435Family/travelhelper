package service;

import Data.ResultInfo;
import Data.Userdata;

public interface UserService {
    public boolean Register(Userdata user)throws Exception;//注册，返回boolen值
    public ResultInfo Loginin(Userdata user)throws Exception; //登录,返回boolen值

}
