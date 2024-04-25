package com.example.controller;

import com.example.service.LoanService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @ResponseBody
    @GetMapping(value = "loan", produces = MediaType.APPLICATION_JSON_VALUE)
    public String approveLoan(@RequestParam(value = "userId") Long id) {
        return loanService.approveLoan(id);
    }
}
