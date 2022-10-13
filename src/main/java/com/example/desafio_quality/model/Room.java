package com.example.desafio_quality.model;

import lombok.Data;

@Data
public class Room {

    private String name;
    private double width;
    private double length;

    public double totalM2() {
        return width * length;
    }

}
