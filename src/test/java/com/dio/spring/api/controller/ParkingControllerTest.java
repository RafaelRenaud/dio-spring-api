package com.dio.spring.api.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingControllerTest extends AbstractContainerBase{

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    void setup(){
        RestAssured.port = randomPort;
    }

    @Test
    void test(){
        System.out.println("Test finished successfully");
    }
}
