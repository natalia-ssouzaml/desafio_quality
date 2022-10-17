package com.example.desafio_quality.controller;

import com.example.desafio_quality.dto.RoomDTO;
import com.example.desafio_quality.model.District;
import com.example.desafio_quality.model.Property;
import com.example.desafio_quality.model.Room;
import com.example.desafio_quality.service.impl.PropertyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PropertyController.class)
class PropertyControllerTest {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PropertyService propertyService;

    private Property property;
    private RoomDTO roomDTO;
    private District district;

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

        roomDTO = new RoomDTO(roomI);

        district = District.builder()
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
    void createProperty_returnNewProperty_whenSuccessfullyCreated() throws Exception {
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
                .andExpect(jsonPath("$.rooms.length()", CoreMatchers.is(property.getRooms().size())));
    }

    @Test
    void getPropertyTotalM2_returnTotalM2_whenSuccessfullyFound() throws Exception {
        BDDMockito.when(propertyService.createProperty(any()))
                .thenReturn(property);

        ResultActions response = mockMvc.perform(
                get("/property/totalM2/{property}", property.getName())
                        .contentType(MediaType.APPLICATION_JSON));


        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", CoreMatchers.is(0.0)));
    }

    @Test
    void getPropertyValue_returnTotalPrice_whenSuccessfullyFound() throws Exception {

        BigDecimal propertyValue = new BigDecimal("34000.0");
        BDDMockito.when(propertyService.getPropertyValue(anyString()))
                .thenReturn(propertyValue);

        ResultActions response = mockMvc.perform(
                get("/property/price/{property}", property.getName())
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", CoreMatchers.is(propertyValue.doubleValue())));
    }

    @Test
    void getTotalM2ByRoom_returnTotalM2ByRoom_whenSuccessfullyFound() throws Exception {
        List<RoomDTO> roomDTOList = new ArrayList<>(List.of(roomDTO));

        BDDMockito.when(propertyService.getTotalM2ByRoom(property.getName()))
                .thenReturn(roomDTOList);


        ResultActions response = mockMvc.perform(
                get("/property/totalM2ByRooms/{property}", property.getName())
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", CoreMatchers.is(roomDTOList.size())))
                .andExpect(jsonPath("$.[0].totalM2", CoreMatchers.is(roomDTOList.get(0).getTotalM2())));
    }

    @Test
    void getBiggestRoom_returnBiggestRoom_whenSuccessfullyFound() throws Exception {

        BDDMockito.when(propertyService.createProperty(any()))
                .thenReturn(property);

        BDDMockito.when(propertyService.getBiggestRoom(property.getName()))
                .thenReturn(roomDTO);

        ResultActions response = mockMvc.perform(
                get("/property/biggestRoom/{property}", property.getName())
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is(roomDTO.getName())))
                .andExpect(jsonPath("$.totalM2", CoreMatchers.is(roomDTO.getTotalM2())));

    }
}