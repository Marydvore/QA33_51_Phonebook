package manager;

import io.qameta.allure.Step;
import models.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    @Step("Open login/registration form")
    public void openLoginRegistrationForm() {
        //wd.findElement(By.cssSelector("a[href='/login']"));
        //WebElement loginTab = wd.findElement(By.xpath("//a[text()='LOGIN']"));
        //loginTab.click();
        click(By.cssSelector("a[href='/login']"));
    }

    @Step("Fill login/registration form with email:{email} and password {password}")
    public void fillLoginRegistrationForm(String email, String password) {
        //WebElement emailInput = wd.findElement(By.name("email"));
        //emailInput.click();
        //emailInput.clear();
        //emailInput.sendKeys(email);
        type(By.name("email"), email);

        //WebElement passwordInput = wd.findElement(By.xpath("//input[@placeholder='Password']"));
        //passwordInput.click();
        //passwordInput.clear();
        //passwordInput.sendKeys(password);
        type(By.xpath("//input[@placeholder='Password']"), password);
        //type(By.xpath("//input[@placeholder='Passwordddd']"), password);
    }

    @Step("Submit login")
    public void submitLogin() {
        click(By.xpath("//button[text()='Login']"));
    }

    @Step("Submit registration")
    public void submitRegistration() {
        click(By.xpath("//button[text()='Registration']"));
    }

    @Step("Check if user logged")
    public boolean isLogged() {
        return isElementPresent(By.xpath("//button[text()='Sign Out']"));
    }

    @Step("Logout")
    public void logout() {
        click(By.xpath("//button[text()='Sign Out']"));
    }



    @Step("Fill login/registration form with user {user}")
    public void fillLoginRegistrationForm(User user) {
        type(By.name("email"), user.getEmail());
        type(By.xpath("//input[@placeholder='Password']"), user.getPassword());
    }



    public void login(User user) {
        openLoginRegistrationForm();
        fillLoginRegistrationForm(user);
        submitLogin();
    }
}
