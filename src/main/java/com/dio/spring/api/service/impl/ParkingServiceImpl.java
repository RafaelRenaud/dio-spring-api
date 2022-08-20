package com.dio.spring.api.service.impl;

import com.dio.spring.api.controller.mapper.ParkingMapper;
import com.dio.spring.api.exception.ParkingNotFoundException;
import com.dio.spring.api.model.Parking;
import com.dio.spring.api.model.dto.ParkingCreateDTO;
import com.dio.spring.api.model.dto.ParkingDTO;
import com.dio.spring.api.repository.ParkingRepository;
import com.dio.spring.api.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private ParkingMapper parkingMapper;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ParkingDTO> findAll(){
        List<Parking> parkingList = parkingRepository.findAll();
        List<ParkingDTO> responseBody = parkingMapper.convertToParkingDto(parkingList);
        return responseBody;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ParkingDTO findById(String id){
        Parking parking = parkingRepository.findById(id).orElseThrow( () -> new ParkingNotFoundException(id));
        return parkingMapper.parkingDto(parking);
    };

    @Transactional
    public ParkingDTO create(ParkingCreateDTO parkingDto){
        Parking parking = parkingMapper.toParkingCreate(parkingDto);
        String uuid = ParkingService.getUUID();

        parking.setId(uuid);
        parking.setEntryDate(LocalDateTime.now());

        parkingRepository.save(parking);
        return parkingMapper.parkingDto(parking);
    }

    @Transactional
    public ParkingDTO update(String id, ParkingCreateDTO parkingDto){
        //Parking parking = parkingMapper.toParkingCreate(parkingDto);
        ParkingDTO parkingOld = this.findById(id);
        ParkingDTO parkingNew = parkingMapper.toParkingDto(parkingDto);
        parkingNew.setId(parkingOld.getId());

        Parking parking = parkingMapper.toParking(parkingNew);
        parkingRepository.save(parking);
        return parkingMapper.parkingDto(parking);
    }

    @Transactional
    public void delete(String id){
        ParkingDTO parking = this.findById(id);
        parkingRepository.deleteById(id);
    }

    @Transactional
    public ParkingDTO checkout(String id){
        ParkingDTO parking = this.findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(this.getBill(parking));
        parkingRepository.save(parkingMapper.toParking(parking));
        return parking;
    }

    public Double getBill(ParkingDTO parkingDto){
        Parking parking = parkingMapper.toParking(parkingDto);
        return getBill(parking.getEntryDate(), parking.getExitDate());
    }

    public Double getBill(LocalDateTime entry, LocalDateTime exit){
        long minutes = entry.until(exit, ChronoUnit.MINUTES);
        Double bill = 0.0;

        if(minutes <= ParkingService.ONE_HOUR){
            return ParkingService.ONE_HOUR_VALUE;
        }

        if(minutes <= ParkingService.ONE_DAY){
            bill = ParkingService.ONE_HOUR_VALUE;
            int hours = (int) (minutes / ParkingService.ONE_HOUR);

            for(int i = 0; i < hours ; i++){
                bill += ParkingService.ADDITIONAL_PER_HOUR_VALUE;
            }
            return bill;

        }

        int days = (int) (minutes / ParkingService.ONE_DAY);
        for(int i =0 ; i < days; i++){
            bill += ParkingService.DAY_VALUE;
        }

        return bill;
    }

}
