package ru.practice.barbershop.model;

import jakarta.persistence.*;
import lombok.Data;
import ru.practice.barbershop.general.BarberStatus;

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
    private BarberStatus barberStatus;

    @OneToMany(mappedBy = "barber")
    private List<Order> orderList;
    @OneToMany(mappedBy = "barber")
    private List<Registration> registrationList;
}
