package com.dio.spring.api.controller;

import com.dio.spring.api.model.dto.ParkingCreateDTO;
import com.dio.spring.api.model.dto.ParkingDTO;
import com.dio.spring.api.service.impl.ParkingServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("parkings")
@RestController
public class ParkingController {

    @Autowired
    private ParkingServiceImpl parkingService;

    @GetMapping
    public ResponseEntity<List<ParkingDTO>> findAll(){
        return ResponseEntity.ok(parkingService.findAll());
    }

    @GetMapping("/{parking_id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String parking_id){
        ParkingDTO parking = parkingService.findById(parking_id);
        return ResponseEntity.ok(parking);
    }

    @PostMapping
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO parking){
        ParkingDTO response = parkingService.create(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO parking){
        System.out.println("Chegou aqui");
        ParkingDTO response = parkingService.update(id, parking);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{parking_id}")
    public ResponseEntity delete(@PathVariable String parking_id){
        parkingService.delete(parking_id);
        return ResponseEntity.noContent().build();
    }
}
