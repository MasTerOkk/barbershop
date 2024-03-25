package ru.practice.barbershop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "orders")
@Data
//create when client came to barbershop
public class Order {
    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    private Long id;
    //Get after client's cut hair is over
    private Integer price;
    //Get from registration
    private LocalTime time;
    private LocalDate day;
    //If user is unanimous get from form on site or get from Client table
    private String clientName;
    private String phone;
    //Evaluation of the order by the client 0 to 5 star
    private Byte mark;
    @ManyToOne
    @JoinColumn(name = "barber_id")
    private Barber barber;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
