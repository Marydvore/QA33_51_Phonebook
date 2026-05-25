package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class HelperBase {
    WebDriver wd;
    Logger logger = LoggerFactory.getLogger(HelperBase.class);

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void type(By locator, String text) {
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        clearFiledName(element);
        if (text != null) {
            element.sendKeys(text);
        }
    }

    public void click(By locator) {
        WebElement element = wd.findElement(locator);
        element.click();
    }

    public boolean isElementPresent(By locator) {
        List<WebElement> list = wd.findElements(locator);
        return list.size() > 0;
    }

    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isAlertPresent(String message) {
        Alert alert = new WebDriverWait(wd, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        if (alert!=null&&alert.getText().contains(message)){
            //System.out.println(alert.getText());
            // click Ok --> alert.accept();
            // click Cancel --> alert.dismiss();
            // type into alert --> alert.sendKeys("text");
            pause(2000);
            alert.accept();
            return true;
        }
        return false;
    }

    public void clearFiledName(WebElement element){
        element.sendKeys(" ");
        element.sendKeys(Keys.BACK_SPACE);
    }

    public void getScreen(String link) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) wd;
        File tmp = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp,new File(link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isNoContactsHereDisplayed() {
        WebDriverWait wait=new WebDriverWait(wd, Duration.ofSeconds(5));
        boolean res = wait.until(ExpectedConditions
                .textToBePresentInElement(wd.findElement(By.cssSelector(".contact-page_message__2qafk>h1")),
                        "No Contacts here!"));
        return res;
    }
}
