package com.dio.spring.api.service;

import com.dio.spring.api.model.dto.ParkingCreateDTO;
import com.dio.spring.api.model.dto.ParkingDTO;

import java.util.List;
import java.util.UUID;

public interface ParkingService {

    List<ParkingDTO> findAll();

    ParkingDTO findById(String id);

    ParkingDTO create(ParkingCreateDTO parking);

    ParkingDTO update(String id, ParkingCreateDTO parking);

    void delete(String id);

    static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    };
}
