package com.example.desafio_quality.model;

import lombok.Data;

import java.util.List;

@Data
public class Property {
    private int id;
    private String name;
    private int districtId;
    private List<Room> rooms;


}
