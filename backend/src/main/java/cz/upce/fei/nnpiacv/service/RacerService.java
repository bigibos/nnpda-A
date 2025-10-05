package cz.upce.fei.nnpiacv.service;

import cz.upce.fei.nnpiacv.domain.*;
import cz.upce.fei.nnpiacv.dto.RacerRequestDTO;
import cz.upce.fei.nnpiacv.dto.RacerRespondDTO;
import cz.upce.fei.nnpiacv.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.ResponseCache;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class RacerService {

    private final RacerRepository racerRepository;
    private final TeamRepository teamRepository;
    private final CarRepository carRepository;

    private final ModelMapper modelMapper;

    @Transactional
    public RacerRespondDTO addRacer(RacerRequestDTO racer) {
        Racer newRacer = modelMapper.map(racer, Racer.class);

        newRacer = racerRepository.save(newRacer);

        return modelMapper.map(newRacer, RacerRespondDTO.class);
    }

    @Transactional
    public RacerRespondDTO assignTeam(Long racerId, Long teamId) {
        Racer racer = racerRepository.findById(racerId)
                .orElseThrow(() -> new RuntimeException("Team not found with ID: " + racerId));

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found with ID: " + teamId));

        racer.setTeam(team);

        racerRepository.save(racer);

        return modelMapper.map(racer, RacerRespondDTO.class);
    }

    @Transactional
    public RacerRespondDTO assignCar(Long racerId, Long carId) {
        Racer racer = racerRepository.findById(racerId)
                .orElseThrow(() -> new RuntimeException("Team not found with ID: " + racerId));

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Team not found with ID: " + carId));

        racer.setCar(car);

        racerRepository.save(racer);

        return modelMapper.map(racer, RacerRespondDTO.class);
    }

    @Transactional
    public RacerRespondDTO removeTeam(Long racerId) {
        Racer racer = racerRepository.findById(racerId)
                .orElseThrow(() -> new RuntimeException("Team not found with ID: " + racerId));

        racer.setTeam(null);

        racerRepository.save(racer);

        return modelMapper.map(racer, RacerRespondDTO.class);
    }

    @Transactional
    public RacerRespondDTO removeCar(Long racerId) {
        Racer racer = racerRepository.findById(racerId)
                .orElseThrow(() -> new RuntimeException("Team not found with ID: " + racerId));

        racer.setCar(null);

        racerRepository.save(racer);

        return modelMapper.map(racer, RacerRespondDTO.class);
    }

    @Transactional
    public RacerRespondDTO updateRacer(Long id, RacerRequestDTO racerDTO) {
        Racer existingRacer = racerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Racer not found"));

        modelMapper.map(racerDTO, existingRacer);

        Racer updatedRacer = racerRepository.save(existingRacer);

        return modelMapper.map(updatedRacer, RacerRespondDTO.class);
    }


    @Transactional
    public RacerRespondDTO deleteRacer(Long id) {
        Racer existingRacer = racerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        racerRepository.deleteById(existingRacer.getId());

        return modelMapper.map(existingRacer, RacerRespondDTO.class);
    }

    public Collection<RacerRespondDTO> findRacers() {
        return racerRepository.findAll().stream()
                .map(racer -> modelMapper.map(racer, RacerRespondDTO.class))
                .collect(Collectors.toList());
    }

    public RacerRespondDTO findRacer(Long id) {
        Racer book = racerRepository.findById(id).orElseThrow();
        return modelMapper.map(book, RacerRespondDTO.class);
    }

}
