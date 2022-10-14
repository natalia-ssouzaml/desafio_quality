package com.example.desafio_quality.service.impl;

import com.example.desafio_quality.Exception.NotFoundException;
import com.example.desafio_quality.dto.RoomDTO;
import com.example.desafio_quality.model.District;
import com.example.desafio_quality.model.Property;
import com.example.desafio_quality.model.Room;
import com.example.desafio_quality.repository.DistrictRepo;
import com.example.desafio_quality.repository.PropertyRepo;
import com.example.desafio_quality.service.IProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService implements IProperty {
    @Autowired
    PropertyRepo propertyRepo;

    @Autowired
    DistrictRepo districtRepo;

    private Property getProperty(String propertyName) {
        Optional<Property> property = propertyRepo.getByName(propertyName);
        if (property.isEmpty()) {
            throw new NotFoundException("Property [" + propertyName + "] does not exist");
        }
        return property.get();
    }


    @Override
    public double getPropertyTotalM2(String propertyName) {
        Property property = getProperty(propertyName);
        return property.getRooms().stream()
                .mapToDouble(r -> r.getLength() * r.getWidth()).sum();
    }


    @Override
    public BigDecimal getPropertyValue(String propertyName) {
        Property property = getProperty(propertyName);
        District district = districtRepo.getDistrictById(property.getDistrictId()).get();
        double valueM2 = getPropertyTotalM2(propertyName);
        return district.getValueM2().multiply(BigDecimal.valueOf(valueM2));
    }

    @Override
    public List<RoomDTO> getTotalM2ByRoom(String propertyName) {
        Property property = getProperty(propertyName);

        return RoomDTO.convertListToResponse(property.getRooms());
    }

    @Override
    public RoomDTO getBiggestRoom(String propertyName) {
        return getTotalM2ByRoom(propertyName).stream().max(Comparator.comparing(RoomDTO::getTotalM2)).get();
    }



    @Override
    public Property createProperty(Property property) {
        districtRepo.getDistrictById(property.getDistrictId()).orElseThrow(() -> new NotFoundException("DistrictId does not exist"));
        return propertyRepo.createProperty(property);

    }

}
