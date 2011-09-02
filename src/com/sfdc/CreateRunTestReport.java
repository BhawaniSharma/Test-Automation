package com.sfdc;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.sforce.soap._2006._08.apex.CodeCoverageResult;
import com.sforce.soap._2006._08.apex.RunTestsResult;

/**
 * Desription : Create a report for run test result
 * 
 * @author Bhawani
 */
public class CreateRunTestReport {

	/**
	 * 
	 * @param runTestsResult :
	 *            Run all test result (iterate results and create the report)
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Throwable
	 */
	public void createTestResultReport(RunTestsResult runTestsResult)
			throws FileNotFoundException, IOException {

		// set up coverage file data
		CoverageReport coverageReport = new CoverageReport();

		// instantiate the XLS file needs to be write
		HSSFWorkbook wb = new HSSFWorkbook();

		// get the coverage result
		CodeCoverageResult[] coverageResults = runTestsResult.getCodeCoverage();

		// check if coverage result is not null only then write the data
		// else there can be some unhandled complilation error
		if (coverageResults != null) {

			// create a list of coverage result wrapper
			ClassCoverageResult[] listClassCoverageResult = new ClassCoverageResult[coverageResults.length];

			int i = 0;
			// iterate through the results
			for (CodeCoverageResult result : coverageResults) {

				// add a wrapper instance
				listClassCoverageResult[i] = new ClassCoverageResult();

				// get the number of lines not covered
				Integer numOfLinesNotCovered = result
						.getNumLocationsNotCovered();

				// get the number of lines covered
				Integer numOfLinesConvered = result.getNumLocations();

				Double coverageResult = (numOfLinesConvered * 1.0)
						/ (numOfLinesConvered + numOfLinesNotCovered) * 100;

				// set the properties
				listClassCoverageResult[i].setClassName(result.getName());
				listClassCoverageResult[i].setCoveragePercentage(coverageResult
						.intValue());

				i++;
			}

			// write coverage result
			coverageReport.createCoverageReport(listClassCoverageResult, wb);

		}

		// write the coverage warnings
		coverageReport.createCodeCoverageWarningSheet(runTestsResult
				.getCodeCoverageWarnings(), wb);

		// write the all the failure cases
		coverageReport
				.createFailureTestsSheet(runTestsResult.getFailures(), wb);

		// write all the success cases
		coverageReport.createSuccessTestsSheet(runTestsResult.getSuccesses(),
				wb);

		// write the summary sheet
		coverageReport.createRunAllTestSummarySheet(runTestsResult, wb);

		// write the file in local disk
		coverageReport.writeCoverageFileOnLocalDisk(wb);
	}
}
