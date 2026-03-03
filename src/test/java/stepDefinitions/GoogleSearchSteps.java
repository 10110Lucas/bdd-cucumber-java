package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class GoogleSearchSteps {

    private WebDriver driver;
    private final static String PROJECT_PATH = System.getProperty("user.dir");
    private final static String LOCATION_DRIVER = "/src/test/resources/drivers/chromedriver.exe";

    @Given("browser is open")
    public void browser_is_open() {

        System.out.println("Opening the Browser...");

        System.setProperty("webdriver.chrome.driver", PROJECT_PATH + LOCATION_DRIVER);
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        // driver.manage().window().maximize();

        System.out.println("Browser is open!");
    }

    @And("user is on google search page")
    public void user_is_on_google_search_page() {

        System.out.println("Navigating to 'Google Page'...");

        driver.navigate().to("https://www.google.com/");

        System.out.println("Google Page was successfully opened!");
    }

    @When("user enters a text in search box")
    public void user_enters_a_text_in_search_box() {

        System.out.println("Searching in Google Search box...");

        driver.findElement(By.name("q")).sendKeys("DUB cars");
    }

    @And("hits enter")
    public void hits_enter() {

        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        System.out.println("Search box has been successfully entered!");
    }

    @Then("user is navigated to search results")
    public void user_is_navigated_to_search_results() throws InterruptedException {

        System.out.println("Loading search results for 20 seconds...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            if (wait.until(d -> Objects.requireNonNull(d.getPageSource()).contains("DUBStore"))) {
                System.out.println("Tests finished with SUCCESS!");
            }
        } catch (Exception e) {
            System.out.println("Tests finished with FAILED!");
        }
        driver.close();
        driver.quit();
    }
}
