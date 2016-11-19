package com.bridgelabz.results;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.bridgelabz.constants.ConstantData;
import com.bridgelabz.model.SecretFileModel;
import com.csvreader.CsvWriter;
import com.google.common.collect.Multimap;

public class SummaryReportCsv {
	
	//getting the start date and end date in integer format
	int startDate = Integer.parseInt(SecretFileModel.startDate.replace("-", ""));
	int endDate = Integer.parseInt(SecretFileModel.endDate.replace("-", ""));
	
	//method for creating csv file
	public void csvCreation(ArrayList<String> task,ArrayList<Multimap<Integer, String>> list) {

		String summaryfile = ConstantData.resultsFilepath+"SummaryReport.csv";
		File file  = new File(summaryfile);
		try {
			if(file.exists()){
				//System.out.println("inside delete");
				file.delete();
			}
			
			CsvWriter csvOutput = new CsvWriter(new FileWriter(summaryfile, true),ConstantData.csvDelimiter);
				
				//headings for csv file
				csvOutput.write("Task/Date");
				for(int i=0;i<=(endDate-startDate);i++){
					csvOutput.write(Integer.toString(startDate+i));
				}
				csvOutput.endRecord();
				
				//Writing the data inside file
				for(int i=0;i<list.size();i++){
					csvOutput.write(task.get(i));
					for (int k = 0; k <= (endDate - startDate); k++)
						csvOutput.write(Integer.toString(list.get(i).get(startDate + k).size()));
				}
				csvOutput.endRecord();
				csvOutput.close();
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
