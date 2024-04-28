package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long income;
    @OneToOne
    @JoinTable(name = "person_car",
            joinColumns =
                    { @JoinColumn(name = "person_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "car_id", referencedColumnName = "id") })
    private Car car;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Person{");
        sb.append(" id=").append(id);
        if (income != null) {
            sb.append(" income=").append(income);
        }
        if (car != null) {
            sb.append(" car=").append(car.getModel());
        }
        sb.append(" }");
        return sb.toString();
    }
}
