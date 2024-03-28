package ru.practice.barbershop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practice.barbershop.model.Barber;
import ru.practice.barbershop.model.Order;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    /**
     * Function for getting Order via ID. Get without collections.
     * @param id ID of order
     * @return Optional<Order> entity
     */
    Optional<Order> getOrderById(Long id);

    Optional<Order> getOrderByTimeAndDayAndBarber(LocalTime time, LocalDate day, Barber barber);

    @Query(value = "SELECT o FROM Order o WHERE o.barber.id = ?1")
    List<Order> getByBarbers(Long barberId);
}
