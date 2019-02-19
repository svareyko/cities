package com.example.cities.repository;

import com.example.cities.enums.City;
import com.example.cities.model.CityRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author s.vareyko
 * @since 10/22/2018
 */
@Repository
public interface CityRouteRepository extends JpaRepository<CityRoute, Long> {

    /**
     * Retrieve route between two cities if exists.
     *
     * @param first  city for retrieving
     * @param second city for retrieving
     * @return found route wrapped in optional
     */
    @Query("from CityRoute cd where cd.first=:first and cd.second=:second or cd.first=:second and cd.second=:first")
    Optional<CityRoute> findByPair(@Param("first") City first, @Param("second") City second);
}
