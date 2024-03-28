package ru.practice.barbershop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practice.barbershop.dto.AmenitiesDto;
import ru.practice.barbershop.service.AmenitiesService;

@RestController
@AllArgsConstructor
@RequestMapping("/amenity")
public class AmenitiesController {
    private final AmenitiesService amenitiesService;
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addAmenity(@RequestBody AmenitiesDto dto) {

        try {
            AmenitiesDto amenitiesDto = amenitiesService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(amenitiesDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
    @RequestMapping(value = "/avg", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAvgPrice() {
        return ResponseEntity.status(HttpStatus.OK).body("Avg price of amenities=" + amenitiesService.getAvgPrice());
    }

}
