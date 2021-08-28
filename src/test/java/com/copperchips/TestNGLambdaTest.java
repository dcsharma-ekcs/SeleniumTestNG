package com.copperchips;


//TestNG Todo : Sample App

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class TestNGLambdaTest{
    public String username = "dalchndr";
    public String accesskey = "hn6QSSixsXkWq7H3e4WmG7kp9JlZakVksdetBFrzDzMnlRPxNC";
    public static RemoteWebDriver driver = null;
    public String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;

    @BeforeClass
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("version", "70.0");
        capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get the any available one
        capabilities.setCapability("build", "LambdaTestSampleApp");
        capabilities.setCapability("name", "LambdaTestJavaSample");

        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
/*
    @Test
    public void testSimple() throws Exception {
        try {
            //Change it to production page
            driver.get("https://lambdatest.github.io/sample-todo-app/");

            //Let's mark done first two items in the list.
            driver.findElement(By.name("li1")).click();
            driver.findElement(By.name("li2")).click();

            // Let's add an item in the list.
            driver.findElement(By.id("sampletodotext")).sendKeys("Yey, Let's add it to list");
            driver.findElement(By.id("addbutton")).click();

            // Let's check that the item we added is added in the list.
            String enteredText = driver.findElementByXPath("/html/body/div/div/div/ul/li[6]/span").getText();
            if (enteredText.equals("Yey, Let's add it to list")) {
                status = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
*/
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

    @AfterClass
    public void tearDown() throws Exception {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}