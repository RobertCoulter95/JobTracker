package com.example.jobtracker.job;

import com.example.jobtracker.company.Company;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name ="Jobs")
@Table
@Data
@JsonDeserialize(using = JobDeserializer.class)
public class Job {
    @Id
    @SequenceGenerator(
            name = "job_sequence",
            sequenceName = "job_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "job_sequence"
    )
    private Long id;
    private double pay;
    private boolean salary;
    private String position;
    private String description;
    private int numberOfApplicants;
    private LocalDateTime created;
    private LocalDateTime listingOpened;
    @ManyToOne()
    private Company company;

    private String companyJobId;

    public Job(Long id, double pay, boolean isSalary, String description,Company company) {
        this.id = id;
        this.pay = pay;
        this.salary = isSalary;
        this.description = description;
        this.numberOfApplicants = 0;
        this.company = company;

    }

    public Job(double pay, boolean isSalary, String description,Company company, String companyJobId,String position) {
        this.pay = pay;
        this.salary = isSalary;
        this.description = description;
        this.numberOfApplicants = 0;
        this.company = company;
        this.companyJobId = companyJobId;
        this.position = position;
    }
    public Job() {

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfApplicants() {
        return numberOfApplicants;
    }

    public void setNumberOfApplicants(int numberOfApplicants) {
        this.numberOfApplicants = numberOfApplicants;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
