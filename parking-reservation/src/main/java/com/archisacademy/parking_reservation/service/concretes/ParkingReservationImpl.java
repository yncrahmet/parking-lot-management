package com.archisacademy.parking_reservation.service.concretes;

import com.archisacademy.parking_reservation.apiResponse.ApiResponse;
import com.archisacademy.parking_reservation.dto.request.ParkingReservationRequest;
import com.archisacademy.parking_reservation.dto.request.ParkingReservationUpdateRequest;
import com.archisacademy.parking_reservation.dto.response.ParkingReservationResponse;
import com.archisacademy.parking_reservation.entity.ParkingReservation;

import com.archisacademy.parking_reservation.exception.ParkingReservationNotFoundException;
import com.archisacademy.parking_reservation.modelMapper.ModelMapperServiceImpl;
import com.archisacademy.parking_reservation.repository.ParkingReservationRepository;
import com.archisacademy.parking_reservation.service.abstracts.ParkingReservationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingReservationImpl implements ParkingReservationService {
    private final ParkingReservationRepository parkingReservationRepository;
    private final ModelMapperServiceImpl modelMapperService;

    @Transactional()
    @Override
    public ApiResponse<ParkingReservationResponse> addParkingReservation( ParkingReservationRequest reservationRequest) {

        ParkingReservation reservation=modelMapperService.request().map(reservationRequest, ParkingReservation.class);
        ParkingReservation saved=parkingReservationRepository.save(reservation);
        ParkingReservationResponse response= modelMapperService.request().
                map(saved, ParkingReservationResponse.class);
        return new ApiResponse<>(true,"Parking reservation added successfully",response);
    }

    @Override
    public ApiResponse<ParkingReservationRequest> getParkingReservationById(long id) {
        Optional<ParkingReservation> reservation = parkingReservationRepository.findById(id);
        if (reservation.isEmpty()) {
            throw new ParkingReservationNotFoundException("Parking Reservation not found for ID: " + id);
        }
        ParkingReservationRequest reservationRequest = modelMapperService.request().map(reservation.get(), ParkingReservationRequest.class);
        return new ApiResponse<>(true,"Parking reservation found successfully",reservationRequest);
    }

    @Override
    public ApiResponse<ParkingReservationResponse> updateParkingReservation(Long id,ParkingReservationUpdateRequest
                                                                                        reservationRequest) {
      ParkingReservation reservation=parkingReservationRepository.findById(id).get();
      modelMapperService.request().map(reservationRequest, reservation);
      ParkingReservation saved=parkingReservationRepository.save(reservation);
      ParkingReservationResponse response=modelMapperService.request().map(reservation, ParkingReservationResponse.class);
      return new ApiResponse<>(true,"Parking reservation updated successfully",response);
    }

    @Transactional
    @Override
    public ApiResponse<String> deleteParking(long id) {
        Optional<ParkingReservation> reservation = parkingReservationRepository.findById(id);
        if (reservation.isEmpty()) {
            throw new ParkingReservationNotFoundException("Parking Reservation not found for ID: " + id);
        }
        parkingReservationRepository.delete(reservation.get());
        return new ApiResponse<>(true,"Parking reservation deleted successfully",null);
    }

}
