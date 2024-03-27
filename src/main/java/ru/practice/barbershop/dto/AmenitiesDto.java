package ru.practice.barbershop.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Amenities dto for Amenities entity
 */
@Data
public class AmenitiesDto implements Serializable {
    private Long id;
    private Integer price;
    private String name;
}
