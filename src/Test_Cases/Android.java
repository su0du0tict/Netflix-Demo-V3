package Test_Cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


import com.aventstack.extentreports.*;

import Test_Helpers.Anrdroid_Factory;
import Test_Helpers.AppiumServerJava_Appium_Node;
import Test_Helpers.AppiumServerJava_CMD;
import Test_Helpers.Browser_Factory;
import Test_Helpers.ExtentManager;

import org.testng.annotations.*;


public class Android {


static DesiredCapabilities capabilities;
static WebDriver mobile_driver;
static ExtentReports extent;
static ExtentTest test;
static ExtentTest childtest;
static AppiumServerJava_CMD appiumServer;





@BeforeTest
public void setUp() throws Exception
{
		
		appiumServer = new AppiumServerJava_CMD();
		appiumServer.startServer();
		System.out.println("Server Started");
		boolean status=true;
		
		extent=ExtentManager.GetExtent();
		test=ExtentManager.createTest("Test", "Test");
		
		
		String deviceName="PLEGAR2780422674";
		String platformVersion="7.1.1";
		String URL="http://127.0.0.1:4723/wd/hub";
		capabilities=Anrdroid_Factory.config_desired_capabilities(deviceName,platformVersion);
		// Launch the application using Anrdoid Driver
		mobile_driver=Anrdroid_Factory.launch_App(URL);
		childtest=test.createNode("Test 1");
		ExtentManager.Report_Status(childtest, status, "Test", mobile_driver);
		
		
		
		
		
}

@Test()
public void Web_Application()
{
	
}


@AfterTest
public void tear_down()
{
	extent.flush(); System.out.println("Extent Flushed");
	
	mobile_driver.quit();  System.out.println("Mobile Driver Closed");
	appiumServer.stopServer();  System.out.println("Stopping Appium Driver");
}












}
