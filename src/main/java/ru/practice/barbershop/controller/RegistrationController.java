package ru.practice.barbershop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practice.barbershop.model.Registration;
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
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestParam(name = "time") LocalTime time,
                           @RequestParam(name = "day") LocalDate day,
                           @RequestParam(name = "clientId") Long clientId) {
        try {
            Registration registration = registrationService.setClient(time, day, clientId);
            registrationService.save(registration);
            return "registration success";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }

    }

    /**
     * Cancel registration
     * @param time The time of registration
     * @param day The date of registration
     * @return Answer
     */
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public String cancel(@RequestParam(name = "time") LocalTime time,
                         @RequestParam(name = "day") LocalDate day) {
        try {
            registrationService.canceled(time,day);
            return "Registration canceled";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }

    }

}
