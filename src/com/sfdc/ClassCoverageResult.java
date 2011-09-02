package com.sfdc;

/**
 * Description: Wrapper clas for coverage result
 * 
 * @author Bhawani
 * 
 */
public class ClassCoverageResult {

	public Integer coverPercentage;
	public String className;

	public Integer getCoveragePercentage() {
		return this.coverPercentage;
	}

	public void setCoveragePercentage(Integer coveragePercentage) {
		this.coverPercentage = coveragePercentage;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
