package com.example.cities.service.impl;

import com.example.cities.dto.CityRouteDto;
import com.example.cities.dto.ResultDto;
import com.example.cities.enums.City;
import com.example.cities.model.CityRoute;
import com.example.cities.repository.CityRouteRepository;
import com.example.cities.service.CitiesService;
import com.example.cities.service.transformer.CityRouteTransformer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author s.vareyko
 * @since 22.10.2018
 */
@Service
@AllArgsConstructor
public class CitiesServiceImpl implements CitiesService {

    private final CityRouteRepository repository;
    private final CityRouteTransformer transformer;

    @Override
    public void save(final CityRouteDto route) {
        final CityRoute entity = repository.findByPair(route.getFirst(), route.getSecond())
                .orElseGet(() -> transformer.transform(route));

        if (Objects.nonNull(entity.getId())) {
            entity.setDistance(route.getDistance());
        }

        repository.save(entity);
    }

    @Override
    public List<ResultDto> find(final City from, final City to) {
        final List<ResultDto> results = new ArrayList<>(); // we need mutable lists
        final List<City> exclusions = new ArrayList<>();
        // as said in conditions we have no limitations in memory/resources,
        // so we collect all distances from DB and work with them in memory
        // also we know that mysql works only in one thread, so we don't care about multithreading conflicts
        final List<CityRoute> paths = repository.findAll();
        findPaths(paths, Collections.emptyList(), results, exclusions, from, to);
        results.forEach(result -> result.setFrom(from));
        return results;
    }

    /**
     * Recursive method that search all paths between cities.
     *
     * @param paths        list of all paths
     * @param currentPaths current built path
     * @param results      list of found result paths, list modifiable
     * @param exclusions   list of cities where we shouldn't get back
     * @param from         start city from which we should search
     * @param to           target city to which should be path build
     */
    private void findPaths(final List<CityRoute> paths,
                           final List<CityRoute> currentPaths,
                           final List<ResultDto> results,
                           final List<City> exclusions,
                           final City from,
                           final City to) {

        // find all routes for city, excluding already used
        final List<CityRoute> connections = paths.stream()
                .filter(path -> path.getFirst() == from && !exclusions.contains(path.getSecond())
                        || path.getSecond() == from && !exclusions.contains(path.getFirst()))
                .collect(Collectors.toList());
        // exclude just searched city to not go again to this city
        exclusions.add(from);
        connections.forEach(route -> {
            // define next city
            final City city = route.getFirst() == from ? route.getSecond() : route.getFirst();
            if (city == to) { // if target city matches build and add result
                final List<CityRouteDto> path = extend(currentPaths, route)
                        .stream().map(transformer::transform).collect(Collectors.toList());
                results.add(new ResultDto(from, to, path));
            } else { // if not continue search
                findPaths(paths, extend(currentPaths, route), results, exclusions, city, to);
            }
        });
    }

    /**
     * Helper method that extend provided array with additional element.
     * todo: move this to array utils in the future
     *
     * @param arr to be extended
     * @param el  to be added in array
     * @return array that contains all elements
     */
    private <T> List<T> extend(final List<T> arr, final T el) {
        final ArrayList<T> list = new ArrayList<>(arr);
        list.add(el);
        return list;
    }
}
