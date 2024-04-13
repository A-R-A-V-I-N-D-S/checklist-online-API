package com.dailychecklist.service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailychecklist.XLstyle.ExcelStyle;
import com.dailychecklist.entity.JobsDetails;
import com.dailychecklist.repository.JobsDetailsRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ExcelDownloadService {

	@Autowired
	private JobsDetailsRepository repository;
	
	public void generateExcel(HttpServletResponse response) throws IOException {
		List<JobsDetails> listOfJobsDetails = repository.findAll();
		ExcelStyle style = new ExcelStyle();
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Jobs Checklist");
		
		XSSFRow headerRow=sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Job Name");
		headerRow.createCell(1).setCellValue("Folder Name");
		headerRow.createCell(2).setCellValue("Job Status");
		headerRow.createCell(3).setCellValue("Start Time");
		headerRow.createCell(4).setCellValue("End Time");
		headerRow.createCell(5).setCellValue("Deleted");
		headerRow.createCell(6).setCellValue("Held");
		
		int rowInd = 1;
		for(JobsDetails job: listOfJobsDetails) {
			XSSFRow row = sheet.createRow(rowInd);
			row.createCell(0).setCellValue(job.getJobName());
			row.createCell(1).setCellValue(job.getFolderName());
			row.createCell(2).setCellValue(job.getJobStatus());
			row.createCell(3).setCellValue(job.getStartTime());
			row.createCell(4).setCellValue(job.getEndTime());
			row.createCell(5).setCellValue(job.getIsDeleted());
			row.createCell(6).setCellValue(job.getIsHeld());
			
			rowInd++;
			
		}
		for(int i=0;i<=sheet.getLastRowNum();i++) {
			for(int j=0;j<7;j++) {
				style.setBorder(workbook, sheet.getRow(i).getCell(j));
			}
		}
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		
	}
	
}
