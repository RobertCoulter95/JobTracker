package com.example.jobtracker.company;

import com.example.jobtracker.job.Job;
import com.example.jobtracker.job.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public void addNewCompany(Company company) {
        Optional<Company> companyOptional = companyRepository.findByName(company.getName()); // Short term solution. How will we handle companies with the same name? (Small shops)
        if (companyOptional.isPresent()){
            throw new IllegalStateException("Company with id: " + companyOptional.get().getId() + " already exists"); // Should return a link for the company
        }
        companyRepository.save(company);
    }
}
