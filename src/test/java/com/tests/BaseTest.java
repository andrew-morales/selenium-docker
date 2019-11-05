package com.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	protected WebDriver driver;
	
	@BeforeTest
	public void setupDriver(ITestContext ctx){
		String completeUrl = null;
		String testName = ctx.getCurrentXmlTest().getName();
		
		//set default hose and browser
		String host = "localhost";
		DesiredCapabilities dc;
		
		//check for user inputed browser and host
		if(System.getProperty("BROWSER") != null && System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
			dc = DesiredCapabilities.firefox();
		}
		else{
			dc = DesiredCapabilities.chrome();
		}
		
		if(System.getProperty("HUB_HOST") != null){
			host = System.getProperty("HUB_HOST");
		}
		
		//set full grid url
		completeUrl = "http://" + host + ":4444/wd/hub";
		dc.setCapability("name", testName);
		
		try {
			//where grid is running is your url
			driver = new RemoteWebDriver(new URL(completeUrl), dc);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//driver = new ChromeDriver();
	}
	
	@AfterTest
	public void quitDriver(){
		driver.quit();
	}
}
