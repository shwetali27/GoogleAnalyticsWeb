package com.bridgelabz.results;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.bridgelabz.constants.ConstantData;
import com.bridgelabz.model.SecretFileModel;
import com.bridgelabz.model.SummaryReportModel;
import com.csvreader.CsvWriter;

public class SummaryReportCsv {
	// method for creating the summary report
	public void createSummaryReport(ArrayList<SummaryReportModel> summaryReportModelList) throws IOException {
		//initializing the dates
		String mStartDate = SecretFileModel.getStartDate();
		String mEndDate = SecretFileModel.getEndDate();
		
		//setting the date format
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calender = Calendar.getInstance();
		
		System.out.println("Inside initialize report");
		CsvWriter csvOutput = null;
		BufferedReader bufferedReader = null;
		try {
			Date sDate = dateFormat.parse(mStartDate);
			Date eDate = dateFormat.parse(mEndDate);

			long dateDifference = eDate.getTime()-sDate.getTime();
			int days = (int) (dateDifference / (24 * 60 * 60 * 1000));
			
			//getting the file from classpath and writing inside the file
			File file = new File(getClass().getClassLoader().getResource("SummaryReport.csv").getFile());
			System.out.println("File exists: " + file.exists());

			System.out.println("startDate: "+mStartDate);
			// writing into file
			csvOutput = new CsvWriter(new FileWriter(file), ConstantData.csvDelimiter);
			csvOutput.write("Task/Date");
			for (int i = 0; i <= (days); i++) {
				
				//code to increase the date by particular days 
				calender.setTime(dateFormat.parse(mStartDate));
				calender.add(Calendar.DATE, i);
				String date = dateFormat.format(calender.getTime());

				csvOutput.write(date);
			}
			csvOutput.endRecord();
			
			for (int i = 0; i < summaryReportModelList.size(); i++) {
				csvOutput.write(summaryReportModelList.get(i).getmGaDiscription());
				for (int j = 0; j < summaryReportModelList.get(i).getDates().size(); j++) {
					csvOutput.write(Integer.toString(summaryReportModelList.get(i).getTotalCount().get(j)));
				}
				csvOutput.endRecord();
			}
			csvOutput.close();
			System.out.println("filePath: "+file.getAbsolutePath());

			//reading the data inside the file
			/*String sCurrentLine;
			bufferedReader = new BufferedReader(new FileReader(file));
			System.out.println("After buffer reader");
			while ((sCurrentLine = bufferedReader.readLine()) != null) {
				System.out.println("Readig current line");
				System.out.println(sCurrentLine);
			}
			bufferedReader.close();*/
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (csvOutput != null) {
				csvOutput.close();
			}
			if(bufferedReader != null){
				bufferedReader.close();
			}
		}
	}
}
