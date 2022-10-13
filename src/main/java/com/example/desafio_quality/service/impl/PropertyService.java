package com.example.desafio_quality.service.impl;

import com.example.desafio_quality.dto.RoomDTO;
import com.example.desafio_quality.model.Property;
import com.example.desafio_quality.model.Room;
import com.example.desafio_quality.repository.PropertyRepo;
import com.example.desafio_quality.service.IProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PropertyService implements IProperty {
    @Autowired
    PropertyRepo propertyRepo;

    @Override
    public double getPropertyTotalM2(String propertyName) {
        return 0;
    }

    @Override
    public BigDecimal getPropertyValue(String propertyName) {
        return null;
    }

    @Override
    public Room getBiggestRoom(String propertyName) {
        return null;
    }

    @Override
    public List<RoomDTO> getTotalM2ByRoom(String propertyName) {
        return null;
    }

    @Override
    public Property createProperty(Property property) {
        return propertyRepo.createProperty(property);
    }

}
