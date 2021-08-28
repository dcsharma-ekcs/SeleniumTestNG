package io.vtcustomer;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.VtCustomerPageObjects;

import java.util.Random;

import static junit.framework.TestCase.fail;

public class VtCustomerTest_demo {

    WebDriver driver = null;
    Random rand = new Random();
    int randNumber = rand.nextInt(10000);

    @BeforeTest
    private void setUpTest(){
        System.out.println("In Side BeforeTest..");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://vt-customer-dev.azurewebsites.net");
    }

    @Test
    public void vtUserLogin() throws InterruptedException {
       // driver.get("https://vt-customer-dev.azurewebsites.net");
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
    private void vtAddUser() throws Exception {

        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (isElementPresent(By.xpath("//li[normalize-space()='Account Management']"))) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }
        driver.findElement(By.xpath("//li[normalize-space()='Account Management']")).click();

        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (isElementPresent(By.xpath("//li[contains(text(),'Users')]"))) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }
        driver.findElement(By.xpath("//li[contains(text(),'Users')]")).click();

        clickStickyArrow();

        String addNewUserButton = "//div[@id='__next']/div/div/div[2]/div/div/div/div/div[2]/div/div/button/span";
        driver.findElement(By.xpath(addNewUserButton)).click();

        clickStickyArrow();

        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (isElementPresent(By.name("Name"))) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }
        driver.findElement(By.name("Name")).click();
        driver.findElement(By.name("Name")).clear();
        driver.findElement(By.name("Name")).sendKeys("Test User "+randNumber);
        driver.findElement(By.name("Email ID")).click();
        driver.findElement(By.name("Email ID")).clear();
        driver.findElement(By.name("Email ID")).sendKeys(randNumber+"testuser@gmail.com");
        driver.findElement(By.name("Phone")).click();
        driver.findElement(By.name("Phone")).clear();
        driver.findElement(By.name("Phone")).sendKeys(randNumber+""+randNumber);

        driver.findElement(By.xpath("//div[@id='__next']/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div[2]/div/div/button/span/img")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='View'])[1]/preceding::span[2]")).click();


        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (isElementPresent(By.xpath("//button[contains(text(),'CREATE')]"))) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }
        driver.findElement(By.xpath("//button[contains(text(),'CREATE')]")).click();
        Thread.sleep(3000);

        clickStickyArrow();
        Thread.sleep(1000);
    }

    @Test(dependsOnMethods = "vtAddUser")
    private void  vtSearchUsers() throws InterruptedException {
//
//        for (int second = 0;; second++) {
//            if (second >= 60) fail("timeout");
//            try { if (isElementPresent(By.xpath("//li[normalize-space()='Account Management']"))) break; } catch (Exception e) {}
//            Thread.sleep(1000);
//        }
//        driver.findElement(By.xpath("//li[normalize-space()='Account Management']")).click();
//
//        for (int second = 0;; second++) {
//            if (second >= 60) fail("timeout");
//            try { if (isElementPresent(By.xpath("//li[contains(text(),'Users')]"))) break; } catch (Exception e) {}
//            Thread.sleep(1000);
//        }
//        driver.findElement(By.xpath("//li[contains(text(),'Users')]")).click();
//        clickStickyArrow();


        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (isElementPresent(By.xpath("//input[@id='searchInput']"))) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }
        driver.findElement(By.xpath("//input[@id='searchInput']")).click();
        driver.findElement(By.xpath("//input[@id='searchInput']")).clear();
        //driver.findElement(By.xpath("//input[@id='searchInput']")).sendKeys("Test User "+randNumber);
        driver.findElement(By.xpath("//input[@id='searchInput']")).sendKeys("Test User "+randNumber);

        //clickStickyArrow();
        Thread.sleep(1000);

    }

    @Test()
    private void vtEditUsers() throws InterruptedException {


    }



    @Test(dependsOnMethods = "vtSearchUsers")
    public void vtUserLogOut() throws InterruptedException {

        driver.findElement(By.xpath("//div[@class='icon-block'][normalize-space()='VI']")).click();
        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (isElementPresent(By.xpath("//label[normalize-space()='Sign out']"))) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }
        driver.findElement(By.xpath("//label[normalize-space()='Sign out']")).click();
    }

    @AfterTest
    private void tearDownTest() throws InterruptedException {

        Thread.sleep(1000);
        driver.close();
        driver.quit();
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
        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (isElementPresent(By.xpath("//div[@class='sticky-arrow showOn']"))) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }
        driver.findElement(By.xpath("//div[@class='sticky-arrow showOn']")).click();
    }
}
