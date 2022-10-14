package com.example.desafio_quality.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Builder
public class District {

    private int id;
    @NotBlank(message = "The name of the district can't be empty")
    @Size(max = 45, message = "The length of the name must have maximum length of 45 characters")
    private String name;
    @NotNull(message = "The name of the district can't be empty")
    @Digits(integer = 13, fraction = 2, message = "The value of the M2 must have the maximun length of 13 characters")
    @Positive(message= "The value must be positive")
    private BigDecimal valueM2;
}
