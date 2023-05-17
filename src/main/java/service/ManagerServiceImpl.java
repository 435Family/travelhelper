package service;

import Dao.ManagerDao;
import Dao.ManagerDaoImpl;
import Data.Userdata;

import java.util.ArrayList;

public class ManagerServiceImpl implements ManagerService {
    ManagerDao managerDao =new ManagerDaoImpl();

    public ManagerServiceImpl() throws Exception {
    }

    @Override
    public ArrayList<Userdata> getAllUser() throws Exception {
        return managerDao.getAllUser();
    }
    @Override
    public Userdata getUser(String userid) throws Exception {
        return managerDao.getUser(userid);
    }

    @Override
    public void deleteUser(String userid) throws Exception {
        managerDao.deleteUser(userid);
    }
}
