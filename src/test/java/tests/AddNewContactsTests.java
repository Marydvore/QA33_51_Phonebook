package tests;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;

public class AddNewContactsTests extends TestBase {

    @BeforeClass
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("art276@art.com")
                    .setPassword("$Art1$2$3456789"));
            logger.info("Before method finished login");
        }
    }

    @Test
    public void addNewContactSuccessAllFields() {
        int i = new Random().nextInt(1000) + 10000000;
        int z = (int) (System.currentTimeMillis() / 1000 % 3600);

        Contact contact = Contact.builder()
                .name("Alex" + z)
                .lastName("Levin")
                .phone("0547567" + z) // "05" + i
                .email("alex" + z + "@lev.com")
                .address("Roboad av. 7/278")
                .description("friend")
                .build();

        logger.info("Test data ---> name: 'Alex" + z + "', last name: 'Levin', phone: '0547567" + z + "', " +
                "email: 'alex" + z + "@lev.com', address: 'Roboad av. 7/278', description: 'friend'");
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().getScreen("src/test/screenchots/screen-" + i + ".png");
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        logger.info("Assert check is contact added by name");
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        logger.info("Assert check is contact added by phone");
    }

    @Test
    public void addNewContactSuccessRequiredFields() {
        int i = new Random().nextInt(1000) + 10000000;
        int z = (int) (System.currentTimeMillis() / 1000 % 3600);

        Contact contact = Contact.builder()
                .name("Tony" + z)
                .lastName("Dvoretskiy")
                .phone("0547567" + z)
                .email("tony" + z + "@dvo.com")
                .address("Dali av. 79/147")
                .description(null)
                .build();

        logger.info("Test data ---> name: 'Tony" + z + "', last name: 'Dvoretskiy', phone: '0547567 " + z + "'," +
                "email: 'tony" + z + "@dvo.com', address: 'Dali av. 79/147', description: ' '");
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        logger.info("Assert check is contact added by name");
        logger.info("Assert check is contact added by phone");
    }

    @Test
    public void addNewContactFiledNameIsEmpty() {
        logger.info("Test data ---> name: ' ', last name: 'Dor', phone: '0541035974'," +
                "tony@dvo.com', address: 'Dali av. 79/147', description: 'NameIsEmpty'");
        Contact contact = Contact.builder()
                .name(null)
                .lastName("Dor")
                .phone("0541035974")
                .email("tony@dvo.com")
                .address("Dali av. 79/147")
                .description("NameIsEmpty")
                .build();

        app.getHelperContact().openContactForm();
        //app.getHelperContact().clearFiledName();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isBtnSaveNoActive());
        Assert.assertTrue(app.getHelperContact().isAddContactPageStillDisplayed());
        logger.info("Assert check is element button 'Save' no active'");
        logger.info("Assert check is add contact page still displayed");
    }

    @Test
    public void addNewContactFiledLastNameIsEmpty() {
        logger.info("Test data ---> name: 'Tony001', last name: ' ', phone: '0541035974'," +
                "tony@dvo.com', address: 'Dali av. 79/147', description: 'LastNameIsEmpty'");
        Contact contact = Contact.builder()
                .name("Tony001")
                .lastName(null)
                .phone("0541035974")
                .email("tony@dvo.com")
                .address("Dali av. 79/147")
                .description("LastNameIsEmpty")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isBtnSaveNoActive());
        Assert.assertTrue(app.getHelperContact().isAddContactPageStillDisplayed());
        logger.info("Assert check is element button 'Save' no active'");
        logger.info("Assert check is add contact page still displayed");
    }

    @Test
    public void addNewContactWrongPhone() {
        logger.info("Test data ---> name: 'Tony001', last name: 'Brook', phone: '04568'," +
                "tony@dvo.com', address: 'Dali av. 79/147', description: 'WrongPhone'");
        Contact contact = Contact.builder()
                .name("Tony001")
                .lastName("Brook")
                .phone("04568")
                .email("tony@dvo.com")
                .address("Dali av. 79/147")
                .description("WrongPhone")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAlertPresent("Phone not valid"));
        Assert.assertTrue(app.getHelperContact().isAddContactPageStillDisplayed());
        logger.info("Assert check is alert present with error text 'Phone not valid'");
        logger.info("Assert check is add contact page still displayed");
    }

    @Test
    public void addNewContactWrongEmail() {
        logger.info("Test data ---> name: 'Tony001', last name: 'Brook', phone: '0541035789'," +
                "tonydvo.com', address: 'Dali av. 79/147', description: 'WrongEmail'");
        Contact contact = Contact.builder()
                .name("Tony001")
                .lastName("Brook")
                .phone("0541035789")
                .email("tonydvo.com")
                .address("Dali av. 79/147")
                .description("WrongEmail")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid"));
        Assert.assertTrue(app.getHelperContact().isAddContactPageStillDisplayed());
        logger.info("Assert check is alert present with error text 'Email not valid'");
        logger.info("Assert check is add contact page still displayed");
    }

    @Test
    public void addNewContactWrongAddress() {
        logger.info("Test data ---> name: 'Tony001', last name: 'Brook', phone: '0541035789'," +
                "tony@dvo.com', address: ' ', description: 'Wrong address'");
        Contact contact = Contact.builder()
                .name("Tony001")
                .lastName("Brook")
                .phone("0541035789")
                .email("tony@dvo.com")
                .address("")
                .description("Wrong address")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isBtnSaveNoActive());
        Assert.assertTrue(app.getHelperContact().isAddContactPageStillDisplayed());
        logger.info("Assert check is element button 'Save' no active'");
        logger.info("Assert check is add contact page still displayed");
    }
}
