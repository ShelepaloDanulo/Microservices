package edu.kpi.mmsa.cars.sell.users.repo;

import edu.kpi.mmsa.cars.sell.users.repo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
}