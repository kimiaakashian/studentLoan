package org.example.repository.city;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.model.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class CityRepositoryImpl extends BaseRepositoryImpl<City, Long> implements CityRepository {
    public CityRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<City> getEntityClass() {
        return City.class;
    }

    @Override
    public List<City> findAllCities() {

        ArrayList<City> cities = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            cities = (ArrayList<City>) session.createQuery(
                            "FROM City c ", City.class)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }
}

