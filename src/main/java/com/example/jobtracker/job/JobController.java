package com.example.jobtracker.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public void registerNewJob(@RequestBody Job job){
        jobService.addNewJob(job);
    }

    @DeleteMapping(path = "{jobId}")
    public void deleteJob(@PathVariable("jobId") Long id){
        jobService.deleteJob(id);
    }

    @PutMapping(path = "{id}")
    public void updateJob(
            @PathVariable("id") Long id,
            @RequestParam(required = false) Double pay,
            @RequestParam(required = false) Boolean isSalary,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) int numberOfApplicants
    ) {
        jobService.updateJob(id,pay,isSalary,description,numberOfApplicants);
    }
}