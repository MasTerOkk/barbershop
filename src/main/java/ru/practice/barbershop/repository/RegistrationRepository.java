package ru.practice.barbershop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practice.barbershop.model.Registration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    /**
     * Function for getting Registration via ID
     * @param id ID of registration
     * @return <code>Optional<Registration></code> registration
     */
    @Query("SELECT r FROM Registration r WHERE r.id = ?1")
    Optional<Registration> getRegistrationById(Long id);

    /**
     * Function for getting Registration List via time and day of registration
     * @param time time of registration
     * @param day day of registration
     * @return List registrations
     */
    List<Registration> getRegistrationsByTimeAndDay(LocalTime time, LocalDate day);


}
