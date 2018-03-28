package com.hospital.dao.impl;

import com.hospital.dao.RoleDao;
import com.hospital.entity.Role;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by N1muRa on 2018/3/28.
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    public Role load(Integer id) {
        Session session = getCurrentSession();
        try {
            return (Role) session.load(Role.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public Role get(Integer id) {
        Session session = getCurrentSession();
        try {
            return (Role) session.get(Role.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public List<Role> loadAll() {
        Session session = getCurrentSession();
        try {
            String hql = "from Role";
            Query query = session.createQuery(hql);
            return query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    //插入数据
    public Integer save(Role entity) {
        Session session = getCurrentSession();
        try {
            return (Integer) session.save(entity);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return -1;
    }

    //更新数据
    public void update(Role entity) {
        try {
            Session session = getCurrentSession();
            Transaction tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    //删除数据
    public void delete(Integer id) {
        try {
            Session session = getCurrentSession();
            Transaction tx = session.beginTransaction();
            Role role = (Role) session.load(Role.class, id);
            session.delete(role);
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    //清理
    public void flush() {
        getCurrentSession().flush();
    }
}
