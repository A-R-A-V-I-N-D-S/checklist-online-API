package com.dailychecklist.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dailychecklist.entity.JobsDetails;
import com.dailychecklist.repository.JobsDetailsRepository;

@Service
public class JobsDetailsService {

	@Autowired
	private JobsDetailsRepository repository;
	
	public void saveJobsDetails(MultipartFile file) throws IOException {
		if(ExcelUploadService.isExcelFileValid(file)) {
			List<JobsDetails> jobsDetails=ExcelUploadService.getJobsDetailsFromXL(file.getInputStream());
			this.repository.saveAll(jobsDetails);
		}
	}
	
	public List<JobsDetails> getJobsDetails() {
		return repository.findAll();
	}
	
}
