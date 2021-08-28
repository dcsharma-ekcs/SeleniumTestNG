package com.copperchips;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;

public class TestSeleniumGridDemo {

    public WebDriver driver;
    public String URL, Node;
    protected ThreadLocal<RemoteWebDriver> threadDriver = null;

    @Parameters("browser")
    @BeforeTest
    public void launchapp(String browser) throws MalformedURLException {
        String URL = "http://www.calculator.net";

        if (browser.equalsIgnoreCase("firefox")) {
            System.out.println(" Executing on FireFox");
            String Node = "http://10.112.66.52:5555/wd/hub";
            DesiredCapabilities cap = DesiredCapabilities.firefox();
            cap.setBrowserName("firefox");

            driver = new RemoteWebDriver(new URL(Node), cap);
            // Puts an Implicit wait, Will wait for 10 seconds before throwing exception
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // Launch website
            driver.navigate().to(URL);
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("chrome")) {
            System.out.println(" Executing on CHROME");
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setBrowserName("chrome");
            String Node = "http://10.112.66.52:5557/wd/hub";
            driver = new RemoteWebDriver(new URL(Node), cap);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // Launch website
            driver.navigate().to(URL);
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("ie")) {
            System.out.println(" Executing on IE");
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setBrowserName("ie");
            String Node = "http://10.112.66.52:5558/wd/hub";
            driver = new RemoteWebDriver(new URL(Node), cap);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // Launch website
            driver.navigate().to(URL);
            driver.manage().window().maximize();
        } else {
            throw new IllegalArgumentException("The Browser Type is Undefined");
        }
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
    public void closeBrowser() {
        driver.quit();
    }


}

