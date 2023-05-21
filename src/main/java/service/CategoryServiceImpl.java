package service;

import java.util.ArrayList;

public class CategoryServiceImpl implements CategoryService {
    String[] AllCategory ={"旅游码头","丁汝昌寓所","甲午海战纪念馆","刘公岛鲸馆","海军公所","北洋海军忠魂碑","东泓炮台","水师学堂","国家与森林公园","观光与潜水艇站"};
    //"旅游码头","国家与森林公园","丁汝昌寓所","甲午海战纪念馆"
    int[][] distance= {
            {0,1000,450,730,340,800,2600,1000,580,940},
            {1000,0,1300,1700,770,920,3500,920,690,250},
            {450,1300,0,540,630,1100,2200,1300,880,1200},
            {730,1700,540,0,900,1400,2200,1700,1200,1500},
            {340,770,630,900,0,760,2800,770,360,740},
            {800,920,1100,1400,760,0,3200,920,230,1200},
            {2600,3500,2200,2200,2800,3200,0,3500,3000,3400},
            {1000,920,1300,1700,770,920,3500,0,790,370},
            {580,690,880,1200,360,230,3000,790,0,890},
            {940,250,1200,1500,740,1200,3400,370,890,0}
    };
    public CategoryServiceImpl() {

    }
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
    public ArrayList<String> getCategory(String category) throws Exception {
        ArrayList<String>find=new ArrayList<>();
        for (int i=0;i<10;i++)
        {
            if(KMP(AllCategory[i],category))
            {
                find.add(AllCategory[i]);
            }
        }
        return  find;
    }

    @Override
    public ArrayList<String> getShortestRoad(String[] selected)throws Exception {
        int[]selectedCategory=new int[selected.length];
        int num=0,now=0;
        for(String select:selected)
        {
            for (int j=0;j<10;j++)
            {
                if(select.equals(AllCategory[j]))
                {
                    num=j;
                    break;
                }
            }
            selectedCategory[now++]=num;
        }

        boolean[]flag = new boolean[selected.length];//标记数组
        ArrayList<String>Road=new ArrayList<>();//结构数组
        int[] shortest=new int[selected.length];


        for(int i=0;i<selectedCategory.length;i++)
        {
            flag[i]=false;
            shortest[i]=distance[selectedCategory[0]][selectedCategory[i]];//初始化最短距离
        }

        Road.add(AllCategory[selectedCategory[0]]);
        flag[0]=true;

        for(int i=1;i<selectedCategory.length;i++)
        {
            int min=999999,next=1;
            for(int j=1;j<selectedCategory.length;j++)
            {
               if(shortest[j]<min&&flag[j]==false)
               {
                   min=shortest[j];
                   next=j;//找到shortest数组最小值对应的序号(最近顶点的序号)
               }
            }
            flag[next]=true;
            Road.add(AllCategory[selectedCategory[next]]);//寻找到最近的顶点入栈

            for(int j=1;j<selectedCategory.length;j++)
            {
                if(shortest[j]>distance[selectedCategory[next]][selectedCategory[j]]&&flag[j]==false)
                {
                    shortest[j]=distance[selectedCategory[next]][selectedCategory[j]];//更新距离
                }
            }
        }
        return Road;
   }
}
