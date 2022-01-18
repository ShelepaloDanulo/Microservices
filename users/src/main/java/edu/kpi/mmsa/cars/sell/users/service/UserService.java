package edu.kpi.mmsa.cars.sell.users.service;

import edu.kpi.mmsa.cars.sell.users.repo.UserRepo;
import edu.kpi.mmsa.cars.sell.users.repo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepo;

    public List<User> fetchAll(){
        return userRepo.findAll();
    }

    public User fetchById(Long id) throws IllegalArgumentException {
        final Optional<User> maybeCar = userRepo.findById(id);

        if (maybeCar.isPresent())
            return maybeCar.get();
        else
            throw new IllegalArgumentException("Invalid car ID");
    }

    public long create(String name, String surname, String email){
        final User user = new User(name, surname, email);
        final User savedUser = userRepo.save(user);
        return savedUser.getId();
    }

    public User update(Long id, User updatedUser) throws IllegalArgumentException{
        final Optional<User> maybeUser = userRepo.findById(id);

        if (maybeUser.isEmpty())
            throw new IllegalArgumentException("Invalid car ID");

        User oldUser = maybeUser.get();
        updateUsers(oldUser, updatedUser);
        return userRepo.save(oldUser);
    }

    private void updateUsers(User oldUser, User updatedUser) {
        oldUser.setEmail(updatedUser.getEmail());
        oldUser.setSurname(updatedUser.getSurname());
        oldUser.setName(updatedUser.getName());
    }

    public void delete(Long id) {
        userRepo.deleteById(id);
    }
}

