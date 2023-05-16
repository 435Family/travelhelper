package Data;

public class Userdata {
    private  int num;
    private String userid;
    private String enterpassword;
    private String gender;

    public Userdata()
    {

    }
    public Userdata(int num, String userid, String enterpassword, String gender) {
        this.num = num;
        this.userid = userid;
        this.enterpassword = enterpassword;
        this.gender = gender;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEnterpassword() {
        return enterpassword;
    }

    public void setEnterpassword(String enterpassword) {
        this.enterpassword = enterpassword;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
