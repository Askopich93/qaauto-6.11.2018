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

        Assert.assertTrue(loginPage.isPageLoaded(),
                "Login Page is not loaded.");
    }

    @Test
    public void successfulLoginTest() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("nastask31@gmail.com", "Betmen291293");
        HomePage homePage = new HomePage(webDriver);

        Assert.assertTrue(homePage.isPageLoaded(),
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


    @Test
    public void negativeLeadsToLoginSubmitPage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("nastyask31@@gmail.com","wrong");
        LoginSubmitPage loginSubmitPage = new  LoginSubmitPage(webDriver);

        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login Submit page is not loeded.");

        Assert.assertEquals(loginSubmitPage.getUserEmailError(), "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", "userEmail Validation message is wrong.");

        Assert.assertEquals(loginSubmitPage.getUserPassError(), "");
    }


}