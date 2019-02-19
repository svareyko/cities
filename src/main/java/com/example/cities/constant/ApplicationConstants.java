package com.example.cities.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author s.vareyko
 * @since 24.10.2018
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationConstants {

    public final static String ATTR_ERRORS = "errors";

    public final static String URL_API = "/rest-api";
    public final static String URL_ADD_ROUTE = "/add-route";
    public final static String URL_SEARCH = "/search";

    public final static int MIN_DISTANCE = 1;
}
