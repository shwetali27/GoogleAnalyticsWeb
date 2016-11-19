package com.bridgelabz.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.inputReader.InputJsonReader;
import com.bridgelabz.model.GaReportInputModel;
import com.bridgelabz.model.ResponseModel;
import com.bridgelabz.responseElementReader.ResponseElementReader;
import com.bridgelabz.responseFetcher.GaReportResponseFetcher;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

@Controller
public class GoogleAnalyticController {
	private static final String UPLOAD_DIRECTORY = "/home/bridgeit/FileUpload";
	
	@RequestMapping("/uploadform")
	public ModelAndView uploadForm() {
		System.out.println("File upload get method");
		return new ModelAndView("uploadform");
	}
	
	@RequestMapping(value="savefile", method = RequestMethod.POST) 
	public ModelAndView uploadForm(@RequestParam MultipartFile file,HttpSession session) throws FileNotFoundException, IOException, GeneralSecurityException {
		System.out.println("File upload get method");
		FileCopyUtils.copy(file.getBytes(), new FileOutputStream(UPLOAD_DIRECTORY + "/" + file.getOriginalFilename()));
		String jsonfilePath = UPLOAD_DIRECTORY + "/" + file.getOriginalFilename();
		System.out.println("File is Stored in: " + jsonfilePath);
		//reading the input json file and storing inside the list
				InputJsonReader inputJsonReader = new InputJsonReader();
				ArrayList<GaReportInputModel> gaReportInputInfoArrayList = inputJsonReader.readInputJsonFile(jsonfilePath);
			
				ResponseModel responseModelObject = new ResponseModel();
				GaReportResponseFetcher gaReportResponseFetcherObject = new GaReportResponseFetcher(); 
				ResponseElementReader elementReader = new ResponseElementReader();
				
				for (int i = 0; i < gaReportInputInfoArrayList.size(); i++) {

					// making ArrayList of responseModel after passing one by one gaReportInputInfoArrayList

					responseModelObject = gaReportResponseFetcherObject
							.getResponse(gaReportInputInfoArrayList.get(i));

					//reading the response and finding the result
					elementReader.responseElementReader(responseModelObject,
							gaReportInputInfoArrayList.get(i),gaReportInputInfoArrayList.size());

				}
				System.out.println("Finished");
		
				System.out.println(ResponseElementReader.list.get(0).get(20161009).size());
		return new ModelAndView("fileSuccess","gaReportResponseFetcherObject",gaReportInputInfoArrayList);
	}

	
}
