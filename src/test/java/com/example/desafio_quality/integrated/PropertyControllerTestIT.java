package com.example.desafio_quality.integrated;

import com.example.desafio_quality.model.Property;
import com.example.desafio_quality.model.Room;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
class PropertyControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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

        property = Property.builder()
                .name("Shopping B")
                .districtId(4)
                .rooms(listRoom)
                .build();
    }


    @Test
    void createProperty_returnNewProperty_whenSuccessfullyCreated() throws Exception {
        ResultActions response = mockMvc.perform(
                post("/property", property)
                        .content(objectMapper.writeValueAsString(property))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", CoreMatchers.is(property.getName())));
    }


    @Test
    void getPropertyTotalM2_returnTotalM2_whenSuccessfullyFound() throws Exception {
        ResultActions response = mockMvc.perform(
                get("/property/totalM2/{property}", "Shopping Z")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", CoreMatchers.is(792.0)));
    }

    @Test
    void getPropertyValue_returnTotalPrice_whenSuccessfullyFound() throws Exception {
        ResultActions response = mockMvc.perform(
                get("/property/price/{property}", "Shopping Z")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", CoreMatchers.is(792000.0)));
    }

    @Test
    void getTotalM2ByRoom_returnTotalM2ByRoom_whenSuccessfullyFound() throws Exception {
        ResultActions response = mockMvc.perform(
                get("/property/totalM2ByRooms/{property}", "Shopping Z")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", CoreMatchers.is(2)))
                .andExpect(jsonPath("$.[0].name", CoreMatchers.is("Farmacia")))
                .andExpect(jsonPath("$.[0].totalM2", CoreMatchers.is(240.0)));

    }

    @Test
    void getBiggestRoom_returnBiggestRoom_whenSuccessfullyFound() throws Exception {
        ResultActions response = mockMvc.perform(
                get("/property/biggestRoom/{property}", "Shopping Z")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is("Americanas")))
                .andExpect(jsonPath("$.totalM2", CoreMatchers.is(552.0)));
    }


}
