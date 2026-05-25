package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("art276@art.com")
                    .setPassword("$Art1$2$3456789"));
        }
        app.getHelperContact().provideContacts();// if list of contacts <3 ---> add 3 contacts
    }

    @Test
    public void removeFirstContact(){
        //Assert size contactList less by one
        Assert.assertEquals(app.getHelperContact().removeOneContact(), 1);
    }

    @Test
    public void removeAllContacts(){
        //"No contacts here" is present
        app.getHelperContact().removeAllContacts();
        Assert.assertTrue(app.getHelperContact().isNoContactsHereDisplayed());
    }
}
