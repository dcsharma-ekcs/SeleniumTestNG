package com.copperchips;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.copperchips.pages.HomePageObjects;

import util.Screenshot;

public class ExtentReports_TestNG {
	
	
private WebDriver driver = null;
ExtentReports extent = null;
ExtentSparkReporter spark = null;

private static final String CODE1 = "{\n    \"theme\": \"standard\",\n    \"encoding\": \"utf-8\n}";
private static final String CODE2 = "{\n    \"protocol\": \"HTTPS\",\n    \"timelineEnabled\": false\n}";

	
	@BeforeTest
	public void setUpTest() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();		
		driver.get("https://google.com/");
		
		extent = new ExtentReports();
        spark = new ExtentSparkReporter("target/Spark/Spark.html");
        extent.attachReporter(spark);
		
	}
	
	@Test
	public void googleSearchText() throws InterruptedException, IOException {

		HomePageObjects searchPageObjets = new HomePageObjects(driver);
		searchPageObjets.setTextInSearchBox("A B C D");




		searchPageObjets.clickSearchButton();
		
		Thread.sleep(3000);
		
		extent.createTest("ScreenCapture")
        .addScreenCaptureFromPath(Screenshot.takescreenShot(driver));
		
		Thread.sleep(3000);
		
extent.createTest("LogLevels")
        .info("info")
        .pass("pass")
        .warning("warn")
        .skip("skip")
        .fail("fail");

extent.createTest("CodeBlock").generateLog(
        Status.PASS,
        MarkupHelper.createCodeBlock(CODE1, CODE2));

extent.createTest("ParentWithChild")
        .createNode("Child")
        .pass("This test is created as a toggle as part of a child test of 'ParentWithChild'");

extent.createTest("Tags")
        .assignCategory("MyTag")
        .pass("The test 'Tags' was assigned by the tag <span class='badge badge-primary'>MyTag</span>");

extent.createTest("Authors")
        .assignAuthor("TheAuthor")
        .pass("This test 'Authors' was assigned by a special kind of author tag.");

extent.createTest("Devices")
        .assignDevice("TheDevice")
        .pass("This test 'Devices' was assigned by a special kind of devices tag.");

extent.createTest("Exception! <i class='fa fa-frown-o'></i>")
        .fail(new RuntimeException("A runtime exception occurred!"));
		
		
	}
	
	@AfterTest
	public void tearDownTest() {
		
		driver.close();
		driver.quit();
		System.out.println("Test Compleated Successfuly");
		
		extent.flush();
	}

}
