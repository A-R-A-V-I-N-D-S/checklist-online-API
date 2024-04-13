package com.dailychecklist.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dailychecklist.entity.JobsDetails;

@Service
public class ExcelUploadService {

	public static boolean isExcelFileValid(MultipartFile file) {
		return Objects.equals(file.getContentType(),
				"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	}

	public static List<JobsDetails> getJobsDetailsFromXL(InputStream inputStream) throws IOException {
		List<JobsDetails> listOfJobsDetails = new ArrayList<>();
		DataFormatter formatter = new DataFormatter();

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			if (!formatter.formatCellValue(sheet.getRow(i).getCell(0)).equals("Type")) {
				JobsDetails jobsDetails = new JobsDetails();
				String jobName = formatter.formatCellValue(sheet.getRow(i).getCell(1));
				String folderName = formatter.formatCellValue(sheet.getRow(i).getCell(28));
				String jobStatus = formatter.formatCellValue(sheet.getRow(i).getCell(2));
				String startTime = formatter.formatCellValue(sheet.getRow(i).getCell(14));
				String endTime = formatter.formatCellValue(sheet.getRow(i).getCell(15));
				String orderDate = formatter.formatCellValue(sheet.getRow(i).getCell(20));
				String isDeleted = formatter.formatCellValue(sheet.getRow(i).getCell(26));
				String isHeld = formatter.formatCellValue(sheet.getRow(i).getCell(10));

				jobsDetails.setJobName(jobName);
				jobsDetails.setFolderName(folderName);
				jobsDetails.setJobStatus(jobStatus);
				jobsDetails.setStartTime(startTime);
				jobsDetails.setEndTime(endTime);
				jobsDetails.setOrderDate(orderDate);
				jobsDetails.setIsDeleted(isDeleted);
				jobsDetails.setIsHeld(isHeld);
				listOfJobsDetails.add(jobsDetails);
			}
		}
		workbook.close();
		return listOfJobsDetails;
	}

}
