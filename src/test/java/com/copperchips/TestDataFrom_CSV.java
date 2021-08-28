package com.copperchips;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class TestDataFrom_CSV {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testData() throws Exception {
        // ERROR: Caught exception [ERROR: Unsupported command [loadVars | data.csv | ]]
        driver.get("https://katalon-test.s3.amazonaws.com/aut/html/form.html");
        driver.findElement(By.id("first-name")).clear();
        CharSequence first = "null";
        driver.findElement(By.id("first-name")).sendKeys(first);
        driver.findElement(By.id("last-name")).clear();
        CharSequence last = "null";
        driver.findElement(By.id("last-name")).sendKeys(last);
        // ERROR: Caught exception [ERROR: Unsupported command [endLoadVars |  | ]]
        // ERROR: Caught exception [ERROR: Unsupported command [storeCsv | data.csv,1,last | ]]
        System.out.println(last);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
