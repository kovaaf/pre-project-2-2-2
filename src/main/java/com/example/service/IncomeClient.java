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

@Component
public class IncomeClient {
    private final RestTemplate defaultRestTemplate;
    private final LoanProperties loanProperties;

    public IncomeClient(RestTemplate defaultRestTemplate, LoanProperties loanProperties) {
        this.defaultRestTemplate = defaultRestTemplate;
        this.loanProperties = loanProperties;
    }

    public double getIncome(long personId) {
        return getPersonList().stream()
                .filter(p -> personId == p.getId())
                .findFirst()
                .map(Person::getIncome)
                .orElseThrow(() -> new NoPersonWithSuchIdException(personId));
    }

    private List<Person> getPersonList() {
        String url = loanProperties.getUrl();
        List<Person> personList = defaultRestTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Person>>() {}).getBody();

        if (personList == null) {
            throw new NonValidExternalResourceOrEmptyException(url);
        }
        return personList;
    }
}
