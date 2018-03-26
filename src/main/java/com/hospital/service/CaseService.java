package com.hospital.service;

import com.hospital.entity.CaseEntity;

import java.util.List;

public interface CaseService {
    Integer saveCase(CaseEntity caseEntity);

    List<CaseEntity> getAllCase();

    List<CaseEntity> getCase(String name);

    void deleteCase(Integer id);

    void updateCase(CaseEntity caseEntity);

    List<CaseEntity> getCaseInClassification(String classification);

    CaseEntity getCaseByID(Integer id);
}
