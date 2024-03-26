package ru.practice.barbershop.dto;

import lombok.Data;

@Data
public class ClientDto {
    private Long id;
    private String login;
    private String name;
    private String phone;
    private String password;
    private Boolean deleted;
}
