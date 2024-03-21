package ru.practice.barbershop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Properties;

@Service
//TODO Доделать расписание для каждого дня недели
//TODO Сделать общее расписания
public class ScheduleService {


    private Properties properties;

    private final String configFilePath= "src/main/resources/schedule.properties";

    public ScheduleService() {
        File ConfigFile=new File(configFilePath);
        try {
            FileInputStream configFileReader=new FileInputStream(ConfigFile);
            properties = new Properties();
            try {
                properties.load(configFileReader);
                configFileReader.close();
            } catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }  catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException("config.properties not found at config file path " + configFilePath);
        }
    }

    //TODO Переделать с LocalTime на строки
    public HashMap<String, LocalTime> getMonday() {
        String mondayStart= properties.getProperty("week.monday.start");
        String mondayEnd= properties.getProperty("week.monday.end");

        if(mondayStart != null && mondayEnd != null) {

            String[] mondayParseStart = mondayStart.split(":");
            if (mondayParseStart[0].length() < 2) {
                mondayParseStart[0] = "0" + mondayParseStart[0];
            }
            if (mondayParseStart[1].length() < 2) {
                mondayParseStart[1] = "0" + mondayParseStart[1];
            }

            mondayStart = mondayParseStart[0] + ":" + mondayParseStart[1];

            String[] mondayParseEnd = mondayEnd.split(":");
            if (mondayParseEnd[0].length() < 2) {
                mondayParseEnd[0] = "0" + mondayParseEnd[0];
            }
            if (mondayParseEnd[1].length() < 2) {
                mondayParseEnd[1] = "0" + mondayParseEnd[1];
            }

            mondayEnd = mondayParseEnd[0] + ":" + mondayParseEnd[1];

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
                LocalTime start = LocalTime.parse(mondayStart,formatter);
                LocalTime end = LocalTime.parse(mondayEnd,formatter);
                HashMap<String, LocalTime> monday = new HashMap<>();

                monday.put("start", start);
                monday.put("end", end);

                return monday;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new RuntimeException("Wrong data from property file");
            }
        }
        else
            throw new RuntimeException("Application url not specified in the config.properties file.");
    }

}
