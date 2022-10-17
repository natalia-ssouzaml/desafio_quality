package com.example.desafio_quality.service;

import com.example.desafio_quality.Exception.NotFoundException;
import com.example.desafio_quality.dto.RoomDTO;
import com.example.desafio_quality.model.District;
import com.example.desafio_quality.model.Property;
import com.example.desafio_quality.model.Room;
import com.example.desafio_quality.repository.DistrictRepo;
import com.example.desafio_quality.repository.PropertyRepo;
import com.example.desafio_quality.service.impl.PropertyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PropertyServiceTest {

    @InjectMocks
    PropertyService propertyService;

    @Mock
    PropertyRepo propertyRepo;

    @Mock
    DistrictRepo districtRepo;


    private Property property;

    private District district;

    private List<Room> list;
    private Room roomI;
    private Room roomII;


    @BeforeEach
    void setup() {

        list = new ArrayList<>();

        roomI = Room.builder()
                .name("Quarto")
                .width(6.0)
                .length(3.0)
                .build();
        roomII = Room.builder()
                .name("Cozinha")
                .width(4.0)
                .length(4.0)
                .build();

        list.add(roomI);

        list.add(roomII);


        district = District.builder()
                .id(4)
                .name("Centro")
                .valueM2(BigDecimal.valueOf(1000.0))
                .build();

        property = Property.builder()
                .id(1)
                .name("Casa do teste")
                .districtId(district.getId())
                .rooms(list)
                .build();
    }

    @Test
    void createProperty_returnNewProperty_whenSuccess() {
        when(districtRepo.getDistrictById(anyInt())).thenReturn(Optional.of(district));
        when(propertyService.createProperty(property)).thenReturn(property);
        Property op = propertyService.createProperty(property);
        verify(propertyRepo, times(1)).createProperty(op);
        //   verify(districtRepo, times(2)).getDistrictById(district.getId());
        Assertions.assertEquals(district.getId(), property.getDistrictId());
        Assertions.assertNotNull(op);
    }

    @Test
    void createProperty_throwsNotFoundException_whenDistrictIdDoesNotExist() {
        property.setDistrictId(99);
        assertThrows(NotFoundException.class, () -> propertyService.createProperty(property));
    }


    @Test
    void getPropertyTotalM2_returnTotalM2_whenCorrectsAttributes() {
        when(propertyRepo.getByName(property.getName())).thenReturn(Optional.ofNullable(property));
        var result = propertyService.getPropertyTotalM2(property.getName());
        Assertions.assertEquals(34.0, result);
    }

    @Test
    void getPropertyTotalM2_throwsNotFoundException_whenPropertyDoesNotExist() {
        property.setName("Nome que nao existe");
        assertThrows(NotFoundException.class, () -> propertyService.getPropertyTotalM2(property.getName()));
    }

    @Test
    void getPropertyValue_returnTotalValue_whenCorrectsAttributes() {
        when(propertyRepo.getByName(property.getName())).thenReturn(Optional
                .ofNullable(property));
        when(districtRepo.getDistrictById(property.getDistrictId())).thenReturn(Optional.of(district));
        BigDecimal result = propertyService.getPropertyValue(property.getName());
        assertThat(result.compareTo(new BigDecimal(34000.00))).isZero();
    }

    @Test
    void getTotalM2ByRoom_returnTotalM2ByRoom_whenCorrectsAttributes() {
        when(propertyRepo.getByName(property.getName())).thenReturn(Optional.ofNullable((property)));
        List<RoomDTO> list = propertyService.getTotalM2ByRoom(property.getName());
        assertFalse(list.isEmpty());
        Assertions.assertEquals(18.0, list.get(0).getTotalM2());
    }

    @Test
    void getBiggestRoom_returnTheBiggestRoom_whenCorrectsAttributes() {
        when(propertyRepo.getByName(property.getName())).thenReturn(Optional.of(property));
        var biggest = propertyService.getBiggestRoom(property.getName());
        assertFalse(list.isEmpty());
        Assertions.assertEquals(18.0, biggest.getTotalM2());
        Assertions.assertEquals("Quarto", biggest.getName());
    }

}
