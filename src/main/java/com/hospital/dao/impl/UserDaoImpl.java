package com.hospital.dao.impl;

import com.hospital.dao.UserDao;
import com.hospital.entity.User;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangCheng on 2018/3/13.
 */

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    public User load(Integer id) {
        Session session = getCurrentSession();
        try {
            return (User) session.load(User.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public User get(Integer id) {
        Session session = getCurrentSession();
        try {
            return (User) session.get(User.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public User getUserByName(String name) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        User user = null;
        try {
            String hsql = "from User where user.user_name = ?";
            Query query = session.createQuery(hsql);
            query.setString(0, name);
            user = (User) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    public List<User> loadAll() {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = null;
        try {
            String hsql = "from User";
            Query query = session.createQuery(hsql);
            users = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

//    插入数据

    public Integer save(User user) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Integer res = -1;
        try {
            res = (Integer) session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return res;
    }

//    更新数据

    public void update(User user) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(user);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


//    删除数据

    public void delete(Integer id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = (User) session.load(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

//    清理

    public void flush() {
        getCurrentSession().flush();
    }
}
