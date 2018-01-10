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
	private static String destDir=(System.getProperty("user.dir"));
	//private static String dest=(System.getProperty("user.dir"));
	
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
        htmlReporter = new ExtentHtmlReporter(destDir+"/"+filename);
		
	// make the charts visible on report open
        htmlReporter.config().setChartVisibilityOnOpen(true);
		
        htmlReporter.config().setDocumentTitle("Demo Report");
        htmlReporter.config().setReportName("Demo Report");
        extent.setSystemInfo("Application Version", "Demo Version");
       
        return htmlReporter;
	}
	
	public static ExtentTest createTest(String name, String description){
		test = extent.createTest(name, description);
		return test;
	}
	
	public static String takeScreenShot(WebDriver driver) {
		  
		
		  String A_destDir=(System.getProperty("user.dir"));
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
