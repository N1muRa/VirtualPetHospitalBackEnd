package com.hospital.dao;

import com.hospital.entity.Staff;

public interface StaffDao extends GenericDao<Staff, Integer> {
    Staff getByName(String name);
}