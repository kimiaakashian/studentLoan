package org.example.service.city;

import org.example.base.service.BaseServiceImpl;
import org.example.model.City;
import org.example.repository.city.CityRepository;
import org.hibernate.SessionFactory;

import java.util.List;

public class CityServiceImpl extends BaseServiceImpl<City, Long, CityRepository> implements CityService {
    public CityServiceImpl(CityRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }

    @Override
    public List<City> findAllCities() {
        return repository.findAllCities();
    }
}
