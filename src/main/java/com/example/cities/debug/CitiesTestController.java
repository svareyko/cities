package com.example.cities.debug;

import com.example.cities.controller.CitiesController;
import com.example.cities.dto.CityRouteDto;
import com.example.cities.dto.CitySearchDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

import static com.example.cities.constant.ApplicationConstants.URL_ADD_ROUTE;
import static com.example.cities.constant.ApplicationConstants.URL_SEARCH;
import static com.example.cities.debug.DebugConstants.ATTR_FROM;
import static com.example.cities.debug.DebugConstants.ATTR_PATHS;
import static com.example.cities.debug.DebugConstants.ATTR_ROUTE;
import static com.example.cities.debug.DebugConstants.ATTR_TO;
import static com.example.cities.debug.DebugConstants.URL_REDIRECT_HOME;
import static com.example.cities.debug.DebugConstants.VIEW_MAIN;

/**
 * Special proxy controller, that provide UI for debugging service.
 *
 * @author s.vareyko
 * @since 10/22/2018
 */
@Controller
@AllArgsConstructor
@RequestMapping
public class CitiesTestController {

    private final CitiesController controller;

    /**
     * Form with add/search and results.
     *
     * @param model container for attributes.
     * @return view name
     */
    @GetMapping
    public String form(final Model model) {
        model.addAttribute(ATTR_ROUTE, new CityRouteDto());
        return VIEW_MAIN;
    }

    /**
     * Handler for add city.
     *
     * @param route  dto with new route to be added
     * @param result container for attributes
     * @return view name
     */
    @PostMapping(URL_ADD_ROUTE)
    public String addRoute(@Valid final CityRouteDto route, final BindingResult result) {

        if (!result.hasErrors()) {
            controller.addRoute(route, result);
        }

        return URL_REDIRECT_HOME;
    }

    /**
     * Method for search of routes between cities.
     *
     * @param dto   for search
     * @param model container for attributes
     * @return view name
     */
    @GetMapping(URL_SEARCH)
    public String search(@Valid final CitySearchDto dto, final Model model) {

        final Collection<SpecialResultDto> paths = controller.search(dto).stream()
                .map(SpecialResultDto::new).collect(Collectors.toList());
        model.addAttribute(ATTR_PATHS, paths);
        model.addAttribute(ATTR_FROM, dto.getFrom());
        model.addAttribute(ATTR_TO, dto.getTo());
        return form(model);
    }
}
