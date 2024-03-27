package ru.practice.barbershop.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Order dto for Order entity
 */
@Data
public class OrderDto {
    private Long id;
    private Integer price;
    private LocalTime time;
    private LocalDate day;
    private String clientName;
    private String phone;
    private Byte mark;
    private Long barber_id;
    private Long client_id;
}
