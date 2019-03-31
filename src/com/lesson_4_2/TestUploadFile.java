package com.lesson_4_2;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import java.lang.Thread;

import static junit.framework.TestCase.assertEquals;

public class TestUploadFile {
    WebDriver driver;

    @org.junit.Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/upload");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @org.junit.After
    public void tearDown() throws Exception {
        driver.close();
    }
    @Test
    public void uploadTest() throws InterruptedException {
        String dirNmae = "C:\\Olga\\WebDriver\\UploadFile\\";
        String expFileName = "test_file.xlsx";
        driver.findElement(By.id("file-upload")).sendKeys(dirNmae + expFileName);
        driver.findElement(By.id("file-submit")).click();
        String flName = driver.findElement(By.id("uploaded-files")).getText();
        assertEquals(flName, expFileName);
    }
}
