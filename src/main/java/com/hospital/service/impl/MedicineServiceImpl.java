package com.hospital.service.impl;

import com.hospital.dao.MedicineDao;
import com.hospital.entity.Medicine;
import com.hospital.service.MedicineService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangCheng on 2018/3/19.
 */

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineDao medicineDao;

    //log工厂
    private final Log log = LogFactory.getLog(getClass());

    public Integer saveMedicine(Medicine medicine) {
        try {
            return medicineDao.save(medicine);
        } catch (HibernateException e) {
            log.error("saveUser出错", e);
            e.printStackTrace();
        }
        return -1;
    }

    public List<Medicine> getAllMedicine() {
        try {
            List list = medicineDao.loadAll();
            return list;
        } catch (HibernateException e) {
            log.error("getAllMedicine出错", e);
            e.printStackTrace();
        }
        return null;
    }

    public Medicine getMedicine(String name) {
        try {
            return medicineDao.getByName(name);
        } catch (HibernateException e) {
            log.error("getMedicine出错", e);
            e.printStackTrace();
        }
        return null;
    }

    public void updateMedicine(Medicine medicine) {
        try {
            medicineDao.update(medicine);
        } catch (HibernateException e) {
            log.error("updateMedicine出错", e);
            e.printStackTrace();
        }
    }

    public void deleteMedicine(Integer id) {
        try {
            medicineDao.delete(id);
        } catch (HibernateException e) {
            log.error("deleteMedicine出错", e);
            e.printStackTrace();
        }
    }
}
