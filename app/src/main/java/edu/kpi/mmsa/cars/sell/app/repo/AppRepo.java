package edu.kpi.mmsa.cars.sell.app.repo;

import edu.kpi.mmsa.cars.sell.app.repo.model.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepo extends JpaRepository<App,Long> {
}
