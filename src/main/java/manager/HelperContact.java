package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperContact extends HelperBase {
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactForm() {
        pause(2000);
        click(By.cssSelector("a[href=\"/add\"]"));// a[href='/add']
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
}
