package com.example.entity;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long income;
    @OneToOne()
    @JoinTable(name = "person_car",
            joinColumns =
                    { @JoinColumn(name = "person_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "car_id", referencedColumnName = "id") })
    private Car car;

    public Person() {
    }

    public Person(Long income, Car car) {
        this.income = income;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }

    public Optional<Car> getCar() {
        return Optional.ofNullable(car);
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Person{");
        sb.append(" id=").append(id);
        if (income != null) {
            sb.append(" income=").append(income);
        }
        if (car != null) {
            sb.append(" car").append(car.getModel());
        }
        sb.append(" }");
        return sb.toString();
    }
}
