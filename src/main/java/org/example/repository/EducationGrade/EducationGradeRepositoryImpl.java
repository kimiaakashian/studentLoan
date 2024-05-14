package org.example.repository.EducationGrade;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.model.EducationGrade;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class EducationGradeRepositoryImpl extends BaseRepositoryImpl<EducationGrade, Long> implements EducationGradeRepository {
    public EducationGradeRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<EducationGrade> getEntityClass() {
        return EducationGrade.class;
    }

    @Override
    public List<EducationGrade> findAllEducationGrades() {
        ArrayList<EducationGrade> educationGrades = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            educationGrades = (ArrayList<EducationGrade>) session.createQuery(
                            "FROM EducationGrade e ", EducationGrade.class)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return educationGrades;
    }
}
