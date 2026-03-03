package stepDefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginSteps {

    private WebDriver driver = null;

    @Given("opening browser")
    public void opening_browser() {
        System.out.println("Inside Step - Browser is open");

        WebDriverManager.chromedriver().setup();// obtem o driver automaticamente
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
    }

    @And("user is on login page")
    public void user_is_on_login_page() {
        System.out.println("Inside Step - User is on login page");

        driver.navigate().to("https://www.saucedemo.com/");
    }

    @When("user enters {string} and {string}")
    public void user_enters_username_and_password(String username, String password) {
        System.out.println("Inside Step - User enters 'username' and 'password'");

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("user click on login")
    public void user_click_on_login() {
        System.out.println("Inside Step - User click on login");

        driver.findElement(By.id("login-button")).click();
    }

    @Then("user is navigated to the home page")
    public void user_is_navigated_to_the_home_page() {
        System.out.println("Inside Step - User is navigated to the home page");

        // esperar por 5 segundos
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        final String expectedTitle = "Products";

        try {
            if (wait.until(d -> expectedTitle.equals(d.findElement(By.className("title")).getText()))) {
                String title = driver.findElement(By.className("title")).getText();
                System.out.println("Title actual: "+title);
                Assert.assertEquals(expectedTitle, title);
                System.out.println("Tests finished with SUCCESS!");
            }
        } catch (Exception ex) {
            System.out.println("Tests finished with FAILED!");
        }
        driver.close();
        driver.quit();
    }

    @Then("user is not navigated to the home page")
    public void user_is_not_navigated_to_the_home_page() {
        if (driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed()) {
            final String expectedMessage = "Epic sadface: Sorry, this user has been locked out.";
            final String actualMessage = driver.findElement(By.cssSelector("[data-test='error']")).getText();
            System.out.println("Mensagem real: " + actualMessage);
            Assert.assertEquals(expectedMessage, actualMessage);
        }
        driver.close();
        driver.quit();
    }
}
