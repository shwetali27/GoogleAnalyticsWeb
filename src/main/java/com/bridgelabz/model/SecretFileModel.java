package com.bridgelabz.model;

public class SecretFileModel {
	public static String startDate;
	public static String endDate;
	public static String APPLICATION_NAME;
	public static String KEY_FILE_LOCATION;
	public static String SERVICE_ACCOUNT_EMAIL;
	public static String VIEW_ID;
	public static String CsvFilePath;
	
	//getters and setters
	public static String getStartDate() {
		return startDate;
	}
	public static void setStartDate(String startDate) {
		SecretFileModel.startDate = startDate;
	}
	public static String getEndDate() {
		return endDate;
	}
	public static void setEndDate(String endDate) {
		SecretFileModel.endDate = endDate;
	}
	public static String getAPPLICATION_NAME() {
		return APPLICATION_NAME;
	}
	public static void setAPPLICATION_NAME(String aPPLICATION_NAME) {
		APPLICATION_NAME = aPPLICATION_NAME;
	}
	public static String getKEY_FILE_LOCATION() {
		return KEY_FILE_LOCATION;
	}
	public static void setKEY_FILE_LOCATION(String kEY_FILE_LOCATION) {
		KEY_FILE_LOCATION = kEY_FILE_LOCATION;
	}
	public static String getSERVICE_ACCOUNT_EMAIL() {
		return SERVICE_ACCOUNT_EMAIL;
	}
	public static void setSERVICE_ACCOUNT_EMAIL(String sERVICE_ACCOUNT_EMAIL) {
		SERVICE_ACCOUNT_EMAIL = sERVICE_ACCOUNT_EMAIL;
	}
	public static String getVIEW_ID() {
		return VIEW_ID;
	}
	public static void setVIEW_ID(String vIEW_ID) {
		VIEW_ID = vIEW_ID;
	}
	public static String getCsvFilePath() {
		return CsvFilePath;
	}
	public static void setCsvFilePath(String csvFilePath) {
		CsvFilePath = csvFilePath;
	}
	
	
}
