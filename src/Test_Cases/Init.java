package Test_Cases;

import java.io.IOException;

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


public class Init {

static WebDriver driver;
static DesiredCapabilities capabilities;
static WebDriver mobile_driver;
static ExtentReports extent;
static ExtentTest test;
static ExtentTest childtest;
static AppiumServerJava_CMD appiumServer;


@BeforeTest
public void setUp() throws Exception
{
		boolean status=true;
		driver=Browser_Factory.launch_WEB_URL("https://www.netflix.com","Chrome");
		extent=ExtentManager.GetExtent();
		test=ExtentManager.createTest("Netflix Demo", "This test validates that Netflix login on Web Browser and Mobile Device");
		childtest=test.createNode("Validate Netflix Launch on Web Browser");
		ExtentManager.Report_Status(childtest, status, "Launch on Web Browser", driver);
		appiumServer = new AppiumServerJava_CMD();
		appiumServer.startServer();System.out.println("Appium Server Started");
		
		String deviceName="PLEGAR2780422674";
		String platformVersion="7.1.1";
		String URL="http://127.0.0.1:4723/wd/hub";
		capabilities=Anrdroid_Factory.config_desired_capabilities(deviceName,platformVersion);
		// Launch the application using Anrdoid Driver
		mobile_driver=Anrdroid_Factory.launch_App(URL);
		childtest=test.createNode("Validate Netflix Launch on Android Application");
		ExtentManager.Report_Status(childtest, status, "Netflix Launch on Android Driver", mobile_driver);
		
		
		
		
		
}

@Test()
public void Web_Application() throws InterruptedException, IOException
{
	// Login:html/body/div[1]/div/div[1]/a[2]
	boolean status=true;
	
	
	
	
	driver.findElement(By.xpath("html/body/div[1]/div/div[1]/a[2]")).click();
	childtest=test.createNode("Validate Login Page");
	ExtentManager.Report_Status(childtest, status, "Validate Login Page on Web Browser", driver);
	
	//Email : //*[@id="email"]
	driver.findElement(By.xpath(" //*[@id=\"email\"]")).sendKeys("testemail@gmail.com");
	//PWD : //*[@id="password"]
	
	driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("testemail@gmail.com");
	
	//Sign In ://*[@id="appMountPoint"]/div/div[2]/div/div/form[1]/button
	
	Thread.sleep(5000);
	driver.findElement(By.xpath("//*[@id=\"appMountPoint\"]/div/div[2]/div/div/form[1]/button")).click();
	status=false;
	childtest=test.createNode("Validate Login");
	ExtentManager.Report_Status(childtest, status, "Validate Login Success on Web Browser", driver);
	
	
}


@AfterTest
public void tear_down()
{
	extent.flush();
	driver.quit();
	mobile_driver.quit();  System.out.println("Mobile Driver Closed");
	appiumServer.stopServer();  System.out.println("Stopping Appium Driver");
}












}
