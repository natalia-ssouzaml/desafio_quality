package com.example.desafio_quality.repository;

import com.example.desafio_quality.Exception.CreationFailureException;
import com.example.desafio_quality.Exception.NotFoundException;
import com.example.desafio_quality.model.Property;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class PropertyRepo {

    private final String linkFile = "src/main/resources/properties.json";

    public List<Property> getAllProperties() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Arrays.asList(mapper.readValue(new File(linkFile), Property[].class));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new NotFoundException("Empty properties list");
        }
    }

    public Optional<Property> getByName(String name) {
        return getAllProperties().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst();
    }


    public Property createProperty(Property property) {
        ObjectMapper mapper = new ObjectMapper();

        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        List<Property> propertyList = getAllProperties();
        propertyList = new ArrayList<>(propertyList);

        property.setId(propertyList.get(propertyList.size() - 1).getId() + 1);
        propertyList.add(property);

        try {
            writer.writeValue(new File(linkFile), propertyList);
        } catch (Exception ex) {
            throw new CreationFailureException("Invalid creation attributes");
        }

        return property;
    }
}
