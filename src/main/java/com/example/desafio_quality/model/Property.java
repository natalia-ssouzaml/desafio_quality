package com.example.desafio_quality.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
public class Property {
    private int id;
    @NotBlank(message = "The name of the property can't be empty")
    @Pattern(regexp = "^[A-Z][A-Za-z0-9 áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]*", message = "The name of the room must start with a capital letter")
    @Size(max = 30, message = "The length of the name must have maximum length of 30 characters")
    private String name;

    @NotNull(message = "The district's id can't be empty")
    @Positive(message= "The value must be positive")
    private int districtId;

    @Valid
    @Size(min = 1, message = "A lista não pode estar vazia")
    private List<Room> rooms;
}
