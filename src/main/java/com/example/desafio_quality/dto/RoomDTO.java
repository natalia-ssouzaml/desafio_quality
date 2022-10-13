package com.example.desafio_quality.dto;

import com.example.desafio_quality.model.Room;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class RoomDTO {
    private String name;
    private Double totalM2;

    private static RoomDTO convertToResponse(Room room) {
        return new RoomDTO(room);
    }

    public static List<RoomDTO> convertListToResponse(List<Room> roomList) {
        return roomList.stream().map(RoomDTO::convertToResponse)
        .collect(Collectors.toList());
    }


    public RoomDTO(Room room) {
        this.name = room.getName();
        this.totalM2 = room.totalM2();
    }
}
