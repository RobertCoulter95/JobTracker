package com.example.jobtracker.job;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
//    @Query("Select j from Job j where j.pay = ?1")
    Optional<Job> findJobByPay(double pay);

//    Optional<List<Job>> findJobByCompany()
}
