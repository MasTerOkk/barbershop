package ru.practice.barbershop.controller;

import lombok.AllArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import ru.practice.barbershop.general.MyDayOfWeek;
import ru.practice.barbershop.service.ScheduleService;

import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/info")
public class InformationController {

    private final ScheduleService scheduleService;

    /**
     * Get up-to-date schedule for current week
     */
    @RequestMapping (
            path = "/schedule",
            method = RequestMethod.GET
    )
    @ResponseBody
    public JSONObject schedule() {
        HashMap<String,String> schedule = scheduleService.getScheduleOfWeek();
        JSONObject Json = new JSONObject();

        Json.put("Monday", schedule.get("monday"));
        Json.put("Tuesday", schedule.get("tuesday"));
        Json.put("Wednesday", schedule.get("wednesday"));
        Json.put("Thursday", schedule.get("thursday"));
        Json.put("Friday", schedule.get("friday"));
        Json.put("Saturday", schedule.get("saturday"));
        Json.put("Sunday", schedule.get("sunday"));

        return Json;
    }

    /**
     * Get up-to-date schedule for current day in week
     */
    @RequestMapping (
            path = "/schedule/{day}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public JSONObject scheduleForCurrentDay(@PathVariable(name = "day") String day) {
        JSONObject Json = new JSONObject();
        try {
            String currentDay = scheduleService.getDay(MyDayOfWeek.valueOf(day.toUpperCase()));
            if (currentDay == null || currentDay.isEmpty()) {
                Json.put(day.toLowerCase(), "not found");
                return Json;
            }
            Json.put(day.toLowerCase(), currentDay);
        } catch (Exception e) {
            Json.put(day.toLowerCase(), "not found");
            System.out.println(e.getMessage());
        }
        return Json;

    }
}
