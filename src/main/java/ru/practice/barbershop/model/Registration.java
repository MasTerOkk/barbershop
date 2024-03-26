package ru.practice.barbershop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "registrations")
@Data
public class Registration {
    @Id
    @SequenceGenerator(
            name = "registration_sequence",
            sequenceName = "registration_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "registration_sequence"
    )
    private Long id;
    private LocalTime time;
    private LocalDate day;
    //If user is unanimous get from form on site or get from Client table
    private String clientName;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    private LocalDateTime registrationTime;
    private Boolean canceled;
}
