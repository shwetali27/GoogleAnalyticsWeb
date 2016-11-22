package com.bridgelabz.responseFetcher;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.log4j.Logger;

import com.bridgelabz.model.GaReportInputModel;
import com.bridgelabz.model.ResponseModel;
import com.bridgelabz.responseReader.ResponseReader;
import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;

public class GaReportResponseFetcher {
	
	Logger logger = Logger.getLogger(GaReportResponseFetcher.class);
	// creating object of InitializeAnalyticsReporting
	GAreportHandler reportHandler = GAreportHandler.getInstance();
	AnalyticsReporting service;

	// default constructor
	public GaReportResponseFetcher() throws GeneralSecurityException, IOException {
		// calling initializeAnalyticsReporting method of
		// InitializeAnalyticsReporting class to initialize all credential
		this.service = reportHandler.initializeAnalyticsReporting();
	}

	/*-------------------------method to get the response model ArrayList------------------------------------*/
	public ResponseModel getResponse(GaReportInputModel gaReportInputModel) {
		// creating object of ResponseModel
		ResponseModel responseModelObject = new ResponseModel();

		try {

			// calling getReport method to get response
			GetReportsResponse response = reportHandler.getReport(service, gaReportInputModel);

			System.out.println(response);
			//logger.debug(response+"\n\n");

			// reading response and placing it to responseModelArrayList
			responseModelObject = ResponseReader.responseReader(response.toString());
		} catch (Exception e) {
			e.printStackTrace();

		}

		return responseModelObject;
	}
}
