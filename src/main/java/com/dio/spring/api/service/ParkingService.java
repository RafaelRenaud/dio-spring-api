package com.dio.spring.api.service;

import com.dio.spring.api.model.Parking;
import com.dio.spring.api.model.dto.ParkingCreateDTO;
import com.dio.spring.api.model.dto.ParkingDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ParkingService {

    static final int ONE_HOUR = 60;
    static final int ONE_DAY = 24 * ONE_HOUR;
    static final double ONE_HOUR_VALUE = 5.00;
    static final double ADDITIONAL_PER_HOUR_VALUE = 2.00;
    static final double DAY_VALUE = 20.00;

    List<ParkingDTO> findAll();

    ParkingDTO findById(String id);

    ParkingDTO create(ParkingCreateDTO parking);

    ParkingDTO update(String id, ParkingCreateDTO parking);

    void delete(String id);

    ParkingDTO checkout(String id);

    Double getBill(ParkingDTO parking);

    Double getBill(LocalDateTime entry, LocalDateTime exit);

    static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    };
}
