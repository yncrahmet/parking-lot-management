package com.archisacademy.parking_reservation.repository;

import com.archisacademy.parking_reservation.entity.ParkingReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingReservationRepository extends JpaRepository<ParkingReservation,Long> {
}
