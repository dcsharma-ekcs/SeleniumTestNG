package io.vtcustomer;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

import static junit.framework.TestCase.fail;

public class PartnersShopify_demo {

    WebDriver driver = null;
    WebDriver driver1 = null;

    @BeforeTest
    private void setUpTest() {
        System.out.println("In Side BeforeTest..");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.shopify.com/partners");
    }

    @Test
    public void partnersShopifyLogin() throws InterruptedException {
        try {

            waitAndClickXpath("//a[@class='marketing-nav__item marketing-nav__item--user'][normalize-space()='Log in']");

            waitAndClickXpath("//input[@id='account_email']");

            driver.findElement(By.xpath("//input[@id='account_email']")).click();
            driver.findElement(By.xpath("//input[@id='account_email']")).clear();
            driver.findElement(By.xpath("//input[@id='account_email']")).sendKeys("reymundo.paga@ubiquity.com");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[normalize-space()='Next']")).click();

            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='account_password']")));

            driver.findElement(By.xpath("//input[@id='account_password']")).click();
            driver.findElement(By.xpath("//input[@id='account_password']")).clear();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@id='account_password']")).sendKeys("!Password@123");

            driver.findElement(By.xpath("//button[normalize-space()='Log in']")).click();

            waitAndClickXpath("//span[normalize-space()='Vesta Corporation']");

            waitAndClickXpath("//span[normalize-space()='Stores']");

            waitAndClickXpath("//span[normalize-space()='vesta-dev']");

