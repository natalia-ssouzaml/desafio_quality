package com.example.desafio_quality.model;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class Room {
    @NotBlank(message = "The name of the room can't be empty")
    @Pattern(regexp = "^[A-Z][a-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]+$", message = "The name of the room must start with a capital letter")
    @Size(max = 30, message = "The length of the name must have maximum length of 30 characters")
    private String name;

    @NotNull(message = "The width of the room can't be empty")
    @Max(value = 25, message = "The maximum width allowed per room is 25 meters")
    private double width;

    @NotNull(message = "The width of the room can't be empty")
    @Max(value = 33, message = "The maximum width allowed per room is 33 meters")
    private double length;

    public double totalM2() {
        return width * length;
    }

}
