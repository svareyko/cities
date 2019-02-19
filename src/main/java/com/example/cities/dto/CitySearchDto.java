package com.example.cities.dto;

import com.example.cities.enums.City;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author s.vareyko
 * @since 24.10.2018
 */
@Getter
@Setter
public class CitySearchDto {
    @NotNull
    private City from;
    @NotNull
    private City to;
}
