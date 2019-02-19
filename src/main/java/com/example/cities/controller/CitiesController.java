package com.example.cities.controller;

import com.example.cities.dto.CityRouteDto;
import com.example.cities.dto.CitySearchDto;
import com.example.cities.dto.ResultDto;
import com.example.cities.service.CitiesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.example.cities.constant.ApplicationConstants.ATTR_ERRORS;
import static com.example.cities.constant.ApplicationConstants.URL_ADD_ROUTE;
import static com.example.cities.constant.ApplicationConstants.URL_API;
import static com.example.cities.constant.ApplicationConstants.URL_SEARCH;

/**
 * @author s.vareyko
 * @since 24.10.2018
 */
@RestController
@AllArgsConstructor
@RequestMapping(URL_API)
public class CitiesController {

    private final CitiesService service;

    /**
     * Handler for adding element. In case if some validation errors occurred
     * will be added header {@link com.example.cities.constant.ApplicationConstants#ATTR_ERRORS},
     * that contain detailed info about it.
     *
     * @param route  dto with new distance to be added
     * @param result container for attributes
     * @return response code
     */
    @PostMapping(URL_ADD_ROUTE)
    public ResponseEntity addRoute(@Valid final CityRouteDto route, final BindingResult result) {

        if (!result.hasErrors()) {
            service.save(route);
            return new ResponseEntity(HttpStatus.OK);
        }

        final LinkedMultiValueMap<String, List<ObjectError>> headers = new LinkedMultiValueMap<>();
        headers.add(ATTR_ERRORS, result.getAllErrors());
        return new ResponseEntity(headers, HttpStatus.I_AM_A_TEAPOT); // JK :D
    }

    /**
     * Method for searching of cities, accept as argument DTO with from/to cities.
     *
     * @param dto with settings
     * @return list of found routes
     */
    @GetMapping(URL_SEARCH)
    public List<ResultDto> search(@Valid final CitySearchDto dto) {
        return service.find(dto.getFrom(), dto.getTo());
    }
}
