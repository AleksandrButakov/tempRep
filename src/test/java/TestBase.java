import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class TestBase {

    @BeforeAll
    static void beforeAll() throws MalformedURLException {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        baseUrl = "https://rshb.ru";
        Configuration.browserPosition = ("0x0");
        Configuration.browserSize = "1920x1080";

        //password and user for remote browser
        String user = System.getProperty("user");
        String password = System.getProperty("password");
        //Configuration.remote = "https://" + user + ":" + password + "@" + System.getProperty("remoteBrowser");
        Configuration.remote = System.getProperty("remote_driver_url", "http://62.113.108.218:4444/wd/hub/");

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
        // Attach.addVideo();
        closeWebDriver();



//        Attach.screenshotAs("Last screenshot");
//        Attach.pageSource();
//        Attach.browserConsoleLogs();
//        Attach.addVideo();
//        closeWebDriver();
    }

//    public static String getSessionId(){
//        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
//    }

}
