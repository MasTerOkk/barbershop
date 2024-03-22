package ru.practice.barbershop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practice.barbershop.general.MyDayOfWeek;
import ru.practice.barbershop.repository.ScheduleRepository;

import java.util.HashMap;


/**
 * This is service class for maintain the schedule
 */
@Service
@AllArgsConstructor
public class ScheduleService {


    private final ScheduleRepository scheduleRepository;

    //TODO подумать как можно преобразовать строку в MyDayOfWeek.param
    /**
     * This is a function to get the current day
     * @Param day The <code>String</code> variable witch represents the day of the week
     */
    public String getCurrentDay(String day) {
        return getScheduleOfWeek().get(day.toLowerCase());
    }

    /**
     * This is a function for getting the weekly schedule
     */
    public HashMap<String, String> getScheduleOfWeek() {

        HashMap<String, String> schedule = new HashMap<>();

        schedule.put("monday", scheduleRepository.getDay(MyDayOfWeek.monday));
        schedule.put("tuesday", scheduleRepository.getDay(MyDayOfWeek.tuesday));
        schedule.put("wednesday", scheduleRepository.getDay(MyDayOfWeek.wednesday));
        schedule.put("thursday", scheduleRepository.getDay(MyDayOfWeek.thursday));
        schedule.put("friday", scheduleRepository.getDay(MyDayOfWeek.friday));
        schedule.put("saturday", scheduleRepository.getDay(MyDayOfWeek.saturday));
        schedule.put("sunday", scheduleRepository.getDay(MyDayOfWeek.sunday));

        return schedule;
    }




}
