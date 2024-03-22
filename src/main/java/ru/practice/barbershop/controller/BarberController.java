package ru.practice.barbershop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practice.barbershop.general.BarberStatus;
import ru.practice.barbershop.model.Barber;
import ru.practice.barbershop.service.BarberService;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/barber")
@AllArgsConstructor
public class BarberController {

    private final BarberService barberService;
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String saveBarber(
            @RequestBody Barber barber
    ) {
        try {
            barberService.save(barber);
            return "Barber saved";
        } catch (Exception e) {
            return "Wrong data";
        }
    }

    @RequestMapping(value = "/change",
            method = RequestMethod.POST)
    @ResponseBody
    public String changeStatus (
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "status") String status
            ) {
        try {
            Barber barber = barberService.getById(id);
            barber.setBarberStatus(BarberStatus.valueOf(status.toUpperCase()));
            barberService.save(barber);
            return "Barber changed";
        } catch (Exception e) {
            return "Wrong data";
        }
    }

}
