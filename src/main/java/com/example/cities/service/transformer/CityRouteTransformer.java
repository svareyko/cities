package com.example.cities.service.transformer;

import com.example.cities.dto.CityRouteDto;
import com.example.cities.model.CityRoute;
import org.springframework.stereotype.Component;

/**
 * Component that used for transforming Entity to Dto and back.
 *
 * @author s.vareyko
 * @since 23.10.2018
 */
@Component
public class CityRouteTransformer {

    /**
     * Transforming {@link com.example.cities.dto.CityRouteDto} to it's Entity.
     *
     * @param dto to be transformed
     * @return transformed entity
     */
    public CityRoute transform(final CityRouteDto dto) {
        final CityRoute entity = new CityRoute();
        entity.setFirst(dto.getFirst());
        entity.setSecond(dto.getSecond());
        entity.setDistance(dto.getDistance());
        return entity;
    }

    /**
     * Transforming {@link com.example.cities.model.CityRoute} to it's DTO.
     *
     * @param entity to be transformed
     * @return transformed DTO
     */
    public CityRouteDto transform(final CityRoute entity) {
        final CityRouteDto dto = new CityRouteDto();
        dto.setFirst(entity.getFirst());
        dto.setSecond(entity.getSecond());
        dto.setDistance(entity.getDistance());
        return dto;
    }
}
