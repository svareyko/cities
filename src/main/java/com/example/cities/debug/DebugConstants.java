package com.example.cities.debug;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author s.vareyko
 * @since 24.10.2018
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class DebugConstants {

    final static String ATTR_ROUTE = "route";
    final static String ATTR_PATHS = "paths";
    final static String ATTR_FROM = "from";
    final static String ATTR_TO = "to";
    final static String JOIN_DELIMITER = " => ";
    final static String VIEW_MAIN = "main";
    final static String URL_REDIRECT_HOME = "redirect:/";
}
