import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static javax.swing.UIManager.put;
import static jdk.internal.ref.Cleaner.add;

public class TestBase {

    @BeforeAll
    static void beforeAll() throws MalformedURLException {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        baseUrl = "https://rshb.ru";
        Configuration.browserPosition = ("0x0");
        Configuration.browserSize = "1920x1080";

        //password and user for remote browser
//        String user = System.getProperty("user");
//        String password = System.getProperty("password");
//        Configuration.remote = "https://" + user + ":" + password + "@" + System.getProperty("remoteBrowser");


        ChromeOptions options = new ChromeOptions();
        options.setCapability("browserVersion", "95.0");
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            /* How to add test badge */
            put("name", "Test badge...");

            /* How to set session timeout */
            put("sessionTimeout", "15m");

            /* How to set timezone */
            put("env", new ArrayList<String>() {{
                add("TZ=UTC");
            }});

            /* How to add "trash" button */
            put("labels", new HashMap<String, Object>() {{
                put("manual", "true");
            }});

            /* How to enable video recording */
            put("enableVideo", true);
        }});
        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://62.113.108.218:4444/wd/hub"), options);


                /* Jenkins не имеет графического интерфейса поэтому для тестирования web интерфейса необходимо
           подключить selenoid
         */
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;



    }

    @BeforeEach
    void beforeEachMethod() {
        open(baseUrl);
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }

}
