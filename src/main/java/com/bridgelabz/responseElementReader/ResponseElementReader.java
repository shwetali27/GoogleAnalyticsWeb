package com.bridgelabz.responseElementReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import com.bridgelabz.constants.ConstantData;
import com.bridgelabz.model.AllElementModels;
import com.bridgelabz.model.AppOpenModel;
import com.bridgelabz.model.AppReOpenModel;
import com.bridgelabz.model.GaReportInputModel;
import com.bridgelabz.model.ResponseModel;
import com.bridgelabz.model.SecretFileModel;
import com.bridgelabz.results.AppOpenCsv;
import com.bridgelabz.results.AppReopenCsv;
import com.bridgelabz.results.Operations;
import com.bridgelabz.results.SummaryReportCsv;
import com.bridgelabz.summary.Summary;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class ResponseElementReader {
	
	//creating the object for classes
	Summary summaryObject = new Summary();
	SummaryReportCsv summaryReportCsvObject = new SummaryReportCsv();

	int sum = 0;

	// creating object of dimensionHashMapArrayList to store
	// dimensionHashMapArrayList
	ArrayList<HashMap<String, String>> dimensionHashMapArrayList = new ArrayList<HashMap<String, String>>();
	// valueList to store values
	ArrayList<HashMap<String, String>> valueList = new ArrayList<HashMap<String, String>>();

	Operations operationObject = new Operations();
	Multimap<String, String> multiMapId = ArrayListMultimap.create();
	Multimap<String, String> multiMapEvent = ArrayListMultimap.create();
	Multimap<String, Collection<String>> multiMapvalue = ArrayListMultimap.create();

	// hash set for app open android id
	HashSet<String> androidIdAppOpen;
	// hash set for app Reopen android id
	HashSet<String> androidIdReAppOpen;

	//arraylist of map for date and total visited android ids
	ArrayList<Multimap<Integer, String>> list = new ArrayList<Multimap<Integer,String>>();
	//list for event performed
	ArrayList<String> task = new ArrayList<String>();
	//map for date and total visited android ids
	Multimap<Integer, String> totalCount = ArrayListMultimap.create();

	
	public void responseElementReader(ResponseModel responseModelObject, GaReportInputModel gaReportInputModel,
			int size) throws IOException {
		try {

			sum++;
			// creating object of ArrayListAppOpenModel
			ArrayList<AppOpenModel> appOpenModelArrayListObject = new ArrayList<AppOpenModel>();
			// creating object of ArrayListReAppOpenModel
			ArrayList<AppReOpenModel> appReOpenModelArrayListObject = new ArrayList<AppReOpenModel>();
			// creating object of AllElementArrayList
			ArrayList<AllElementModels> allElementModelArrayListObject = new ArrayList<AllElementModels>();
			boolean appOpenFlag = false;
			boolean appReOpenFlag = false;
			boolean allElementFlag = false;

			// assigning to dimensionHashMapArrayList
			dimensionHashMapArrayList = responseModelObject.getDimensionHashMapArrayList();
			valueList = responseModelObject.getMetricHashMapArrayList();
			// System.out.println("valuelist is is:"+valueList);

			/*-----------------------if response object have null value------------------------*/
			if (dimensionHashMapArrayList.equals(ConstantData.isNull)) {
				AllElementModels allElementModelsObject = new AllElementModels();
				allElementModelsObject.setmDate("");
				allElementModelsObject.setmAndroidId("");
			}

			else {
				for (int i = 0; i < dimensionHashMapArrayList.size(); i++) {
					// creating object of AppOpenModel
					AppOpenModel appOpenModelObject = new AppOpenModel();
					// creating object of AppReOpenModel
					AppReOpenModel appReOpenModelObject = new AppReOpenModel();
					// creating object of AllElementModel
					AllElementModels allElementModelsObject = new AllElementModels();

					// iterating element of hashmapArrayList
					for (Entry<String, String> dimentionList : dimensionHashMapArrayList.get(i).entrySet()) {

						/*----------------------------------for appOpen-----------------------------------------------------*/
						if (gaReportInputModel.getmGaID().equals(ConstantData.one)) {

							appOpenFlag = true;
							appOpenModelObject.setmGaId(gaReportInputModel.getmGaID());

							appOpenModelObject.setmGadiscription(gaReportInputModel.getmGaDiscription());

							if (dimentionList.getKey().equals(ConstantData.date)) {
								appOpenModelObject.setmDate(dimentionList.getValue());

							}
							if (dimentionList.getKey().equals(ConstantData.dimension1)) {
								appOpenModelObject.setmAndroidId(dimentionList.getValue());

							}
							if (dimentionList.getKey().equals(ConstantData.eventCategory)) {
								appOpenModelObject.setmEventcategory(dimentionList.getValue());

							}

						}
						/*----------------------------------for appReOpen------------------------------------------------*/

						if (gaReportInputModel.getmGaID().equals(ConstantData.two)) {
							
							appReOpenFlag = true;
							appReOpenModelObject.setmGaId(gaReportInputModel.getmGaID());

							appReOpenModelObject.setmGadiscription(gaReportInputModel.getmGaDiscription());

							if (dimentionList.getKey().equals(ConstantData.date)) {
								appReOpenModelObject.setmDate(dimentionList.getValue());

							}
							if (dimentionList.getKey().equals(ConstantData.dimension1)) {
								appReOpenModelObject.setmAndroidId(dimentionList.getValue());

							}
							if (dimentionList.getKey().equals(ConstantData.eventCategory)) {
								appReOpenModelObject.setmEventcategory(dimentionList.getValue());

							}

						}
						/*-------------if other than App open and ReOpen------*/
						if (!gaReportInputModel.getmGaID().equals(ConstantData.one) && !gaReportInputModel.getmGaID().equals(ConstantData.two)) {
							allElementFlag = true;
							allElementModelsObject.setmGaid(gaReportInputModel.getmGaID());

							allElementModelsObject.setmGadiscription(gaReportInputModel.getmGaDiscription());

							if (dimentionList.getKey().equals(ConstantData.date)) {
								allElementModelsObject.setmDate(dimentionList.getValue());
							}
							if (dimentionList.getKey().equals(ConstantData.dimension1)) {
								allElementModelsObject.setmAndroidId(dimentionList.getValue());
							}
							
						}

					}
					if (appOpenFlag)
						appOpenModelArrayListObject.add(appOpenModelObject);
					if (appReOpenFlag)
						appReOpenModelArrayListObject.add(appReOpenModelObject);
					if (allElementFlag)
						allElementModelArrayListObject.add(allElementModelsObject);
				}
			}

			// System.out.println(allElementModelArrayListObject.toString());

			/*----------------------getting data for app Open and putting inside map--------------------*/
			if (gaReportInputModel.getmGaID().equals(ConstantData.one)) {

				//creating csv file for app open
				AppOpenCsv appOpenCsv = new AppOpenCsv();
				appOpenCsv.createReport(appOpenModelArrayListObject);
				for (int i = 0; i < appOpenModelArrayListObject.size(); i++) {
					// System.out.println(SecretFileModel.startDate.replace("-",""));
					// taking the id for first day app open
					if (appOpenModelArrayListObject.get(i).getmDate()
							.equals(SecretFileModel.startDate.replace("-", ""))) {
						multiMapId.put(appOpenModelArrayListObject.get(i).getmAndroidId(),
								appOpenModelArrayListObject.get(i).getmDate());
						multiMapEvent.put(appOpenModelArrayListObject.get(i).getmAndroidId(),
								appOpenModelArrayListObject.get(i).getmGadiscription());
						multiMapvalue.put(appOpenModelArrayListObject.get(i).getmAndroidId(),
								valueList.get(i).values());

					}
				}

				//checking if particular android id have opened the app again on another date
				for (int i = 0; i < appOpenModelArrayListObject.size(); i++) {
					Set<String> keys = multiMapId.keySet();
					// System.out.println(keys.size());
					for (String key : keys) {
						// if particular android id is present inside app open
						// then only add data
						if (appOpenModelArrayListObject.get(i).getmAndroidId().equals(key)
								&& !appOpenModelArrayListObject.get(i).getmDate()
										.equals(SecretFileModel.startDate.replace("-", ""))) {
							
							multiMapId.put(appOpenModelArrayListObject.get(i).getmAndroidId(),
									appOpenModelArrayListObject.get(i).getmDate());
							multiMapEvent.put(appOpenModelArrayListObject.get(i).getmAndroidId(),
									appOpenModelArrayListObject.get(i).getmGadiscription());
							multiMapvalue.put(appOpenModelArrayListObject.get(i).getmAndroidId(),
									valueList.get(i).values());
						}
					}
				}
				task.add(appOpenModelArrayListObject.get(0).getmGadiscription());
				//calling the method for summary report creation
				totalCount.clear();
				totalCount = summaryObject.creatReport(appOpenModelArrayListObject, multiMapId);
				list.add(totalCount);
			}

			/*----------------------getting data for app Reopen and putting inside map--------------------*/
			if (gaReportInputModel.getmGaID().equals(ConstantData.two)) {

				//creating csv for app reopen
				AppReopenCsv appReopenCsv = new AppReopenCsv();
				appReopenCsv.createReport(appReOpenModelArrayListObject);
				
				for (int i = 0; i < appReOpenModelArrayListObject.size(); i++) {
					Set<String> keys = multiMapId.keySet();
					// System.out.println(keys.size());
					for (String key : keys) {

						// if particular android id is present inside app open
						// then only add data
						if (appReOpenModelArrayListObject.get(i).getmAndroidId().equals(key)) {
							
							multiMapId.put(appReOpenModelArrayListObject.get(i).getmAndroidId(),
									appReOpenModelArrayListObject.get(i).getmDate());
							multiMapEvent.put(appReOpenModelArrayListObject.get(i).getmAndroidId(),
									appReOpenModelArrayListObject.get(i).getmGadiscription());
							multiMapvalue.put(appReOpenModelArrayListObject.get(i).getmAndroidId(),
									valueList.get(i).values());
						}
					}
				}

			}
			
			/*----------------------getting data for other task and putting inside map--------------------*/			if (!gaReportInputModel.getmGaID().equals("1") && !gaReportInputModel.getmGaID().equals("2")) {
				for (int i = 0; i < allElementModelArrayListObject.size(); i++) {
					Set<String> keys = multiMapId.keySet();
					for (String key : keys) {
						// if particular android id is present inside app open
						// then only add data
						if (allElementModelArrayListObject.get(i).getmAndroidId().equals(key)) {
							multiMapId.put(allElementModelArrayListObject.get(i).getmAndroidId(),
									allElementModelArrayListObject.get(i).getmDate());
							multiMapEvent.put(allElementModelArrayListObject.get(i).getmAndroidId(),
									allElementModelArrayListObject.get(i).getmGadiscription());
							multiMapvalue.put(allElementModelArrayListObject.get(i).getmAndroidId(),
									valueList.get(i).values());
						}
					}
				}

			}
		} catch (Exception e) {
			System.out.println("there is 0 rows in response");
			// e.printStackTrace();

		}

		//creating the report text file
		if (sum == size) {
			operationObject.fileCreation(multiMapId, multiMapEvent, multiMapvalue);
		}
		
		//calling the method for csv creation
		summaryReportCsvObject.csvCreation(task, list);

	}// end of method

}
