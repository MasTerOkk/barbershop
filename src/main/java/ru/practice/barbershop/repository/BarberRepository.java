package ru.practice.barbershop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.practice.barbershop.model.Barber;

import java.util.Optional;

@Repository
public interface BarberRepository extends JpaRepository<Barber, Long> {


    Optional<Barber> getBarberById(Long id);

    //TODO Разобраться почему FETCH не работает
//    @Query("SELECT b FROM Barber b JOIN FETCH b.orders WHERE b.id = ?1")
//    Optional<Barber> getBarberByIdAllCollections(Long id);

}
