package ru.practice.barbershop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practice.barbershop.general.MyService;
import ru.practice.barbershop.model.Client;
import ru.practice.barbershop.model.Registration;
import ru.practice.barbershop.repository.RegistrationRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * This is service class for maintain the registration entity
 */
@Service
@AllArgsConstructor
public class RegistrationService implements MyService<Registration> {
    private final RegistrationRepository registrationRepository;
    private final ClientService clientService;
    /**
     * Return Registration entity by his id
     * @param id entity id
     * @return Registration entity
     */
    @Override
    public Registration getById(Long id) {
        return registrationRepository.getRegistrationById(id)
                .orElseThrow(() -> new RuntimeException("Registration with id=" + id + " not found."));
    }


    /**
     * Save entity in db
     * @param entity Registration entity object
     * @throws RuntimeException Registration has already been created
     */
    @Override
    public void save(Registration entity) throws RuntimeException {
        List<Registration> registrationList = registrationRepository.
                getRegistrationsByTimeAndDay(entity.getTime(),entity.getDay());
        for (Registration registration: registrationList) {
            if (!registration.getCanceled()) {
                throw new RuntimeException("Registration on " + entity.getDay() +
                        " " + entity.getTime() + " has already been created.");
            }
        }
        registrationRepository.save(entity);
    }

    /**
     * Cancel registration
     * @param time The time of registration
     * @param date The date of registration
     * @throws RuntimeException Registration not found
     */
    public void canceled(LocalTime time, LocalDate date) throws RuntimeException {
        List<Registration> registrationList = registrationRepository.
                getRegistrationsByTimeAndDay(time,date);
        if (registrationList != null) {
            for (Registration registration: registrationList) {
                if (!registration.getCanceled()) {
                    registration.setCanceled(Boolean.TRUE);
                    registrationRepository.save(registration);
                    break;
                }
            }
        } else {
            throw new RuntimeException("Registration on " + date +
                    " " + time + " not found.");
        }
    }

    /**
     * For link Registration to Client and get basic initialization
     * @param time The time of registration
     * @param date The date of registration
     * @param clientId The client who registering
     * @return Registration entity link to the client
     */
    public Registration setClient(LocalTime time, LocalDate date, Long clientId) {
        Client client = clientService.getById(clientId);
        Registration registration = new Registration();

        registration.setClient(client);
        registration.setRegistrationTime(LocalDateTime.now());
        registration.setPhone(client.getPhone());
        registration.setClientName(client.getName());
        registration.setCanceled(Boolean.FALSE);
        registration.setDay(date);
        registration.setTime(time);

        return registration;
    }
}
