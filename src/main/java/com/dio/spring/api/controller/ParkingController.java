package com.dio.spring.api.controller;

import com.dio.spring.api.model.dto.ParkingCreateDTO;
import com.dio.spring.api.model.dto.ParkingDTO;
import com.dio.spring.api.service.impl.ParkingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/parkings")
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
        return ResponseEntity.ok(parkingService.findById(parking_id));
    }

    @PostMapping
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO parking){
        ParkingDTO response = parkingService.create(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
