package ru.practice.barbershop.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AmenitiesDto implements Serializable {
    private Long id;
    private Integer price;
    private String name;
}
