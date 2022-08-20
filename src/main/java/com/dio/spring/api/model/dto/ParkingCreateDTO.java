package com.dio.spring.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ParkingCreateDTO {

    private String state;
    private String license;
    private String model;
    private String color;
}
