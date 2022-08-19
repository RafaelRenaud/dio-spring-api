package com.dio.spring.api.controller;

import com.dio.spring.api.model.dto.ParkingDTO;
import com.dio.spring.api.service.impl.ParkingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/parkings")
@RestController
public class ParkingController {

    @Autowired
    private ParkingServiceImpl parkingService;

    @GetMapping
    public List<ParkingDTO> findAll(){
        return parkingService.findAll();
    }
}
