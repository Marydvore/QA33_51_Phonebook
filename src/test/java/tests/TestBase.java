package tests;

import manager.ApplicationManager;
import manager.TestNGListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@Listeners(TestNGListener.class)

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    static ApplicationManager app = new ApplicationManager();

    @BeforeSuite
    public void setUp() {
        app.init();
    }

    @BeforeMethod
    public void startLogger(Method m) {
        logger.info("Name of method (test) ---> " + m.getName());
    }

    @AfterMethod
    public void end(){
        logger.info("===============================================");
    }

    @AfterSuite
    public void tearDown() {
        //app.stop();
    }

}


