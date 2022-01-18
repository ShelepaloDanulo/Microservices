package edu.kpi.mmsa.cars.sell.app.api;

import edu.kpi.mmsa.cars.sell.app.servise.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/apps")
public class AppController {

    private final AppService appService;

    @GetMapping
    public ResponseEntity<List<edu.kpi.mmsa.cars.sell.app.repo.model.App>> index(){
        final List<edu.kpi.mmsa.cars.sell.app.repo.model.App> apps = appService.fetchAll();
        return ResponseEntity.ok(apps);
    }

    @GetMapping("/{id}")
    public ResponseEntity<edu.kpi.mmsa.cars.sell.app.repo.model.App> show(@PathVariable long id){
        try {
            final edu.kpi.mmsa.cars.sell.app.repo.model.App app = appService.fetchById(id);
            return ResponseEntity.ok(app);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/users/{app_id}")
    public ResponseEntity getUserName(@PathVariable Long app_id){
        try{
            return ResponseEntity.ok(appService.getUserName(app_id));
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/cars/{app_id}")
    public ResponseEntity getCarModel(@PathVariable Long app_id){
        try{
            return ResponseEntity.ok(appService.getCarModel(app_id));
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody edu.kpi.mmsa.cars.sell.app.api.dto.App app){
        final Long userId = app.getUserId();
        final Long carId = app.getCarId();
        final Long price = app.getPrice();
        final long id = appService.create(userId, carId, price);
        final String location = String.format("/apps/%d", id);

        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody edu.kpi.mmsa.cars.sell.app.repo.model.App updatedApp){
        try {
            appService.update(id, updatedApp);

            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        appService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
