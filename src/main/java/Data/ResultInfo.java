package Data;
/*
    封装后端返回前端的数据对象
*/

import java.util.ArrayList;

public class ResultInfo {
    private  boolean flag;//后端返回结果正常为true
    private Userdata user;//后端返回结果的数据对象
    private String errorMsh;//发生异常的错误消息
    private ArrayList<Userdata>userlist;//后端返回结果的数组
    public ResultInfo() {
    }
    public ArrayList<Userdata> getUserlist() {
        return userlist;
    }

    public void setUserlist(ArrayList<Userdata> userlist) {
        this.userlist = userlist;
    }



    public ResultInfo(boolean flag) {
        this.flag = flag;
    }

    public ResultInfo(boolean flag, String errorMsh) {
        this.flag = flag;
        this.errorMsh = errorMsh;
    }

    public ResultInfo(boolean flag, Userdata user, String errorMsh) {
        this.flag = flag;
        this.user = user;
        this.errorMsh = errorMsh;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Userdata getUser() {
        return user;
    }

    public void setUser(Userdata user) {
        this.user = user;
    }

    public String getErrorMsh() {
        return errorMsh;
    }

    public void setErrorMsh(String errorMsh) {
        this.errorMsh = errorMsh;
    }
}
