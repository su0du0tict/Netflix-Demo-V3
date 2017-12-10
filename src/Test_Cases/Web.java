package Test_Cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


import com.aventstack.extentreports.*;


import Test_Helpers.Browser_Factory;
import Test_Helpers.ExtentManager;

import org.testng.annotations.*;


public class Web {

static WebDriver driver;
static DesiredCapabilities capabilities;

static ExtentReports extent;
static ExtentTest test;
static ExtentTest childtest;



@BeforeTest
public void setUp() throws Exception
{
		boolean status=true;
		driver=Browser_Factory.launch_WEB_URL("https://www.netflix.com","Chrome");
		extent=ExtentManager.GetExtent();
		test=ExtentManager.createTest("Test", "Test");
		childtest=test.createNode("Test");
		ExtentManager.Report_Status(childtest, status, "Test", driver);
		
		
				
		
		
		
}

@Test()
public void Web_Application()
{
	// Login:html/body/div[1]/div/div[1]/a[2]
	
	driver.findElement(By.xpath("html/body/div[1]/div/div[1]/a[2]")).click();
	
	/*
	driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("NFUN");
	driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("NFPWD");
	driver.findElement(By.xpath(".//*[@id='appMountPoint']/div/div[2]/div/div/form[1]/button")).click();
	
	
	
	
	driver.findElement(By.xpath(".//*[@id='hdPinTarget']/div/div[1]/div/button")).click();
	driver.findElement(By.xpath(".//*[@id='hdPinTarget']/div/div[1]/div/div/input")).sendKeys("Bahubali");;
	*/
}


@AfterTest
public void tear_down()
{
	extent.flush();
	driver.quit();
}












}
