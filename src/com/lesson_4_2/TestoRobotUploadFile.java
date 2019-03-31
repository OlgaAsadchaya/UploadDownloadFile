package com.lesson_4_2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

public class TestoRobotUploadFile {
    WebDriver driver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/upload");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @After
    public void tearDown() throws Exception {
        driver.close();
    }
    @Test
    public void uploadTest() throws AWTException, InterruptedException {
        String dirName = "C:\\Olga\\WebDriver\\UploadFile\\";
        String expFileName = "test_file.xlsx";
        String fullFilename = dirName + expFileName;

        driver.findElement(By.id("file-upload")).click();

        StringSelection stringSelection = new StringSelection(fullFilename);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(1000);

        driver.findElement(By.id("file-submit")).click();

        String flName = driver.findElement(By.id("uploaded-files")).getText();
        assertEquals(flName, expFileName);
    }
}
