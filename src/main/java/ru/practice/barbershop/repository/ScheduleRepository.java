package ru.practice.barbershop.repository;

import lombok.Getter;

import java.io.*;
import java.util.Properties;
/**
 * This is repository class for up-to-date information from properties file
 */
@Getter
public class ScheduleRepository {

    private Properties properties;

    /**
     * Set property from file
     * @param file properties file name
     */
    public void setProperties(String file) {
        // Path to the prop file
        String configFilePath = "src/main/resources/" + file;
        File ConfigFile=new File(configFilePath);
        try {
            FileInputStream configFileReader=new FileInputStream(ConfigFile);
            this.properties = new Properties();
            try {
                this.properties.load(configFileReader);
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
     * Upload new data to properties file
     * @param file properties file name
     */
    public void uploadProperties(String file) {
        // Path to the prop file
        String configFilePath = "src/main/resources/" + file;
        File ConfigFile = new File (configFilePath);
        try {
            FileOutputStream configFileReader = new FileOutputStream(ConfigFile);
            try {
                this.properties.store(configFileReader, "Updated property value");
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
