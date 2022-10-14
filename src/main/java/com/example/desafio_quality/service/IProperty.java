package com.example.desafio_quality.service;

import com.example.desafio_quality.dto.RoomDTO;
import com.example.desafio_quality.model.Property;
import com.example.desafio_quality.model.Room;

import java.math.BigDecimal;
import java.util.List;

public interface IProperty {

    double getPropertyTotalM2(String propertyName);
    BigDecimal getPropertyValue(String propertyName);
    RoomDTO getBiggestRoom(String propertyName);
    List<RoomDTO>getTotalM2ByRoom(String propertyName);


    Property createProperty(Property property);

}
