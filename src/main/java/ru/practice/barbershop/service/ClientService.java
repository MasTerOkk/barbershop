package ru.practice.barbershop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practice.barbershop.general.MyService;
import ru.practice.barbershop.model.Client;
import ru.practice.barbershop.repository.ClientRepository;

/**
 * This is service class for maintain the client entity
 */
@Service
@AllArgsConstructor
public class ClientService implements MyService<Client> {

    private final ClientRepository clientRepository;

    /**
     * Return Client entity by his id
     * @param id entity id
     * @return Client entity
     */
    @Override
    public Client getById(Long id) {
        return clientRepository.getClientById(id)
                .orElseThrow(() -> new RuntimeException("Client with id=" + id + " not found."));
    }

    /**
     * Save or change entity in db
     * @param client entity object
     */
    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }
}
