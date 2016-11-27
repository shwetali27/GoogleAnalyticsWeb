package com.bridgelabz.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.dao.HibernateDaoImpl;
import com.bridgelabz.inputReader.InputJsonReader;
import com.bridgelabz.model.GaReportInputModel;
import com.bridgelabz.model.ResponseModel;
import com.bridgelabz.responseElementReader.ResponseElementReader;
import com.bridgelabz.responseFetcher.GaReportResponseFetcher;

@Controller

public class GoogleAnalyticController {
	@Autowired(required = true)
	HibernateDaoImpl hibernateDaoImpl;

	@RequestMapping("/uploadform")
	public ModelAndView uploadForm() {
		System.out.println("File upload get method");
		return new ModelAndView("uploadform");
	}

	@RequestMapping(value = "savefile", method = RequestMethod.POST)
	public ModelAndView uploadForm(@RequestParam MultipartFile file, HttpSession session)
			throws FileNotFoundException, IOException, GeneralSecurityException {
		System.out.println("File upload post method");
		
		//storing the uploaded file inside reader
		InputStreamReader reader=new InputStreamReader(file.getInputStream());
		
		// reading the input json file and storing inside the list
		InputJsonReader inputJsonReader = new InputJsonReader();
		ArrayList<GaReportInputModel> gaReportInputInfoArrayList = inputJsonReader.readInputJsonFile(reader);

		ResponseModel responseModelObject = new ResponseModel();
		GaReportResponseFetcher gaReportResponseFetcherObject = new GaReportResponseFetcher();
		ResponseElementReader elementReader = new ResponseElementReader();

		for (int i = 0; i < gaReportInputInfoArrayList.size(); i++) {

			// making ArrayList of responseModel after passing one by one
			// gaReportInputInfoArrayList

			responseModelObject = gaReportResponseFetcherObject.getResponse(gaReportInputInfoArrayList.get(i));

			// reading the response and finding the result
			elementReader.responseElementReader(
					responseModelObject, gaReportInputInfoArrayList.get(i), gaReportInputInfoArrayList.size());

		}
		System.out.println("Finished");

		//adding the summary report inside database
		hibernateDaoImpl.save(ResponseElementReader.summaryDatabaseModellist);
		return new ModelAndView("fileSuccess", "summaryReportModellist", ResponseElementReader.summaryReportModellist);
	}

}
