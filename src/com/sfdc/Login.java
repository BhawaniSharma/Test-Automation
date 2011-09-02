package com.sfdc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.xml.rpc.ServiceException;

import com.sforce.soap.enterprise.LoginResult;
import com.sforce.soap.enterprise.SessionHeader;
import com.sforce.soap.enterprise.SforceServiceLocator;
import com.sforce.soap.enterprise.SoapBindingStub;

/**
 * Description : Class to make a connection with salesforce instance
 * 
 * @author Bhawani
 * 
 */
public class Login {

	// hold the user id across the request
	public static String USER_ID;
	public static SoapBindingStub SOAP_BINDING_STUB;

	/**
	 * 
	 * @param userName
	 *            : SFDC username
	 * @param password
	 *            : SFDC password + security token
	 * @return : SFDC session Id
	 * @throws ServiceException
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Throwable
	 */
	public static String getSessionId() throws ServiceException,
			FileNotFoundException, IOException {
		SoapBindingStub soapBindingStub = (SoapBindingStub) new SforceServiceLocator()
				.getSoap();

		// read the property file and get the username and password
		Properties properties = new Properties();
		properties.load(new FileInputStream("build.properties"));

		LoginResult loginResult = soapBindingStub.login(
				properties.getProperty("sf.username"),
				properties.getProperty("sf.password"));

		// create a session to set the required properties
		SessionHeader sessionHeader = new SessionHeader();
		sessionHeader.setSessionId(loginResult.getSessionId());

		// set the session header
		soapBindingStub.setHeader(new SforceServiceLocator().getServiceName()
				.getNamespaceURI(), "SessionHeader", sessionHeader);

		soapBindingStub._setProperty(SoapBindingStub.ENDPOINT_ADDRESS_PROPERTY,
				loginResult.getServerUrl());

		USER_ID = loginResult.getUserId();
		SOAP_BINDING_STUB = soapBindingStub;
		return loginResult.getSessionId();
	}

}
