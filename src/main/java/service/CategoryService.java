package service;

import java.util.ArrayList;

public interface CategoryService {
    public ArrayList<String> getCategory(String category) throws Exception;//查询地点
    public ArrayList<String> getShortestRoad(String[] selected) throws Exception;//寻找最短路径
}
