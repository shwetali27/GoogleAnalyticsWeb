package com.bridgelabz.summary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.bridgelabz.model.AllElementModels;
import com.bridgelabz.model.AppOpenModel;
import com.bridgelabz.model.SecretFileModel;
import com.bridgelabz.model.SummaryReportModel;
import com.bridgelabz.results.SummaryReportCsv;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class Summary {
	// getting start date and end date in integer format
	int startDate = Integer.parseInt(SecretFileModel.startDate.replace("-", ""));
	int endDate = Integer.parseInt(SecretFileModel.endDate.replace("-", ""));

	SummaryReportCsv summuryReportCsv = new SummaryReportCsv();

	//adding the data for app open for first day
	public SummaryReportModel creatReport(ArrayList<AppOpenModel> appOpenModelArrayListObject,
			Multimap<String, String> multiMapId) {
		
		SummaryReportModel summaryReportModelObject = new SummaryReportModel();
		
		Multimap<Integer, String> totalCount = ArrayListMultimap.create();
		Set<String> keys = multiMapId.keySet();
		// counting the visitors for each date and adding inside totalCount
		for (String key : keys) {
			totalCount.put((startDate), key);
			
			for (int k = 1; k <= (endDate - startDate); k++) {
				String date = Integer.toString(startDate + k);
				for (int i = 0; i < appOpenModelArrayListObject.size(); i++) {

					// System.out.println("date is: "+date);
					if (appOpenModelArrayListObject.get(i).getmAndroidId().equals(key)
							&& appOpenModelArrayListObject.get(i).getmDate().equals(date)) {
						totalCount.put((startDate + k), appOpenModelArrayListObject.get(i).getmAndroidId());

					}
				}
			}
		}
		
		List<Integer> dates = new ArrayList<Integer>();
		List<Integer> count = new ArrayList<Integer>();
		// System.out.println(task);
		for (int k = 0; k <= (endDate - startDate); k++) {
			dates.add(k,startDate+k);
			count.add(k, totalCount.get(startDate+k).size());
			// System.out.println(reportCount.get(Integer.toString(startDate+k)).size());
		}
		summaryReportModelObject.setDates(dates);
		summaryReportModelObject.setTotalCount(count);
		summaryReportModelObject.setmGaDiscription(appOpenModelArrayListObject.get(0).getmGadiscription());
		System.out.println(summaryReportModelObject.getTotalCount());

		return summaryReportModelObject;
	}

	// method for creating summary report for all task for first day users
	public SummaryReportModel createSummary(String task, Multimap<String, String> multiMapId,
			ArrayList<AllElementModels> allElementModelArrayListObject) throws IOException {
		
		SummaryReportModel summaryReportModelObject = new SummaryReportModel();

		Multimap<String, String> reportCount = ArrayListMultimap.create();

		Set<String> keys = multiMapId.keySet();
		for (String key : keys) {
			for (int j = 0; j < allElementModelArrayListObject.size(); j++) {
				// System.out.println(task.get(i));
				// if particular task and key matches the condition then only
				// put the value inside hash map
				if (allElementModelArrayListObject.get(j).getmGadiscription().equals(task)) {
					if (allElementModelArrayListObject.get(j).getmAndroidId().equals(key)) {
						// System.out.println(key);
						reportCount.put(allElementModelArrayListObject.get(j).getmDate(),
								allElementModelArrayListObject.get(j).getmAndroidId());
					}
				}

			}
		}

		List<Integer> dates = new ArrayList<Integer>();
		List<Integer> totalCount = new ArrayList<Integer>();
		// System.out.println(task);
		for (int k = 0; k <= (endDate - startDate); k++) {
			dates.add(k,startDate+k);
			totalCount.add(k, reportCount.get(Integer.toString(startDate+k)).size());
			// System.out.println(reportCount.get(Integer.toString(startDate+k)).size());
		}
		summaryReportModelObject.setDates(dates);
		summaryReportModelObject.setTotalCount(totalCount);
		summaryReportModelObject.setmGaDiscription(task);

		return summaryReportModelObject;
	}
}
