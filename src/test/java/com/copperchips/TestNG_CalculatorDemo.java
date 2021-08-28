package com.copperchips;

import com.copperchips.pages.HomePageObjects;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class TestNG_CalculatorDemo {

    WebDriver driver = null;

    @BeforeTest
    public void setUpTest(){
        System.out.println("In Side BeforeTest..");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://www.calculator.net");
    }

    @Test
    public void calculatePercent() throws InterruptedException {
        driver.get("https://www.calculator.net/");
        driver.findElement(By.xpath("//span[@onclick='r(5)']")).click();
        driver.findElement(By.xpath("//span[@onclick='r(0)']")).click();
        driver.findElement(By.xpath("//span[@onclick='r(0)']")).click();
        driver.findElement(By.xpath("//span[@onclick=\"r('pc')\"]")).click();
        driver.findElement(By.xpath("//span[@onclick='r(7)']")).click();


        String result =
                driver.findElement(By.xpath("//div[@id='sciOutPut']")).getText();

        // Print a Log In message to the screen
        System.out.println(" The Result is " + result);

        if(result.contains("35")) {
            System.out.println(" The Result is Pass");
        } else {
            System.out.println(" The Result is Fail");
        }
    }

    @AfterTest
    public void tearDownTest(){
        System.out.println("In Side AfterTest..");
        //driver.close();
        driver.quit();
    }
}
