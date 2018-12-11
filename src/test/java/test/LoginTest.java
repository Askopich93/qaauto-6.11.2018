package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;
import page.LoginSubmitPage;

public class LoginTest extends BaseTest {

    @DataProvider
    public Object[][] negativeDataProvider() {
        return new Object[][]{
            { "nastask31@gmail.com", "" },
            { "", "Betmen291293" },
                {"", "" },
        };
    }

    @Test(dataProvider = "negativeDataProvider")
    public void negativeLoginTest(String userEmail, String userPass) {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("a@b.c", "");

        Assert.assertTrue(loginPage.isPageLoaded(),
                "Login Page is not loaded.");
    }

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                { "nastask31@gmail.com", "Betmen291293" },
                { "nastask31@GMAIL.COM", "Betmen291293" },
                { "nastask31@gmail.com ", "Betmen291293" },
        };
    }

    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPass) {
        LoginPage loginPage = new LoginPage(webDriver);
        HomePage homePage = loginPage.login(userEmail, userPass);

        Assert.assertTrue(homePage.isPageLoaded(),
                "Welcome message is not loaded.");
    }

    @DataProvider
    public Object[][] invalidDataProvider() {
        return new Object[][] {
               { "nastask31@@gmail.com", "Betmen291293", "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", "" },
                { "nastask31@gmail.com", "Betmen29129393", "", "Это неверный пароль. Повторите попытку или измените пароль."},
        };
    }

    @Test(dataProvider = "invalidDataProvider")
    public void negativeLeadsToLoginSubmitPage(String userEmail, String userPass, String userEmailError, String userPassError) {
        LoginPage loginPage = new LoginPage(webDriver);

        LoginSubmitPage loginSubmitPage = loginPage.login(userEmail,userPass);

        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login Submit page is not loaded.");

        Assert.assertEquals(loginSubmitPage.getUserEmailError(), userEmailError,
                "userEmail Validation message is wrong.");

        Assert.assertEquals(loginSubmitPage.getUserPassError(), userPassError,
                "userPass Validation message is wrong.");
    }
}
