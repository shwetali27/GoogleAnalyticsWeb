package com.bridgelabz.results;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.bridgelabz.constants.ConstantData;
import com.bridgelabz.model.SecretFileModel;
import com.bridgelabz.model.SummaryReportModel;
import com.csvreader.CsvWriter;

public class SummaryReportCsv {

	int startDate = Integer.parseInt(SecretFileModel.startDate.replace("-", ""));
	int endDate = Integer.parseInt(SecretFileModel.endDate.replace("-", ""));

	// method for creating the summary report
	public void createSummaryReport(ArrayList<SummaryReportModel> summaryReportModelList) throws IOException {
		System.out.println("Inside initialize report");
		CsvWriter csvOutput = null;
		BufferedReader bufferedReader = null;
		try {
			//getting the file from classpath and writing inside the file
			File file = new File(getClass().getClassLoader().getResource("SummaryReport.csv").getFile());
			System.out.println("File exists: " + file.exists());

			System.out.println("startDate: "+startDate);
			// writing into file
			csvOutput = new CsvWriter(new FileWriter(file), ConstantData.csvDelimiter);
			csvOutput.write("Task/Date");
			for (int i = 0; i <= (endDate - startDate); i++) {
				System.out.println("inside write");
				csvOutput.write(Integer.toString(startDate + i));
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
