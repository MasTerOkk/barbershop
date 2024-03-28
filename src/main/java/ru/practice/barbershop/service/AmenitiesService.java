package ru.practice.barbershop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practice.barbershop.dto.AmenitiesDto;
import ru.practice.barbershop.general.MyService;
import ru.practice.barbershop.mapper.AmenitiesMapper;
import ru.practice.barbershop.model.Amenities;
import ru.practice.barbershop.repository.AmenitiesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AmenitiesService implements MyService<AmenitiesDto, Amenities> {
    private final AmenitiesRepository amenitiesRepository;
    @Override
    public AmenitiesDto getDtoById(Long id) {
        return AmenitiesMapper.toDto(amenitiesRepository.getAmenitiesById(id)
                .orElseThrow(() -> new RuntimeException("Amenity with id=" + id + " not found.")));
    }

    @Override
    public Amenities getEntityById(Long id) {
        return amenitiesRepository.getAmenitiesById(id)
                .orElseThrow(() -> new RuntimeException("Amenity with id=" + id + " not found."));
    }

    @Override
    public AmenitiesDto save(AmenitiesDto dto) {
        dto.setId(null);
        Amenities savedAmenity = amenitiesRepository.save(AmenitiesMapper.toEntity(dto));
        return AmenitiesMapper.toDto(savedAmenity);
    }

    @Override
    public AmenitiesDto update(AmenitiesDto dto) {
        return AmenitiesMapper.toDto(amenitiesRepository.save(AmenitiesMapper.toEntity(dto)));
    }

    @Override
    public List<AmenitiesDto> getAllDto() {
        List<Amenities> amenities = amenitiesRepository.findAll();
        return amenities.stream().map(AmenitiesMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateEntity(Amenities amenities) {
        amenitiesRepository.save(amenities);
    }

    public Double getAvgPrice() {
        List<Amenities> amenities = amenitiesRepository.findAll();
        Double sumPrice = 0D;
        for (Amenities a : amenities) {
            sumPrice += a.getPrice();
        }
        return !amenities.isEmpty() ? sumPrice / amenities.size() : sumPrice;
    }
}
