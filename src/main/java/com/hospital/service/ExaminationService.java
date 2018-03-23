package com.hospital.service;

import com.hospital.entity.Examination;

import java.util.List;

public interface ExaminationService {
    Integer saveExamination(Examination examination);

    List<Examination> getAllExamination();

    Examination getExamination(String name);

    void deleteExamination(Integer id);

    void updateExamination(Examination examination);

}
