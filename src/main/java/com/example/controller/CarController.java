package com.example.controller;

import com.example.controller.controllerAdiveces.NoSuchSortingException;
import com.example.entity.Car;
import com.example.service.CarService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collections;

@Controller
public class CarController {
    private final CarService carService;
    private final Environment environment;

    public CarController(CarService carService, Environment environment) {
        this.carService = carService;
        this.environment = environment;
    }

    @GetMapping("cars")
    public String getCars(@RequestParam(value = "count", required = false) Integer count,
                          @RequestParam(value = "sort_by", required = false) String sortMethod,
                          Model model) {

        int maxCar = Integer.parseInt(environment.getRequiredProperty("maxCar"));

        // Заготовка для фильтрации методов сортировки
        String[] sortFields = environment.getRequiredProperty("sortFields").split(",");
        System.out.println(Arrays.toString(sortFields));
        if (sortMethod != null && Arrays.stream(sortFields).noneMatch(x -> x.equalsIgnoreCase(sortMethod))) {
            throw new NoSuchSortingException(sortMethod);
        }

        if (count == null || count >= maxCar) {
            model.addAttribute("carList", carService.findAll(sortMethod));
        } else if (count <= 0) {
            model.addAttribute("carList", Collections.<Car> emptyList());
        } else {
            model.addAttribute("carList", carService.findAll(count, sortMethod));
        }

        return "index";
    }
}
