package com.hospital.dao;

import com.hospital.entity.User;

/**
 * Created by WangCheng on 2018/3/13.
 */

public interface UserDao extends GenericDao<User, Integer> {
    User getUserByName(String name);
}
