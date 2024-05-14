package org.example.service.educationGrade;

import org.example.base.service.BaseServiceImpl;
import org.example.model.EducationGrade;
import org.example.repository.EducationGrade.EducationGradeRepository;
import org.hibernate.SessionFactory;

import java.util.List;

public class EducationGradeServiceImpl extends BaseServiceImpl<EducationGrade, Long, EducationGradeRepository> implements EducationGradeService {
    public EducationGradeServiceImpl(EducationGradeRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
    @Override
    public List<EducationGrade> findAllEducationGrades() {
        return repository.findAllEducationGrades();
    }
}
