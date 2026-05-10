package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        //If button 'Sign Out' present ---> Logout
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess() {
        // 1 variable
        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        System.out.println(i);
        System.out.println("==========================");
        // 2 variable
        int z = (int) (System.currentTimeMillis() / 1000) % 3600;
        System.out.println(z);

        User user = new User()
                .setEmail("art" + z + "@art.com")
                .setPassword("$Art1$2$3456789");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void RegistrationWrongEmail(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("artart.com", "$Art1$2$3456789");
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }

    @Test
    public void RegistrationWrongPassword(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("art11@art.com", "$Art1");
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }

    @Test
    public void RegistrationRegisteredUser(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("margo@gmail.com", "Mmar123456$");
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));

    }
}
