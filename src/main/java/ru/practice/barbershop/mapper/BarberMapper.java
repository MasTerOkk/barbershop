package ru.practice.barbershop.mapper;

import ru.practice.barbershop.dto.BarberDto;
import ru.practice.barbershop.model.Barber;

public class BarberMapper {
    public static BarberDto toDto(Barber entity) {
        BarberDto barber = new BarberDto();

        barber.setId(entity.getId());
        barber.setEmail(entity.getEmail());
        barber.setBarberStatus(entity.getBarberStatus());
        barber.setPhone(entity.getPhone());

        return barber;
    }

    public static Barber toEntity(BarberDto dto) {
        Barber barber = new Barber();

        barber.setId(dto.getId());
        barber.setPhone(dto.getPhone());
        barber.setEmail(dto.getEmail());
        barber.setBarberStatus(dto.getBarberStatus());

        return barber;
    }
}
