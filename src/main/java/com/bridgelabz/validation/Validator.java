package com.bridgelabz.validation;

import org.springframework.stereotype.Component;

import com.bridgelabz.model.DateData;
import org.springframework.validation.Errors;

@Component
public class Validator {
	public static void validate(DateData dateData, Errors errors) {

		//validation for start date
		if (dateData.getStartDate() == null) {
			errors.rejectValue("startDate","Is Required");
		}

		//validation for end date
		if (dateData.getEndDate()  == null) {
			errors.rejectValue("endDate","Is Required");
		}

	}
}
