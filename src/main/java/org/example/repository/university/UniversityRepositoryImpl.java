package org.example.repository.university;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.model.University;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UniversityRepositoryImpl extends BaseRepositoryImpl<University, Long> implements UniversityRepository {
    public UniversityRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<University> getEntityClass() {
        return University.class;
    }

    @Override
    public List<University> findAllUniversity() {
        ArrayList<University> universities = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            universities = (ArrayList<University>) session.createQuery(
                            "FROM University u ", University.class)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return universities;
    }
}

