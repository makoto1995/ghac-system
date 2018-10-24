package com.huishuo.ghacsystem.model;


public class User {
    private int userID;
    private String userName;
    private String userPwd;
    private String privileges;

    public User(){}

    public User(int userID, String userName, String userPwd, String privileges) {
        this.userID = userID;
        this.userName = userName;
        this.userPwd = userPwd;
        this.privileges = privileges;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
