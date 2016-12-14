package com.bridgelabz.summary;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	
	String startDate = SecretFileModel.getStartDate();
	String endDate = SecretFileModel.getEndDate();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Calendar calender = Calendar.getInstance();

	SummaryReportCsv summuryReportCsv = new SummaryReportCsv();

	// adding the data for app open for first day
	public SummaryReportModel creatReport(ArrayList<AppOpenModel> pAppOpenModelArrayListObject,
			Multimap<String, String> pMultiMapId) {

		//creating object for class
		SummaryReportModel mSummaryReportModelObject = new SummaryReportModel();

		//creating objects of list
		List<String> mDates = new ArrayList<String>();
		List<Integer> mCounts = new ArrayList<Integer>();
		
		//creating map for date as key and id as value
		Multimap<String, String> mTotalCount = ArrayListMultimap.create();
		try {
			Date mStartDate = dateFormat.parse(startDate);
			Date mEndDate = dateFormat.parse(endDate);

			long mDateDifference = mEndDate.getTime()-mStartDate.getTime();
			int mDays = (int) (mDateDifference / (24 * 60 * 60 * 1000));

			Set<String> mKeys = pMultiMapId.keySet();
			// counting the visitors for each date and adding inside totalCount
			for (String key : mKeys) {
				mTotalCount.put((startDate), key);

				for (int k = 1; k <= mDays; k++) {
					
					//code to increase the date by particular days 
					calender.setTime(dateFormat.parse(startDate));
					calender.add(Calendar.DATE, k);
					String date = dateFormat.format(calender.getTime());

					for (int i = 0; i < pAppOpenModelArrayListObject.size(); i++) {

						// System.out.println("date is: "+date);
						if (pAppOpenModelArrayListObject.get(i).getmAndroidId().equals(key)
								&& pAppOpenModelArrayListObject.get(i).getmDate().equals(date.replace("-", ""))) {
							mTotalCount.put((date), pAppOpenModelArrayListObject.get(i).getmAndroidId());

						}
					}
				}
			}

			// System.out.println(task);
			for (int k = 0; k <= mDays; k++) {

				calender.setTime(dateFormat.parse(startDate));
				calender.add(Calendar.DATE, k);
				String date = dateFormat.format(calender.getTime());

				mDates.add(k, date);
				mCounts.add(k, mTotalCount.get(date).size());
				// System.out.println(reportCount.get(Integer.toString(startDate+k)).size());
			}
			mSummaryReportModelObject.setDates(mDates);
			mSummaryReportModelObject.setTotalCount(mCounts);
			mSummaryReportModelObject.setmGaDiscription(pAppOpenModelArrayListObject.get(0).getmGadiscription());
			System.out.println(mSummaryReportModelObject.getTotalCount());
		} catch (Exception e) {
			System.out.println(e);
		}
		return mSummaryReportModelObject;
	}

	// method for creating summary report for all task for first day users
	public SummaryReportModel createSummary(String task, Multimap<String, String> multiMapId,
			ArrayList<AllElementModels> allElementModelArrayListObject) throws IOException {
		//creating object of class
		SummaryReportModel mSummaryReportModelObject = new SummaryReportModel();

		//creating instants of lists
		List<String> mDates = new ArrayList<String>();
		List<Integer> mTotalCount = new ArrayList<Integer>();
		
		//creating map for date as key and id as value
		Multimap<String, String> mReportCount = ArrayListMultimap.create();

		try {

			Date mStartDate = dateFormat.parse(startDate);
			Date mEndDate = dateFormat.parse(endDate);

			long mDateDifference = mEndDate.getTime()-mStartDate.getTime();
			int days = (int) (mDateDifference / (24 * 60 * 60 * 1000));

			Set<String> keys = multiMapId.keySet();
			for (String key : keys) {
				for (int j = 0; j < allElementModelArrayListObject.size(); j++) {
					// System.out.println(task.get(i));
					// if particular task and key matches the condition then
					// only
					// put the value inside hash map
					if (allElementModelArrayListObject.get(j).getmGadiscription().equals(task)) {
						if (allElementModelArrayListObject.get(j).getmAndroidId().equals(key)) {
							// System.out.println(key);
							mReportCount.put(allElementModelArrayListObject.get(j).getmDate(),
									allElementModelArrayListObject.get(j).getmAndroidId());
						}
					}

				}
			}

			// System.out.println(task);
			for (int k = 0; k <= days; k++) {
				calender.setTime(dateFormat.parse(startDate));
				calender.add(Calendar.DATE, k);
				String date = dateFormat.format(calender.getTime());

				mDates.add(k, date);
				mTotalCount.add(k, mReportCount.get(date.replace("-", "")).size());
				//System.out.println(reportCount.get(date.replace("-", "")).size());
			}
			mSummaryReportModelObject.setDates(mDates);
			mSummaryReportModelObject.setTotalCount(mTotalCount);
			mSummaryReportModelObject.setmGaDiscription(task);
		} catch (Exception e) {
			System.out.println(e);
		}
		return mSummaryReportModelObject;
	}

}
