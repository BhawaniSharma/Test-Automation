package com.sfdc;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.rpc.ServiceException;

import com.sforce.soap._2006._08.apex.ApexBindingStub;
import com.sforce.soap._2006._08.apex.ApexServiceLocator;
import com.sforce.soap._2006._08.apex.RunTestsRequest;
import com.sforce.soap._2006._08.apex.RunTestsResult;
import com.sforce.soap.enterprise.SessionHeader;

/**
 * Description : Run SFDC's all test methods
 * 
 * @author Bhawani
 */
public class RunAllTest {

	/**
	 * 
	 * @param sessionId
	 *            : SFDC session Id
	 * @throws ServiceException
	 * @throws IOException
	 * @throws FileNotFoundException
	 * 
	 */
	public void runAllTest(String sessionId) throws ServiceException,
			FileNotFoundException, IOException {

		// create a session to set the required properties
		SessionHeader sessionHeader = new SessionHeader();
		sessionHeader.setSessionId(sessionId);

		// create a stub to execute the Apex methods
		ApexBindingStub apexBinding = (ApexBindingStub) new ApexServiceLocator()
				.getApex();

		// set the session header
		apexBinding.setHeader(new ApexServiceLocator().getServiceName()
				.getNamespaceURI(), "SessionHeader", sessionHeader);

		RunTestsRequest runTestsRequest = new RunTestsRequest();

		// set the org namespace
		runTestsRequest.setNamespace("");

		// run all the test methods
		runTestsRequest.setAllTests(true);
		RunTestsResult runTestsResult = apexBinding.runTests(runTestsRequest);

		// call the method to create the test coverage result
		CreateRunTestReport createRunTestReport = new CreateRunTestReport();
		createRunTestReport.createTestResultReport(runTestsResult);
	}
}