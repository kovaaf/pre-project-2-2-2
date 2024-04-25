package com.example.service;

import com.example.configuration.properties.CarProperties;
import com.example.entity.Car;
import com.example.exceptions.NoSuchSortingException;
import com.example.repositories.CarRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarProperties carProperties;

    public CarService(CarRepository carRepository, CarProperties carProperties) {
        this.carRepository = carRepository;
        this.carProperties = carProperties;
    }


    public void findAllCars(Integer count, String sortMethod, Model model) {
        int maxCar = validateSorting(sortMethod);

        if (count == null || count >= maxCar) {
            model.addAttribute("carList", findAll(sortMethod));
        } else if (count <= 0) {
            model.addAttribute("carList", Collections.<Car> emptyList());
        } else {
            model.addAttribute("carList", findAll(count, sortMethod));
        }
    }

    private int validateSorting(String sortMethod) {
        int maxCar = carProperties.getMaxCar();

        String[] sortFields = carProperties.getSortFields().split(",");
        if (sortMethod != null && Arrays.stream(sortFields).noneMatch(x -> x.equalsIgnoreCase(sortMethod))) {
            throw new NoSuchSortingException(sortMethod);
        }
        return maxCar;
    }

    private List<Car> findAll(String sortMethod) {
        if (sortMethod != null) {
            return carRepository.findAll(Sort.by(sortMethod));
        } else {
            return carRepository.findAll();
        }
    }
    private List<Car> findAll(Integer count, String sortMethod) {
        if (sortMethod != null) {
            return carRepository.findAll(PageRequest.of(0, count, Sort.by(sortMethod))).getContent();
        } else {
            return carRepository.findAll(PageRequest.of(0, count)).getContent();
        }
    }
}
