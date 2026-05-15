package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperContact extends HelperBase {
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactForm() {
        //pause(2000);
        click(By.cssSelector("a[href=\"/add\"]"));// a[href='/add']
    }

    public void clearFiledName(){
        WebElement name = wd.findElement(By.xpath("//input[@placeholder='Name']"));
        String operationSystem = System.getProperty("os.name");
        System.out.println(operationSystem);
        if (operationSystem.startsWith("Win")) {
            name.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        }
        if (operationSystem.startsWith("Mac")) {
            name.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        }
        name.sendKeys(Keys.DELETE);
    }

    public void fillContactForm(Contact contact) {
        type(By.xpath("//input[@placeholder='Name']"), contact.getName());
        //cssSelector("[placeholder='Name']")
        type(By.xpath("//input[@placeholder='Last Name']"), contact.getLastName());
        //cssSelector("[placeholder='Last Name']")
        type(By.xpath("//input[@placeholder='Phone']"), contact.getPhone());
        //cssSelector("[placeholder='Phone']")
        type(By.xpath("//input[@placeholder='email']"), contact.getEmail());
        //cssSelector("[placeholder='email']")
        type(By.xpath("//input[@placeholder='Address']"), contact.getAddress());
        //cssSelector("[placeholder='Address']")
        type(By.xpath("//input[@placeholder='description']"), contact.getDescription());
        //cssSelector("[placeholder='description']")
        pause(3000);
    }

    public void SaveContact() {
        click(By.cssSelector(".add_main__1tbl_ button")); // ".add_form__2rsm2>button"
    }

    public boolean isContactAddedByName(String name) {
        List<WebElement> list = wd.findElements(By.cssSelector("h2"));
        for (WebElement element:list) {
            if (element.getText().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isContactAddedByPhone(String phone) {
        List<WebElement> list = wd.findElements(By.cssSelector("h3"));
        for (WebElement element:list) {
            if (element.getText().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public boolean isBtnSaveNoActive() {
        return isElementPresent(By.cssSelector(".add_form__2rsm2 input[value='']:not(:last-of-type)"));
    }

    public boolean isBtnAddIsBlack() {
        return isElementPresent(By.cssSelector(".active"));
        // //a[@style='border: 1px solid black; background-color: black; color: white;']
    }
}
