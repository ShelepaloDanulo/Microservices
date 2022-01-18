package edu.kpi.mmsa.cars.sell.app.repo.model;

import javax.persistence.*;

@Entity
@Table(name = "applications")
public class App {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "car_id")
    private Long carId;
    private Long price;

    public App(){
    }

    public App(Long userId, Long carId, Long price) {
        this.userId = userId;
        this.carId = carId;
        this.price = price;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
