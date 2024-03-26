package ru.practice.barbershop.dto;

import lombok.Data;
import ru.practice.barbershop.general.BarberStatus;

@Data
public class BarberDto {
    private Long id;
    private String phone;
    private String email;
    private BarberStatus barberStatus;
}
