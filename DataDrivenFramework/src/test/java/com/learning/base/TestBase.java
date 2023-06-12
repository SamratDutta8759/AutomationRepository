package com.learning.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.learning.listeners.CustomListeners;
import com.learning.utilities.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.*;

import org.openqa.selenium.edge.EdgeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

/*
 Initialize
 webdriver
 properties
 logs
 reports
 db
 excel
 mail
 */
public class TestBase {
	public static WebDriver driver=null;
	public static Properties configProperty;
	public static Properties ORProperty;
	public static Logger log = LogManager.getLogger("TestBase.class");
	public static ExcelReader excel;

	@SuppressWarnings("deprecation")
	@BeforeTest
	public void setUp() {
		if (driver == null) {
			String browser;
			
			configProperty = PropertyUtils.fetchProperty(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			log.info("Config property loaded");

			ORProperty = PropertyUtils.fetchProperty(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			log.info("OR property loaded");
			if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty())
				browser = System.getenv("browser");
			else 
				browser = configProperty.getProperty("browser");
			if (browser.equalsIgnoreCase("Chrome")) {
				//System.setProperty("webdriver.chrome.driver",
						//System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				log.info("Chrome launched");
			}

			if (browser.equalsIgnoreCase("Firefox")) {
				//System.setProperty("webdriver.firefox.driver",
						//System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
				log.info("Firefox launched");
			}
			if (browser.equalsIgnoreCase("Edge")) {
				//System.setProperty("webdriver.edge.driver",
						//System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\msedgedriver.exe");
				driver = new EdgeDriver();
				log.info("Edge launched");
			}
			excel = new ExcelReader(configProperty.getProperty("testData"));
			driver.get(configProperty.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			log.info("Test Site Opened:" + configProperty.getProperty("testsiteurl"));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@AfterTest


	public void tearDown() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (driver != null) {
			log.info("Test Execution Complete!");
			// driver.close();



			driver.close();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver=null;
		}
	}

public static void click(String Locator) {
	driver.findElement(By.xpath(ORProperty.getProperty(Locator))).click();
	CustomListeners.test.log(Status.INFO, "Clicking on: "+Locator);
}

public static void type(String Locator, String value) {
	driver.findElement(By.xpath(ORProperty.getProperty(Locator))).sendKeys(value);
	CustomListeners.test.log(Status.INFO, "Typing on: "+Locator+" with value as: "+value);
}

public static boolean isElementPresent(String Locator) {
	boolean isElementPresent = driver.findElement(By.xpath(ORProperty.getProperty(Locator))).isDisplayed();
	return isElementPresent;
}
}
