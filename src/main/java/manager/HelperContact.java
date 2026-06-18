package manager;

import io.qameta.allure.Step;
import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class HelperContact extends HelperBase {
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    @Step("Open contact form")
    public void openContactForm() {
        //pause(2000);
        click(By.cssSelector("a[href=\"/add\"]"));// a[href='/add']
    }

    @Step("Fill contact form")
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
        //pause(3000);
    }

    @Step("Save contact")
    public void saveContact() {
        click(By.cssSelector(".add_main__1tbl_ button")); // ".add_form__2rsm2>button"
    }

    public boolean isContactAddedByName(String name) {
        List<WebElement> list = wd.findElements(By.cssSelector("h2"));
        for (WebElement element : list) {
            if (element.getText().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Step("Check that contact with {phone} is added")
    public boolean isContactAddedByPhone(String phone) {
        List<WebElement> list = wd.findElements(By.cssSelector("h3"));
        for (WebElement element : list) {
            if (element.getText().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public boolean isBtnSaveNoActive() {
        return isElementPresent(By.cssSelector(".add_form__2rsm2 input[value='']:not(:last-of-type)"));
    }

    public boolean isAddContactPageStillDisplayed() {
        return isElementPresent(By.cssSelector(".active[href='/add']"));
        // //a[@style='border: 1px solid black; background-color: black; color: white;']
    }


    @Step("Remove a single contact")
    public int removeOneContact() {
        int before = countOfContacts();
        logger.info("Number of Contacts before remove is -->" + before);
        removeContact();
        int after = countOfContacts();
        logger.info("Number of Contacts after remove is -->" + after);

        return before - after;
    }

    private void removeContact() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
        click(By.xpath("//button[text()='Remove']"));
        pause(1000);
    }

    private int countOfContacts() {
        List<WebElement> list = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
        return list.size();
    }

    @Step("Remove all contacts")
    public void removeAllContacts() {
        while (countOfContacts() != 0) {
            removeOneContact();
        }
    }

    public void provideContacts() {
        if (countOfContacts() < 3) {
            for (int i = 0; i < 3; i++) {
                addOneContact();
            }
        }
    }

    private void addOneContact() {
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Harry")
                .lastName("Potter")
                .phone("0547567" + i)
                .email("harry" + i + "@dvo.com")
                .address("Dali av. 79/147")
                .description(null)
                .build();
        openContactForm();
        fillContactForm(contact);
        saveContact();
        pause(500);

    }
}
