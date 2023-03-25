package com.example.jobtracker.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class JobService {

    private final JobRepository jobRepository;
    @Autowired
    public JobService(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }
    public List<Job> getJob() {
        return jobRepository.findAll();
    }

    public void addNewJob(Job job) {
        Optional<Job> jobOptional = jobRepository.findJobByPay(job.getPay());
        if (jobOptional.isPresent()){
            throw new IllegalStateException("Job with pay already exist");
        }
        jobRepository.save(job);
    }

    public void deleteJob(Long id){
        boolean exists = jobRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Job with id: " + id + " does not exist");
        }
        jobRepository.deleteById(id);
    }

    @Transactional
    public void updateJob(Long id, Double pay, boolean isSalary, String description, int numberOfApplicants) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new IllegalStateException("Job with ids: " + id + " does not exist"));

        if (pay != null && pay != job.getPay()){
            job.setPay(pay);
        }
        job.setSalary(isSalary);
        if (description != null) job.setDescription(description);
        if (numberOfApplicants > job.getNumberOfApplicants()) job.setNumberOfApplicants(numberOfApplicants);
    }
}
