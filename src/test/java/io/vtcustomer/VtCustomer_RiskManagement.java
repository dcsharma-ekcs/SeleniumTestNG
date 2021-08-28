package io.vtcustomer;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.VtCustomerPageObjects;

import java.util.Iterator;
import java.util.Set;

import static junit.framework.TestCase.fail;

public class VtCustomer_RiskManagement {

    WebDriver driver = null;
    WebDriver driver1 = null;

    @BeforeTest
    private void setUpTest() {
        System.out.println("In Side BeforeTest..");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://vt-customer-dev.azurewebsites.net");
    }

    @Test
    public void vtUserLogin() throws InterruptedException {

        //driver.navigate().to("https://vt-customer-dev.azurewebsites.net");
       //driver.get("https://vt-customer-dev.azurewebsites.net");
        VtCustomerPageObjects vtCustomerPageObjects = new VtCustomerPageObjects(driver, "vesta_integration@trustvesta.com", "!@#asdfASDF123");
        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (isElementPresent(By.id("emailFront"))) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }
        vtCustomerPageObjects.setUser_email_address();
        vtCustomerPageObjects.setUserPassword();
        vtCustomerPageObjects.clickNextButton();

        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if ("Dashboard".equals(driver.getTitle())) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }
    }

    @Test(dependsOnMethods = "vtUserLogin")
    private void showRiskManagement(){
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                //WebDriverWait wait = new WebDriverWait(driver, 2);
                //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("xyz")));
                waitAndClickXpath("//li[contains(text(),'Risk Management')]");
                waitAndClickXpath("//tbody/tr[1]/td[1]");

                Thread.sleep(3000);
                clickStickyArrow();
                Thread.sleep(3000);
                js.executeScript("window.scrollTo(0,400)", "");

            }catch (Exception exp){

            }
    }

    @AfterTest
    private void tearDownTest() throws InterruptedException {

//        Thread.sleep(6000);
//        driver.close();
//        driver.quit();
    }


    private void waitAndClickXpath(String strXpath) throws InterruptedException {

        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (isElementPresent(By.xpath(strXpath))) break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }
        driver.findElement(By.xpath(strXpath)).click();

    }

    private void waitAndClickName(String strName) throws InterruptedException {

        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (isElementPresent(By.name(strName))) break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }
        driver.findElement(By.name(strName)).click();

    }
    private void waitAndClickId(String strId) throws InterruptedException {

        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (isElementPresent(By.id(strId))) break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }
        driver.findElement(By.id(strId)).click();

    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    private void clickStickyArrow() throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (isElementPresent(By.xpath("//div[@class='sticky-arrow showOn']"))) break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }
        driver.findElement(By.xpath("//div[@class='sticky-arrow showOn']")).click();
    }
}
