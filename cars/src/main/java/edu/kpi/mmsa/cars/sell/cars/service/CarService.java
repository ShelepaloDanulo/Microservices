package edu.kpi.mmsa.cars.sell.cars.service;

import edu.kpi.mmsa.cars.sell.cars.repo.CarRepo;
import edu.kpi.mmsa.cars.sell.cars.repo.model.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class CarService {

    private final CarRepo carRepo;

    public List<Car> fetchAll(){
        return carRepo.findAll();
    }

    public Car fetchById(Long id) throws IllegalArgumentException {
        final Optional<Car> maybeCar = carRepo.findById(id);

        if (maybeCar.isPresent())
            return maybeCar.get();
        else
            throw new IllegalArgumentException("Invalid car ID");
    }

    public long create(String model, String body, Integer mileage){
        final Car car = new Car(model, body, mileage);
        final Car savedCar = carRepo.save(car);
        return savedCar.getId();
    }

    public Car update(Long id, Car updatedCar) throws IllegalArgumentException{
        final Optional<Car> maybeCar = carRepo.findById(id);

        if (maybeCar.isEmpty())
            throw new IllegalArgumentException("Invalid car ID");

        Car oldCar = maybeCar.get();
        updateCars(oldCar, updatedCar);
        return carRepo.save(oldCar);
    }

    private void updateCars(Car oldCar, Car updatedCar) {
        oldCar.setModel(updatedCar.getModel());
        oldCar.setBody(updatedCar.getBody());
        oldCar.setMileage(updatedCar.getMileage());
    }

    public void delete(Long id) {
        carRepo.deleteById(id);
    }
}
