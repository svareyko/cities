package com.example.cities.dto;

import com.example.cities.enums.City;
import com.example.cities.validator.CitiesNotEqual;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static com.example.cities.constant.ApplicationConstants.MIN_DISTANCE;

/**
 * @author s.vareyko
 * @since 23.10.2018
 */
@Getter
@Setter
@CitiesNotEqual
public class CityRouteDto {
    @NotNull
    private City first;
    @NotNull
    private City second;
    @Min(MIN_DISTANCE)
    @NotNull
    private Long distance;
}
