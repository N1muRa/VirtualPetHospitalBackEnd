package com.hospital.service;

import com.hospital.entity.User;

import java.util.List;

/**
 * Created by WangCheng on 2018/3/13.
 */

public interface UserService {
    List<User> getAllUsers();

    User getUser(String name);

    void deleteUser(Integer id);

    void updateUser(User user);

    Integer saveUser(User user);
}
