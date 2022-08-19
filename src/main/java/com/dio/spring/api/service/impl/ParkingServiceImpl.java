package com.dio.spring.api.service.impl;

import com.dio.spring.api.controller.mapper.ParkingMapper;
import com.dio.spring.api.model.dto.ParkingDTO;
import com.dio.spring.api.service.ParkingService;
import com.dio.spring.api.model.Parking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ParkingServiceImpl implements ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap();

    @Autowired
    private ParkingMapper parkingMapper;

    static {
        String id = ParkingService.getUUID();
        Parking parking = new Parking(id, "SP", "RFC-20488", "Mercedes Class A", "VERMELHO", null, null, 0.0d);
        parkingMap.put(id, parking);
    }

    public List<ParkingDTO> findAll(){
        List<Parking> parkingList = parkingMap.values().stream().collect(Collectors.toList());
        List<ParkingDTO> responseBody = parkingMapper.convertToParkingDto(parkingList);
        return responseBody;
    }

}
