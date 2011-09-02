package com.sfdc;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

import com.sforce.soap._2006._08.apex.CodeCoverageWarning;
import com.sforce.soap._2006._08.apex.RunTestFailure;
import com.sforce.soap._2006._08.apex.RunTestSuccess;
import com.sforce.soap._2006._08.apex.RunTestsResult;

/**
 * Description: Create a file with the coverage
 * 
 * @author Bhawani
 */
public class CoverageReport {

	/**
	 * 
	 * @param results
	 *            : list of coverage
	 * @throws IOException
	 */
	public void createCoverageReport(ClassCoverageResult[] results,
			HSSFWorkbook wb) {

		// get the header row style
		HSSFCellStyle style = getStyleForHeader(wb);

		HSSFSheet coverageSheet = wb.createSheet(Constants.COVERAGE_SHEET_NAME);
		HSSFRow headerRow = coverageSheet.createRow((short) 0);

		HSSFCell apexClassNameCell = headerRow.createCell(0);
		apexClassNameCell.setCellStyle(style);
		apexClassNameCell.setCellValue(Constants.CLASS_NAME_COLUMN_HEADER);

		HSSFCell coverageCell = headerRow.createCell(1);
		coverageCell.setCellValue(Constants.CLASS_COVERAGE_COLUMN_HEADER);
		coverageCell.setCellStyle(style);

		if (results != null && results.length > 0) {

			// create report data
			HSSFRow dataRow;

			// loop through the results
			for (int i = 1; i <= results.length; i++) {
				ClassCoverageResult result = results[i - 1];
				dataRow = coverageSheet.createRow(i);
				dataRow.createCell(0).setCellValue(result.getClassName());
				dataRow.createCell(1).setCellValue(
						result.getCoveragePercentage());
			}
		}

	}

	/**
	 * Description: write code coverage warning in sheet
	 * 
	 * @param codeCoverageWarnings
	 */
	public void createCodeCoverageWarningSheet(
			CodeCoverageWarning[] codeCoverageWarnings, HSSFWorkbook wb) {

		// get the header row style
		HSSFCellStyle style = getStyleForHeader(wb);

		// create a new sheet to hold the coverage warnings
		HSSFSheet warningsSheet = wb
				.createSheet(Constants.COVERAGE_WARNING_SHEET_NAME);
		HSSFRow headerRow = warningsSheet.createRow((short) 0);

		// component header
		HSSFCell componentNameCell = headerRow.createCell(0);
		componentNameCell.setCellStyle(style);
		componentNameCell.setCellValue(Constants.WARNING_NAME_COLUMN_HEADER);

		// warning message header
		HSSFCell warningMessage = headerRow.createCell(1);
		warningMessage.setCellValue(Constants.WARNING_MESSAGE_COLUMN_HEADER);
		warningMessage.setCellStyle(style);

		// check if there is any warning
		if (codeCoverageWarnings != null && codeCoverageWarnings.length > 0) {

			// create report data
			HSSFRow dataRow;

			// loop through the results
			for (int i = 1; i <= codeCoverageWarnings.length; i++) {
				CodeCoverageWarning warning = codeCoverageWarnings[i - 1];
				dataRow = warningsSheet.createRow(i);
				dataRow.createCell(0).setCellValue(warning.getName());
				dataRow.createCell(1).setCellValue(warning.getMessage());
			}
		}
	}

	/**
	 * Description: write all the failure cases in sheet
	 * 
	 * @param failure
	 *            : Run All Test failure result list
	 */
	public void createFailureTestsSheet(RunTestFailure[] failures,
			HSSFWorkbook wb) {

		// get the header row style
		HSSFCellStyle style = getStyleForHeader(wb);

		// create a new sheet to hold the coverage warning
		HSSFSheet failureCasesSheet = wb
				.createSheet(Constants.FAILURE_TEST_METHODS_SHEET_NAME);
		HSSFRow headerRow = failureCasesSheet.createRow((short) 0);

		// Class Name header
		HSSFCell apexClassNameCell = headerRow.createCell(0);
		apexClassNameCell.setCellStyle(style);
		apexClassNameCell
				.setCellValue(Constants.FAILURE_TEST_METHODS_CLASS_NAME_COLUMN_HEADER);

		// method name header
		HSSFCell methodNameCell = headerRow.createCell(1);
		methodNameCell
				.setCellValue(Constants.FAILURE_TEST_METHODS_NAME_COLUMN_HEADER);
		methodNameCell.setCellStyle(style);

		// failure message header
		HSSFCell failureCauseCell = headerRow.createCell(2);
		failureCauseCell
				.setCellValue(Constants.FAILURE_TEST_METHODS_MESSAGE_COLUMN_HEADER);
		failureCauseCell.setCellStyle(style);

		// check if there is any failure
		if (failures != null && failures.length > 0) {

			// create report data
			HSSFRow dataRow;

			// loop through the results
			for (int i = 1; i <= failures.length; i++) {
				RunTestFailure failure = failures[i - 1];
				dataRow = failureCasesSheet.createRow(i);
				dataRow.createCell(0).setCellValue(failure.getName());
				dataRow.createCell(1).setCellValue(failure.getMethodName());
				dataRow.createCell(2).setCellValue(failure.getMessage());
			}
		}
	}

