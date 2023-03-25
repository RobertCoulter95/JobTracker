package com.example.jobtracker.company;

import com.example.jobtracker.enums.CompanySize;
import com.example.jobtracker.enums.Industry;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name ="Companys")
@Table
public class Company {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private CompanySize size;
    private Industry industry;

    public Company() {

    }

    public CompanySize getSize() {
        return size;
    }

    public void setSize(CompanySize size) {
        this.size = size;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }


    public Company(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}