package edu.kpi.mmsa.cars.sell.app.servise;

import edu.kpi.mmsa.cars.sell.app.repo.AppRepo;
import edu.kpi.mmsa.cars.sell.app.repo.model.App;
import edu.kpi.mmsa.cars.sell.app.repo.model.Car;
import edu.kpi.mmsa.cars.sell.app.repo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AppService {
    private final AppRepo appRepo;
    private final RestTemplate restTemplate;

    public List<App> fetchAll(){
        return appRepo.findAll();
    }

    public App fetchById(Long id) throws IllegalArgumentException {
        final Optional<App> maybeApp = appRepo.findById(id);

        if (maybeApp.isPresent())
            return maybeApp.get();
        else
            throw new IllegalArgumentException("Invalid car ID");
    }

    public String getUserName(Long app_id) {
        App app = fetchById(app_id);
        Long userId = app.getUserId();

        User user = restTemplate.getForObject(
                "http://users:8082/users/" + userId,
                User.class
        );
        return user.getName();
    }

    public String getCarModel(Long app_id) {
        App app = fetchById(app_id);
        Long carId = app.getCarId();

        Car car = restTemplate.getForObject(
                "http://cars:8080/cars/" + carId,
                Car.class
        );
        return car.getModel();
    }

    public long create(Long userId, Long carId, Long price){
        final App app = new App(userId, carId, price);
        final App savedApp = appRepo.save(app);
        return savedApp.getId();
    }

    public App update(Long id, App updatedApp) throws IllegalArgumentException{
        final Optional<App> maybeApp = appRepo.findById(id);

        if (maybeApp.isEmpty())
            throw new IllegalArgumentException("Invalid car ID");

        App oldApp = maybeApp.get();
        updateApps(oldApp, updatedApp);
        return appRepo.save(oldApp);
    }

    private void updateApps(App oldApp, App updatedApp) {
        oldApp.setPrice(updatedApp.getPrice());
        oldApp.setUserId(updatedApp.getUserId());
        oldApp.setCarId(updatedApp.getCarId());
    }

    public void delete(Long id) {
        appRepo.deleteById(id);
    }
}
