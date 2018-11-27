import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSubmitPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//form[@class='login__form']")
    private WebElement loginForm;

    @FindBy(xpath = "//div[@id='error-for-username']")
    private WebElement userEmailError;

    @FindBy(xpath = "//div[@id='error-for-password']")
    private WebElement userPassError;

    public LoginSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public boolean isPageLoaded() {
        return loginForm.isDisplayed()
                && webDriver.getTitle().contains("Войти в LinkedIn")
                && webDriver.getCurrentUrl().equals("https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME");
    }

    public String getUserEmailError() {
        return userEmailError.getText();
    }

    public String getUserPassError() {
        return userPassError.getText();
    }

}
