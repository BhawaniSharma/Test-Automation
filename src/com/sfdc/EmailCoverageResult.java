package com.sfdc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.sforce.soap.enterprise.EmailFileAttachment;
import com.sforce.soap.enterprise.SendEmailError;
import com.sforce.soap.enterprise.SendEmailResult;
import com.sforce.soap.enterprise.SingleEmailMessage;

/**
 * Description: Email Coverage result file
 * 
 * @author Bhawani
 */
public class EmailCoverageResult {

	/**
	 * description: This method is to send the coverage result file to the
	 * defined user via email
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void sendEmail() throws FileNotFoundException, IOException {

		EmailFileAttachment[] fileAttachments = new EmailFileAttachment[1];
		EmailFileAttachment fileAttachment = new EmailFileAttachment();
		fileAttachment.setBody(CreateCoverageAttachment
				.getFileAttachmentByteArray());
		fileAttachment.setFileName(Constants.FILE_NAME + Constants.DOT + Constants.EXTENSION_TYPE_EXCEL);
		fileAttachment.setContentType(Constants.CONTENT_TYPE);
		fileAttachments[0] = fileAttachment;

		// get the to addresses from properties file
		Properties properties = new Properties();
		properties.load(new FileInputStream("build.properties"));
		String emailToAddresses = properties.getProperty("sf.toAddresses");
		String emailCCAddresses = properties.getProperty("sf.ccAddresses");
		String emailBCCAddresses = properties.getProperty("sf.bccAddresses");

		// create a message instance to be sent
		SingleEmailMessage message = new SingleEmailMessage();

		// set message properties
		message.setToAddresses(emailToAddresses.split(";"));
		if (emailCCAddresses != null && emailCCAddresses != "")
			message.setCcAddresses(emailCCAddresses.split(";"));
		if (emailBCCAddresses != null && emailBCCAddresses != "")
			message.setBccAddresses(emailBCCAddresses.split(";"));
		message.setTargetObjectId(Login.USER_ID);
		message.setSubject(Constants.EMAIL_SUBJECT);
		message.setPlainTextBody("");
		message.setSaveAsActivity(false);
		message.setFileAttachments(new EmailFileAttachment[] { fileAttachment });

		// send the email notification
		SendEmailResult[] results = Login.SOAP_BINDING_STUB
				.sendEmail(new SingleEmailMessage[] { message });

		// iterate the result to check if mail is sent successfully
		for (SendEmailResult result : results) {
			if (!result.isSuccess()) {
				for (SendEmailError error : result.getErrors()) {
					System.out.println(error.getMessage());
				}
			}
		}
	}
}
