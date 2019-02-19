package com.example.cities.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author s.vareyko
 * @since 24.10.2018
 */
@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = ValidateThatCitiesNotEqual.class)
@Documented
public @interface CitiesNotEqual {

    /**
     * Default message for this error.
     *
     * @return message
     */
    String message() default "Cities shouldn't be equal";

    /**
     * Used in validation processor.
     *
     * @return groups
     */
    Class<?>[] groups() default {};

    /**
     * Used in validation processor.
     *
     * @return payload
     */
    Class<? extends Payload>[] payload() default {};
}
