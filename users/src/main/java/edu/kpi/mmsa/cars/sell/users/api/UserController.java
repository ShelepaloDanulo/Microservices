package edu.kpi.mmsa.cars.sell.users.api;

import edu.kpi.mmsa.cars.sell.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<edu.kpi.mmsa.cars.sell.users.repo.model.User>> index(){
        final List<edu.kpi.mmsa.cars.sell.users.repo.model.User> users = userService.fetchAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<edu.kpi.mmsa.cars.sell.users.repo.model.User> show(@PathVariable long id){
        try {
            final edu.kpi.mmsa.cars.sell.users.repo.model.User car = userService.fetchById(id);
            return ResponseEntity.ok(car);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody edu.kpi.mmsa.cars.sell.users.api.dto.User user){
        final String name = user.getName();
        final String surname = user.getSurname();
        final String email = user.getEmail();
        final long id = userService.create(name, surname, email);
        final String location = String.format("/users/%d", id);

        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody edu.kpi.mmsa.cars.sell.users.repo.model.User updatedUser){
        try {
            userService.update(id, updatedUser);

            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
