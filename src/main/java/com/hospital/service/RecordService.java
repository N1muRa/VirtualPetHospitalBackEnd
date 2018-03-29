package com.hospital.service;

import com.hospital.entity.Record;

import java.util.List;

/**
 * Created by N1muRa on 17/3/29.
 */
public interface RecordService {


    Integer saveRecord(Record record);

    List<Record> getAllRecord();

//    Record getRecord(String name);

    void deleteRecord(Integer id);

    void updateRecord(Record record);
}
