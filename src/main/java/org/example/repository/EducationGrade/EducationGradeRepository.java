package org.example.repository.EducationGrade;

import org.example.base.repository.BaseRepository;
import org.example.model.EducationGrade;

import java.util.List;

public interface EducationGradeRepository extends BaseRepository<EducationGrade, Long> {
    List<EducationGrade> findAllEducationGrades();
}
