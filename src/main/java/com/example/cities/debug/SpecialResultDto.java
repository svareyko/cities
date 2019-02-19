package com.example.cities.debug;

import com.example.cities.dto.CityRouteDto;
import com.example.cities.dto.ResultDto;
import com.example.cities.enums.City;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.cities.debug.DebugConstants.JOIN_DELIMITER;

/**
 * @author s.vareyko
 * @since 24.10.2018
 */
public class SpecialResultDto extends ResultDto {

    /**
     * Constructor that match super.
     *
     * @param from    city from
     * @param to      city to
     * @param results list of results for this search
     */
    public SpecialResultDto(final City from, final City to, final List<CityRouteDto> results) {
        super(from, to, results);
    }

    /**
     * Constructor that build instance from parent instance.
     */
    SpecialResultDto(final ResultDto resultDto) {
        super(resultDto.getFrom(), resultDto.getTo(), resultDto.getResults());
    }

    /**
     * Helper method for generation of path. Method builds path using results field,
     * get from each result {@link com.example.cities.dto.ResultDto#from) and join
     * with {@link com.example.cities.constant.ApplicationConstants#JOIN_DELIMITER}.
     *
     * @return path built from results field
     */
    public String getPath() {
        final List<City> cities = new ArrayList<>();
        cities.add(this.getFrom());
        this.getResults().forEach(distance -> cities.add(distance.getFirst() == cities.get(cities.size() - 1)
                ? distance.getSecond() : distance.getFirst()));
        return cities.stream().map(City::toString).collect(Collectors.joining(JOIN_DELIMITER));
    }
}
