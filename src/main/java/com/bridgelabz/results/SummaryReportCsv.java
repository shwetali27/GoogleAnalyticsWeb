package com.bridgelabz.results;

import java.io.File;
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
	String summaryreport = ConstantData.resultsFilepath + "SummaryReport.csv";

	//method for creating the summary report
	public void createSummaryReport(ArrayList<SummaryReportModel> summaryReportModelList) throws IOException {
		System.out.println("Inside initialize report");

		File file = new File(summaryreport);
		if (file.exists()) {
			// System.out.println("inside delete");
			file.delete();
		}

		CsvWriter csvOutput = new CsvWriter(new FileWriter(summaryreport, true), ConstantData.csvDelimiter);
		csvOutput.write("Task/Date");
		for (int i = 0; i <= (endDate - startDate); i++) {
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
	}
}
