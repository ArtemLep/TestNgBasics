package com.Syntax.class02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class SoftAssertionDemo {
    //as an admin i should be able to login to HRMS
    //logo is displayed
    //user id successfully logged in
    WebDriver driver;

    @BeforeMethod
    public void openAndNavigate() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Owner\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://hrmstest.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void logoAndValidLogin(){
        //verifying that logo is displayed
        WebElement element = driver.findElement(By.xpath("//div[@id = 'divLogo']//img"));
        //creting an object of soft assertion
        SoftAssert softAsert=new SoftAssert();
        softAsert.assertTrue(!element.isDisplayed(),"Logo is not displayed");
        //entering valid credentials to login
        String username="Admin";
        driver.findElement(By.id("txtUsername")).sendKeys(username);
        driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
        driver.findElement(By.id("btnLogin")).click();
        //validating that we ar logged in
        WebElement welcomeMessage = driver.findElement(By.cssSelector("a#welcome"));

        softAsert.assertTrue(!welcomeMessage.isDisplayed(), "Welcome message is not displayed");
        softAsert.assertEquals(welcomeMessage.getText(), "Welcomes "+username, "Welcome text is not matching");
        System.out.println("End of the test case");
        softAsert.assertAll();
    }
}
