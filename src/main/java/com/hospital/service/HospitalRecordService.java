package com.hospital.service;

import com.hospital.entity.HospitalRecord;

import java.util.List;

public interface HospitalRecordService {
    Integer saveHospitalRecord(HospitalRecord hospitalRecord);

    List<HospitalRecord> getAllHospitalRecord();

//    HospitalRecord getHospitalRecord(String name);

    void deleteHospitalRecord(Integer id);

    void updateHospitalRecord(HospitalRecord hospitalRecord);

}
