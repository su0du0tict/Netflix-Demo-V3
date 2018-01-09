package Test_Helpers;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {

	private static ExtentReports extent;
	private static ExtentTest test;
	private static ExtentHtmlReporter htmlReporter;
	private static String filename = "";
	private static String destDir="C:\\Workspace\\Reports\\HTML";
	
	public static ExtentReports GetExtent(){
		if (extent != null)
                    return extent; //avoid creating new instance of html file
                extent = new ExtentReports();		
		extent.attachReporter(getHtmlReporter());
		return extent;
	}

	private static ExtentHtmlReporter getHtmlReporter() {
	
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		String filename = dateFormat.format(new Date()) + ".html";
        htmlReporter = new ExtentHtmlReporter(destDir+filename);
		
	// make the charts visible on report open
        htmlReporter.config().setChartVisibilityOnOpen(true);
		
        htmlReporter.config().setDocumentTitle("MCOE Automation Report");
        htmlReporter.config().setReportName("MyGNP V 6.0.0 Regression cycle");
        extent.setSystemInfo("Application Version", "Version 6.0.0");
       
        return htmlReporter;
	}
	
	public static ExtentTest createTest(String name, String description){
		test = extent.createTest(name, description);
		return test;
	}
	
	public static String takeScreenShot(WebDriver driver) {
		  
		
		  String A_destDir="C:\\Workspace\\Reports\\Screenshots";
		  DateFormat A_dateFormat;
		  // Set folder name to store screenshots.
		 
		  // Capture screenshot.
		  File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		  // Set date format to set It as screenshot file name.
		  A_dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		  // Create folder under project with name "screenshots" provided to destDir.
		  //new File(destDir).mkdirs();
		  // Set file name using current date time.
		  String destFile = A_dateFormat.format(new Date()) + ".png";
		  
		  String Appium_SC_Location=A_destDir + "/" + destFile;
		  try {
		   // Copy paste file at destination folder location
		   FileUtils.copyFile(scrFile, new File(A_destDir + "/" + destFile));
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
	    return Appium_SC_Location; 	 
	}

	
	public static void Report_Status(ExtentTest r_test ,boolean status,String Description,WebDriver driver) throws IOException
	
	{ 
		
		
		if (status==true)
        {
			r_test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver)).build());    
        }
        else 
        {
        	r_test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver)).build());
        }
}

}
