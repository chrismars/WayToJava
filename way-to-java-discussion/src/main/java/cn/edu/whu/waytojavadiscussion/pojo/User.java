package cn.edu.whu.waytojavadiscussion.pojo;

/**
 * @author Jimmy
 * @version 2018/12/21
 * 用户类
 */

public class User {
    private int userId;
    private String userName;
    private String password;
    private String phone;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
