package com.example.jobtracker.job;

import com.example.jobtracker.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/job")
public class JobController {
    private final JobService jobService;
    @Autowired
    public JobController(JobService jobService){
        this.jobService = jobService;
    }
    @GetMapping
    public List<Job> getJob() {
        return jobService.getJob();
    }

    @GetMapping("/company/{id}")
    public Optional<List<Job>> getJobs(@PathVariable("id") Long id) {
        return jobService.getJobsForCompany(id);
    }

    @GetMapping("/1day-old")
    public Optional<List<Job>> getOneDayOldJobs() {
        return jobService.getOneDayOldJobs();
    }

    @GetMapping("/3day-old")
    public Optional<List<Job>> getThreeDayOldJobs() {
        return jobService.getThreeDayOldJobs();
    }

    @GetMapping("/7day-old")
    public Optional<List<Job>> get7DayOldJobs() {
        return jobService.getSevenDayOldJobs();
    }

    @PostMapping
    public void registerNewJob(@RequestBody Job job){
        jobService.addNewJob(job);
    }

    @DeleteMapping(path = "{jobId}")
    public void deleteJob(@PathVariable("jobId") Long id){
        jobService.deleteJob(id);
    }

    @PutMapping(path = "{jobId}")
    public void updateJob(
            @PathVariable("jobId") Long id,
            @RequestParam(required = false) Double pay,
            @RequestParam(required = false) boolean isSalary,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) Company company,
            @RequestParam(required = false) String companyJobId
            ) {
        jobService.updateJob(id,pay,isSalary,description,position,company,companyJobId);
    }
}