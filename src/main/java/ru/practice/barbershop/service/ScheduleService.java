package ru.practice.barbershop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practice.barbershop.general.MyDayOfWeek;
import ru.practice.barbershop.repository.ScheduleRepository;

import java.util.HashMap;


/**
 * This is service class for maintain the schedule
 */
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    /**
     * This is a function for getting the weekly schedule
     */
    public HashMap<String, String> getScheduleOfWeek() {

        HashMap<String, String> schedule = new HashMap<>();

        schedule.put("monday", getDay(MyDayOfWeek.MONDAY));
        schedule.put("tuesday", getDay(MyDayOfWeek.TUESDAY));
        schedule.put("wednesday", getDay(MyDayOfWeek.WEDNESDAY));
        schedule.put("thursday", getDay(MyDayOfWeek.THURSDAY));
        schedule.put("friday", getDay(MyDayOfWeek.FRIDAY));
        schedule.put("saturday", getDay(MyDayOfWeek.SATURDAY));
        schedule.put("sunday", getDay(MyDayOfWeek.SUNDAY));

        return schedule;
    }

    /**
     * Get schedule for current day from properties file
     */
    public String getDay(MyDayOfWeek dayOfWeek) {

        String mondayStart= scheduleRepository.getProperties().getProperty("week." + dayOfWeek.toString().toLowerCase() +".start");
        String mondayEnd= scheduleRepository.getProperties().getProperty("week." + dayOfWeek.toString().toLowerCase() + ".end");
        String day;

        if(mondayStart != null && mondayEnd != null) {

            if (mondayStart.equals("none") || mondayEnd.equals("none")) {
                day = "Weekend";
            } else {
                day = mondayStart + "-" + mondayEnd;
            }

            return day;
        }
        else {
            throw new RuntimeException("Application url not specified in the config.properties file.");
        }
    }





}
