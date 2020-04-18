package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WillhabenLoginPage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement inputFieldEMail;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement inputFieldPassword;

    @FindBy(xpath = "//button[@name='login']")
    private WebElement submitButton;

    //alert message: Bitte gib eine gültige E-Mail-Adresse an
    @FindBy(xpath = "//body[@id='wh-body']//div[@id='skip-to-content']//div//div//div[1]//div[1]//small[1]")
    private WebElement alertIncorrectEMailFormat;


    @FindBy(xpath = "//span[contains(text(),'Die angegebene E-Mail Adresse bzw. das Passwort ko')]")
    private WebElement alertIncorrectCredentials;

    //alert message: Bitte gib dein Passwort ein
    @FindBy(xpath = "//body[@id='wh-body']//div[@id='__next']//div//div//div[2]//div[1]//small[1]")
    private WebElement alertNoPasswordAdded;

    public WillhabenLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setEMail(String eMail) {
        this.inputFieldEMail.sendKeys(eMail);
    }

    public void setPassword(String inputFieldPassword) {
        this.inputFieldPassword.sendKeys(inputFieldPassword);
    }

    public void setSubmitButtonClick() {
        this.submitButton.click();
    }

    public WebElement getAlertIncorrectCredentials() {
        return this.alertIncorrectCredentials;
    }

    public WebElement getAlertIncorrectEMailFormat() {
        return this.alertIncorrectEMailFormat;
    }

    public WebElement getSubmitButton() {
        return this.submitButton;
    }

    public WebElement getAlertNoPasswordAdded() {
        return this.alertNoPasswordAdded;
    }
}
