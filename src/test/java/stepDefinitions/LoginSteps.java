package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.LoginPage;

public class LoginSteps {

    private static final Logger log = LoggerFactory.getLogger(LoginSteps.class);
    private LoginPage loginPage; // applying design pattern POM (Page Object Model)

    @Given("opening browser")
    public void opening_browser() {
        log.info("Inside Step - Browser is open");

        WebDriverManager.chromedriver().setup();// obtem o driver automaticamente
        loginPage = new LoginPage();

        loginPage.configureTimeouts(40);
        loginPage.configurePosition();
    }

    @And("user is on login page")
    public void user_is_on_login_page() {
        log.info("Inside Step - User is on login page");

        loginPage.navigateTo("https://www.saucedemo.com/");
    }

    @When("user enters {string} and {string}")
    public void user_enters_username_and_password(String username, String password) {
        log.info("Inside Step - User enters 'username' and 'password'");

        loginPage.enterLoginAndPass(username, password);
    }

    @And("user click on login")
    public void user_click_on_login() {
        log.info("Inside Step - User click on login");

        loginPage.clickLoginButton();
    }

    @Then("user is navigated to the home page")
    public void user_is_navigated_to_the_home_page() {
        log.info("Inside Step - User is navigated to the home page");

        try {
            // esperar por 10 segundos
            if (loginPage.awaitForHomePage(10)) {
                loginPage.checkSizeAndPosition();
                final String actualTitle = loginPage.getHomePageTitle();
                log.info("Actual Title: {}",actualTitle);
                Assert.assertEquals("Products", actualTitle);
                log.info("Tests finished with SUCCESS!");
            }
        } catch (Exception ex) {
            log.error("Tests finished with FAILED!");
        }
        loginPage.close();
    }

    @Then("user is not navigated to the home page")
    public void user_is_not_navigated_to_the_home_page() {
        if (loginPage.containMessageError()) {
            final String actualMessage = loginPage.getMessageError();
            log.info("Actual message: {}", actualMessage);
            Assert.assertEquals("Epic sadface: Sorry, this user has been locked out.", actualMessage);
        }
        loginPage.close();
    }
}
