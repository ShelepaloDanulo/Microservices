package edu.kpi.mmsa.cars.sell.cars.repo;

import edu.kpi.mmsa.cars.sell.cars.repo.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepo extends JpaRepository<Car,Long> {
}
