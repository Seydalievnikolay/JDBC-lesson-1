package dao;

import pojo.City;

import java.util.List;

public interface CityDao {
    City create(City city);
    City getById(int id);
    List<City> getAllCity();
    City updateCity(City city);
    void deleteCity(City city);
}
