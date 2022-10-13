package com.example.desafio_quality.controller;

import com.example.desafio_quality.model.Property;
import com.example.desafio_quality.service.impl.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        return new ResponseEntity<>(propertyService.createProperty(property), HttpStatus.CREATED);
    }

    @GetMapping("/totalM2/{property}")
    public ResponseEntity<Double> getPropertyTotalM2(@PathVariable String property) {
        return new ResponseEntity<>(propertyService.getPropertyTotalM2(property), HttpStatus.CREATED);
    }

    @GetMapping("/price/{property}")
    public ResponseEntity<BigDecimal> getPropertyValue(@PathVariable String property) {
        return new ResponseEntity<>(propertyService.getPropertyValue(property), HttpStatus.CREATED);
    }
}
