package com.example.job_portal_webApp.repository;

import com.example.job_portal_webApp.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

}
