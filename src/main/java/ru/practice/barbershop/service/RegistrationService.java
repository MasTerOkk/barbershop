package ru.practice.barbershop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practice.barbershop.dto.RegistrationsDto;
import ru.practice.barbershop.mapper.RegistrationsMapper;
import ru.practice.barbershop.model.Barber;
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
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final ClientService clientService;
    private final BarberService barberService;
    /**
     * Return Registration entity by his id
     * @param id entity id
     * @return Registration entity
     */
    public Registration getById(Long id) {
        return registrationRepository.getRegistrationById(id)
                .orElseThrow(() -> new RuntimeException("Registration with id=" + id + " not found."));
    }


    /**
     * Save entity in db
     * @param dto Registration dto object
     * @throws RuntimeException Registration has already been created
     */
    @Transactional
    public RegistrationsDto save(RegistrationsDto dto) throws RuntimeException {

        Registration entity = RegistrationsMapper.toEntity(dto);

        List<Registration> registrationList = registrationRepository.
                getRegistrationsByTimeAndDayAndBarber(entity.getTime(),entity.getDay(), entity.getBarber());

        for (Registration registration: registrationList) {
            if (!registration.getCanceled()) {
                throw new RuntimeException("Registration on " + entity.getDay() +
                        " " + entity.getTime() + " has already been created.");
            }
        }

        Registration registration = registrationRepository.save(entity);
        return RegistrationsMapper.toDto(registration);
    }

    /**
     * Cancel registration
     * @param time The time of registration
     * @param date The date of registration
     * @param barberId Barber
     * @throws RuntimeException Registration not found
     */
    @Transactional
    public RegistrationsDto canceled(LocalTime time, LocalDate date, Long barberId) throws RuntimeException {

        List<Registration> registrationList = registrationRepository.
                getRegistrationsByTimeAndDayAndBarber(time,date,barberService.getEntityById(barberId));
        if (registrationList != null) {
            for (Registration registration: registrationList) {
                if (!registration.getCanceled()) {
                    registration.setCanceled(Boolean.TRUE);
                    registrationRepository.save(registration);
                    return RegistrationsMapper.toDto(registration);
                }
            }
        }
        throw new RuntimeException("Registration on " + date +
                " " + time + " not found.");

    }

    /**
     * For link Registration to Client, Barber and get basic initialization
     * @param time The time of registration
     * @param date The date of registration
     * @param clientId The client who registering
     * @param barberId The barber
     * @return RegistrationDto obj link to the client and barber
     */
    public RegistrationsDto setClient(LocalTime time, LocalDate date, Long clientId, Long barberId) {
        Client client = clientService.getEntityById(clientId);
        Barber barber = barberService.getEntityById(barberId);
        Registration registration = new Registration();

        registration.setClient(client);
        registration.setBarber(barber);
        registration.setRegistrationTime(LocalDateTime.now());
        registration.setPhone(client.getPhone());
        registration.setClientName(client.getName());
        registration.setCanceled(Boolean.FALSE);
        registration.setDay(date);
        registration.setTime(time);

        return RegistrationsMapper.toDto(registration);
    }
}
