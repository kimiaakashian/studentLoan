package org.example.repository.city;

import org.example.base.repository.BaseRepository;
import org.example.model.City;

import java.util.List;

public interface CityRepository extends BaseRepository<City, Long> {
    List<City> findAllCities();
}
