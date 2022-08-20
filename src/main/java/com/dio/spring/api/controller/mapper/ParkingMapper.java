package com.dio.spring.api.controller.mapper;

import com.dio.spring.api.model.Parking;
import com.dio.spring.api.model.dto.ParkingCreateDTO;
import com.dio.spring.api.model.dto.ParkingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingMapper {

    private static ModelMapper MAPPER = new ModelMapper();

    public ParkingDTO parkingDto(Parking parking){
        return MAPPER.map(parking, ParkingDTO.class);
    }

    public List<ParkingDTO> convertToParkingDto(List<Parking> parkingList){
        return parkingList.stream().map(this::parkingDto).collect(Collectors.toList());
    }

    public Parking toParking(ParkingDTO dto) {
        return MAPPER.map(dto, Parking.class);
    }

    public Parking toParkingCreate(ParkingCreateDTO dto){
        return MAPPER.map(dto, Parking.class);
    }
}
