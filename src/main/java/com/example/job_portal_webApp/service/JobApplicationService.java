package com.example.job_portal_webApp.service;

import com.example.job_portal_webApp.model.JobApplication;
import com.example.job_portal_webApp.repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    // Absolute path - files will be saved under your user's home directory
    private final String uploadDir = System.getProperty("user.home") + "/jobportal-uploads/";

    public void saveApplication(JobApplication jobApplication) throws IOException {
        MultipartFile resume = jobApplication.getResume();

        if (resume != null && !resume.isEmpty()) {
            // Use the field uploadDir (absolute)
            File uploadFolder = new File(uploadDir);

            // Create the folder if it does not exist
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            // Create the file path
            File savedFile = new File(uploadFolder, resume.getOriginalFilename());

            // Save the uploaded file
            resume.transferTo(savedFile);

            // Save the absolute path to the database
            jobApplication.setResumePath(savedFile.getAbsolutePath());
        }

        jobApplicationRepository.save(jobApplication);
    }

    public List<JobApplication> getAllApplications() {
        return jobApplicationRepository.findAll();
    }

//    public List<JobApplication> getAllApplications() {
//        return null;
//    }


}





