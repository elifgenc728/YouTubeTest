package com.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class YouTubeTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void loadYouTubeHomePage() {
        driver.get("https://www.youtube.com/");
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("YouTube"));
    }

    @Test
    public void searchVideo() {
        driver.get("https://www.youtube.com/");
        WebElement searchBox = driver.findElement(By.name("search_query"));
        searchBox.sendKeys("Selenium Tutorial");
        searchBox.submit();
        WebElement firstVideo = driver.findElement(By.id("video-title"));
        firstVideo.click();
        WebElement videoPlayer = driver.findElement(By.cssSelector(".html5-video-player"));
        Assert.assertNotNull(videoPlayer);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
