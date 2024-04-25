package com.example.service;

import com.example.configuration.properties.LoanProperties;
import com.example.entity.Person;
import com.example.exceptions.NoPersonWithSuchIdException;
import com.example.exceptions.NonValidExternalResourceOrEmptyException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
public class IncomeClient {
    private final RestTemplate defaultRestTemplate;
    private final LoanProperties loanProperties;

    public IncomeClient(RestTemplate defaultRestTemplate, LoanProperties loanProperties) {
        this.defaultRestTemplate = defaultRestTemplate;
        this.loanProperties = loanProperties;
    }

    public double getIncome(Long id) {
        double monthlyIncome;
        Optional<Person> person = getPersonList().stream()
                .filter(p -> id.equals(p.getId()))
                .findAny();
        if (person.isPresent()) {
            monthlyIncome = person.get().getIncome();
        } else {
            throw new NoPersonWithSuchIdException(id);
        }

        return monthlyIncome;
    }

    private List<Person> getPersonList() {
        List<Person> personList = defaultRestTemplate.exchange(
                loanProperties.getUrl(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Person>>() {}).getBody();

        if (personList == null) {
            throw new NonValidExternalResourceOrEmptyException(loanProperties.getUrl());
        }
        return personList;
    }
}
