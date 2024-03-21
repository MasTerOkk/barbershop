package ru.practice.barbershop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Barber {
    @Id
    @SequenceGenerator(
            name = "barber_sequence",
            sequenceName = "barber_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "barber_sequence"
    )
    private Long id;
    private String phone;
    private String email;

    @OneToMany(mappedBy = "barber")
    private List<Order> orders;
}
