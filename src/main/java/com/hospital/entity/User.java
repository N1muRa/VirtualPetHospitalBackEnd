package com.hospital.entity;

import javax.persistence.*;

/**
 * Created by WangCheng on 2018/3/13.
 */

@Entity
public class User {
    private int id;
    private String username;
    private String password;
    private Integer usertype;

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
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "user_pwd", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "user_type", nullable = false)
    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof User) ) return false;

        final User user = (User) o;

        if (user.getId() != getId()) return false;
        if (!user.getUsername().equals(getUsername())) return false;
        if (!user.getPassword().equals(getPassword())) return false;
        if (!user.getUsertype().equals(getUsertype())) return false;

        return true;
//        if (id != user.id) return false;
//        if (username != null ? !username.equals(user.username) : user.username != null) return false;
//        if (password != null ? !password.equals(user.password) : user.password != null) return false;
//        if (usertype != null ? !usertype.equals(user.usertype) : user.usertype != null) return false;
//
//        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 29 * result + (username != null ? username.hashCode() : 0);
        result = 29 * result + (password != null ? password.hashCode() : 0);
        result = 29 * result + (usertype != null ? usertype.hashCode() : 0);
        return result;
    }
}
