package ru.practice.barbershop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practice.barbershop.general.MyService;
import ru.practice.barbershop.model.Barber;
import ru.practice.barbershop.repository.BarberRepository;

/**
 * This is service class for maintain the barber entity
 */
@Service
@AllArgsConstructor
public class BarberService implements MyService<Barber> {
    private final BarberRepository barberRepository;

    /**
     * Return Barber entity by his id
     * @param id entity id
     * @return Barber entity
     */
    @Override
    public Barber getById(Long id) {
        return barberRepository.getBarberById(id)
                .orElseThrow(() -> new RuntimeException("Barber with id=" + id + " not found."));
    }

    /**
     * Save or change entity in db
     * @param barber entity object
     */
    @Override
    public void save(Barber barber) {
        barberRepository.save(barber);
    }
}
