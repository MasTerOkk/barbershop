package ru.practice.barbershop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practice.barbershop.dto.BarberDto;
import ru.practice.barbershop.general.MyService;
import ru.practice.barbershop.mapper.BarberMapper;
import ru.practice.barbershop.model.Barber;
import ru.practice.barbershop.repository.BarberRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This is service class for maintain the barber entity
 */
@Service
@AllArgsConstructor
public class BarberService implements MyService<BarberDto, Barber> {
    private final BarberRepository barberRepository;

    /**
     * Return Barber entity by his id
     * @param id entity id
     * @return Barber dto
     */
    @Override
    public BarberDto getDtoById(Long id) {
        return BarberMapper.toDto(barberRepository.getBarberById(id)
                .orElseThrow(() -> new RuntimeException("Barber with id=" + id + " not found.")));
    }

    /**
     * Return Barber entity by his id
     * @param id entity id
     * @return Barber entity
     */
    @Override
    public Barber getEntityById(Long id) {
        return barberRepository.getBarberById(id)
                .orElseThrow(() -> new RuntimeException("Barber with id=" + id + " not found."));
    }

    /**
     * Save or change entity in db
     * @param dto entity object
     */
    @Override
    @Transactional
    public BarberDto save(BarberDto dto) {
        dto.setId(null);
        Barber savedBarber = barberRepository.save(BarberMapper.toEntity(dto));
        return BarberMapper.toDto(savedBarber);
    }

    @Override
    public BarberDto update(BarberDto dto) {
        return BarberMapper.toDto(barberRepository.save(BarberMapper.toEntity(dto)));
    }

    @Override
    public List<BarberDto> getAllDto() {
        List<Barber> barbers = barberRepository.findAll();
        return barbers.stream().map(BarberMapper::toDto)
                .collect(Collectors.toList());
    }
}
