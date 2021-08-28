package com.copperchips;

import com.copperchips.pages.HomePageObjects;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNG_Demo {

    WebDriver driver = null;

    @BeforeTest
    public void setUpTest(){
        System.out.println("In Side BeforeTest..");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://google.com/");
    }

    @Test
    public void googleSearch(){
        System.out.println("In Side Test..");
        HomePageObjects searchOnGoogle = new HomePageObjects(driver);
        searchOnGoogle.setTextInSearchBox("A B C D ");
        searchOnGoogle.clickSearchButton();
    }

    @AfterTest
    public void tearDownTest(){
        System.out.println("In Side AfterTest..");
        driver.close();
        driver.quit();
    }
}
