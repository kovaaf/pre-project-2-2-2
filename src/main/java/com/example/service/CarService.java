package com.example.service;

import com.example.entity.Car;
import com.example.repositories.CarRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAll(String sortMethod) {
        if (sortMethod != null) {
            return carRepository.findAll(Sort.by(sortMethod));
        } else {
            return carRepository.findAll();
        }
    }
    public List<Car> findAll(Integer count, String sortMethod) {
        if (sortMethod != null) {
            return carRepository.findAll(PageRequest.of(0, count, Sort.by(sortMethod))).getContent();
        } else {
            return carRepository.findAll(PageRequest.of(0, count)).getContent();
        }
    }
}
