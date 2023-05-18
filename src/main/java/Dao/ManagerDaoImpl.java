package Dao;

import Data.Userdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public  class ManagerDaoImpl implements ManagerDao {
    Connection conn;

    public ManagerDaoImpl() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/first?serverTimezone=GMT%2B8","root","admin");
    }



    @Override
    //找指定对象
    public Userdata getUser(String userid)throws Exception
    {
        //1.建立Userdata对象
        ManagerDaoImpl managerDao=new ManagerDaoImpl();
        ArrayList<Userdata> userlist=managerDao.getAllUser();
        for(Userdata user:userlist)
        {
            if(user.getUserid().equals(userid))
            {
                return user;
            }
        }
        return null;
        //2.建立数据库连接获取数据
        //PreparedStatement prep=conn.prepareStatement("select * from person where userid = ?;");
        //prep.setString(1,userid);
        //prep.execute();
        //ResultSet resultSet= prep.executeQuery();
        //3.赋值并返回
        //while(resultSet.next())
        //{
            //user.setNum(resultSet.getInt("num"));
            //user.setUserid(resultSet.getString("userid")) ;
            //user.setEnterpassword(resultSet.getString("enterpassword"));
            //user.setGender(resultSet.getString("gender"));
        //}
        //return user;
    }

    @Override
    //模糊查询（kmp算法）
    public ArrayList<Userdata> getLikelyUser(String userid) throws Exception {
        ManagerDaoImpl managerDao=new ManagerDaoImpl();
        ArrayList<Userdata>userlist=managerDao.getAllUser();
        ArrayList<Userdata>resultList=new ArrayList<>();
        for(Userdata user :userlist)
        {
            String id=user.getUserid();
            if(KMP(id,userid))
            {
                resultList.add(user);
            }
        }
        return resultList;
    }

    @Override
    public boolean KMP(String s, String t) throws Exception {
        int MaxSize=t.length();
        int[]next=new int[MaxSize];
        int i=0,j=0;
        Getnext(next,t);
        while(i<s.length()&&j<t.length())
        {
            if(j==-1 || s.charAt(i)==t.charAt(j))
            {
                i++;
                j++;
            }
            else j=next[j];               //j回退。。。
        }
        if(j>=t.length())
            return true;         //匹配成功，返回true
        else
            return false;                  //没找到

    }
    void Getnext(int next[],String t)
    {
        int j=0,k=-1;
        next[0]=-1;
        while(j<t.length()-1)
        {
            if(k == -1 || t.charAt(j) == t.charAt(k))
            {
                j++;k++;
                if(t.charAt(j)==t.charAt(k))//当两个字符相同时，就跳过
                    next[j] = next[k];
                else
                    next[j] = k;
            }
            else k = next[k];
        }
    }
    @Override
    //查询所有用户
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
    @Override
    //删除用户
    public void deleteUser(String userid) throws Exception {
        PreparedStatement prep=conn.prepareStatement("delete from person where userid = ?;");
        prep.setString(1,userid);
        prep.execute();
    }
}
