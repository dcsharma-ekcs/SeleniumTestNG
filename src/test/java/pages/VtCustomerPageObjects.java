package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class VtCustomerPageObjects {

    WebDriver driver = null;

    By user_email_address = By.id("emailFront");
    By user_password = By.id("passwordFront");
    By next = By.id("next");
    String userEmail = null;
    String userPassword = null;

    public VtCustomerPageObjects(WebDriver driver, String userEmail, String userPassword)
    {

        this.driver = driver;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public void setUser_email_address(){
        driver.findElement(user_email_address).click();
        driver.findElement(user_email_address).clear();
        driver.findElement(user_email_address).sendKeys(userEmail);

    }

    public void setUserPassword(){
        driver.findElement(user_password).click();
        driver.findElement(user_password).clear();
        driver.findElement(user_password).sendKeys(userPassword);

    }

    public void clickNextButton(){

        driver.findElement(next).click();
    }


}
