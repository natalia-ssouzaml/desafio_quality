package com.example.desafio_quality.repository;

import com.example.desafio_quality.Exception.NotFoundException;
import com.example.desafio_quality.model.District;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class DistrictRepo {

    private final String linkFile = "src/main/resources/districts.json";

    public List<District> getAllDistricts() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Arrays.asList(mapper.readValue(new File(linkFile), District[].class));

        } catch (Exception e) {
            throw new NotFoundException("Empty properties list");
        }
    }

    public Optional<District> getDistrictById(int districtId) {
        return getAllDistricts().stream()
                .filter(d -> d.getId() == districtId)
                .findFirst();
    }
}
