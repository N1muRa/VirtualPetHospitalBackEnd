package com.hospital.dao;

import com.hospital.entity.Medicine;

/**
 * Created by WangCheng on 2018/3/19.
 */

public interface MedicineDao extends GenericDao<Medicine, Integer> {
    Medicine getByName(String name);
}
