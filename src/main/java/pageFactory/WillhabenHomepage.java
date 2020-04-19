package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WillhabenHomepage {

    WebDriver driver;

    @FindBy(xpath = "//span[contains(text(),'Cookies akzeptieren')]")
    WebElement cookiesAlert;

    @FindBy(xpath = "//a[@id='login-logout']")
    WebElement loginLink;

    public WillhabenHomepage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Click on accept cookies button
    public void acceptCookies(){
        this.cookiesAlert.click();
    }


    //Click on "Einloggen" link
    public void clickLogin(){
        this.loginLink.click();
    }

    public WebElement getCookiesAlert() {
        return cookiesAlert;
    }


}
