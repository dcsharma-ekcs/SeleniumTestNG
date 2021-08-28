package com.copperchips.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePageObjects {

    WebDriver driver = null;

    By textboxe_seach = By.name("q");



    public HomePageObjects(WebDriver driver)
    {
        this.driver = driver;
    }

    public void setTextInSearchBox(String text){
        driver.findElement(textboxe_seach).sendKeys(text);
    }

    public void clickSearchButton(){
        driver.findElement(textboxe_seach).sendKeys(Keys.ENTER);
    }
}
