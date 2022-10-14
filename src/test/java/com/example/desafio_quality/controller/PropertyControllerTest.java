package com.example.desafio_quality.controller;

import com.example.desafio_quality.dto.RoomDTO;
import com.example.desafio_quality.model.District;
import com.example.desafio_quality.model.Property;
import com.example.desafio_quality.model.Room;
import com.example.desafio_quality.repository.DistrictRepo;
import com.example.desafio_quality.repository.PropertyRepo;
import com.example.desafio_quality.service.impl.PropertyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PropertyController.class)
class PropertyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PropertyService propertyService;

    @MockBean
    PropertyRepo propertyRepo;
    @Autowired
    ObjectMapper objectMapper;

    private Property property;


    @BeforeEach
    void setup() {

        List<Room> listRoom = new ArrayList<>();

        Room roomI = Room.builder()
                .name("Quarto")
                .width(6.0)
                .length(3.0)
                .build();
        Room roomII = Room.builder()
                .name("Cozinha")
                .width(4.0)
                .length(4.0)
                .build();

        listRoom.add(roomI);

        listRoom.add(roomII);


        District district = District.builder()
                .id(4)
                .name("Centro")
                .valueM2(BigDecimal.valueOf(1000.0))
                .build();

        property = Property.builder()
                .id(1)
                .name("Shopping B")
                .districtId(district.getId())
                .rooms(listRoom)
                .build();
    }


    @Test
    void createProperty_returnProperty_whenCreatedNewProperty() throws Exception {
        BDDMockito.when(propertyService.createProperty(property))
                .thenReturn(property);
        ResultActions response = mockMvc.perform(
                post("/property", property)
                        .content(objectMapper.writeValueAsString(property))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", CoreMatchers.is(property.getName())))
                .andExpect(jsonPath("$.districtId", CoreMatchers.is(property.getDistrictId())))
                //.andExpect(jsonPath("$.rooms", CoreMatchers.is(property.getRooms())));
                .andExpect(jsonPath("$.rooms.length()", CoreMatchers.is(property.getRooms().size())));
    }

    @Test
    void getPropertyTotalM2_whenCorrectsAttributes() throws Exception {
        BDDMockito.when(propertyService.createProperty(any()))
                .thenReturn(property);

        double result = propertyService.getPropertyTotalM2(property.getName());

        ResultActions response = mockMvc.perform(
                get("/property/price/{property}", property.getName())
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
        //Return DTO para comparar com o result

    }

    @Test
    void getPropertyValue() {
    }

    @Test
    void getTotalM2ByRoom() {
    }

    @Test
    void getBiggestRoom() throws Exception {
//        //BDDMockito.when(propertyRepo.getByName(property.getName())).thenReturn(Optional.of(property));
//        List<RoomDTO>roomDTOList = RoomDTO.convertListToResponse(property.getRooms());
//
//        BDDMockito.when(propertyService.createProperty(any()))
//                .thenReturn(property);
//
//        BDDMockito.when(propertyService.getTotalM2ByRoom(property.getName()))
//                        .thenReturn(roomDTOList);
//
//        System.out.println(property.getName());
//        RoomDTO biggest = propertyService.getBiggestRoom(property.getName());
//        System.out.println(biggest);
//        ResultActions response = mockMvc.perform(
//                get("/property/biggestRoom/{property}", property.getName())
//                        .contentType(MediaType.APPLICATION_JSON));
//
//        response.andExpect(status().isOk())
//                .andExpect(jsonPath("$.name",CoreMatchers.is(biggest.getName())))
//                .andExpect(jsonPath("$.totalM2",CoreMatchers.is(biggest.getTotalM2())));

    }
}