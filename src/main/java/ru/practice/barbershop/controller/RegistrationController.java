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
     * @return Answer
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> register(
              @RequestBody RegistrationsDto registrationsDto) {
        try {
            RegistrationsDto registration = registrationService
                    .save(registrationService.setClient(registrationsDto));

            return ResponseEntity.status(HttpStatus.OK).body(registration);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    /**
     * Cancel registration
     * @param time The time of registration
     * @param day The date of registration
     * @return Answer
     */
    @RequestMapping(value = "/cancel", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?>  cancel(
                         @RequestParam(name = "time") LocalTime time,
                         @RequestParam(name = "day") LocalDate day,
                         @RequestParam(name = "barberId") Long barberId) {
        try {
            RegistrationsDto registration = registrationService.canceled(time,day,barberId);
            return ResponseEntity.status(HttpStatus.OK).body(registration);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

}
