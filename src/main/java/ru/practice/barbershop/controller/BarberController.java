package ru.practice.barbershop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practice.barbershop.dto.BarberDto;
import ru.practice.barbershop.general.BarberStatus;
import ru.practice.barbershop.service.BarberService;

/**
 * Rest controller for barbers
 */
@RestController
@RequestMapping(value = "/barber")
@AllArgsConstructor
public class BarberController {

    private final BarberService barberService;

    /**
     * Logic for save barber
     * @param barber Barber entity
     * @return Answer
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BarberDto> createBarber(@RequestBody BarberDto barber) {
        try {
            BarberDto savedBarber = barberService.save(barber);
            return new ResponseEntity<>(savedBarber, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Change status of barber
     * @param id Barber's ID
     * @param status new status for Barber
     * @return Answer
     */
    @RequestMapping(value = "/change", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BarberDto> changeStatus (
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "status") String status
            ) {
        try {
            BarberDto barber = barberService.getDtoById(id);
            barber.setBarberStatus(BarberStatus.valueOf(status.toUpperCase()));
            barberService.save(barber);
            return new ResponseEntity<>(barber, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
