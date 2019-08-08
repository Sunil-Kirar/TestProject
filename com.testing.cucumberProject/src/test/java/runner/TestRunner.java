package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="features",
tags={"@Sanity"},//~@E2E,~@Regression"},/*Here if write all tags inside one double quote then it will called as OR else AND */
glue={"stepDefination"})
public class TestRunner {
	 
	
	/*This plugin and afterclass can be used for extent report
	 * There is a file extent-config.xml which is required to generate report using this code and a property which is a path of the file.
	 * plugin={"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"}
	@AfterClass
	 public static void writeExtentReport() {
	 Reporter.loadXMLConfig(new File(GetProperties.getReportConfigPath()));
	 }*/
}
