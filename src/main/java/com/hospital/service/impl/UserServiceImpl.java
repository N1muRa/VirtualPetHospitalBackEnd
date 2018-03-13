package com.hospital.service.impl;

import com.hospital.dao.UserDao;
import com.hospital.entity.User;
import com.hospital.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangCheng on 2018/3/13.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private final Log log = LogFactory.getLog(getClass());

    public Integer saveUser(User user) {
        try {
            return userDao.save(user);
        } catch (HibernateException e) {
            log.error("saveUser出错", e);
            e.printStackTrace();
        }
        return -1;
    }

    public List<User> getAllUsers() {
        try {
            List list = userDao.loadAll();
            return list;
        } catch (HibernateException e) {
            log.error("getAllUsers出错", e);
            e.printStackTrace();
        }
        return null;
    }

    public User getUser(String name) {
        try {
            return userDao.getUserByName(name);
        } catch (HibernateException e) {
            log.error("getUser出错", e);
            e.printStackTrace();
        }
        return null;
    }

    public void updateUser(User user) {
        try {
            userDao.update(user);
        } catch (HibernateException e) {
            log.error("updateUser出错", e);
            e.printStackTrace();
        }
    }

    public void deleteUser(Integer id) {
        try {
            userDao.delete(id);
        } catch (HibernateException e) {
            log.error("deleteUser出错", e);
            e.printStackTrace();
        }
    }
}
