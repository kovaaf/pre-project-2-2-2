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


    public void findAllCars(Integer count, String sortOrder, Model model) {
        int maxCars = validateSortOrder(sortOrder);

        if (count == null || count >= maxCars) {
            model.addAttribute("carList", findAll(sortOrder));
        } else if (count <= 0) {
            model.addAttribute("carList", Collections.<Car> emptyList());
        } else {
            model.addAttribute("carList", findAll(count, sortOrder));
        }
    }

    private int validateSortOrder(String sortMethod) {
        int maxCars = carProperties.getMaxCars();

        String[] sortFields = carProperties.getSortFields().split(",");
        if (sortMethod != null && Arrays.stream(sortFields).noneMatch(field -> field.equalsIgnoreCase(sortMethod))) {
            throw new NoSuchSortingException(sortMethod);
        }
        return maxCars;
    }

    private List<Car> findAll(String sortField) {
        Sort sort = sortField != null ? Sort.by(sortField) : Sort.unsorted();
        return carRepository.findAll(sort);
    }
    private List<Car> findAll(Integer count, String sortMethod) {
        PageRequest pageRequest = sortMethod != null
                ? PageRequest.of(0, count, Sort.by(sortMethod))
                : PageRequest.of(0, count);
        return carRepository.findAll(pageRequest).getContent();
    }
}
