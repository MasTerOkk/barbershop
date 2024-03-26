package ru.practice.barbershop.mapper;

import ru.practice.barbershop.dto.ClientDto;
import ru.practice.barbershop.model.Client;

public class ClientMapper {
    public static ClientDto toDto(Client entity) {
        ClientDto client = new ClientDto();

        client.setId(client.getId());
        client.setName(entity.getName());
        client.setPhone(entity.getPhone());
        client.setLogin(entity.getLogin());
        client.setPassword(entity.getPassword());
        client.setDeleted(entity.getDeleted());

        return client;
    }

    public static Client toEntity(ClientDto dto) {
        Client client = new Client();

        client.setId(dto.getId());
        client.setDeleted(dto.getDeleted());
        client.setPassword(dto.getPassword());
        client.setLogin(dto.getLogin());
        client.setName(dto.getName());
        client.setPhone(dto.getPhone());

        return client;
    }
}
