package com.example.cities.dto;

import com.example.cities.enums.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * DTO that contains all found paths and search settings.
 *
 * @author s.vareyko
 * @since 10/22/2018
 */
@Getter
@Setter
@AllArgsConstructor
public class ResultDto {

    private City from;
    private City to;
    private List<CityRouteDto> results;

    /**
     * Helper method that obtain whole distance.
     *
     * @return sum of distances from result chain
     */
    public Long getDistance() {
        return this.results.stream().mapToLong(CityRouteDto::getDistance).sum();
    }
}
