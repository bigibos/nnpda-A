package cz.upce.fei.nnpda.service;

import cz.upce.fei.nnpda.domain.Car;
import cz.upce.fei.nnpda.dto.CarRequestDTO;
import cz.upce.fei.nnpda.dto.CarRespondDTO;
import cz.upce.fei.nnpda.repository.CarRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public CarRespondDTO addCar(CarRequestDTO car) {
        Car newCar = modelMapper.map(car, Car.class);
        newCar = carRepository.save(newCar);


        return modelMapper.map(newCar, CarRespondDTO.class);
    }



    @Transactional
    public CarRespondDTO updateCar(Long id, CarRequestDTO car) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        modelMapper.map(car, existingCar);

        Car updatedCar = carRepository.save(existingCar);
        return modelMapper.map(updatedCar, CarRespondDTO.class);
    }

    @Transactional
    public CarRespondDTO deleteCar(Long id) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        carRepository.deleteById(existingCar.getId());

        return modelMapper.map(existingCar, CarRespondDTO.class);
    }

    public Collection<CarRespondDTO> findCars() {
        return carRepository.findAll().stream()
                .map(car -> modelMapper.map(car, CarRespondDTO.class))
                .collect(Collectors.toList());
    }

    public CarRespondDTO findCar(Long id) {
        Car book = carRepository.findById(id).orElseThrow();
        return modelMapper.map(book, CarRespondDTO.class);
    }

}
