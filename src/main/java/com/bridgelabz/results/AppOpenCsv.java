package com.bridgelabz.results;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.bridgelabz.constants.ConstantData;
import com.bridgelabz.model.AppOpenModel;
import com.csvreader.CsvWriter;

public class AppOpenCsv {

	public void createReport(ArrayList<AppOpenModel> appOpenModelArrayListObject) {
		// System.out.println(appOpenModelArrayListObject);
		String appOpenFile = ConstantData.resultsFilepath + "AppOpenReport.csv";
		File file = new File(appOpenFile);
		try {
			if (file.exists()) {
				// System.out.println("inside delete");
				file.delete();
			}
			CsvWriter csvOutput = new CsvWriter(new FileWriter(appOpenFile, true), ConstantData.csvDelimiter);
			//adding headings to csv file
			csvOutput.write("GaID");
			csvOutput.write("GaDiscription");
			csvOutput.write("Android ID");
			csvOutput.write("Date");
			csvOutput.endRecord();
			
			//adding data for app open in csv
			for(int i=0;i<appOpenModelArrayListObject.size();i++){
				csvOutput.write(appOpenModelArrayListObject.get(i).getmGaId());
				csvOutput.write(appOpenModelArrayListObject.get(i).getmGadiscription());
				csvOutput.write(appOpenModelArrayListObject.get(i).getmAndroidId());
				csvOutput.write(appOpenModelArrayListObject.get(i).getmDate());
				csvOutput.endRecord();
			}
			
			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
