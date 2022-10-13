package com.example.desafio_quality.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Property {
    private int id;
    private String name;
    private int districtId;

    @Valid
    @Size(min = 1, message = "A lista não pode estar vazia")
    private List<Room> rooms;
}
