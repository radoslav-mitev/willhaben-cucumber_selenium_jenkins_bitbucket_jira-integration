package dataDrivenTesting;

/*
 * Automated Data-Driven Testing implementation of Selenium WebDriver
 *
 * Test object – the logon process on www.willhaben.at
 *
 * Test features – three of more possible error alerts:
 *      "Bitte gib eine gültige E-Mail-Adresse an",
 *      "Bitte gib dein Passwort ein",
 *      "Login fehlgeschlagen".
 *
 * This class contains 2 JUnit tests performing 2 login attempts implementing Apache POI and 2 using JDBC,
 * all with incorrect credentials (in a valid or non valid format) and verifies the expected 3 error alerts.
 */


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageFactory.WillhabenHomepage;
import pageFactory.WillhabenLoginPage;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class WillhabenLoginTest {

    private WebDriver driver;
    private WillhabenHomepage willhabenHomepage;
    private WillhabenLoginPage willhabenLogin;
    private XSSFSheet sheet;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private WebDriverWait wait;

    /**
     * Method ist invoked in the two @Test methods and carries out following page handling steps:
     * homepage opening,
     * locating the "Einloggen" link,
     * clicking on it,
     * waiting until the "Einloggen" button on the next page appears.
     */

    public void homepageOpening() {

        this.driver.get("https://www.willhaben.at/iad");

        //"Einloggen" element click on
        this.willhabenHomepage.clickLogin();

        //waiting until the last of the 3 login elements required ist present
        this.wait.until(ExpectedConditions.elementToBeClickable(this.willhabenLogin.getSubmitButton()));

    }


    /**
     * Verifying that one ot the 3 expected error alerts is displayed:
     *
     * @param element one of the three expected web elements (test features)
     */

    public void assertExpectedAlertIsDisplayed(WebElement element) {

        boolean statusElementDisplayed = false;

        try {
            statusElementDisplayed = this.wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(statusElementDisplayed);
    }


    /**
     * testSetup() performs:
     * setting up the web driver,
     * homepage opening,
     * accepting the cookies,
     * page objects initialization.
     */

    @Before
    public void testSetup() {

        System.setProperty("webdriver.chrome.driver", "C:\\javaDevelopment\\Selenium\\testdataAndDriver\\chromedriver.exe");

        this.driver = new ChromeDriver();
        this.driver.get("https://www.willhaben.at/iad");
        // this.driver.manage().window().maximize();   //optionally

        //Page objects initialization
        this.willhabenLogin = new WillhabenLoginPage(this.driver);
        this.willhabenHomepage = new WillhabenHomepage(this.driver);

        //accepting cookies in popup element
        this.willhabenHomepage.acceptCookies();

        this.wait = new WebDriverWait(this.driver, 15);
    }


    /**
     * The test method imports and uses test data from an EXCEL file:
     * two pairs of credentials in INVALID e-mail format (without @ and domain) and WITHOUT a password:
     * [test1@gmx|   ]
     * [ t@e@st  |   ]
     *
     * Test features (error alerts):
     * "Bitte gib eine gültige E-Mail-Adresse an."
     * "Bitte gib dein Passwort ein"
     */

    @Test
    public void loginUsingExcelFile() {

        //importing data from an excel file

        try {
            File src = new File("C:\\javaDevelopment\\Selenium\\testdataAndDriver\\Testdata_Excel.xlsx");
            OPCPackage pkg = OPCPackage.open(src);
            XSSFWorkbook workbook = new XSSFWorkbook(pkg);
            this.sheet = workbook.getSheetAt(0);
            pkg.close();
        } catch (IOException | InvalidFormatException e) {
            System.out.println("Fehler: " + e.getMessage());
        }

        //using data from the excel file

        XSSFCell cell;

        for (int i = 1; i <= this.sheet.getLastRowNum(); i++) {

            this.homepageOpening();

            //input e-mail
            cell = this.sheet.getRow(i).getCell(0);
            cell.setCellType(CellType.STRING);
            this.willhabenLogin.setEMail(cell.getStringCellValue());

            //submit
            this.willhabenLogin.setSubmitButtonClick();

            //assert error message "Bitte gib eine gültige E-Mail-Adresse an."
            this.assertExpectedAlertIsDisplayed(this.willhabenLogin.getAlertIncorrectEMailFormat());

            //assert error message "Bitte gib dein Passwort ein"
            this.assertExpectedAlertIsDisplayed(this.willhabenLogin.getAlertNoPasswordAdded());
        }

        this.driver.close();
    }


    /**
     * The test method imports and uses test data from a database:
     * two pairs of credentials in VALID e-mail format (with @ and domain) and a password:
     * [db1@gmx.at | password1 ]
     * [db2@te.st  | password2 ]
     *
     * Test feature (error alert):
     * "Login fehlgeschlagen. Die angegebene E-Mail Adresse bzw. das Passwort konnten nicht erkannt werden."
     */

    @Test
    public void loginUsingDB() {

        try {

            if (this.connection == null) {
                Class.forName("com.mysql.jdbc.Driver");
                this.connection = DriverManager
                        .getConnection("jdbc:mysql://localhost/db_login_data", "root", "");
            }

            this.statement = connection.createStatement();

            this.resultSet = statement.executeQuery("SELECT * FROM tb_login_data_valid_email_format");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Fehler: " + e.getMessage());
        }

        // using data from the database

        try {
            while (this.resultSet.next()) {

                homepageOpening();

                this.willhabenLogin.setEMail(this.resultSet.getString("username"));
                this.willhabenLogin.setPassword(this.resultSet.getString("password"));
                this.willhabenLogin.setSubmitButtonClick();

                //assert error message:
                //"Login fehlgeschlagen. Die angegebene E-Mail Adresse bzw. das Passwort konnten nicht erkannt werden."
                assertExpectedAlertIsDisplayed(this.willhabenLogin.getAlertIncorrectCredentials());

            }
        } catch (SQLException e) {
            System.out.println("Fehler: " + e.getMessage());

        } finally {

            this.driver.quit();

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
}
