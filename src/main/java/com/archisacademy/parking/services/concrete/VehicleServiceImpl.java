package com.archisacademy.parking.services.concrete;

import com.archisacademy.parking.ApiResponse.ApiResponse;
import com.archisacademy.parking.ModelMappper.ModelMapperService;
import com.archisacademy.parking.dtos.request.VehicleRequest;
import com.archisacademy.parking.dtos.request.VehicleUpdateRequest;
import com.archisacademy.parking.dtos.response.VehicleResponse;
import com.archisacademy.parking.exception.VehicleNotFoundException;
import com.archisacademy.parking.model.Vehicle;
import com.archisacademy.parking.repositories.VehicleRepository;
import com.archisacademy.parking.services.abstracts.VehicleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ModelMapperService modelMapperService;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, ModelMapperService modelMapperService) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public ApiResponse<VehicleResponse> save(VehicleRequest vehicleRequest) {

        Vehicle vehicle = modelMapperService.request().map(vehicleRequest, Vehicle.class);

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        VehicleResponse vehicleResponse = modelMapperService.response().map(savedVehicle, VehicleResponse.class);

        return new ApiResponse<>(true, "Vehicle saved successfully.", vehicleResponse);
    }


    @Override
    public ApiResponse<VehicleResponse> update(Long vehicleId, VehicleUpdateRequest vehicleUpdateRequest) {

        Vehicle vehicle = getVehicleById(vehicleId);

        modelMapperService.request().map(vehicleUpdateRequest, vehicle);

        Vehicle updatedVehicle = vehicleRepository.save(vehicle);

        VehicleResponse vehicleResponse = modelMapperService.response().map(updatedVehicle, VehicleResponse.class);

        return new ApiResponse<>(true,"Vehicle updated successfully.", vehicleResponse);
    }

    @Override
    public ApiResponse<String> delete(Long vehicleId) {

        Vehicle vehicle = getVehicleById(vehicleId);
        
        vehicleRepository.delete(vehicle);
        
        return new ApiResponse<>(true,"Vehicle deleted successfully.", null);
    }

    @Override
    public ApiResponse<VehicleResponse> get(Long vehicleId) {

        Vehicle vehicle = getVehicleById(vehicleId);

        VehicleResponse vehicleResponse = modelMapperService.response().map(vehicle, VehicleResponse.class);

        return new ApiResponse<>(true,"Vehicle found.", vehicleResponse);
    }

    @Override
    public ApiResponse<List<VehicleResponse>> getAll() {

        List<Vehicle> vehicles = vehicleRepository.findAll();

        List<VehicleResponse> vehicleResponseList = vehicles.stream()
                .map(vehicle -> modelMapperService.response().map(vehicle, VehicleResponse.class))
                .collect(Collectors.toList());

        return new ApiResponse<>(true,"Vehicles found.", vehicleResponseList);
    }

    private Vehicle getVehicleById (Long vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with id: " + vehicleId));
    }

}
