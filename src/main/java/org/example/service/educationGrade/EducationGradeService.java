package org.example.service.educationGrade;

import org.example.base.service.BaseService;
import org.example.model.EducationGrade;

import java.util.List;

public interface EducationGradeService extends BaseService<EducationGrade,Long> {
    List<EducationGrade> findAllEducationGrades();
}
