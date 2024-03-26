package ru.practice.barbershop.repository;

import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * This is repository class for up-to-date information from properties file
 */
@Repository
@Getter
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
}
