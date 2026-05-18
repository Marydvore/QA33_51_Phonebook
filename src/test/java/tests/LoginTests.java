package tests;

import models.User;
import org.checkerframework.checker.units.qual.A;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends  TestBase{

    @BeforeMethod
    public void preCondition(){
        //If button 'Sign Out' present ---> Logout
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("Before method finished logout");
        }
    }

    @Test
    public void loginSuccess(){
        logger.info("Start test with name 'loginSuccess'");
        logger.info("Test data ---> email: 'art276@art.com' & password: '$Art1$2$3456789'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("art276@art.com", "$Art1$2$3456789");
        app.getHelperUser().submitLogin();

        //Assert
        //Assert.assertEquals();
        //Assert.assertNotEquals();
        //Assert.assertTrue();
        //Assert.assertFalse();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is element button 'Sign out' present");
    }

    @Test
    public void loginSuccessModel(){
        logger.info("Test data ---> email: 'art276@art.com' & password: '$Art1$2$3456789'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("margo@gmail.com", "Mmar123456$");
        app.getHelperUser().submitLogin();

        //Assert
        //Assert.assertEquals();
        //Assert.assertNotEquals();
        //Assert.assertTrue();
        //Assert.assertFalse();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is element button 'Sign out' present");
    }

    @Test
    public void loginWrongEmail(){
        logger.info("Test data ---> email: 'margogmail.com' & password: 'Mmar123456$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("margogmail.com", "Mmar123456$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error text 'Wrong email or password'");
    }

    @Test
    public void loginWrongPassword(){
        logger.info("Test data ---> email: 'margogmail.com' & password: 'Mmar123'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("margo@gmail.com", "Mmar123");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error text 'Wrong email or password'");
    }

    @Test
    public void loginUnregisteredUser(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("moreg@gmail.com", "Mmar123456$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));

    }

    @Test
    public void loginSuccess1(){
        //User user = new User();
        //user.setEmail("margo@gmail.com");
        //user.setPassword("Mmar123456$");
        User user=new User().setEmail("margo@gmail.com").setPassword("Mmar123456$");

        app.getHelperUser().openLoginRegistrationForm();
        //app.getHelperUser().fillLoginRegistrationForm("margo@gmail.com", "Mmar123456$");
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();

        //Assert
        //Assert.assertEquals();
        //Assert.assertNotEquals();
        //Assert.assertTrue();
        //Assert.assertFalse();

        Assert.assertTrue(app.getHelperUser().isLogged());
    }
}