            waitAndClickXpath("//div[@class='ui-title-bar__actions-group']//a[@class='ui-button ui-button--primary ui-title-bar__action'][normalize-space()='Log in']");


        } catch (Exception exp) {
            System.out.println(exp.getMessage().toString());
        }

    }

    @Test(dependsOnMethods = "partnersShopifyLogin")
    private  void myShopify_admin(){
        try {
            String parent = driver.getWindowHandle();
            //System.out.println("parent:   "+parent);
            Set<String> s=driver.getWindowHandles();
            Iterator<String> I1= s.iterator();
            System.out.println("count :   "+s.size());
            while(I1.hasNext())
            {
                String child_window=I1.next();
                //System.out.println(driver.switchTo().window(child_window).getTitle());
                driver.switchTo().window(child_window);
                //System.out.println("child_window:   "+driver.getWindowHandle());
            }

            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Online Store']")));

            waitAndClickXpath("//a[@aria-label='View your online store']//span//*[local-name()='svg']");

        }catch (Exception exp){
            System.out.println(exp.getMessage().toString());
        }

    }
    @Test(dependsOnMethods = "myShopify_admin")
    private void storeOnline(){
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,250)", "");
            WebDriverWait wait = new WebDriverWait(driver, 10);

            Set<String> s=driver.getWindowHandles();
            Iterator<String> I1= s.iterator();
            System.out.println("count :   "+s.size());
            while(I1.hasNext())
            {
                String child_window=I1.next();
                //System.out.println(driver.switchTo().window(child_window).getTitle());
                driver.switchTo().window(child_window);
                //System.out.println("child_window:   "+driver.getWindowHandle());
            }


            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='site-nav__label'][normalize-space()='Catalog']")));

            waitAndClickXpath("//span[@class='site-nav__label'][normalize-space()='Catalog']");

            waitAndClickXpath("//main[@id='MainContent']//ul[1]//li[1]");

            waitAndClickXpath("//button[normalize-space()='Buy it now']");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='checkout_email_or_phone']")));

            driver.findElement(By.xpath("//input[@id='checkout_email_or_phone']")).click();
            driver.findElement(By.xpath("//input[@id='checkout_email_or_phone']")).clear();
            driver.findElement(By.xpath("//input[@id='checkout_email_or_phone']")).sendKeys("dc.sharma@gmail.com");

            driver.findElement(By.xpath("//input[@id='checkout_shipping_address_first_name']")).click();
            driver.findElement(By.xpath("//input[@id='checkout_shipping_address_first_name']")).clear();
            driver.findElement(By.xpath("//input[@id='checkout_shipping_address_first_name']")).sendKeys("DC");

            driver.findElement(By.xpath("//input[@id='checkout_shipping_address_last_name']")).click();
            driver.findElement(By.xpath("//input[@id='checkout_shipping_address_last_name']")).clear();
            driver.findElement(By.xpath("//input[@id='checkout_shipping_address_last_name']")).sendKeys("Sharma");


            waitAndClickXpath("//input[@id='checkout_shipping_address_address1']");
            driver.findElement(By.xpath("//input[@id='checkout_shipping_address_address1']")).clear();
            driver.findElement(By.xpath("//input[@id='checkout_shipping_address_address1']")).sendKeys("5400 Meadows Road, 5th Floor, Lake Oswego, OR 97035");

            js.executeScript("window.scrollBy(0,350)", "");

            driver.findElement(By.xpath("//input[@id='checkout_shipping_address_city']")).click();
            driver.findElement(By.xpath("//input[@id='checkout_shipping_address_city']")).clear();
            driver.findElement(By.xpath("//input[@id='checkout_shipping_address_city']")).sendKeys("Oswego");

            Select drpCountry = new Select(driver.findElement(By.xpath("//select[@id='checkout_shipping_address_country']")));
            drpCountry.selectByVisibleText("United States");

            Select drpCity = new Select(driver.findElement(By.xpath("//select[@id='checkout_shipping_address_province']")));
            drpCity.selectByVisibleText("Oregon");

            driver.findElement(By.xpath("//input[@id='checkout_shipping_address_zip']")).click();
            driver.findElement(By.xpath("//input[@id='checkout_shipping_address_zip']")).clear();
            driver.findElement(By.xpath("//input[@id='checkout_shipping_address_zip']")).sendKeys("97035");

            driver.findElement(By.xpath("//button[@id='continue_button']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-shipping-method-label-title='Economy']")));
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[@id='continue_button']")).click();

            System.out.println("Shipping method");


        }catch (Exception exp){
        System.out.println(exp.getMessage().toString());
    }
    }

    @Test(dependsOnMethods = "storeOnline")
    private void cardPayment(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Integer noOfFrames = Integer.parseInt(js.executeScript("return window.length").toString());
            System.out.println("No. of iframes on the page are " + noOfFrames);
            js.executeScript("window.scrollBy(0,400)", "");

            driver.switchTo().frame(1);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("number")));
            Thread.sleep(3000);
            //driver.findElement(By.id("number")).click();
            //driver.findElement(By.id("number")).clear();
            //driver.findElement(By.id("number")).sendKeys("4242424242424242");
            driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD4);
            driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD2);
            driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD4);
            driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD2);

            driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD4);
            driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD2);
            driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD4);
            driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD2);

            driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD4);
            driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD2);
            driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD4);
            driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD2);

            driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD4);
            driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD2);
            driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD4);
            driver.findElement(By.id("number")).sendKeys(Keys. NUMPAD2);


            driver.switchTo().defaultContent();

            Thread.sleep(1000);
            driver.switchTo().frame(2);
            driver.findElement(By.id("name")).sendKeys("test");

            driver.switchTo().defaultContent();
            Thread.sleep(1000);
            driver.switchTo().frame(3);
            driver.findElement(By.id("expiry")).sendKeys(Keys. NUMPAD1);
            driver.findElement(By.id("expiry")).sendKeys(Keys. NUMPAD2);
            driver.findElement(By.id("expiry")).sendKeys(Keys. NUMPAD2);
            driver.findElement(By.id("expiry")).sendKeys(Keys. NUMPAD4);

            driver.switchTo().defaultContent();
            Thread.sleep(1000);
            driver.switchTo().frame(4);
            driver.findElement(By.id("verification_value")).sendKeys("123");

            //Switching back to the main window
            driver.switchTo().defaultContent();
            driver.findElement(By.xpath("//div[@class='shown-if-js']//button[@id='continue_button']")).click();


        } catch (Exception exp){
            System.out.println(exp.getMessage().toString());
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