	/**
	 * Description: write all the success methods in sheet
	 * 
	 * @param successes
	 *            : Run All Test success result list
	 */
	public void createSuccessTestsSheet(RunTestSuccess[] successes,
			HSSFWorkbook wb) {

		// get the header row style
		HSSFCellStyle style = getStyleForHeader(wb);

		// create a new sheet to hold the coverage warning
		HSSFSheet successCasesSheet = wb
				.createSheet(Constants.SUCCESS_TEST_METHODS_SHEET_NAME);
		HSSFRow headerRow = successCasesSheet.createRow((short) 0);

		// Class Name header
		HSSFCell apexClassNameCell = headerRow.createCell(0);
		apexClassNameCell.setCellStyle(style);
		apexClassNameCell
				.setCellValue(Constants.SUCCESS_TEST_METHODS_CLASS_NAME_COLUMN_HEADER);

		// method name header
		HSSFCell methodNameCell = headerRow.createCell(1);
		methodNameCell
				.setCellValue(Constants.SUCCESS_TEST_METHODS_NAME_COLUMN_HEADER);
		methodNameCell.setCellStyle(style);

		// check if there is any failure
		if (successes != null && successes.length > 0) {

			// create report data
			HSSFRow dataRow;

			// loop through the results
			for (int i = 1; i <= successes.length; i++) {
				RunTestSuccess success = successes[i - 1];
				dataRow = successCasesSheet.createRow(i);
				dataRow.createCell(0).setCellValue(success.getName());
				dataRow.createCell(1).setCellValue(success.getMethodName());
			}
		}
	}

	/**
	 * Description: write summary for Run All Test result
	 * 
	 * @param result
	 *            : Run All Test result
	 */
	public void createRunAllTestSummarySheet(RunTestsResult result,
			HSSFWorkbook wb) {

		// create a new sheet to hold the coverage warning
		HSSFSheet summarySheet = wb.createSheet(Constants.SUMMARY_SHEET_NAME);

		// instance for data row
		HSSFRow dataRow;

		// run all data row
		dataRow = summarySheet.createRow(0);
		dataRow.createCell(0).setCellValue(
				Constants.TOTAL_TEST_METHODS_EXECUTED);
		dataRow.createCell(1).setCellValue(result.getNumTestsRun());

		// run all failure data row
		dataRow = summarySheet.createRow(1);
		dataRow.createCell(0)
				.setCellValue(Constants.TOTAL_TEST_METHODS_FAILURE);
		dataRow.createCell(1).setCellValue(result.getNumFailures());

		// run all test execution time
		dataRow = summarySheet.createRow(2);
		dataRow.createCell(0).setCellValue(Constants.EXECUTION_TIME);
		dataRow.createCell(1).setCellValue(result.getTotalTime());
	}

	/**
	 * Description: This method is to create the style for header row across the
	 * file.
	 * 
	 * @param wb
	 *            : Run all test workbook instance
	 * @return: style element for header row
	 */
	private HSSFCellStyle getStyleForHeader(HSSFWorkbook wb) {

		// produce header styles : bold font,
		Font headerFont = wb.createFont();
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);

		HSSFCellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE
				.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(headerFont);

		return style;
	}

	/**
	 * Description: Write the RunAllTest result file on the local disk
	 * 
	 * @param wb
	 *            : file instance needs to be written
	 * @throws IOException
	 */
	public void writeCoverageFileOnLocalDisk(HSSFWorkbook wb)
			throws FileNotFoundException, IOException {

		// write the report data
		FileOutputStream fileOut = new FileOutputStream(Constants.FILE_NAME
				+ Constants.DOT + Constants.EXTENSION_TYPE_EXCEL);
		wb.write(fileOut);
		fileOut.close();
	}
}
