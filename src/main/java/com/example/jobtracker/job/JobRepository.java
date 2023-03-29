package com.example.jobtracker.job;

import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
    Optional<Job> findJobByPay(double pay);
    Optional<List<Job>> findJobsByCompanyId(Long id);
    Optional<Job> findByCompanyIdAndCompanyJobId(Long companyId,String companyJobId);

    @Query("SELECT job FROM Jobs job WHERE job.created BETWEEN ?1 and ?2")
    Optional<List<Job>> findJobsByTimeFrame(LocalDateTime start, LocalDateTime end);
}
