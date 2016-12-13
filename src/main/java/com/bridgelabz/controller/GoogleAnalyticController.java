package com.bridgelabz.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.dao.HibernateDaoImpl;
import com.bridgelabz.inputReader.InputJsonReader;
import com.bridgelabz.model.Date;
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
	public ModelAndView uploadForm(@ModelAttribute Date date, @RequestParam MultipartFile file)
			throws FileNotFoundException, IOException, GeneralSecurityException {
		
		System.out.println(date.getStartDate()+","+date.getEndDate());
		System.out.println("is Empty: "+date.getEndDate().isEmpty());
		ResponseElementReader.summaryReportModellist.clear();
		ResponseElementReader.summaryDatabaseModellist.clear();

		if(date.getStartDate().isEmpty() & date.getEndDate().isEmpty()){
			
		}
		System.out.println("File upload post method");
		// storing the uploaded file inside reader
		InputStreamReader reader = new InputStreamReader(file.getInputStream());

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
			elementReader.responseElementReader(responseModelObject, gaReportInputInfoArrayList.get(i),
					gaReportInputInfoArrayList.size());

		}
		System.out.println("Finished");

		if (ResponseElementReader.summaryDatabaseModellist.size() > 0) {
			hibernateDaoImpl.truncate();
		}
		// adding the summary report inside database
		hibernateDaoImpl.save(ResponseElementReader.summaryDatabaseModellist);
		return new ModelAndView("fileSuccess", "summaryReportModellist", ResponseElementReader.summaryReportModellist);
	}

	//method for downloading the summary report
	@RequestMapping(value="download",method=RequestMethod.GET)
	public void downloadFile(HttpServletResponse response){
		System.out.println("inside file download");
		try{
			//reading file from classpath 
			File file = new File(getClass().getClassLoader().getResource("SummaryReport.csv").getFile());
			
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			System.out.println("mimetype before : " + mimeType);
	
			//for default mime type
			if (mimeType == null) {
				System.out.println("mimetype is not detectable, will take default");
				mimeType = "application/octet-stream";
			}
	
			System.out.println("mimetype : " + mimeType);
			
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() +"\""));
	
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		}catch (IOException e) {
			System.out.println(e);
		}
	}
}
