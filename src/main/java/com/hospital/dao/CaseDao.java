package com.hospital.dao;

import com.hospital.entity.CaseEntity;
import sun.net.www.content.text.Generic;

import java.util.List;

public interface CaseDao extends GenericDao<CaseEntity, Integer> {
    List<CaseEntity> getByName(String name);

    List<CaseEntity> getByClassification(String classification);

    CaseEntity getByID(Integer id);
}