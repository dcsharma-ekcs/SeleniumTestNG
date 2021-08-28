package com.copperchips.testcase.home;

import com.copperchips.pages.HomePageObjects;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverManagerTest {


    public static WebDriver driver =null;

    public static void main(String[] args) {

        googlePageSearch();
        System.out.println("In Side WebDriverManagerTest Class...");

    }

    public static void googlePageSearch(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://google.com/");

        HomePageObjects searchOnGoogle = new HomePageObjects(driver);
        searchOnGoogle.setTextInSearchBox("A B C D ");
        searchOnGoogle.clickSearchButton();


    }
}
