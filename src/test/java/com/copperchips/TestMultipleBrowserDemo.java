package com.copperchips;

import com.copperchips.pages.HomePageObjects;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestMultipleBrowserDemo {


    WebDriver driver = null;

    @Parameters("browserName")
    @BeforeTest
    public void setUp(String browserName){



        if(browserName.equalsIgnoreCase("chrome")) {
            System.out.println("browserName:" + browserName);
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if(browserName.equalsIgnoreCase("firefox")) {
            System.out.println("browserName:" + browserName);
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else if(browserName.equalsIgnoreCase("ie")) {
            System.out.println("browserName:" + browserName);
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("ignoreProtectedModeSettings", true);
            //caps.setCapability("ignoreZoomSetting", true );
            WebDriverManager.iedriver().setup();


            driver = new InternetExplorerDriver();
        }

        driver.get("http://google.com/");
    }

    @Test
    public void googleSearch(){
        System.out.println("In Side Test googleSearch: ");
        HomePageObjects searchOnGoogle = new HomePageObjects(driver);
        searchOnGoogle.setTextInSearchBox("A B C D ");
        searchOnGoogle.clickSearchButton();
    }

    @AfterTest
    public void tearDownTest() throws InterruptedException {
        System.out.println("In Side AfterTest..");

        Thread.sleep(3000);
        driver.close();

    }

}
