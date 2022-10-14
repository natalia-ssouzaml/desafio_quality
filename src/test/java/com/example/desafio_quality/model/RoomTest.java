package com.example.desafio_quality.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoomTest {

    private Room room;

    @BeforeEach
    void setup() {
        room = Room.builder()
                .name("Quarto")
                .width(22)
                .length(10)
                .build();

    }

    @Test
    void totalM2_returnDouble_whenValidInput (){
        double resp = room.totalM2();
        assertEquals(220, resp);
        assertEquals("Quarto", room.getName());
    }
}