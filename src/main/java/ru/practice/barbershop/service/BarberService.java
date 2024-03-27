package ru.practice.barbershop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practice.barbershop.dto.BarberDto;
import ru.practice.barbershop.mapper.BarberMapper;
import ru.practice.barbershop.model.Barber;
import ru.practice.barbershop.repository.BarberRepository;

/**
 * This is service class for maintain the barber entity
 */
@Service
@AllArgsConstructor
public class BarberService {
    private final BarberRepository barberRepository;

    /**
     * Return Barber entity by his id
     * @param id entity id
     * @return Barber dto
     */

    public BarberDto getDtoById(Long id) {
        return BarberMapper.toDto(barberRepository.getBarberById(id)
                .orElseThrow(() -> new RuntimeException("Barber with id=" + id + " not found.")));
    }

    /**
     * Return Barber entity by his id
     * @param id entity id
     * @return Barber entity
     */

    public Barber getEntityById(Long id) {
        return barberRepository.getBarberById(id)
                .orElseThrow(() -> new RuntimeException("Barber with id=" + id + " not found."));
    }

    /**
     * Save or change entity in db
     * @param dto entity object
     */
    @Transactional
    public BarberDto save(BarberDto dto) {
        Barber savedBarber = barberRepository.save(BarberMapper.toEntity(dto));
        return BarberMapper.toDto(savedBarber);
    }
}
