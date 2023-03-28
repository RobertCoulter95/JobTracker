package com.example.jobtracker.company;

import com.example.jobtracker.job.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/company")
public class CompanyController {
    CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {this.companyService = companyService;}

    @PostMapping
    public void registerNewCompany(@RequestBody Company company){
        companyService.addNewCompany(company);
    }
}
