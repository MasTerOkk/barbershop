package ru.practice.barbershop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practice.barbershop.dto.RegistrationsDto;
import ru.practice.barbershop.service.RegistrationService;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Rest controller for registration
 */
@RestController
@AllArgsConstructor
@RequestMapping("/reg")
public class RegistrationController {

    private final RegistrationService registrationService;

    /**
     * Register client logic
     * @param time The time of registration
     * @param day The date of registration
     * @param clientId The client's id which is being registered
     * @return Answer
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<RegistrationsDto> register(
              @RequestParam(name = "time") LocalTime time,
              @RequestParam(name = "day") LocalDate day,
              @RequestParam(name = "clientId") Long clientId,
              @RequestParam(name = "barberId") Long barberId) {
        try {
            RegistrationsDto registration = registrationService
                    .save(registrationService.setClient(time, day, clientId, barberId));

            return new ResponseEntity<>(registration, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Cancel registration
     * @param time The time of registration
     * @param day The date of registration
     * @return Answer
     */
    @RequestMapping(value = "/cancel", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded")
    @ResponseBody
    public ResponseEntity<RegistrationsDto>  cancel(
                         @RequestParam(name = "time") LocalTime time,
                         @RequestParam(name = "day") LocalDate day,
                         @RequestParam(name = "barberId") Long barberId) {
        try {
            RegistrationsDto registration = registrationService.canceled(time,day,barberId);
            return new ResponseEntity<>(registration, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
