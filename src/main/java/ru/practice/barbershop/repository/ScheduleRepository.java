package ru.practice.barbershop.repository;

import org.springframework.stereotype.Repository;
import ru.practice.barbershop.conf.MyDayOfWeek;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * This is repository class for up-to-date information from properties file
 */
@Repository
public class ScheduleRepository {

    private final Properties properties;

    public ScheduleRepository() {
        // Path to the prop file
        String configFilePath = "src/main/resources/schedule.properties";
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

    /**
     * Get schedule for current day from properties file
     */
    public String getDay(MyDayOfWeek dayOfWeek) {

        String mondayStart= properties.getProperty("week." + dayOfWeek +".start");
        String mondayEnd= properties.getProperty("week." + dayOfWeek + ".end");
        String day;

        if(mondayStart != null && mondayEnd != null) {

            if (mondayStart.equals("none") || mondayEnd.equals("none")) {
                day = "Weekend";
            } else {
                day = mondayStart + "-" + mondayEnd;
            }

            return day;
        }
        else
            throw new RuntimeException("Application url not specified in the config.properties file.");
    }
}
