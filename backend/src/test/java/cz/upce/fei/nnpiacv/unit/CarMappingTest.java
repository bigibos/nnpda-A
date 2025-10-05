package cz.upce.fei.nnpiacv.unit;
import cz.upce.fei.nnpiacv.domain.Car;
import cz.upce.fei.nnpiacv.domain.Racer;
import cz.upce.fei.nnpiacv.dto.CarRequestDTO;
import cz.upce.fei.nnpiacv.dto.CarRespondDTO;
import cz.upce.fei.nnpiacv.dto.RacerRespondDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarMappingTest {

    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
    }

    @Test
    void shouldMapCarToCarRespondDTO() {
        Car car = new Car(1L, "Škoda", "Fabia", Collections.emptyList());

        CarRespondDTO dto = modelMapper.map(car, CarRespondDTO.class);

        assertEquals(car.getId(), dto.getId());
        assertEquals(car.getBrand(), dto.getBrand());
        assertEquals(car.getName(), dto.getName());
        assertNotNull(dto.getRacers());
        assertEquals(0, dto.getRacers().size());
    }

    @Test
    void shouldMapCarRequestDTOToCarEntity() {
        CarRequestDTO dto = new CarRequestDTO("Ford", "Mustang");

        Car car = modelMapper.map(dto, Car.class);

        assertEquals(dto.getBrand(), car.getBrand());
        assertEquals(dto.getName(), car.getName());
        assertNull(car.getRacers()); // není nastavováno
    }

    @Test
    void shouldMapCarWithRacersToDto() {
        Racer racer = new Racer();
        racer.setId(1L);
        racer.setFirstName("Tom");
        racer.setLastName("Speed");

        Car car = new Car(5L, "Tesla", "Model S", List.of(racer));

        CarRespondDTO dto = modelMapper.map(car, CarRespondDTO.class);

        assertEquals(1, dto.getRacers().size());
        RacerRespondDTO mappedRacer = dto.getRacers().get(0);
        assertEquals("Tom", mappedRacer.getFirstName());
        assertEquals("Speed", mappedRacer.getLastName());
    }
}
