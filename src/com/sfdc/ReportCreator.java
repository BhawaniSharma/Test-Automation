package com.sfdc;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.rpc.ServiceException;

public class ReportCreator {
	public static void main(String[] args) {

		// make a login call and get the session Id
		String sessionId;
		try {
			sessionId = Login.getSessionId();

			// Create an instance of the class to run all the test methods
			RunAllTest runAllTest = new RunAllTest();
			runAllTest.runAllTest(sessionId);

			// send the created report via email
			EmailCoverageResult.sendEmail();
			System.out.println("Mail sent successfully.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
