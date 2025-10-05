package cz.upce.fei.nnpiacv.controller;

import cz.upce.fei.nnpiacv.domain.Racer;
import cz.upce.fei.nnpiacv.dto.RacerRequestDTO;
import cz.upce.fei.nnpiacv.dto.RacerRespondDTO;
import cz.upce.fei.nnpiacv.service.RacerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@AllArgsConstructor
public class RacerController {

    private final RacerService racerService;

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/racers/{id}")
    public RacerRespondDTO updateRacer(@PathVariable Long id, @Valid @RequestBody RacerRequestDTO racer) {
        return racerService.updateRacer(id, racer);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/racers/{racerId}/teams/{teamId}")
    public RacerRespondDTO assignTeam(@PathVariable Long racerId, @PathVariable Long teamId) {
        return racerService.assignTeam(racerId, teamId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/racers/{racerId}/cars/{carId}")
    public RacerRespondDTO assignCar(@PathVariable Long racerId, @PathVariable Long carId) {
        return racerService.assignCar(racerId, carId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/racers")
    public RacerRespondDTO addRacer(@Valid @RequestBody RacerRequestDTO racer) {
        return racerService.addRacer(racer);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/racers/{id}")
    public RacerRespondDTO deleteRacer(@PathVariable Long id) {
        return racerService.deleteRacer(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/racers/{racerId}/teams")
    public RacerRespondDTO removeTeam(@PathVariable Long racerId) {
        return racerService.removeTeam(racerId);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/racers/{racerId}/cars")
    public RacerRespondDTO removeCar(@PathVariable Long racerId) {
        return racerService.removeCar(racerId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/racers")
    public Collection<RacerRespondDTO> getRacers() {
        return racerService.findRacers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/racers/{id}")
    public RacerRespondDTO findRacer(@PathVariable Long id) {
        return racerService.findRacer(id);
    }

}
