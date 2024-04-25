package com.example.service;

import com.example.configuration.properties.LoanProperties;
import com.example.entity.Car;
import com.example.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    private final CarRepository carRepository;
    private final LoanProperties loanProperties;
    private final IncomeClient incomeClient;

    public LoanService(CarRepository carRepository, LoanProperties loanProperties, IncomeClient incomeClient) {
        this.carRepository = carRepository;
        this.loanProperties = loanProperties;
        this.incomeClient = incomeClient;
    }

    public String approveLoan(Long id) {
        Long minimalCarPrice = loanProperties.getMinimalCarPrice();
        Long minimalIncome = loanProperties.getMinimalIncome();
        Long maxShare = loanProperties.getMaxShare() / 100;

        return String.valueOf(calculateApprovedLoan(id, minimalCarPrice, minimalIncome, maxShare));
    }

    private double calculateApprovedLoan(Long id, Long minimalCarPrice, Long minimalIncome, Long maxShare) {
        double monthlyIncome = incomeClient.getIncome(id);
        Long carPrice = getCarPrice(id);

        double approvedLoan = 0;
        if (carPrice >= minimalCarPrice || monthlyIncome > minimalIncome) {
            approvedLoan = Math.max((double) (carPrice * maxShare), monthlyIncome * 12 / 2);
        }

        return approvedLoan;
    }

    private Long getCarPrice(Long id) {
        List<Car> carList = carRepository.findByPersonId(id);
        Long carPrice = 0L;
        if (carList.size() == 1) {
            carPrice = carList.getFirst().getPrice();
        } else if (carList.size() > 1) {
            for (Car car : carList) {
                carPrice += car.getPrice();
            }
        }
        return carPrice;
    }
}
