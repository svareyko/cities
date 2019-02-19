package com.example.cities.service;

import com.example.cities.dto.CityRouteDto;
import com.example.cities.dto.ResultDto;
import com.example.cities.enums.City;

import java.util.List;

/**
 * @author s.vareyko
 * @since 10/22/18
 */
public interface CitiesService {

    /**
     * Method for saving route between cities.
     *
     * @param route to be saved
     */
    void save(CityRouteDto route);

    /**
     * Search routes and distance.
     *
     * @param from start city
     * @param to   end city
     * @return DTO with results from each data source
     */
    List<ResultDto> find(City from, final City to);
}
