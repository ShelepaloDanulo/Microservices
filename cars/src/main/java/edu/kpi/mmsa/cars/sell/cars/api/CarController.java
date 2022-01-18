package edu.kpi.mmsa.cars.sell.cars.api;

import edu.kpi.mmsa.cars.sell.cars.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<edu.kpi.mmsa.cars.sell.cars.repo.model.Car>> index(){
        final List<edu.kpi.mmsa.cars.sell.cars.repo.model.Car> cars = carService.fetchAll();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<edu.kpi.mmsa.cars.sell.cars.repo.model.Car> show(@PathVariable long id){
        try {
            final edu.kpi.mmsa.cars.sell.cars.repo.model.Car car = carService.fetchById(id);
            return ResponseEntity.ok(car);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody edu.kpi.mmsa.cars.sell.cars.api.dto.Car car){
        final String model = car.getModel();
        final String body = car.getBody();
        final Integer mileage = car.getMileage();
        final long id = carService.create(model, body, mileage);
        final String location = String.format("/cars/%d", id);

        return ResponseEntity.created(URI.create(location)).build();
    }

   @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody edu.kpi.mmsa.cars.sell.cars.repo.model.Car updatedCar){
       try {
           carService.update(id, updatedCar);

           return ResponseEntity.noContent().build();
       } catch (IllegalArgumentException e) {
           return ResponseEntity.notFound().build();
       }
   }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
