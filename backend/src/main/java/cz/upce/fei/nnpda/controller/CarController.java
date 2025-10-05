package cz.upce.fei.nnpda.controller;

import cz.upce.fei.nnpda.dto.CarRequestDTO;
import cz.upce.fei.nnpda.dto.CarRespondDTO;
import cz.upce.fei.nnpda.service.CarService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/cars/{id}")
    public CarRespondDTO updateCar(@PathVariable Long id, @Valid @RequestBody CarRequestDTO car) {
        return carService.updateCar(id, car);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cars")
    public CarRespondDTO addCar(@Valid @RequestBody CarRequestDTO car) {
        return carService.addCar(car);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/cars/{id}")
    public CarRespondDTO deleteCar(@PathVariable Long id) {
        return carService.deleteCar(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cars/{id}")
    public CarRespondDTO findCar(@PathVariable Long id) {
        return carService.findCar(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cars")
    public Collection<CarRespondDTO> getCars() {
        return carService.findCars();
    }

}
