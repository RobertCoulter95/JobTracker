package com.example.jobtracker.job;

import com.example.jobtracker.company.Company;
import com.example.jobtracker.company.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    @Autowired
    public JobService(JobRepository jobRepository, CompanyRepository companyRepository){
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }
    public List<Job> getJob() {
        return jobRepository.findAll();
    }

    public Optional<List<Job>> getOneDayOldJobs() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusDays(1);
        return jobRepository.findJobsByTimeFrame(startDate,endDate);
    }

    public Optional<List<Job>> getThreeDayOldJobs() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusDays(3);
        return jobRepository.findJobsByTimeFrame(startDate,endDate);
    }

    public Optional<List<Job>> getSevenDayOldJobs() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusDays(7);
        return jobRepository.findJobsByTimeFrame(startDate,endDate);
    }

    public Optional<List<Job>> getJobsForCompany(Long id) {
        return jobRepository.findJobsByCompanyId(id);
    }

    public void addNewJob(Job job) {
        Long companyId = job.getCompany().getId();
        String companyJobId = job.getCompanyJobId();
        if (companyId != null && companyJobId!= null){
            Optional<Job> jobOptional = jobRepository.findByCompanyIdAndCompanyJobId(companyId ,companyJobId);
            if (jobOptional.isPresent()){
                Optional<Company> companyOptional = companyRepository.findById(companyId);
                // Should return a link for the job
                throw new IllegalStateException("Job with id: " + companyJobId + "for " + companyOptional.get().getName() + " already exists");
            }
        }
        job.setCreated(LocalDateTime.now());
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
    public void updateJob(Long id, Double pay, boolean isSalary, String description,String position,Company company,String companyJobId) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new IllegalStateException("Job with ids: " + id + " does not exist"));
        if (pay != null) job.setPay(pay);
        System.out.println(pay);
        if (description != null) job.setDescription(description);
    }
}
