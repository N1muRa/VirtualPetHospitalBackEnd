package com.hospital.dao.impl;

import com.hospital.dao.MedicineDao;
import com.hospital.entity.Medicine;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangCheng on 2018/3/19.
 */

@Repository
public class MedicineDaoImpl implements MedicineDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    public Medicine load(Integer id) {
        Session session = getCurrentSession();
        try {
            return (Medicine) session.load(Medicine.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public Medicine get(Integer id) {
        Session session = getCurrentSession();
        try {
            return (Medicine) session.get(Medicine.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public Medicine getByName(String name) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Medicine medicine = null;
        try {
            String hsql = "from Medicine where medicine_name = ?";
            Query query = session.createQuery(hsql);
            query.setString(0, name);
            medicine = (Medicine) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return medicine;
    }

    public List<Medicine> loadAll() {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Medicine> medicines = null;
        try {
            String hsql = "from Medicine";
            Query query = session.createQuery(hsql);
            medicines = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return medicines;
    }

//    插入
    public Integer save(Medicine medicine) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Integer res = -1;
        try {
            res = (Integer) session.save(medicine);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return res;
    }

//    更新
    public void update(Medicine medicine) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(medicine);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

//    删除
    public void delete(Integer id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            Medicine medicine = (Medicine) session.load(Medicine.class, id);
            session.delete(medicine);
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
