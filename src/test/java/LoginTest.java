import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.get("https://www.linkedin.com");
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

    @Test
    public void negativeLoginTest() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("a@b.c", "");

        //Verify that page title is "LinkedIn: Log In or Sign Up"
        Assert.assertEquals(webDriver.getTitle(), "LinkedIn: Войти или зарегистрироваться",
                "Login page title is wrong.");
        Assert.assertTrue(loginPage.signInButton.isDisplayed(), "SignIn button is not displayed.");
    }

    @Test
    public void successfulLoginTest() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("nastask31@gmail.com", "Betmen291293");

        //Verify that page title is "LinkedIn: Log In or Sign Up"
        WebElement welcomeMessage = webDriver.findElement(
                By.xpath("//a[@data-control-name='identity_welcome_message']"));

        Assert.assertTrue(webDriver.getTitle().contains("LinkedIn"),
                "Home page title is wrong.");
        Assert.assertTrue(welcomeMessage.isDisplayed(),
                "Welcome message is not displayed.");


    }

    @Test
    public void invalidEmailLoginTest() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("nastask31#@gmail.com","Betmen291293");

        //Verify that page title is "LinkedIn: Log In or Sign Up"
        Assert.assertEquals(webDriver.getTitle(), "Войти в LinkedIn",
                "This email address Mail is not registered with LinkedIn. Try again.");


    }

    @Test
    public void invalidPasswordLoginTest() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("nastyask31@gmail.com","Betmen29129376533");

        //Verify that page title is "LinkedIn: Log In or Sign Up"
        Assert.assertEquals(webDriver.getTitle(), "Войти в LinkedIn",
                "This is an invalid password. Try again or change your password.");
    }




}