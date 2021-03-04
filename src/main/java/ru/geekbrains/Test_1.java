package ru.geekbrains;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class Test_1 {

    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String STUDENT_LOGIN = "Applanatest";
    private static final String STUDENT_PASSWORD = "Student2020!";
    private static final WebDriver driver;

    static {
        //System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // DIFF #1 ---------------------------------------------------------
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    public static void main(String[] args) throws InterruptedException {
        login();

        driver.findElement(By.xpath(
                "//*[@id=\"main-menu\"]/ul/li[3]/a")).click();

        driver.findElement(By.xpath(
                "//*[@id=\"main-menu\"]/ul/li[3]/ul/li[3]/a/span")).click();

        WebDriverWait waitFiveSeconds = new WebDriverWait(driver, 5);
        waitFiveSeconds.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(
                "/html/body/div[2]/div/div[2]/div[3]/div[1]/div/div/div[2]/div/div/a"))));

        driver.findElement(By.cssSelector("/html/body/div[2]/div/div[2]/div[3]/div[1]/div/div/div[2]/div/div/a")).click();


    }

    private static void login() {
        driver.get(LOGIN_PAGE_URL);

        WebElement loginTextInput = driver.findElement(By.cssSelector("input[id='prependedInput']"));
        loginTextInput.sendKeys(STUDENT_LOGIN);

        WebElement passwordTextInput = driver.findElement(By.cssSelector(".span2[name='_password']"));
        passwordTextInput.sendKeys(STUDENT_PASSWORD);

        WebElement loginButton = driver.findElement(By.xpath(".//button[@name='_submit']"));
        loginButton.click();
    }

}
