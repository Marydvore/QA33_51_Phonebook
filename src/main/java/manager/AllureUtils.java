package manager;

import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class AllureUtils {
    public static void saveScreenshot(byte[] screen) {
        Allure.addAttachment("Screenshot", "image/png", new ByteArrayInputStream(screen), "png");
    }

    public static void saveTextLog(String message) {
        Allure.addAttachment("Log", "text/plain", new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8)), ".txt");
    }

    public static void saveTextInfo(String name, String message) {
        Allure.addAttachment(name, "text/plain", new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8)), ".txt");
    }
}
