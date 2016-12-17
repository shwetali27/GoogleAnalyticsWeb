package com.bridgelabz.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.bridgelabz.model.DateData;

@Component
public class Validator {
	public static void validate(DateData dateData, Errors errors) {
		long mDateDifference = 0;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			// validation for start date
			if (dateData.getStartDate() == null) {
				System.out.println("inside start date validator");
				errors.rejectValue("startDate", "Is Required");
			}

			// validation for end date
			if (dateData.getEndDate() == null) {
				System.out.println("Inside end date validator");
				errors.rejectValue("endDate", "Is Required");
			}

			if (dateData.getStartDate() != null && dateData.getEndDate() != null) {
				Date mStartDate = dateFormat.parse(dateData.getStartDate());
				Date mEndDate = dateFormat.parse(dateData.getEndDate());

				mDateDifference = mEndDate.getTime() - mStartDate.getTime();
			}
			if (mDateDifference < 0) {
				System.out.println("-----");
				errors.rejectValue("endDate", "select properly");
			}
		} catch (ParseException e) {
			System.out.println(e);
		}

	}
}
