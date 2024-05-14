package org.example.service.city;

import org.example.base.service.BaseService;
import org.example.model.City;
import java.util.List;

public interface CityService extends BaseService<City,Long> {
    List<City> findAllCities();
}
