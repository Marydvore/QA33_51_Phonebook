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
        }
    }

    @Test
    public void addNewContactSuccessAllFields() {
        int i = new Random().nextInt(1000) + 10000000;
        int z = (int) (System.currentTimeMillis() / 1000 % 3600);

        Contact contact = Contact.builder()
                .name("Alex" + z)
                .lastName("Levin")
                .phone("05" + i) //"0547567"+z"
                .email("alex" + z + "@lev.com")
                .address("Roboad av. 7/278")
                .description("friend")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().SaveContact();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test
    public void addNewContactSuccessRequiredFields() {
        int i = new Random().nextInt(1000) + 10000000;
        int z = (int) (System.currentTimeMillis() / 1000 % 3600);

        Contact contact = Contact.builder()
                .name("Tony" + z)
                .lastName("Dvoretskiy")
                .phone("05" + i)
                .email("tony" + z + "@dvo.com")
                .address("Dali av. 79/147")
                .description(null)
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().SaveContact();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }


}
