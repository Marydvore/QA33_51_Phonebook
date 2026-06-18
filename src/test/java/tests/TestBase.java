package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import manager.ApplicationManager;
import manager.TestNGListener;
import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@Listeners({TestNGListener.class, AllureTestNg.class})

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    static ApplicationManager app = new ApplicationManager(
            System.getProperty("browser", Browser.CHROME.browserName()));

    public static ApplicationManager getApp() {
        return app;
    }

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        app.init();
    }

    @BeforeMethod(alwaysRun = true)
    public void startLogger(Method m) {
        Allure.step("Start test: " + m.getName());
        logger.info("Name of method (test) ---> " + m.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void end() {
        logger.info("===============================================");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

}


