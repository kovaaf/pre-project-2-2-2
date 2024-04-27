package com.example.service;

import com.example.configuration.properties.LoanProperties;
import com.example.entity.Car;
import com.example.repositories.CarRepository;
import org.springframework.stereotype.Service;

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

    public String approveLoan(long personId) {
        long minimalCarPrice = loanProperties.getMinimalCarPrice();
        long minimalIncome = loanProperties.getMinimalIncome();
        long maxShare = loanProperties.getMaxShare() / 100;

        return String.valueOf(calculateApprovedLoan(personId, minimalCarPrice, minimalIncome, maxShare));
    }

    private double calculateApprovedLoan(long personId, long minimalCarPrice, long minimalIncome, long maxShare) {
        double monthlyIncome = incomeClient.getIncome(personId);
        long carPrice = getCarPrice(personId);

        double approvedLoan = 0;
        if (carPrice >= minimalCarPrice || monthlyIncome > minimalIncome) {
            approvedLoan = Math.max((double) (carPrice * maxShare), monthlyIncome * 12 / 2);
        }

        return approvedLoan;
    }

    private long getCarPrice(long personId) {
        return carRepository.findByPersonId(personId)
                .stream()
                .mapToLong(Car::getPrice)
                .sum();
    }
}
