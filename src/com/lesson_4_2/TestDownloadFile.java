package com.lesson_4_2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TestDownloadFile {
    WebDriver driver;
    String downloadFilepath = "C:\\Olga\\WebDriver\\UploadFile\\";
    String fileName = "logo.png";

    @Before
    public void setUp() throws Exception {
        File tempFile = new File(downloadFilepath+fileName);
        tempFile.delete();

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--test-type");
        options.addArguments("--disable-extensions"); //to disable browser extension popup
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.get("https://the-internet.herokuapp.com/download");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @After
    public void tearDown() throws Exception {
        driver.close();

        File tempFile = new File(downloadFilepath+fileName);
        tempFile.delete();
    }

    @Test
    public void downloadTest() throws InterruptedException {
        WebElement downloadLink = driver.findElement(By.linkText(fileName));
        downloadLink.click();

        Thread.sleep(1000);

        File tempFile = new File(downloadFilepath+fileName);
        boolean exists = tempFile.exists();
        assertTrue(exists);
    }
}
