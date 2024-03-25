package ru.practice.barbershop.model;

import lombok.Data;

import java.util.List;

@Data
public class Amenities {
    private Long id;
    private Integer price;
    private String name;
    private List<Order> orderList;
}
