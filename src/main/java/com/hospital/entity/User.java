package com.hospital.entity;

import javax.persistence.*;

/**
 * Created by WangCheng on 2018/3/13.
 */

@Entity
public class User {
    private int id;
    private String userName;
    private String userPwd;
    private Integer userType;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_pwd", nullable = false, length = 255)
    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Basic
    @Column(name = "user_type", nullable = false)
    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof User) ) return false;

        final User user = (User) o;

        if (user.getId() != getId()) return false;
        if (!user.getUserName().equals(getUserName())) return false;
        if (!user.getUserPwd().equals(getUserPwd())) return false;
        if (!user.getUserType().equals(getUserType())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 29 * result + (userName != null ? userName.hashCode() : 0);
        result = 29 * result + (userPwd != null ? userPwd.hashCode() : 0);
        result = 29 * result + (userType != null ? userType.hashCode() : 0);
        return result;
    }
}
