package com.example.cities.validator;

import com.example.cities.dto.CityRouteDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Special validator that checks if first and second city in the object are not equal.
 *
 * @author s.vareyko
 * @since 24.10.2018
 */
public class ValidateThatCitiesNotEqual implements ConstraintValidator<CitiesNotEqual, CityRouteDto> {

    @Override
    public boolean isValid(final CityRouteDto dto, final ConstraintValidatorContext context) {
        return dto.getFirst() != dto.getSecond();
    }
}
