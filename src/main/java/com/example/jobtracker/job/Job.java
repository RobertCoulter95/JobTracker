package com.example.jobtracker.job;

import com.example.jobtracker.company.Company;
import jakarta.persistence.*;

@Entity(name ="Jobs")
@Table
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
    private boolean isSalary;
    private String description;
    private int numberOfApplicants;
//    @ManyToOne
//    @JoinColumn(name = "company",nullable = false)
//    private Company company;

    public Job(Long id, double pay, boolean isSalary, String description) {
        this.id = id;
        this.pay = pay;
        this.isSalary = isSalary;
        this.description = description;
        this.numberOfApplicants = 0;
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

    public boolean isSalary() {
        return isSalary;
    }

    public void setSalary(boolean salary) {
        isSalary = salary;
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
