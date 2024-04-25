package com.example.controller;

import com.example.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("cars")
    public String getCars(@RequestParam(value = "count", required = false) Integer count,
                          @RequestParam(value = "sort_by", required = false) String sortMethod,
                          Model model) {

        carService.findAllCars(count, sortMethod, model);

        return "index";
    }




}
