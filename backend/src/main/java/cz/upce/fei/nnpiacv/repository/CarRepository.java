package cz.upce.fei.nnpiacv.repository;

import cz.upce.fei.nnpiacv.controller.EventController;
import cz.upce.fei.nnpiacv.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
