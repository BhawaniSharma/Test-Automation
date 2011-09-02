package com.sfdc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Description: Create a byte array from coverage file
 * 
 * @author Bhawani
 * 
 */
public class CreateCoverageAttachment {

	/**
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		getFileAttachmentByteArray();
	}

	/**
	 * 
	 * @return: byte array for the coverage file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static byte[] getFileAttachmentByteArray()
			throws FileNotFoundException, IOException {

		// create a file object instance
		File coverageFile = new File(Constants.FILE_NAME + Constants.DOT + Constants.EXTENSION_TYPE_EXCEL);
		InputStream fis;
		fis = new FileInputStream(coverageFile);

		long fileLength = coverageFile.length();

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) fileLength];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = fis.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		fis.close();

		return bytes;
	}

}
