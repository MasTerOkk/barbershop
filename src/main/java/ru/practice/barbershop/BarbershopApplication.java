package ru.practice.barbershop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.practice.barbershop.service.ScheduleService;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;

@SpringBootApplication
public class BarbershopApplication {

	public static void main(String[] args) {
//		SpringApplication.run(BarbershopApplication.class, args);
		ScheduleService service = new ScheduleService();
		HashMap<String, LocalTime> time = service.getMonday();
		System.out.println(time);
	}

}
