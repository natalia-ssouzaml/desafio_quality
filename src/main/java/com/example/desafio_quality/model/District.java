package com.example.desafio_quality.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class District {

    private int id;
    private String name;
    private BigDecimal valueM2;
}
