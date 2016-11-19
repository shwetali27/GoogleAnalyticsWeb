package com.bridgelabz.results;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.bridgelabz.constants.ConstantData;
import com.bridgelabz.model.AppReOpenModel;
import com.csvreader.CsvWriter;

public class AppReopenCsv {
	public void createReport(ArrayList<AppReOpenModel> appReOpenModelArrayListObject) {
		// System.out.println(appOpenModelArrayListObject);
		String appReOpenFile = ConstantData.resultsFilepath + "AppReOpenReport.csv";
		File file = new File(appReOpenFile);
		try {
			if (file.exists()) {
				// System.out.println("inside delete");
				file.delete();
			}
			CsvWriter csvOutput = new CsvWriter(new FileWriter(appReOpenFile, true), ConstantData.csvDelimiter);
			//adding headings to csv file
			
			csvOutput.write("GaID");
			csvOutput.write("GaDiscription");
			csvOutput.write("Android ID");
			csvOutput.write("Date");
			csvOutput.endRecord();
			
			//adding data for app reopen in csv
			for(int i=0;i<appReOpenModelArrayListObject.size();i++){
				csvOutput.write(appReOpenModelArrayListObject.get(i).getmGaId());
				csvOutput.write(appReOpenModelArrayListObject.get(i).getmGadiscription());
				csvOutput.write(appReOpenModelArrayListObject.get(i).getmAndroidId());
				csvOutput.write(appReOpenModelArrayListObject.get(i).getmDate());
				csvOutput.endRecord();
			}
			
			csvOutput.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
