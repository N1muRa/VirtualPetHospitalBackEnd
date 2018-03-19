package com.hospital.service;

import com.hospital.entity.Medicine;

import java.util.List;

/**
 * Created by WangCheng on 2018/3/19.
 */

public interface MedicineService {
    Integer saveMedicine(Medicine medicine);

    List<Medicine> getAllMedicine();

    Medicine getMedicine(String name);

    void deleteMedicine(Integer id);

    void updateMedicine(Medicine medicine);
}
