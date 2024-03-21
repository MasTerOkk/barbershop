package ru.practice.barbershop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.practice.barbershop.service.ScheduleService;

import java.util.HashMap;

@RestController
@AllArgsConstructor
public class InformationController {

    private final ScheduleService scheduleService;

    /**
     * Get up-to-date schedule for current week
     */
    @GetMapping(
            path = "/schedule"
    )
    public HashMap<String,String> schedule() {
        return scheduleService.getScheduleOfWeek();
    }

    /**
     * Get up-to-date schedule for current day in week
     */
    @GetMapping(
            path = "/schedule/{day}"
    )
    public String scheduleForCurrentDay(@PathVariable(name = "day") String day) {
        String currentDay = scheduleService.getCurrentDay(day);
        if (currentDay == null) {
            return day + " not found";
        }
        return currentDay;
    }



}
