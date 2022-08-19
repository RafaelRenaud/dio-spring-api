package com.dio.spring.api.service;

import com.dio.spring.api.model.dto.ParkingDTO;

import java.util.List;
import java.util.UUID;

public interface ParkingService {

    List<ParkingDTO> findAll();

    static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    };
}
