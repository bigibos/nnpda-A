package cz.upce.fei.nnpda.repository;

import cz.upce.fei.nnpda.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
