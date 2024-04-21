package com.dailychecklist.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dailychecklist.entity.JobsDetails;
import com.dailychecklist.service.ExcelDownloadService;
import com.dailychecklist.service.JobsDetailsService;
import com.dailychecklist.service.LoginService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("jobs")
@CrossOrigin(origins = "*")
public class JobsDetailsController {

	@Autowired
	private JobsDetailsService jobsDetailsService;

	@Autowired
	private ExcelDownloadService excelDownloadService;

	@Autowired
	private LoginService loginService;

	@PostMapping("upload-jobs-details")
	public ResponseEntity<?> uploadJobsDetails(@RequestParam("file") MultipartFile file) throws IOException {
		this.jobsDetailsService.saveJobsDetails(file);
		return ResponseEntity.ok(Map.of("Message", "Jobs Details saved to the Database"));
	}

	@GetMapping
	public ResponseEntity<List<JobsDetails>> getJobsDetails() {
		return new ResponseEntity<>(jobsDetailsService.getJobsDetails(), HttpStatus.FOUND);
	}

	@GetMapping("download-excel")
	public void generateExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		String headerKey = "Content-Disposition";// to save the excel locally
		String headerValue = "attachment;filename=Checklist.xlsx";// to set the file format as .xlsx
		response.setHeader(headerKey, headerValue);
		excelDownloadService.generateExcel(response);
	}

	// Request links for login/sign-up

	@PostMapping("sign-up")
	public ResponseEntity<?> addNewUserDetails(@RequestParam("email") String emailAddr,
			@RequestParam("userName") String userName, @RequestParam("password") String password) {
		this.loginService.saveNewUserDetails(emailAddr, userName, password);
		return ResponseEntity.ok(Map.of("Message", loginService.responseMessage));
	}

	@PostMapping("login")
	public ResponseEntity<?> loginUser(@RequestParam("userName") String userName,
			@RequestParam("password") String password) {
		this.loginService.loginUser(userName, password);
		return ResponseEntity.ok(Map.of("Message", loginService.responseMessage));
	}

}
