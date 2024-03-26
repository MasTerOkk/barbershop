package ru.practice.barbershop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Amenities {
    @Id
    @SequenceGenerator(
            name = "amenities_sequence",
            sequenceName = "amenities_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "amenities_sequence"
    )
    private Long id;
    private Integer price;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "ordered_amenities",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<Order> orderList;
}
