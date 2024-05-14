package org.example.service.university;

import org.example.base.service.BaseServiceImpl;
import org.example.model.University;
import org.example.repository.university.UniversityRepository;
import org.hibernate.SessionFactory;

import java.util.List;

public class UniversityServiceImpl extends BaseServiceImpl<University,Long, UniversityRepository> implements UniversityService{
    public UniversityServiceImpl(UniversityRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public List<University> findAllUniversity() {
        return repository.findAllUniversity();
    }
}
