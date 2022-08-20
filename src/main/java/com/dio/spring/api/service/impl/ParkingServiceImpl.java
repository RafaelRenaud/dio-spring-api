package com.dio.spring.api.service.impl;

import com.dio.spring.api.controller.mapper.ParkingMapper;
import com.dio.spring.api.model.dto.ParkingCreateDTO;
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
        String id1 = ParkingService.getUUID();
        Parking parking1 = new Parking(id1, "MG", "CGA-40963", "Ford Ka", "CINZA", null, null, 25.70);
        parkingMap.put(id1, parking1);
    }

    public List<ParkingDTO> findAll(){
        List<Parking> parkingList = parkingMap.values().stream().collect(Collectors.toList());
        List<ParkingDTO> responseBody = parkingMapper.convertToParkingDto(parkingList);
        return responseBody;
    }

    public ParkingDTO findById(String id){
        Parking parking = parkingMap.get(id);
        return parkingMapper.parkingDto(parking);
    };

    public ParkingDTO create(ParkingCreateDTO parkingDto){
        Parking parking = parkingMapper.toParkingCreate(parkingDto);
        String uuid = ParkingService.getUUID();

        parking.setId(uuid);
        parking.setEntryDate(LocalDateTime.now());

        parkingMap.put(uuid, parking);
        return parkingMapper.parkingDto(parking);
    }

}
