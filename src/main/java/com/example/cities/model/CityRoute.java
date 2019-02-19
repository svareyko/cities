package com.example.cities.model;

import com.example.cities.enums.City;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author s.vareyko
 * @since 22.10.2018
 */
@Setter
@Getter
@Entity
@Table
public class CityRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated
    private City first;
    @Enumerated
    private City second;
    private Long distance;
}
