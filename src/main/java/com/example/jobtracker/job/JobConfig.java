package com.example.jobtracker.job;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class JobConfig {
    @Bean
    CommandLineRunner commandLineRunner(JobRepository repository) {
        return args -> {
            Job bookface = new Job(
                    1L,
                    145000,
                    true,
                    "Full stack developer working on cool things!"
            );
            Job uberSoft = new Job(
                    2L,
                    155000,
                    true,
                    "Full stack developer working on very cool things!"
            );
            repository.saveAll(List.of(bookface,uberSoft));
        };
    }
}
