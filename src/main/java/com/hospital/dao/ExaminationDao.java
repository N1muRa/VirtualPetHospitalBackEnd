package com.hospital.dao;

import com.hospital.entity.Examination;

public interface ExaminationDao extends GenericDao<Examination, Integer> {
    Examination getByName(String name);
}