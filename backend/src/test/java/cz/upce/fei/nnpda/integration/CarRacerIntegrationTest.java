package cz.upce.fei.nnpda.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CarRacerIntegrationTest {

    @Autowired
    private CarService carService;

    @Autowired
    private RacerService racerService;

    @Test
    void testAssignCarToRacer() {
        // 1. vytvoření auta
        CarRequestDTO carRequest = new CarRequestDTO();
        carRequest.setBrand("Ferrari");
        carRequest.setName("SF-24");

        CarRespondDTO createdCar = carService.addCar(carRequest);

        assertThat(createdCar).isNotNull();
        assertThat(createdCar.getId()).isPositive();

        // 2. vytvoření jezdce (bez auta)
        RacerRequestDTO racerRequest = new RacerRequestDTO();
        racerRequest.setFirstName("Carlos");
        racerRequest.setLastName("Sainz");
        racerRequest.setNumber(55);

        RacerRespondDTO createdRacer = racerService.addRacer(racerRequest);

        assertThat(createdRacer).isNotNull();
        assertThat(createdRacer.getId()).isPositive();

        // 3. přiřazení auta jezdci pomocí speciální metody ve službě
        RacerRespondDTO updatedRacer = racerService.assignCar(createdRacer.getId(), createdCar.getId());

        assertThat(updatedRacer.getCar()).isNotNull();
        assertThat(updatedRacer.getCar().getId()).isEqualTo(createdCar.getId());
        assertThat(updatedRacer.getCar().getName()).isEqualTo("SF-24");
    }
}
