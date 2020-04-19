package behaviorDrivenTesting.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageFactory.WillhabenHomepage;
import pageFactory.WillhabenLoginPage;

import java.sql.*;

public class WillhabenLoginStepdefs {


    private WebDriver driver;
    private WillhabenHomepage willhabenHomepage;
    private WillhabenLoginPage willhabenLogin;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private WebDriverWait wait;
    private String tb_login_data_non_valid_email_format = "SELECT * FROM tb_login_data_non_valid_email_format";
    private String tb_login_data_valid_email_format = "SELECT * FROM tb_login_data_valid_email_format";


    @Before("@database")
    public void establishDBConnection() {

        if (this.connection == null) {

            try {

                Class.forName("com.mysql.jdbc.Driver");
                this.connection = DriverManager
                        .getConnection("jdbc:mysql://localhost/db_login_data", "root", "");

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Fehler: " + e.getMessage());
            }
        }
    }

    //Background
    @Given("an user opens www.willhaben.at in a chrome browser")
    public void anUserOpensWwwWillhabenAtInChromeBrowser() {

        System.setProperty("webdriver.chrome.driver", "C:\\javaDevelopment\\Selenium\\testdataAndDriver\\chromedriver.exe");

        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(this.driver, 30);
        this.willhabenLogin = new WillhabenLoginPage(this.driver);
        this.willhabenHomepage = new WillhabenHomepage(this.driver);

    }


    @When("trying to log into account using non-valid e-mail address format")
    public void tryingToLogIntoAccountUsingNonValidEMailAddressFormat() {

        this.login(tb_login_data_non_valid_email_format, this.willhabenLogin.getAlertIncorrectEMailFormat());
    }


    public void login(String sqlQuery, WebElement element) {

        try {
            this.statement = this.connection.createStatement();
            this.resultSet = this.statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        this.driver.get("https://www.willhaben.at/iad");

        //accepting cookies in popup element
        this.willhabenHomepage.acceptCookies();

        try {
            while (this.resultSet.next()) {

                this.driver.get("https://www.willhaben.at/iad");

                //"Einloggen" element on the homepage click on
                this.willhabenHomepage.clickLogin();

                //waiting until the last of all 3 login elements required ist clickable
                this.wait.until(ExpectedConditions.elementToBeClickable(this.willhabenLogin.getSubmitButton()));

                //input e-mail
                this.willhabenLogin.setEMail(this.resultSet.getString("username"));

                //input password
                this.willhabenLogin.setPassword(this.resultSet.getString("password"));

                //submit
                this.willhabenLogin.setSubmitButtonClick();

                //asserting error message
                this.assertingExpectedAlertIsDisplayed(element);

            }
        } catch (SQLException e) {
            System.out.println("Fehler: " + e.getMessage());
        }

        this.driver.close();

    }

    @Then("should see [Bitte gib eine gueltige E-Mail-Adresse an]")
    public void shouldSeeBitteGibEineGueltigeEMailAdresseAn() {
    }


    @When("trying to log into account using an e-mail address in valid format")
    public void tryingToLogIntoAccountUsingAnEMailAddressInValidFormat() {

        this.login(tb_login_data_valid_email_format, this.willhabenLogin.getAlertIncorrectCredentials());

    }

    @Then("user should see [Login fehlgeschlagen]")
    public void userShouldSeeLoginFehlgeschlagen() {
    }


    public void assertingExpectedAlertIsDisplayed(WebElement element) {

        boolean statusElementDisplayed = false;

        try {

            statusElementDisplayed = this.wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(statusElementDisplayed);

    }

    @Then("user should see [Bitte gib dein Passwort ein]")
    public void userShouldSeeBitteGibDeinPasswortEin() {

        this.assertingExpectedAlertIsDisplayed(this.willhabenLogin.getAlertNoPasswordAdded());
        this.driver.quit();
    }

    @When("trying to log into account using an {string} in valid format without a {string}")
    public void tryingToLogIntoAccountUsingAnInValidFormatWithoutAAnd(String email, String password) {

        this.driver.get("https://www.willhaben.at/iad");

        //accepting cookies in popup element
        this.willhabenHomepage.acceptCookies();

        //"Einloggen" element on the homepage click on
        this.willhabenHomepage.clickLogin();

        //waiting until the last of the 3 login elements required ist present
        wait.until(ExpectedConditions.visibilityOf(this.willhabenLogin.getSubmitButton()));

        //input e-mail
        this.willhabenLogin.setEMail(email);
        System.out.println(email);

        //input password
        this.willhabenLogin.setPassword(password);
        System.out.println(password);

        //submit
        this.willhabenLogin.setSubmitButtonClick();

    }

    @After("@database and @last")
    public void closeConnnection() {

        try {
            if (this.statement != null) {
                this.statement.close();
            }
            if (this.resultSet != null) {
                this.resultSet.close();
            }
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }
}
