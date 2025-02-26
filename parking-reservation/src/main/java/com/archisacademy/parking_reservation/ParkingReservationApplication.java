package com.archisacademy.parking_reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class ParkingReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingReservationApplication.class, args);
	}

}
