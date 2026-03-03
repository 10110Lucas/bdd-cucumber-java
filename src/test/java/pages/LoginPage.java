package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class LoginPage {

    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
    private final WebDriver driver;
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By btnLogin = By.id("login-button");
    private final By homePageTitle = By.className("title");
    private final By messageError = By.cssSelector("[data-test='error']");

    public LoginPage() {
        this.driver = new ChromeDriver();
    }

    public void configureTimeouts(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(seconds));
    }
    public void configurePosition() {
        driver.manage().window().setPosition(new Point(1594, 370));
        driver.manage().window().setSize(new Dimension(1363, 831));
    }
    public void checkSizeAndPosition() {
        Dimension size = driver.manage().window().getSize();
        log.info("Largura (width): '{}', Altura (height): '{}'", size.getWidth(), size.getHeight());
        Point pos = driver.manage().window().getPosition();
        log.info("Posicao: X: '{}', Y: '{}'", pos.getX(), pos.getY());
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }
    public void enterLoginAndPass(String login, String pass) {
        driver.findElement(username).sendKeys(login);
        driver.findElement(password).sendKeys(pass);
    }
    public void clickLoginButton() {
        driver.findElement(btnLogin).click();
    }

    public boolean awaitForHomePage(int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(d -> d.findElement(homePageTitle).isDisplayed());
    }
    public String getHomePageTitle() {
        return driver.findElement(homePageTitle).getText();
    }

    public boolean containMessageError() {
        return driver.findElement(messageError).isDisplayed();
    }
    public String getMessageError() {
        return driver.findElement(messageError).getText();
    }

    public void close() {
        driver.close();
        driver.quit();
    }
}
