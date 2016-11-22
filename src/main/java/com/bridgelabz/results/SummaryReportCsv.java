package com.bridgelabz.results;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.bridgelabz.constants.ConstantData;
import com.bridgelabz.model.SecretFileModel;
import com.csvreader.CsvWriter;
import com.google.common.collect.Multimap;

public class SummaryReportCsv {

	int startDate = Integer.parseInt(SecretFileModel.startDate.replace("-", ""));
	int endDate = Integer.parseInt(SecretFileModel.endDate.replace("-", ""));
	String summaryreport = ConstantData.resultsFilepath+"SummaryReport.csv";
	
	public void initialize() throws IOException {
		System.out.println("Inside initialize report");
		
		File file  = new File(summaryreport);
		if(file.exists()){
			//System.out.println("inside delete");
			file.delete();
		}
		
		CsvWriter csvOutput = new CsvWriter(new FileWriter(summaryreport, true),ConstantData.csvDelimiter);
		csvOutput.write("Task/Date");
		for(int i=0;i<=(endDate-startDate);i++){
			csvOutput.write(Integer.toString(startDate+i));
		}
		csvOutput.endRecord();
		csvOutput.close();
	}
	
	public void createReport(String task,Multimap<String, String> reportCount) throws IOException{
		CsvWriter csvOutput = new CsvWriter(new FileWriter(summaryreport, true),ConstantData.csvDelimiter);
		csvOutput.write(task);
		for (int k = 0; k <= (endDate - startDate); k++)
			csvOutput.write(Integer.toString(reportCount.get(Integer.toString(startDate + k)).size()));
			csvOutput.endRecord();
			csvOutput.close();
	}
}
