package ru.practice.barbershop.service;

import org.springframework.stereotype.Service;
import ru.practice.barbershop.general.MyDayOfWeek;
import ru.practice.barbershop.repository.ScheduleRepository;

import java.util.HashMap;
import java.util.Map;


/**
 * This is service class for maintain the schedule
 */
@Service
public class ScheduleService {
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
        ScheduleRepository scheduleRepository = new ScheduleRepository();
        scheduleRepository.setProperties("schedule.properties");
        String mondayStart= scheduleRepository.getProperties()
                .getProperty("week." + dayOfWeek.toString().toLowerCase() +".start");

        String mondayEnd= scheduleRepository.getProperties()
                .getProperty("week." + dayOfWeek.toString().toLowerCase() + ".end");

        String day;

        if(mondayStart != null && mondayEnd != null) {

            if (mondayStart.equals("none") || mondayEnd.equals("none")) {
                day = "Weekend";
            } else {
                day = mondayStart + "-" + mondayEnd;
            }

            return day;
        }
        throw new RuntimeException("Application url not specified in the config.properties file.");
    }

    /**
     * Get barbershop info
     * @return HashMap<String, String> of barbershop info
     */
    public HashMap<String, String> getInfoOfBarbershop() {
        ScheduleRepository scheduleRepository = new ScheduleRepository();
        scheduleRepository.setProperties("schedule.properties");

        HashMap<String, String> hashMap = new HashMap<>();
        String email= scheduleRepository.getProperties().getProperty("barbershop.email");
        String address= scheduleRepository.getProperties().getProperty("barbershop.address");
        String phone= scheduleRepository.getProperties().getProperty("barbershop.phone");

        if (email != null && address != null && phone != null) {
            hashMap.put("email", email);
            hashMap.put("address", address);
            hashMap.put("phone", phone);
            return hashMap;
        }
        throw new RuntimeException("Information not found");
    }

    //TODO hard code

    /**
     * Update schedule
     */
    public void newSchedule() {
        try {
            //initialize repos
            ScheduleRepository scheduleRepository = new ScheduleRepository();
            scheduleRepository.setProperties("schedule.properties");

            ScheduleRepository newScheduleRepository = new ScheduleRepository();
            newScheduleRepository.setProperties("newSchedule.properties");
            // Hard code. Get every property
            HashMap<String, String> schedule = new HashMap<>();
            schedule.put("week.monday.start", newScheduleRepository.getProperties().getProperty("week.monday.start"));
            schedule.put("week.monday.end", newScheduleRepository.getProperties().getProperty("week.monday.end"));

            schedule.put("week.tuesday.start", newScheduleRepository.getProperties().getProperty("week.tuesday.start"));
            schedule.put("week.tuesday.end", newScheduleRepository.getProperties().getProperty("week.tuesday.end"));

            schedule.put("week.wednesday.start", newScheduleRepository.getProperties().getProperty("week.wednesday.start"));
            schedule.put("week.wednesday.end", newScheduleRepository.getProperties().getProperty("week.wednesday.end"));

            schedule.put("week.thursday.start", newScheduleRepository.getProperties().getProperty("week.thursday.start"));
            schedule.put("week.thursday.end", newScheduleRepository.getProperties().getProperty("week.thursday.end"));

            schedule.put("week.friday.start", newScheduleRepository.getProperties().getProperty("week.friday.start"));
            schedule.put("week.friday.end", newScheduleRepository.getProperties().getProperty("week.friday.end"));

            schedule.put("week.saturday.start", newScheduleRepository.getProperties().getProperty("week.saturday.start"));
            schedule.put("week.saturday.end", newScheduleRepository.getProperties().getProperty("week.saturday.end"));

            schedule.put("week.sunday.start", newScheduleRepository.getProperties().getProperty("week.sunday.start"));
            schedule.put("week.sunday.end", newScheduleRepository.getProperties().getProperty("week.sunday.end"));

            schedule.put("barbershop.email", newScheduleRepository.getProperties().getProperty("barbershop.email"));
            schedule.put("barbershop.address", newScheduleRepository.getProperties().getProperty("barbershop.address"));
            schedule.put("barbershop.phone", newScheduleRepository.getProperties().getProperty("barbershop.phone"));

            //Add values from hashmap to property
            for (Map.Entry<String, String> entry : schedule.entrySet()) {
                if (entry.getValue() != null) {
                    scheduleRepository.getProperties().setProperty(entry.getKey(),entry.getValue());
                }
            }
            //Update properties file
            scheduleRepository.uploadProperties("schedule.properties");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Information not found");
        }

    }
}
