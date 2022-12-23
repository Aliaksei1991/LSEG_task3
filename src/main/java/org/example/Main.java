package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;

public class Main {
    private static final String CHROMEDRIVER_EXE = "chromedriver.exe";
    private static final String SVG_PATH = "//*[@id=\"gatsby-focus-wrapper\"]//*[local-name()='svg']";
    private static final String TEXT_PATH = "//*[@id=\"gatsby-focus-wrapper\"]//*[local-name()='text']";

    public static void main(String[] args) {
        WebDriver driver = createDriverAndGoToPage();
        scrollToSvg(driver);
        sleepTwoSec();
        System.out.println(driver.findElement(By.xpath(TEXT_PATH)).getText());
    }

    private static String getFilePath() {
        ClassLoader classLoader = Main.class.getClassLoader();
        return Objects.requireNonNull(classLoader.getResource(CHROMEDRIVER_EXE)).getPath();
    }

    private static WebDriver createDriverAndGoToPage() {
        System.setProperty("webdriver.chrome.driver", getFilePath());
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.okg.se/en");
        return driver;
    }

    private static void scrollToSvg(WebDriver driver) {
        WebElement svg = driver.findElement(By.xpath(SVG_PATH));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, " + svg.getLocation() + ");");
    }

    private static void sleepTwoSec() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
