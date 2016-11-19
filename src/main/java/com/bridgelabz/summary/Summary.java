package com.bridgelabz.summary;

import java.util.ArrayList;
import java.util.Set;

import com.bridgelabz.model.AppOpenModel;
import com.bridgelabz.model.SecretFileModel;
import com.bridgelabz.results.SummaryReportCsv;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class Summary {
	SummaryReportCsv summaryReportCsvObject = new SummaryReportCsv();
	
	//getting start date and end date in integer format
	int startDate = Integer.parseInt(SecretFileModel.startDate.replace("-", ""));
	int endDate = Integer.parseInt(SecretFileModel.endDate.replace("-", ""));
	
	//csv creation for app open 
	public Multimap<Integer, String> creatReport(ArrayList<AppOpenModel> appOpenModelArrayListObject,Multimap<String, String> multiMapId ){
		
		Multimap<Integer, String> totalCount = ArrayListMultimap.create();
		Set<String> keys = multiMapId.keySet();
		//counting the visitors for each date and adding inside totalCount
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
		
		return totalCount;
	}
}
