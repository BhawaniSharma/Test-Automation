package com.sfdc;

/**
 * Description: Constants properties across the application
 * 
 * @author Bhawani Sharma 
 */
public class Constants {

	//EXCEL sheet properties
	public static final String FILE_NAME = "Coverage Result";
	public static final String EMAIL_SUBJECT = "Run All Test Result";
	public static final String CONTENT_TYPE = "application/vnd.ms-excel";
	public static final String DOT = ".";

	// coverage sheet parameters
	public static final String COVERAGE_SHEET_NAME = "Coverage Result";
	public static final String CLASS_NAME_COLUMN_HEADER = "Apex Class";
	public static final String CLASS_COVERAGE_COLUMN_HEADER = "Coverage";

	// warning sheet parameters
	public static final String COVERAGE_WARNING_SHEET_NAME = "Coverage Warnings";
	public static final String WARNING_NAME_COLUMN_HEADER = "Name";
	public static final String WARNING_MESSAGE_COLUMN_HEADER = "Message";

	// Failure test methods sheet parameters
	public static final String FAILURE_TEST_METHODS_SHEET_NAME = "Failure Methods";
	public static final String FAILURE_TEST_METHODS_CLASS_NAME_COLUMN_HEADER = "Apex Class";
	public static final String FAILURE_TEST_METHODS_NAME_COLUMN_HEADER = "Method Name";
	public static final String FAILURE_TEST_METHODS_MESSAGE_COLUMN_HEADER = "Message";

	// Success test methods sheet parameters
	public static final String SUCCESS_TEST_METHODS_SHEET_NAME = "Successful Methods";
	public static final String SUCCESS_TEST_METHODS_CLASS_NAME_COLUMN_HEADER = "Apex Class";
	public static final String SUCCESS_TEST_METHODS_NAME_COLUMN_HEADER = "Method Name";

	// Run All test summary sheet parameters
	public static final String SUMMARY_SHEET_NAME = "Summary";
	public static final String TOTAL_TEST_METHODS_EXECUTED = "Total methods executed";
	public static final String TOTAL_TEST_METHODS_FAILURE = "Total failures";
	public static final String EXECUTION_TIME = "Total Time";
	
	//EXCEL file extension
	public static final String EXTENSION_TYPE_EXCEL = "xls";
	public static final String AUTHER = "auther";
}
