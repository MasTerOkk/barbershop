package ru.practice.barbershop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practice.barbershop.model.Amenities;

import java.util.Optional;

@Repository
public interface AmenitiesRepository extends JpaRepository<Amenities, Long> {

    /**
     * Function for getting Amenities via ID. Get without collections.
     * @param id ID of amenity
     * @return Optional<Amenities> entity
     */
    Optional<Amenities> getAmenitiesById(Long id);
}
